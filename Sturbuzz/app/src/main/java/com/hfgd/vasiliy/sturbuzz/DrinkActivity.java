package com.hfgd.vasiliy.sturbuzz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
            SQLiteDatabase db = starbuzzDatabaseHalper.getWritableDatabase();
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
                boolean isFavorite = cursor.getInt(3) == 1;

                ImageView photo = (ImageView)this.findViewById(R.id.image);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                ((TextView)findViewById(R.id.name)).setText(nameText);
                ((TextView)findViewById(R.id.description)).setText(descriptionText);
                ((CheckBox)findViewById(R.id.favorite)).setChecked(isFavorite);
            }

            cursor.close();
            db.close();

        }catch (SQLiteException exception){
            Toast toast = Toast.makeText(this, "Database unavalible", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view){
        int drinkNO = getIntent().getExtras().getInt(EXTRA_DRINKNO);
        CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("favorite", favorite.isChecked());

        try{
            SQLiteOpenHelper starbuzzDatabaseHalper = new SturbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHalper.getWritableDatabase();
            db.update("drink", drinkValues, "_id = ?", new String[]{Integer.toString(drinkNO)});
            db.close();
        }catch (SQLiteException exception){
            Toast toast = Toast.makeText(this, "Database unavalible", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
