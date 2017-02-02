package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sas on 05.12.16.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTest = new Date();
        Assert.assertNotEquals(shortener, null);
        for (String string : strings) {
            Long id = shortener.getId(string);
            Assert.assertNotEquals(id, null);
            ids.add(id);
        }
        Date endTest = new Date();
        return endTest.getTime() - startTest.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTest = new Date();
        Assert.assertNotEquals(shortener, null);
        for (Long id : ids) {
            String string = shortener.getString(id);
            Assert.assertNotEquals(string, null);
            strings.add(string);
        }
        Date endTest = new Date();
        return endTest.getTime() - startTest.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        long time1;
        long time2;
        Set<Long> ids = new HashSet<>();
        Set<String> strings = new HashSet<>();

        time1 = getTimeForGettingIds(shortener1, origStrings, ids);
        ids.clear();
        time2 = getTimeForGettingIds(shortener2, origStrings, ids);
        Assert.assertTrue(time1 > time2);

        time1 = getTimeForGettingStrings(shortener1, ids, strings);
        strings.clear();
        time2 = getTimeForGettingStrings(shortener2, ids, strings);
        Assert.assertEquals(time1, time2, 5);
    }
}
