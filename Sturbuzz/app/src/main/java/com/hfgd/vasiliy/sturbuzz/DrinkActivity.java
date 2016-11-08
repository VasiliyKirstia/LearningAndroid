package com.hfgd.vasiliy.sturbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKNO = "EXTRA_DRINKNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Intent intent = this.getIntent();
        int drinkNO = 0;

        if(intent != null)
            drinkNO = intent.getIntExtra(EXTRA_DRINKNO, 0);

        try{
            SQLiteOpenHelper starbuzzDatabaseHalper = new SturbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHalper.getReadableDatabase();
            Cursor cursor = db.query("drink",
                    new String[]{"name", "description", "image_resource_id"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNO)},
                    null, null, null
            );

            if (cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                ImageView photo = (ImageView)this.findViewById(R.id.image);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                ((TextView)findViewById(R.id.name)).setText(nameText);
                ((TextView)findViewById(R.id.description)).setText(descriptionText);
            }

            cursor.close();
            db.close();

        }catch (SQLiteException exception){
            Toast toast = Toast.makeText(this, "Database unavalible", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
