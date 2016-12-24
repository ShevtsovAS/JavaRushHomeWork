package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sas on 05.12.16.
 */
public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String s1 = "String for test";
        String s2 = "String for test2";
        String s3 = "String for test";
        Long id1 = shortener.getId(s1);
        Long id2 = shortener.getId(s2);
        Long id3 = shortener.getId(s3);

        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        String s1FromId1 = shortener.getString(id1);
        String s2FromId2 = shortener.getString(id2);
        String s3FromId3 = shortener.getString(id3);

        Assert.assertEquals(s1, s1FromId1);
        Assert.assertEquals(s2, s2FromId2);
        Assert.assertEquals(s3, s3FromId3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        testStorage(new Shortener(new HashMapStorageStrategy()));
    }
    @Test
    public void testOurHashMapStorageStrategy() {
        testStorage(new Shortener(new OurHashMapStorageStrategy()));
    }
    @Test
    public void testFileStorageStrategy() {
        testStorage(new Shortener(new FileStorageStrategy()));
    }
    @Test
    public void testHashBiMapStorageStrategy() {
        testStorage(new Shortener(new HashBiMapStorageStrategy()));
    }
    @Test
    public void testDualHashBidiMapStorageStrategy() {
        testStorage(new Shortener(new DualHashBidiMapStorageStrategy()));
    }
    @Test
    public void testOurHashBiMapStorageStrategy() {
        testStorage(new Shortener(new OurHashBiMapStorageStrategy()));
    }
}
