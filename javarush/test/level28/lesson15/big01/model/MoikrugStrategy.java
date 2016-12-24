package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sas on 26.10.16.
 */
public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    @Override
    public List<Vacancy> getVacancies(String searchString) {

        String tmp = null;
        try {
            tmp = Jsoup.connect("https://moikrug.ru/vacancies?q=java+Dnepropetrovsk").get().html();
        } catch (IOException e) {}
        tmp = "";

        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 1;
            Document document;
            while (true) {
                document = getDocument(searchString, page++);
                if (document == null) break;

                Elements elements = document.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break;

                for (Element element : elements) {
                    // title
                    Element titleElement = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElement.text();

                    // salary
                    Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text();
                    }

                    // city
                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();

                    // company
                    String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();

                    // site
                    String siteName = "http://hh.ru/";

                    // url
                    String url = titleElement.attr("href");


                    // add vacancy to the list
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);
                }
            }
        } catch (Exception e) {}
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        // proxy settings
        /*??*/ //System.setProperty("http.proxyHost", "192.168.1.102");
        /*??*/ //System.setProperty("http.proxyPort", "808");
        String url = String.format(URL_FORMAT, page, searchString);
        final String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";
        final String referrer = "none";
        Document document = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(5000).get();
        return document;
    }
}
