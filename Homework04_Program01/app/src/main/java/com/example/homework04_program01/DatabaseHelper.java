package com.example.homework04_program01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Handyman.db";
    private static final String TABLE_NAME_USERS = "users";
    private static final String TABLE_NAME_JOBS  = "jobs";
    private static final String TABLE_NAME_HANDYMAN_JOBS = "handymanJobs";
    private static final String TABLE_NAME_HANDYMAN_CALLS = "handymanCalls";
    private static final String TABLE_NAME_HANDYMAN_REVIEWS = "handymanReviews";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement;
        //Create the Users table
        statement = "create table if not exists " +
                TABLE_NAME_USERS + "( " +
                "email varchar(255) not null, " +
                "password varchar(255), " +
                "name varchar(255), " +
                "address varchar(255), " +
                "phoneNumber varchar(255), " +
                "description varchar(1000)," +
                "isHandyman boolean, " +
                "primary key (email)" +
                ");";
        db.execSQL(statement);

        //Create the Jobs table
        statement = "create table if not exists " +
                TABLE_NAME_JOBS + "(" +
                "  jobId integer primary key autoincrement," +
                "  jobName varchar(255)" +
                "  );";
        db.execSQL(statement);

        //Create the HandymanJobs table
        statement = "create table if not exists " +
                TABLE_NAME_HANDYMAN_JOBS + "(" +
                "  handymanJobId integer primary key autoincrement," +
                "  username varchar(255) not null," +
                "  jobId int not null,\n" +
                "  foreign key (username) references users (username)," +
                "  foreign key (jobId) references jobs (jobId)" +
                "  );";
        db.execSQL(statement);

        //Create the HandymanCalls table
        statement = "create table if not exists " +
                TABLE_NAME_HANDYMAN_CALLS + "(" +
                "  handymanCallId integer primary key autoincrement," +
                "  usernameHandyman varchar(255) not null," +
                "  usernameCaller varchar(255) not null," +
                "  jobId int not null," +
                "  date datetime default current_date," +
                "  foreign key (usernameHandyman) references users (username)," +
                "  foreign key (usernameCaller) references users (username)," +
                "  foreign key (jobId) references jobs (jobId)" +
                "  );";
        db.execSQL(statement);

        //Create the HandymanReviews table
        statement = "create table if not exists " +
                TABLE_NAME_HANDYMAN_REVIEWS + "(" +
                "  handymanReviewId integer primary key autoincrement," +
                "  usernameHandyman varchar(255) not null," +
                "  usernameCaller varchar(255) not null," +
                "  review varchar(1000)," +
                "  rating int," +
                "  foreign key (usernameHandyman) references users (username)," +
                "  foreign key (usernameCaller) references users (username)" +
                "  );";
        db.execSQL(statement);

        //Filling up with test values
        db.execSQL("insert into users values ('ndyk@email.com','password','Nick Dyk','123 Imaginary Road Basket, Michigan','(419)-343-2176','',true);");
        db.execSQL("insert into users values ('ldyk@email.com','p123','Luke Dyk','124 Imaginary Road Basket, Michigan','(419)-213-8876','',false);");

        //Fill up with jobs (this code should remain unchanged
        fillWithJobs(db);

        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 1);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 3);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 6);");

        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk@email.com', 'ldyk@email.com', 1);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk@email.com', 'ldyk@email.com', 3);");

        db.execSQL("insert into handymanReviews (usernameHandyman, usernameCaller, review, rating) values ('ndyk@email.com','ldyk@email.com','Bad job.',3);");
        db.execSQL("insert into handymanReviews (usernameHandyman, usernameCaller, review, rating) values ('ndyk@email.com','ldyk@email.com','good stuff!',5);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Delete the tables upon upgrading
        String statement;

        statement = "DROP TABLE IF EXISTS " + TABLE_NAME_USERS + ";";
        db.execSQL(statement);

        statement = "DROP TABLE IF EXISTS " + TABLE_NAME_JOBS + ";";
        db.execSQL(statement);

        statement = "DROP TABLE IF EXISTS " + TABLE_NAME_HANDYMAN_JOBS + ";";
        db.execSQL(statement);

        statement = "DROP TABLE IF EXISTS " + TABLE_NAME_HANDYMAN_CALLS + ";";
        db.execSQL(statement);

        statement = "DROP TABLE IF EXISTS " + TABLE_NAME_HANDYMAN_REVIEWS + ";";
        db.execSQL(statement);

        //Create a new table
        onCreate(db);
    }

    //Used to fill all preset jobs
    private void fillWithJobs(SQLiteDatabase db)
    {
        db.execSQL("insert into jobs (jobName) values ('Plumbing');");
        db.execSQL("insert into jobs (jobName) values ('Electrical');");
        db.execSQL("insert into jobs (jobName) values ('HVAC');");
        db.execSQL("insert into jobs (jobName) values ('Flooring');");
        db.execSQL("insert into jobs (jobName) values ('Paint');");
        db.execSQL("insert into jobs (jobName) values ('Carpentry');");
        db.execSQL("insert into jobs (jobName) values ('Other');");
    }

    @SuppressLint("Range")
    public User getUser(String e)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + TABLE_NAME_USERS + ".email = '" + e + "';";

        Cursor cursor = db.rawQuery(query, null);

        String email;
        String password;
        String name;
        String address;
        String phoneNumber;
        String description;
        Boolean isHandyman;

        if (cursor.moveToFirst())
        {
            email = cursor.getString(cursor.getColumnIndex("email"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            name    = cursor.getString(cursor.getColumnIndex("name"));
            address  = cursor.getString(cursor.getColumnIndex("address"));
            phoneNumber    = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            description = cursor.getString(cursor.getColumnIndex("description"));
            isHandyman = cursor.getColumnIndex("isHandyman") == 1;

            User user = new User(email,password,name,address,phoneNumber,description,isHandyman);

            db.close();

            return user;
        }

        db.close();

        return new User();
    }

    public void addUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String statement = "insert into users values(" +
                " '" + u.getEmail() + "'," +
                " '" + u.getPassword() + "'," +
                " '" + u.getName() + "'," +
                " '" + u.getAddress() + "'," +
                " '" + u.getPhoneNumber() + "'," +
                " '" + u.getDescription() + "'," +
                " " + u.isHandyman() +
                " );";
        db.execSQL(statement);
    }

    public boolean checkIfEmailFree(User u)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + TABLE_NAME_USERS + ".email = '" + u.getEmail() + "';";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {
            return false;
        }
        return true;
    }
}
