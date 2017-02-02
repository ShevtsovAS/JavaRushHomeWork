package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("name", "Ivanov");
        stringMap.put("country", "Ukraine");
        stringMap.put("city", "Kiev");
        stringMap.put("age", null);
        System.out.println(getCondition(stringMap));
    }

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        Map.Entry<String, String> param;
        while (iterator.hasNext()) {
            param = iterator.next();
            if (param.getValue() != null) {
                result.append(String.format("%s = '%s'", param.getKey(), param.getValue()));
                if (iterator.hasNext()) result.append(" and ");
            }
        }
        if (result.toString().endsWith(" and ")) result.delete(result.length()-5, result.length());
        return result;
    }
}
