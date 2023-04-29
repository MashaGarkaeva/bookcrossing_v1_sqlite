package com.bookcross;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookScreen extends AppCompatActivity {

    EditText name_book;
    EditText auhtor_book;
    Button save;
    Button prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        name_book = findViewById(R.id.name_book);
        auhtor_book = findViewById(R.id.auhtor_book);
        save = findViewById(R.id.save);
        prof = findViewById(R.id.see_place_books);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddBookScreen.this);
                databaseHelper.addBook(name_book.getText().toString().trim(),
                        auhtor_book.getText().toString().trim());
                showAlert("Вы хотите отправить книгу?", name_book, auhtor_book);
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

    public void onClick (View v){
        startActivity(new Intent(AddBookScreen.this, RegistrScreen.class));
    }

    private void showAlert(String text, EditText name_book, EditText auhtor_book){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddBookScreen.this);
        builder.setMessage(name_book.getText().toString())
                .setCancelable(false)
                .setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(AddBookScreen.this);
                        databaseHelper.addBook(name_book.getText().toString().trim(),
                                auhtor_book.getText().toString().trim());
                        Toast.makeText(AddBookScreen.this, "Книга отправлена", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AddBookScreen.this, "Книга не отправлена", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
    }

}
