package com.bookcross;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DBpath;
    public static String DBName = "bookcrossing.db";
    public static final int SCHEMA = 6;
    static final String TABLE_USER = "User";
    static final String COLUMN_ID = "id";
    static final String COLUMN_FIO = "FIO";
    static final String COLUMN_LOGIN = "Login";
    static final String COLUMN_PASSWORD = "Password";
    static final String COLUMN_ID_BOOK = "id_book";
    static final String TABLE_BOOK = "Book";
    static final String COLUMN_NAME = "Name";
    static final String COLUMN_AUHTOR = "Auhtor";
    private Context myContext;

    DatabaseHelper(Context context){
        super(context, DBName, null, SCHEMA);
        this.myContext = context;
        DBpath = context.getFilesDir().getPath() + DBName;

        this.getReadableDatabase();
    }


    @Override public void onCreate(SQLiteDatabase db){}
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_USER);

        try {
            createDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void createDB() throws IOException{
        File file = new File(DBpath);
        if(!file.exists()){
            try(InputStream myInput = myContext.getAssets().open(DBName);
                OutputStream myOutput = new FileOutputStream(DBpath)){
                byte[] buffer = new byte[1024];
                int length;
                while((length = myInput.read(buffer)) > 0){
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
            catch(IOException ex) {
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open() throws SQLException{
        return SQLiteDatabase.openDatabase(DBpath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public Boolean insertData(String login, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOGIN, login);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USER, null, contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkLogin(String login){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where login = ?", new String[]{login});

        if (cursor.getCount() > 0){
            return true;
        } else {return false;}
    }

    public Boolean checkPassword(String login, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where login = ? and password = ?", new String[]{login, password});

        if (cursor.getCount() > 0){
            return true;
        } else {return false;}
    }
    /*void addUser(String FIO, String Login, String Password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.COLUMN_FIO, FIO);
        cv.put(DatabaseHelper.COLUMN_LOGIN, Login);
        cv.put(DatabaseHelper.COLUMN_PASSWORD, Password);

        long result = sqLiteDatabase.insert(DatabaseHelper.TABLE_USER, null, cv);
        if(result == -1){
            Toast.makeText(myContext, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(myContext, "added successfuly", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
    }*/

    void addBook(String Name, String Auhtor){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, Name);
        contentValues.put(COLUMN_AUHTOR, Auhtor);


        long res = sql.insert(TABLE_BOOK, null, contentValues);
        if( res == -1){
            Toast.makeText(myContext, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(myContext, "added successfuly", Toast.LENGTH_SHORT).show();
        }
        sql.close();
    }

}
