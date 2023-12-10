package com.example.homework04_program01;

public class DatabaseInfo
{
    private static final String JOB_PLUMBING = "Plumbing";
    private static final String JOB_ELECTRICAL = "Electrical";
    private static final String JOB_HVAC = "HVAC";
    private static final String JOB_FLOORING = "Flooring";
    private static final String JOB_PAINT = "Paint";
    private static final String JOB_CARPENTRY = "Carpentry";
    private static final String JOB_OTHER = "Other";

    private static final String TABLE_NAME_USERS = "users";
    private static final String TABLE_NAME_JOBS  = "jobs";
    private static final String TABLE_NAME_HANDYMAN_JOBS = "handymanJobs";
    private static final String TABLE_NAME_HANDYMAN_CALLS = "handymanCalls";
    private static final String TABLE_NAME_HANDYMAN_REVIEWS = "handymanReviews";

    public static String getJobPlumbing()
    {
        return JOB_PLUMBING;
    }

    public static String getJobElectrical() {
        return JOB_ELECTRICAL;
    }

    public static String getJobHvac() {
        return JOB_HVAC;
    }

    public static String getJobFlooring() {
        return JOB_FLOORING;
    }

    public static String getJobPaint() {
        return JOB_PAINT;
    }

    public static String getJobCarpentry() {
        return JOB_CARPENTRY;
    }

    public static String getJobOther() {
        return JOB_OTHER;
    }

    public static String getTableNameUsers() {
        return TABLE_NAME_USERS;
    }

    public static String getTableNameJobs() {
        return TABLE_NAME_JOBS;
    }

    public static String getTableNameHandymanJobs() {
        return TABLE_NAME_HANDYMAN_JOBS;
    }

    public static String getTableNameHandymanCalls() {
        return TABLE_NAME_HANDYMAN_CALLS;
    }

    public static String getTableNameHandymanReviews() {
        return TABLE_NAME_HANDYMAN_REVIEWS;
    }
}
