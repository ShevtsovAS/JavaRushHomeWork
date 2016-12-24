package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user = new User();
            user.setFirstName("Александр");
            user.setLastName("Шевцов");
            user.setMale(true);
            user.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").parse("15-06-1985"));
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            for (User currentUser : loadedObject.users) {
                System.out.println(currentUser.getFirstName());
                System.out.println(currentUser.getLastName());
                System.out.println(currentUser.isMale());
                System.out.println(new SimpleDateFormat("dd-MM-yyyy").format(currentUser.getBirthDate()));
                System.out.println(currentUser.getCountry());
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(users.size());
            for (int i = 0; i < users.size(); i++) {
                printWriter.println(users.get(i).getFirstName());
                printWriter.println(users.get(i).getLastName());
                printWriter.println(users.get(i).isMale());
                printWriter.println(dateFormat.format(users.get(i).getBirthDate()));
                printWriter.println(users.get(i).getCountry());
            }
            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int usersSize = Integer.parseInt(reader.readLine());
            for (int i = 0; i < usersSize; i++) {
                User user = new User();
                user.setFirstName(reader.readLine());
                user.setLastName(reader.readLine());
                user.setMale(Boolean.parseBoolean(reader.readLine()));
                user.setBirthDate(dateFormat.parse(reader.readLine()));
                user.setCountry(User.Country.valueOf(reader.readLine()));
                users.add(user);
            }
        }
    }
}
