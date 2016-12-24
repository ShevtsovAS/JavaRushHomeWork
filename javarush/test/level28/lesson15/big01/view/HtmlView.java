package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by sas on 25.10.16.
 */
public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";
    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        String cityName = "Odessa";
        controller.onCitySelect(cityName);
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String fileContent;
        try {
            Document document = getDocument();
            Element element = document.select(".template").first();
            Element cloneElement = element.clone();

            cloneElement.removeAttr("style");
            cloneElement.removeClass("template");

            document.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancies) {
                Element newElement = cloneElement.clone();
                newElement.getElementsByClass("city").first().text(vacancy.getCity());
                newElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                newElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = newElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                element.before(newElement.outerHtml());
            }

            fileContent = document.html();
        } catch (IOException e) {
            e.printStackTrace();
            fileContent = "Some exception occurred";
        }
        return fileContent;
    }

    private void updateFile(String s) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
