package com.bookcross;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AvtorizScreen extends AppCompatActivity {

    Button enter;
    Button registr1;
    EditText login;
    EditText password;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avtoriz);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        enter = (Button) findViewById(R.id.enter1);

        sqlHelper = new DatabaseHelper(this);
        try {
            sqlHelper.createDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = sqlHelper.open();

        registr1 = findViewById(R.id.registr);
        registr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AvtorizScreen.this, RegistrScreen.class));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resetUI();
    }
    private void resetUI() {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onClick(View view) {
        System.out.println(login.toString());
        System.out.println(password.toString());
        String[] columns = {DatabaseHelper.COLUMN_LOGIN, DatabaseHelper.COLUMN_PASSWORD};
        userCursor = db.query(DatabaseHelper.TABLE_USER, columns, null, null, null, null, null);
        while (userCursor.moveToNext()){
            String log = userCursor.getString(0);
            String pass = userCursor.getString(1);
            if(log.equals(login.getText().toString()) & pass.equals(password.getText().toString())){
                System.out.println("success");
                Intent i = new Intent(getApplicationContext(), ProfilScreen.class);
                startActivity(i);
            }else{
                System.out.println("failed");
            }
        }
    }
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}
