package com.arif_ginanjar.lesson5crudsqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBiodataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button activity_update_biodata_btsave, activity_update_biodata_btback;
    EditText activity_update_biodata_etnumber;
    EditText activity_update_biodata_etname;
    EditText activity_update_biodata_etbirthday;
    EditText activity_update_biodata_etgender;
    EditText activity_update_biodata_etaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);

        dbHelper = new DataHelper(this);
        activity_update_biodata_etnumber = (EditText)findViewById(R.id.activity_update_biodata_etnumber);
        activity_update_biodata_etname = (EditText)findViewById(R.id.activity_update_biodata_etname);
        activity_update_biodata_etbirthday = (EditText)findViewById(R.id.activity_update_biodata_etbirthday);
        activity_update_biodata_etgender = (EditText)findViewById(R.id.activity_update_biodata_etgender);
        activity_update_biodata_etaddress = (EditText)findViewById(R.id.activity_update_biodata_etaddress);
        activity_update_biodata_btsave = (Button)findViewById(R.id.activity_update_biodata_btsave);
        activity_update_biodata_btback = (Button)findViewById(R.id.activity_update_biodata_btback);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            activity_update_biodata_etnumber.setText(cursor.getString(0));
            activity_update_biodata_etname.setText(cursor.getString(1));
            activity_update_biodata_etbirthday.setText(cursor.getString(2));
            activity_update_biodata_etgender.setText(cursor.getString(3));
            activity_update_biodata_etaddress.setText(cursor.getString(4));
        }

        activity_update_biodata_btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE biodata set nama='" +
                        activity_update_biodata_etname.getText().toString()+"', birthday='" +
                        activity_update_biodata_etbirthday.getText().toString()+"',jk='" +
                        activity_update_biodata_etgender.getText().toString()+"',alamat='" +
                        activity_update_biodata_etaddress.getText().toString()+"' WHERE no='" +
                        activity_update_biodata_etnumber.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "BERHASIL", Toast.LENGTH_LONG).show();

                MainActivity.activity_main.RefreshList();
                finish();
            }
        });

        activity_update_biodata_btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
