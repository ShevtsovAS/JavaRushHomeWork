package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by sas on 29.12.16.
 */
public class Controller {
    private Model model = new Model();

    public List<String> onDataListShow() {
        return model.getStringDataList();
    }
}