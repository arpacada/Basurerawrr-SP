package com.example.alpha.fragment;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by Alpha on 11/4/2016.
 */
public final class ShakespeareContract {
    private ShakespeareContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.alpha.fragment";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_SHAKESPEARE = "shakespeareDB";

    public static final class ShakespeareEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SHAKESPEARE).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_SHAKESPEARE;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_SHAKESPEARE;

        public static final String COLUMN_ID ="id";

        public static final String COLUMN_TITLE ="title";

        public static final String COLUMN_DIALOGUE = "dialogue";

        public static final String COLUMN_IMAGE = "image";

        public static final String COLUMN_GENRE = "genre";

        public static final String COLUMN_YEAR = "year";

        public static final String COLUMN_COPIES = "numofcopies";
        public static final String TABLE_NAME = "books";


        public static Uri buildGenreUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);}
    }
}
