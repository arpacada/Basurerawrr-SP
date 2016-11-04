package com.example.alpha.fragment;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.example.alpha.fragment.ShakespeareContract.ShakespeareEntry;

/**
 * Created by Alpha on 11/4/2016.
 */
public class ShakespeareProvider extends ContentProvider{
    private ShakespeareDBHelper dbHelper;

    private static final String DBNAME = "shakespeareDB";
    private SQLiteDatabase db;

    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //integer code for a shakespeare table with access to multiple rows is 1.
         sUriMatcher.addURI(ShakespeareContract.CONTENT_AUTHORITY, ShakespeareContract.PATH_SHAKESPEARE, 1);

        //integer code for a single row is 2.
        sUriMatcher.addURI(ShakespeareContract.CONTENT_AUTHORITY, ShakespeareContract.PATH_SHAKESPEARE, 2);
         }
    // Implements ContentProvider.query()
    public Cursor query(
             Uri uri,
             String[] projection,
             String selection,
             String[] selectionArgs,
             String sortOrder) {

         /*
         * Choose the table to query and a sort order based on the code returned for the incoming
         * URI. Here, too, only the statements for table 3 are shown.
         */
        db = dbHelper.getReadableDatabase();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {


            // If the incoming URI was for table
            case 1:
                cursor = db.query(ShakespeareEntry.TABLE_NAME,projection, selection,selectionArgs, null, null, sortOrder);
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                 break;

            // If the incoming URI was for a single row
             case 2:


                 selection = selection + "_ID = " + uri.getLastPathSegment();

                 cursor = db.query(ShakespeareEntry.TABLE_NAME, projection, selection, selectionArgs,null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI"+ uri);
                 }
    cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
       }
    public boolean onCreate(){
        dbHelper = new ShakespeareDBHelper(getContext(), DBNAME, 1);
        return true;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (sUriMatcher.match(uri)){
            case 1:
                return insertBook(uri,values);
            default:
                throw new IllegalArgumentException("Not for URI code 2");
        }

    }
    private Uri insertBook(Uri uri, ContentValues values) {
        String title = values.getAsString(ShakespeareEntry.COLUMN_TITLE);
        String dialogue = values.getAsString(ShakespeareEntry.COLUMN_DIALOGUE);
        Integer numofcopies = values.getAsInteger(ShakespeareEntry.COLUMN_COPIES);
        Integer year = values.getAsInteger(ShakespeareEntry.COLUMN_YEAR);
        String genre  = values.getAsString(ShakespeareEntry.COLUMN_GENRE);

        db = dbHelper.getWritableDatabase();

        long id = db.insert(ShakespeareEntry.TABLE_NAME, null, values);
        if(id == -1) {

            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);

    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)){
            case 1:
                return updateBook(uri,values, selection, selectionArgs);
            case 2:
                selection = ShakespeareEntry.COLUMN_ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri)) };
                return updateBook(uri,values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update not supported.");
        }
    }
    private int updateBook(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if(values.containsKey(ShakespeareEntry.COLUMN_TITLE)) {
            String title = values.getAsString(ShakespeareEntry.COLUMN_TITLE);
        }
        if(values.containsKey(ShakespeareEntry.COLUMN_DIALOGUE)) {
            String dialogue = values.getAsString(ShakespeareEntry.COLUMN_DIALOGUE);
        }
        if(values.containsKey(ShakespeareEntry.COLUMN_YEAR)) {
            Integer year = values.getAsInteger(ShakespeareEntry.COLUMN_YEAR);
        }
        if(values.containsKey(ShakespeareEntry.COLUMN_GENRE)) {
            String genre = values.getAsString(ShakespeareEntry.COLUMN_GENRE);
        }
        if(values.containsKey(ShakespeareEntry.COLUMN_COPIES)) {
            Integer numofcopies = values.getAsInteger(ShakespeareEntry.COLUMN_COPIES);
        }

        if(values.size() == 0){
            return 0;
        }
        db = dbHelper.getWritableDatabase();
        int rowsUpdated = db.update(ShakespeareEntry.TABLE_NAME, values, selection, selectionArgs);
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;

    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        switch (sUriMatcher.match(uri)) {
            case 1:

                rowsDeleted = database.delete(ShakespeareEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case 2:

                selection = ShakespeareEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(ShakespeareEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }


        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case 1:
                return ShakespeareEntry.CONTENT_TYPE;
            case 2:
                return ShakespeareEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }


}
