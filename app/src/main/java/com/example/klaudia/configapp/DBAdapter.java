package com.example.klaudia.configapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Klaudia on 2015-11-16.
 */

class DBAdapter {
    private static final String DB_PATH = "/data/data/com.example.klaudia.configapp/databases/";
    private static final String DB_NAME = "db.s3db";
    private static final int DATABASE_VERSION = 1;

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DATABASE_VERSION);
            try {
                createDB();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void createDB() throws IOException
        {
            boolean dbExist = checkDataBase();

            if(dbExist)
            {
                //do nothing - database already exist
            }else{

                this.getWritableDatabase();

                try {

                    copyDataBase();

                } catch (IOException e) {

                    throw new Error("Error copying database");

                }
            }

        }

        private boolean checkDataBase()
        {
            SQLiteDatabase checkDB = null;

            try
            {
                String myPath = DB_PATH + DB_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

            }catch(SQLiteException e)
            {
                //database does't exist yet.
            }
            if(checkDB != null)
            {
                checkDB.close();
            }

            return checkDB != null ? true : false;
        }
        private void copyDataBase() throws IOException
        {
            //Open your local db as the input stream
            InputStream myInput = context.getAssets().open(DB_NAME);

            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0)
            {
                myOutput.write(buffer, 0, length);
            }
            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }


        @Override
        public void onCreate(SQLiteDatabase db){
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void openDB() {
        db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void close() {
        DBHelper.close();
    }

    public List<Category> getCategories() {
        List<Category> categories = new LinkedList<Category>();
        String columns[] = {"Nazwa", "Typ", "Audio1", "Audio2"};
        Cursor cursor = db.query("KATEGORIA", columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Category cat = new Category();
            cat.setName(cursor.getString(0));
            cat.setType(cursor.getString(1));
            cat.setAudio1(cursor.getString(2));
            cat.setAudio2(cursor.getString(3));
            categories.add(cat);
        }
        return categories;
    }

    public void addCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put("nazwa", category.getName());
        values.put("typ", category.getType());
        values.put("audio1", category.getAudio1());
        values.put("audio2", category.getAudio2());
        db.insertOrThrow("KATEGORIA", null, values);
    }

    public void addNode(Node node) {
        ContentValues values = new ContentValues();
        values.put("id", node.getId());
        values.put("zbior", node.getSet());
        values.put("kategoria", node.getCategory());
        values.put("grafika", node.getImage());
        db.insertOrThrow("OBRAZ", null, values);
    }

    public List<Node> getNodesFromCategory(String category) {
        List<Node> nodes = new LinkedList<Node>();
        Cursor cursor = db.rawQuery("select id, zbior, kategoria, grafika from OBRAZ where kategoria='" + category + "'", null);
        while (cursor.moveToNext()) {
            Node node = new Node();
            node.setId(cursor.getInt(0));
            node.setSet(cursor.getString(1));
            node.setCategory(cursor.getString(2));
            node.setImage(cursor.getString(3));
            nodes.add(node);
        }
        return nodes;
    }

    public List<Category> getCategoriesFromType(String type) {
        List<Category> categories = new LinkedList<Category>();
        Cursor cursor = db.rawQuery("select nazwa, typ, audio1, audio2 from KATEGORIA where typ='" + type + "'", null);
        while (cursor.moveToNext()) {
            Category cat = new Category();
            cat.setName(cursor.getString(0));
            cat.setType(cursor.getString(1));
            cat.setAudio1(cursor.getString(2));
            cat.setAudio2(cursor.getString(3));
            categories.add(cat);
        }
        return categories;
    }
}
