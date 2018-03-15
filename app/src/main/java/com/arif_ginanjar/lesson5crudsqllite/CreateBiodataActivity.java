package com.arif_ginanjar.lesson5crudsqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateBiodataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button activity_create_biodata_simpanbutton, activity_create_biodata_backbutton;
    EditText activity_create_biodata_numberedittext;
    EditText activity_create_biodata_namaeditext;
    EditText activity_create_biodata_birthdayeditext;
    EditText activity_create_biodata_jkeditext;
    EditText activity_create_biodata_addresseditext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_biodata);

        dbHelper = new DataHelper(this);
        activity_create_biodata_numberedittext = (EditText)findViewById(R.id.activity_create_biodata_numberedittext);
        activity_create_biodata_namaeditext = (EditText)findViewById(R.id.activity_create_biodata_namaeditext);
        activity_create_biodata_birthdayeditext = (EditText)findViewById(R.id.activity_create_biodata_birthdayeditext);
        activity_create_biodata_jkeditext = (EditText)findViewById(R.id.activity_create_biodata_jkeditext);
        activity_create_biodata_addresseditext = (EditText)findViewById(R.id.activity_create_biodata_addresseditext);
        activity_create_biodata_simpanbutton = (Button)findViewById(R.id.activity_create_biodata_simpanbutton);
        activity_create_biodata_backbutton = (Button)findViewById(R.id.activity_create_biodata_backbutton);

        activity_create_biodata_simpanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO biodata(no, nama, birthday, jk, alamat) VALUES ('"+
                        activity_create_biodata_numberedittext.getText().toString()+"','"+
                        activity_create_biodata_namaeditext.getText().toString()+"','"+
                        activity_create_biodata_birthdayeditext.getText().toString()+"','"+
                        activity_create_biodata_jkeditext.getText().toString()+"','"+
                        activity_create_biodata_addresseditext.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),"Berhasil", Toast.LENGTH_LONG).show();

                MainActivity.activity_main.RefreshList();
                finish();
            }
        });

        activity_create_biodata_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
