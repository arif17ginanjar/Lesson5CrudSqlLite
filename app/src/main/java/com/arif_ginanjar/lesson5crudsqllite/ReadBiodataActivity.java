package com.arif_ginanjar.lesson5crudsqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadBiodataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button activity_read_biodata_buttonback;
    TextView tvnumber,tvnama,tvbirthday,tvgender,tvaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_biodata);

        dbHelper = new DataHelper(this);
        tvnumber = (TextView)findViewById(R.id.activity_read_biodata_tv1);
        tvnama = (TextView)findViewById(R.id.activity_read_biodata_tv2);
        tvbirthday = (TextView)findViewById(R.id.activity_read_biodata_tv3);
        tvgender = (TextView)findViewById(R.id.activity_read_biodata_tv4);
        tvaddress = (TextView)findViewById(R.id.activity_read_biodata_tv5);
        activity_read_biodata_buttonback = (Button)findViewById(R.id.activity_read_biodata_buttonback);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama ='" +
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tvnumber.setText(cursor.getString(0));
            tvnama.setText(cursor.getString(1));
            tvbirthday.setText(cursor.getString(2));
            tvgender.setText(cursor.getString(3));
            tvaddress.setText(cursor.getString(4));
        }

        activity_read_biodata_buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}