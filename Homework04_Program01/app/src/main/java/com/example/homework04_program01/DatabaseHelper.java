package com.example.homework04_program01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Handyman.db";
    private static final String TABLE_NAME_USERS = DatabaseInfo.getTableNameUsers();
    private static final String TABLE_NAME_JOBS  = DatabaseInfo.getTableNameJobs();
    private static final String TABLE_NAME_HANDYMAN_JOBS = DatabaseInfo.getTableNameHandymanJobs();
    private static final String TABLE_NAME_HANDYMAN_CALLS = DatabaseInfo.getTableNameHandymanCalls();

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,16);
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

        //Filling up with test values
        db.execSQL("insert into users values ('ldyk@email.com','password','Luke Dyk','124 Imaginary Road Basket, Michigan, 12345','(419)-213-8876','',false);");
        db.execSQL("insert into users values ('jdyk@email.com','password','Jake Dyk','128 Imaginary Road Basket, Michigan, 12345','(419)-331-3213','',false);");
        db.execSQL("insert into users values ('ndyk@email.com','password','Nicks Emporium Of Services','123 Imaginary Road Basket, Michigan, 12345','(419)-343-2176','Here at Nicks Emporium, we do all kinds of services! Such as: Services!',true);");
        db.execSQL("insert into users values ('jblk@email.com','password','Mason Carpentry','125 Imaginary Road Basket, Michigan, 12345','(419)-643-8876','We just do carpentry. Nothing interesting here.',true);");
        db.execSQL("insert into users values ('qwer@email.com','password','Plumbing Pizzera','126 Imaginary Road Basket, Michigan, 12345','(419)-643-8875','So much plumbing wowowow',true);");
        db.execSQL("insert into users values ('poiu@email.com','password','Sams HVAC','127 Imaginary Road Basket, Michigan, 12345','(419)-643-8874','HVAC FOR THE WIN!!! WOOOOH!!!!',true);");

        //Fill up with jobs (this code should remain unchanged
        fillWithJobs(db);

        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 1);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('qwer@email.com', 1);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 2);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 3);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('poiu@email.com', 3);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 4);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 5);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 6);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('jblk@email.com', 6);");
        db.execSQL("insert into handymanJobs (username, jobId) values ('ndyk@email.com', 7);");

        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk@email.com', 'ldyk@email.com', 1);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk@email.com', 'jdyk@email.com', 2);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk@email.com', 'ldyk@email.com', 5);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('ndyk@email.com', 'ldyk@email.com', 3);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('jblk@email.com', 'jdyk@email.com', 6);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('jblk@email.com', 'ldyk@email.com', 6);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('qwer@email.com', 'ldyk@email.com', 1);");
        db.execSQL("insert into handymanCalls (usernameHandyman, usernameCaller, jobId) values ('poiu@email.com', 'jdyk@email.com', 3);");
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
            isHandyman = cursor.getInt(cursor.getColumnIndex("isHandyman")) != 0;

            User user = new User(email,password,name,address,phoneNumber,description,isHandyman);

            db.close();

            return user;
        }

        db.close();

        return new User();
    }

    @SuppressLint("Range")
    public User getUser(String e, SQLiteDatabase db)
    {
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
            isHandyman = cursor.getInt(cursor.getColumnIndex("isHandyman")) != 0;

            User user = new User(email,password,name,address,phoneNumber,description,isHandyman);

            return user;
        }

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

        db.close();
    }

    public boolean checkIfEmailFree(User u)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + TABLE_NAME_USERS + ".email = '" + u.getEmail() + "';";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {
            db.close();
            return false;
        }
        db.close();
        return true;
    }

    //Returns every user from the database
    @SuppressLint("Range")
    public ArrayList<User> getAllUsers()
    {
        //Get a readable copy of the database
        SQLiteDatabase db = this.getReadableDatabase();

        String statement = "SELECT * FROM " + TABLE_NAME_USERS + ";";

        ArrayList<User> listOfUsers = new ArrayList<User>();

        String email;
        String password;
        String name;
        String address;
        String phoneNumber;
        String description;
        Boolean isHandyman;
        User u;

        Cursor cursor = db.rawQuery(statement, null);

        if (cursor.moveToFirst())
        {
            do {
                email = cursor.getString(cursor.getColumnIndex("email"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                name    = cursor.getString(cursor.getColumnIndex("name"));
                address  = cursor.getString(cursor.getColumnIndex("address"));
                phoneNumber    = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                description = cursor.getString(cursor.getColumnIndex("description"));
                isHandyman = cursor.getInt(cursor.getColumnIndex("isHandyman")) != 0;

                u = new User(email,password,name,address,phoneNumber,description,isHandyman);

                listOfUsers.add(u);
            } while (cursor.moveToNext());
        }

        db.close();

        return listOfUsers;
    }

    //Gets all handymen from the database that have the job provided in j
    @SuppressLint("Range")
    public ArrayList<User> getAllHandymen(String j)
    {
        //Get a readable copy of the database
        SQLiteDatabase db = this.getReadableDatabase();

        int jobId = getJobId(j, db);

        String statement = "SELECT " + TABLE_NAME_USERS + ".* FROM " + TABLE_NAME_USERS + " INNER JOIN " + TABLE_NAME_HANDYMAN_JOBS + " on " + TABLE_NAME_USERS + ".email = " + TABLE_NAME_HANDYMAN_JOBS + ".username WHERE " + TABLE_NAME_HANDYMAN_JOBS + ".jobId = '" + jobId + "';";

        ArrayList<User> listOfUsers = new ArrayList<User>();

        String email;
        String password;
        String name;
        String address;
        String phoneNumber;
        String description;
        Boolean isHandyman;
        User u;

        Cursor cursor = db.rawQuery(statement, null);

        if (cursor.moveToFirst())
        {
            do {
                email = cursor.getString(cursor.getColumnIndex("email"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                name    = cursor.getString(cursor.getColumnIndex("name"));
                address  = cursor.getString(cursor.getColumnIndex("address"));
                phoneNumber    = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                description = cursor.getString(cursor.getColumnIndex("description"));
                isHandyman = cursor.getInt(cursor.getColumnIndex("isHandyman")) != 0;

                u = new User(email,password,name,address,phoneNumber,description,isHandyman);

                listOfUsers.add(u);
            } while (cursor.moveToNext());
        }

        db.close();

        return listOfUsers;
    }

    @SuppressLint("Range")
    public int getJobId(String j, SQLiteDatabase db)
    {

        String statement = "SELECT * from " + TABLE_NAME_JOBS + " WHERE jobName = '" + j + "';";

        Cursor cursor = db.rawQuery(statement, null);

        if (cursor.moveToFirst())
        {
            int id = cursor.getInt(cursor.getColumnIndex("jobId"));

            return id;
        }

        return -999;
    }

    @SuppressLint("Range")
    public String getJob(int j, SQLiteDatabase db)
    {
        String statement = "SELECT * from " + TABLE_NAME_JOBS + " WHERE jobId = '" + j + "';";

        Cursor cursor = db.rawQuery(statement, null);

        String job;

        if (cursor.moveToFirst())
        {
            job = cursor.getString(cursor.getColumnIndex("jobName"));

            return job;
        }

        return "";
    }

    public void addCall(String handyman, String user, String job)
    {
        //Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //Get the jobId
        int jobId = getJobId(job, db);

        //Write the insertion statement
        String statement = "INSERT INTO " + TABLE_NAME_HANDYMAN_CALLS + " (usernameHandyman, usernameCaller, jobId) VALUES ('" + handyman + "','" + user + "','" + jobId + "');";

        db.execSQL(statement);

        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<String> getHandymanJobs(String h)
    {
        ArrayList<String> jobs = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        String statement = "SELECT * FROM " + TABLE_NAME_HANDYMAN_JOBS + " WHERE username = '" + h + "';";

        Cursor cursor = db.rawQuery(statement,null);

        if (cursor.moveToFirst())
        {
            do {
                jobs.add(getJob(cursor.getInt(cursor.getColumnIndex("jobId")), db));
            } while (cursor.moveToNext());
        }

        return jobs;
    }

    public void setHandymanJobs(String h, ArrayList<String> jobs)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Delete all jobs of a handyman
        String statement = "DELETE FROM " + TABLE_NAME_HANDYMAN_JOBS + " WHERE username = '" + h + "';";

        db.execSQL(statement);

        int jobId;

        //Add the new jobs into the handyman's list
        for (int i = 0; i < jobs.size(); i++)
        {
            jobId = getJobId(jobs.get(i), db);
            statement = "INSERT INTO " + TABLE_NAME_HANDYMAN_JOBS + " (username, jobId) VALUES ('" + h + "','" + jobId + "');";

            db.execSQL(statement);
        }

        db.close();
    }

    public void setHandymanDescription(String h, String d)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String statement = "UPDATE " + TABLE_NAME_USERS + " SET description = '" + d + "' WHERE email = '" + h + "';";

        db.execSQL(statement);

        db.close();
    }

    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String statement = "UPDATE " + TABLE_NAME_USERS + " SET password = '" + u.getPassword() + "', name = '" + u.getName() + "', address = '" + u.getAddress() + "', phoneNumber = '" + u.getPhoneNumber() + "' WHERE email = '" + u.getEmail() + "';";

        db.execSQL(statement);

        db.close();
    }

    public void deleteUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String statement = "DELETE FROM " + TABLE_NAME_USERS + " WHERE email = '" + u.getEmail() + "';";

        db.execSQL(statement);

        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<Appointment> getCalls(User u)
    {
        //Depending on whether the provided user is a handyman or a regular user will change how we do this
        //If the user is a handyman, we get all of their calls
        //If the user is not a handyman, we get all of the calls they've booked
        SQLiteDatabase db = this.getReadableDatabase();

        String statement;

        ArrayList<Appointment> listOfCalls = new ArrayList<Appointment>();

        User handyman;
        User caller;
        String job;
        String date;
        int id;

        Appointment curAppointment;

        if (u.isHandyman())
        {
            statement = "SELECT * FROM " + TABLE_NAME_HANDYMAN_CALLS + " WHERE usernameHandyman = '" + u.getEmail() + "' ORDER BY date;";

            Cursor cursor = db.rawQuery(statement, null);

            if (cursor.moveToFirst())
            {
                do {
                    Log.d("TAG", "getCalls: ");
                    handyman = u;
                    caller = getUser(cursor.getString(cursor.getColumnIndex("usernameCaller")), db);
                    job = getJob(cursor.getInt(cursor.getColumnIndex("jobId")), db);
                    date = cursor.getString(cursor.getColumnIndex("date"));
                    id = cursor.getInt(cursor.getColumnIndex("handymanCallId"));

                    curAppointment = new Appointment(handyman,caller,job,date,id);

                    listOfCalls.add(curAppointment);

                } while(cursor.moveToNext());
            }
        }
        else
        {
            statement = "SELECT * FROM " + TABLE_NAME_HANDYMAN_CALLS + " WHERE usernameCaller = '" + u.getEmail() + "' ORDER BY date;";

            Cursor cursor = db.rawQuery(statement,null);

            if (cursor.moveToFirst())
            {
                do {
                    handyman = getUser(cursor.getString(cursor.getColumnIndex("usernameHandyman")), db);
                    caller = u;
                    job = getJob(cursor.getInt(cursor.getColumnIndex("jobId")), db);
                    date = cursor.getString(cursor.getColumnIndex("date"));
                    id = cursor.getInt(cursor.getColumnIndex("handymanCallId"));

                    curAppointment = new Appointment(handyman,caller,job,date,id);

                    listOfCalls.add(curAppointment);
                } while(cursor.moveToNext());
            }
        }

        db.close();

        return listOfCalls;
    }

    public void deleteCall(Appointment a)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String statement = "DELETE FROM " + TABLE_NAME_HANDYMAN_CALLS + " WHERE handymanCallId = '" + a.getCallId() + "';";

        db.execSQL(statement);

        db.close();
    }
}
