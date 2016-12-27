package com.javarush.test.level19.lesson10.bonus03;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SAS on 12.05.2016.
 */
public class tagFinder {
    private String tagName;
    private File file;
    private String text;
    private Pattern pattern;

    public tagFinder(String tagName, File file) throws IOException {
        this.tagName = tagName;
        this.file = file;
        this.text = getTextFromFile();
        this.pattern = Pattern.compile("<" + tagName + ".*?>|</" + tagName + ">");
    }

    public tagFinder(String tagName, String text) {
        this.tagName = tagName;
        this.text = text;
    }

    private String getTextFromFile() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String textFromFile = "";
        String line;
        while ((line = fileReader.readLine()) != null) textFromFile += line;
        fileReader.close();
        return textFromFile;
    }

    void printTags() {
        ArrayList<Integer> open = new ArrayList<>();
        ArrayList<Integer> close = new ArrayList<>();
        Matcher tagMatcher = pattern.matcher(text);
        int count = 0;
        int tagBegin;
        int tagEnd;
        while (tagMatcher.find()) {
            if (tagMatcher.group().startsWith("<" + tagName)) {
                open.add(tagMatcher.start());
                count++;
            }
            else if (tagMatcher.group().startsWith("</")) {
                close.add(tagMatcher.end());
                if (count > 1) count--;
                else if (count == 1) {
                    for (int i = 0; i < open.size(); i++) {
                        tagBegin = open.get(i);
                        tagEnd = close.get(close.size()-i-1);
                        System.out.println(text.substring(tagBegin, tagEnd));
                    }
                    count = 0;
                    open.clear();
                    close.clear();
                }
            }
        }
    }

    void printTags2() {
        Matcher tag1 = pattern.matcher(text);
        while (tag1.find()) {
            if (tag1.group().startsWith("<" + tagName)) {
                int count = 0;
                Matcher tag2 = pattern.matcher(text.substring(tag1.end()));
                while (tag2.find()) {
                    if (tag2.group().startsWith("<" + tagName)) count++;
                    if (tag2.group().startsWith("</") && count > 0) count--;
                    if (count == 0) {
                        System.out.println(text.substring(tag1.start(), tag1.end()+tag2.end()));
                        break;
                    }
                }
            }
        }
    }

    void printTagsOld() { // С этим методом был пройден тест, но мне он показался слишком замороченным
        Pattern pBeginTag = Pattern.compile("<"+tagName+".*?>");
        Pattern pEndTag = Pattern.compile("</"+tagName+">");
        Matcher beginTag = pBeginTag.matcher(text);
        Matcher endTag = pEndTag.matcher(text);

        int beginCount;
        int endCount;
        String tag;
        while (beginTag.find()) {
            endCount = 0;
            while (endTag.find()) {
                beginCount = 0;
                endCount++;
                if (endTag.start() < beginTag.start()) {
                    endCount = 0;
                    continue;
                }
                tag = text.substring(beginTag.start(), endTag.end());
                Matcher beginTags = pBeginTag.matcher(tag);
                while (beginTags.find()) beginCount++;
                if (beginCount == endCount) {
                    System.out.println(tag);
                    break;
                }
            }
            endTag.reset();
        }
    }
}