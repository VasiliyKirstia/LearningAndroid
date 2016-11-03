package com.hfgd.vasiliy.sturbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKNO = "EXTRA_DRINKNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Intent intent = this.getIntent();
        Integer drinkNO = 0;

        if(intent != null)
            drinkNO = intent.getIntExtra(EXTRA_DRINKNO, 0);

        Drink drink = Drink.drinks[drinkNO];

        ImageView photo = (ImageView)this.findViewById(R.id.image);
        photo.setImageResource(drink.getImageResourseId());
        photo.setContentDescription(drink.getName());

        ((TextView)findViewById(R.id.name)).setText(drink.getName());
        ((TextView)findViewById(R.id.description)).setText(drink.getDescription());
    }
}
