package com.hfgd.vasiliy.sturbuzz;

public class Drink{
    private final String name;
    private final String description;
    private final Integer imageResourseId;

    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte),
            new Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
    };

    private Drink(String name, String description, Integer imageResourseId){
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Integer getImageResourseId(){
        return this.imageResourseId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
