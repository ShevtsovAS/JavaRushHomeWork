package com.javarush.test.level14.lesson06.home01;

/**
 * Created by SAS on 20.03.2016.
 */
public class MoldovanHen extends Hen {
    private final String myCountry = Country.MOLDOVA;

    @Override
    public int getCountOfEggsPerMonth() {
        return 800;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+" Моя страна - "+myCountry+". Я несу "+getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
