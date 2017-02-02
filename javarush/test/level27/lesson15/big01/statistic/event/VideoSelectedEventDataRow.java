package com.javarush.test.level27.lesson15.big01.statistic.event;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class VideoSelectedEventDataRow implements EventDataRow{
    Date currentDate;
    List<Advertisement> optimalVideoSet;
    long amount;
    int totalDuration;

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }

    public long getAmount() {
        return amount;
    }
}
