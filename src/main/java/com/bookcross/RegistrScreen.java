package com.bookcross;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrScreen extends AppCompatActivity {
    EditText reg_login;
    EditText reg_password;
    EditText reg_fio;
    Button enter2;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
        reg_login = findViewById(R.id.reg_login);
        reg_password = findViewById(R.id.reg_password);
        reg_fio = findViewById(R.id.reg_fio);
        enter2 = (Button) findViewById(R.id.enter2);


        databaseHelper = new DatabaseHelper(this);

    }

    /*@Override
    public void onClick(View v){
        String login = reg_login.getText().toString();
        String password = reg_password.getText().toString();
        String fio = reg_fio.getText().toString();

        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUMN_FIO, fio);
        contentValues.put(DatabaseHelper.COLUMN_LOGIN, login);
        contentValues.put(DatabaseHelper.COLUMN_PASSWORD, password);

        database.insert(DatabaseHelper.TABLE_USER, null, contentValues);


        databaseHelper.close();
    }*/

    public void onClick(View view) {
        String fio = reg_fio.getText().toString();
        String login = reg_login.getText().toString();
        String password = reg_password.getText().toString();

        if (fio.equals("") || login.equals("") || password.equals("")) {
            Toast.makeText(RegistrScreen.this, "Norm", Toast.LENGTH_SHORT).show();
            Boolean checkUserLogin = databaseHelper.checkLogin(login);

            if (checkUserLogin == false){
                Boolean insert = databaseHelper.insertData(login, password);

                if (insert == true){
                    Toast.makeText(RegistrScreen.this, "Registration proshla", Toast.LENGTH_SHORT).show();
                    enter2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(RegistrScreen.this, ProfilScreen.class));
                        }
                    });

                } else {
                    Toast.makeText(RegistrScreen.this, "Registration ne proshla", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegistrScreen.this, "user est", Toast.LENGTH_SHORT);
            }
        } else {
            Toast.makeText(RegistrScreen.this, "Invalid", Toast.LENGTH_SHORT);
        }
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
}
