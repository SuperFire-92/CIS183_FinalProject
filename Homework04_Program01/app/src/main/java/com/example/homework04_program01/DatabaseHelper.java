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
        super(context,DATABASE_NAME,null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement;
        //Create the Users table
        statement = "create table if not exists " +
                TABLE_NAME_USERS + "( " +
                "username varchar(255) not null, " +
                "password varchar(255), " +
                "fName varchar(255), " +
                "lName varchar(255), " +
                "address varchar(255), " +
                "email varchar(255), " +
                "isHandyman boolean, " +
                "primary key (username)" +
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
                "  username varchar(255) not null," +
                "  review varchar(1000)," +
                "  rating int," +
                "  foreign key (username) references users (username)" +
                "  );";
        db.execSQL(statement);

        //Filling up with test values
        db.execSQL("insert into users values ('ndyk','password','Nick','Dyk','123 Imaginary Road Basket, Michigan','nickdyk@email.com',true);");
        db.execSQL("insert into users values ('ldyk','p123','Luke','Dyk','124 Imaginary Road Basket, Michigan','lukedyk@email.com',false);");

        //Fill up with jobs (this code should remain unchanged
        fillWithJobs(db);

        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk', 1);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk', 3);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk', 6);");

        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk', 'ldyk', 1);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk', 'ldyk', 3);");

        db.execSQL("insert into handymanReviews (username, review, rating) values ('ndyk','Bad job.',3);");
        db.execSQL("insert into handymanReviews (username, review, rating) values ('ndyk','good stuff!',5);");
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
    public User getUser(String u)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + TABLE_NAME_USERS + ".username = '" + u + "';";

        Cursor cursor = db.rawQuery(query, null);

        String username;
        String password;
        String fName;
        String lName;
        String address;
        String email;
        Boolean isHandyman;

        if (cursor.moveToFirst())
        {
            username = cursor.getString(cursor.getColumnIndex("username"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            fName    = cursor.getString(cursor.getColumnIndex("fName"));
            lName    = cursor.getString(cursor.getColumnIndex("lName"));
            address  = cursor.getString(cursor.getColumnIndex("address"));
            email    = cursor.getString(cursor.getColumnIndex("email"));
            isHandyman = cursor.getColumnIndex("isHandyman") == 1;

            User user = new User(username,password,fName,lName,address,email,isHandyman);

            db.close();

            return user;
        }

        db.close();

        return new User();
    }
}
