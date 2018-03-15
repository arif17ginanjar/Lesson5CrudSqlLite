package com.arif_ginanjar.lesson5crudsqllite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] data_list;
    ListView activity_main_listview;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button activity_main_button = (Button)findViewById(R.id.activity_main_button);

        activity_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateBiodataActivity.class);

                startActivity(intent);
            }
        });

        activity_main = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase database = dbcenter.getReadableDatabase();
        cursor = database.rawQuery("Select * from biodata", null);
        data_list = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int n = 0; n < cursor.getCount(); n++){
            cursor.moveToPosition(n);
            data_list[n] = cursor.getString(1);
        }

        activity_main_listview = (ListView)findViewById(R.id.activity_main_listview);
        activity_main_listview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data_list));
        activity_main_listview.setSelected(true);
        activity_main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String sellection = data_list[position]; //.getItemAtPosition(position).toString();
                final CharSequence[] dialogitem = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), ReadBiodataActivity.class);
                                i.putExtra("nama", sellection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateBiodataActivity.class);
                                in.putExtra("nama", sellection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getReadableDatabase();
                                db.execSQL("delete from biodata where nama = '"+sellection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });

        ((ArrayAdapter)activity_main_listview.getAdapter()).notifyDataSetInvalidated();
    }
}