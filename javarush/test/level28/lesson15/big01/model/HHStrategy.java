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
 * Created by admin on 15.10.16.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
//    private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int page = 0;
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
        String url = String.format(URL_FORMAT, searchString, page);
        final String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
        final String referrer = "https://hh.ru/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";
        Document document = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(5000).get();
        return document;
    }
}