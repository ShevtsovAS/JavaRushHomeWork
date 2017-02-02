package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
/*
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(new PersonScannerAdapter(new Scanner(new FileReader("myFile.txt"))).read());
    }
*/

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner;
        public PersonScannerAdapter(Scanner scanner) {
            this.scanner = scanner;
        }
        @Override
        public Person read() throws IOException, ParseException {
            String[] PersonData = null;
            Date bd = null;
            if (scanner.hasNextLine()) {
                PersonData = scanner.nextLine().split(" ");
                bd = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH).parse(String.format("%s %s %s", PersonData[3], PersonData[4], PersonData[5]));
                return new Person(PersonData[1], PersonData[2], PersonData[0], bd);
            }
            return null;
        }

        @Override
        public void close() throws IOException {
            scanner.close();
        }
    }
}
