package com.example.alpha.fragment;

/**
 * Created by Alpha on 10/28/2016.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.util.ArrayList;
        import java.util.List;

public class ShakespeareDBHelper extends SQLiteOpenHelper {

    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "shakespeareDB";

    // Contacts table name

    private static final String TABLE_NAME = "books";
    // Shops Table Columns names

    private static final String KEY_ID ="id";

    private static final String KEY_TITLE ="title";

    private static final String KEY_DIALOGUE = "dialogue";

    private static final String KEY_IMAGE = "image";

    public ShakespeareDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // incremented database version from 2 to 3
    }

    @Override

    public void onCreate(SQLiteDatabase db) {



        //SQL statement to create database.

        String CREATE_SP_TABLE = "CREATE TABLE " + TABLE_NAME + "("

                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"

                + KEY_DIALOGUE + " TEXT," + KEY_IMAGE + " INTEGER" + ")";



        db.execSQL(CREATE_SP_TABLE);

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);



        // Creating tables again

        onCreate(db);

    }



    //function to add a book

    public void addBook(Shakespeare spObject) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, spObject.getTitle()); // Add the Title

        values.put(KEY_DIALOGUE, spObject.getDialogue()); // Add the Dialogue

        values.put(KEY_IMAGE, spObject.getImage()); // Add the Image

        db.insert(TABLE_NAME, null, values); // Insert the row

        db.close(); // Closing database connection

    }



    public void addBook(String titlePassed, String dialoguePassed, int imgPassed) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();



        values.put(KEY_TITLE, titlePassed);

        values.put(KEY_DIALOGUE, dialoguePassed); // Add the Dialogue

        values.put(KEY_IMAGE, imgPassed); // Add the Image



        db.insert(TABLE_NAME, null, values); // Insert the row

        db.close(); // Closing database connection

    }



    // Get one book from the database

    public Shakespeare getBook(int id) {

        SQLiteDatabase db = this.getReadableDatabase();



        //SQL statement to retrieve a book

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,

                        KEY_TITLE, KEY_DIALOGUE, KEY_IMAGE }, KEY_ID + "=?",

                new String[] { String.valueOf(id) }, null, null, null, null);



        if (cursor != null)

            cursor.moveToFirst();



        //Store the retrieved ID, title and dialogue to a Shakespeare object, named book

        Shakespeare contact = new Shakespeare(Integer.parseInt(cursor.getString(0)),

                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        // return book

        return contact;

    }



    // Getting All Shops

    public ArrayList<Shakespeare> getAllBooks() {

        ArrayList<Shakespeare> bookList = new ArrayList<Shakespeare>();

        // Select All Query

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);



        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {

            do {

                Shakespeare gotBook = new Shakespeare();

                gotBook.setID(Integer.parseInt(cursor.getString(0)));

                gotBook.setTitle(cursor.getString(1));

                gotBook.setDialogue(cursor.getString(2));

                gotBook.setImage(Integer.parseInt(cursor.getString(3)));

                // Adding contact to list

                bookList.add(gotBook);

            } while (cursor.moveToNext());

        }

        // return contact list

        return bookList;

    }

    public int updateBook (Shakespeare bookSample) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, bookSample.getTitle());

        values.put(KEY_DIALOGUE, bookSample.getDialogue()); // Add the Dialogue

        values.put(KEY_IMAGE, bookSample.getImage()); // Add the Imagelues.put

        return db.update(TABLE_NAME,values,KEY_ID + "=?", new String[]{String.valueOf(bookSample.getID())});
    }

    public void deleteBook (Shakespeare bookSample) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_ID + "=?",  new String[]{String.valueOf(bookSample.getID())});

        db.close();

    }


}