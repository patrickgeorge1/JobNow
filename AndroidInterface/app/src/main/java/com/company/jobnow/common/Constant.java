package com.company.jobnow.common;

public final class Constant {

    public final class Numeric {
        public final static int LOGO_DELAY_MS = 1500;
        public final static int MIN_LENGTH_JOB_TITLE = 5;
        public final static int MAX_LENGTH_JOB_TITLE = 30;
        public final static int MAX_JOB_CATEGORIES = 5;

        public final static float MAX_PRICE_FILTER = 500f;
        public final static float DEFAULT_ZOOM = 15f;

        public final static int DEFAULT_DISTANCE = 100;
        public final static int DEFAULT_MIN_PRICE = 0;
        public final static int DEFAULT_MAX_PRICE = (int) MAX_PRICE_FILTER;
    }

    public final class RequestCode {
        public final static int MAP = 101;
        public static final int LOCATION_PERMISSION = 102;
        public final static int JOB_ADD = 103;
        public final static int JOB_PREVIEW = 103;
        public final static int FILTER_PREFERENCES = 104;
    }



    // PREFERENCES
    public final static String LOGIN_PREFERENCES = "UserPreferences";
    public final static String AUTH_TOKEN = "token";

    public final static String FILTER_PREFERENCES = "filterPreferences";

    public final static String SELECTED_CATEGORIES = "categorySet";
    public final static String PREFERED_DISTANCE = "distance";
    public final static String PREFERED_MIN_PRICE= "minPrice";
    public final static String PREFERED_MAX_PRICE = "maxPrice";


    public final static String SUCESS_STATUS = "status";
    public final static String BASE_URL = "https://app-jobnow.herokuapp.com/";


    public final class Tag {
        public final static String DIALOG_EDIT_JOB_CATEGORIES = "DIALOG_EDIT_JOB_CATEGORIES";
        public final static String DIALOG_CONFIRM_JOB_DATA = "DIALOG_CONFIRM_JOB_DATA";
    }

    public final class Key {
        public final static String LATITUDE = "latitude";
        public final static String LONGITUDE = "longitude";

        public final static String JOB_TITLE = "jobTitle";
        public final static String JOB_PRICE = "jobPrice";
        public final static String JOB_DESCRIPTION = "jobDescripition";
        public final static String JOB_DISTANCE = "jobDistance";
    }

}
