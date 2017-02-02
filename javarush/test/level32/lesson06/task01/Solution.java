package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] pass = new byte[8];

        Random random = new Random();
        for (int i = 0; i < 8; i+=4) {
            byte s1 = (byte) (random.nextInt(25) + 97);
            byte s2 = (byte) (random.nextInt(25) + 65);
            byte s3 = (byte) (random.nextInt(25) + 97);
            byte s4 = (byte) (random.nextInt(9) + 48);
            pass[i] = s1;
            pass[i+1] = s2;
            pass[i+2] = s3;
            pass[i+3] = s4;
        }

        byteArrayOutputStream.write(pass);
        return byteArrayOutputStream;
    }
}
