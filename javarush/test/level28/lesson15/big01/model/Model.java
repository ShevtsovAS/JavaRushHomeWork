package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sas on 25.10.16.
 */
public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (providers == null || view == null || providers.length == 0) throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            for (Provider provider : providers) {
                vacancies.addAll(provider.getJavaVacancies(city));
            }
        } catch (NullPointerException e) {}
        view.update(vacancies);
    }
}
