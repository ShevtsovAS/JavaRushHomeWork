package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdvertisementManager {
    private int timeSeconds;

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException{
        List<Advertisement> candidates = new ArrayList<>();
        for (Advertisement ad: storage.list()) {
            if (ad.getDuration() <= timeSeconds && ad.getHits() > 0)
                candidates.add(ad);
        }

        if (candidates.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        OptimalVideoSet optimalVideoSet = new OptimalVideoSet(candidates, timeSeconds);
        List<Advertisement> optimalVideoList = optimalVideoSet.getOptimalVideoSet();
        Collections.sort(optimalVideoList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) (o1.getAmountPerOneDisplaying() == o2.getAmountPerOneDisplaying()
                        ? (o1.getAmountPerOneDisplaying()*1000/o1.getDuration() - o2.getAmountPerOneDisplaying()*1000/o2.getDuration())
                        : o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            }
        });

        StatisticEventManager.getInstance().register(
                new VideoSelectedEventDataRow(
                        optimalVideoList,
                        optimalVideoSet.getOptimalVideoSetAmount(),
                        optimalVideoSet.getOptimalVideoSetDuration()
                )
        );

        for (int i = 0; i < optimalVideoList.size(); i++) {
            Advertisement showingAd = optimalVideoList.get(i);
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    showingAd.getName(),
                    showingAd.getAmountPerOneDisplaying(),
                    (int)(showingAd.getAmountPerOneDisplaying()*1000/showingAd.getDuration())
            ));
            showingAd.revalidate();

        }
    }

    private class OptimalVideoSet  {
        private int toShowTimeSeconds;
        private CopyOnWriteArrayList<Advertisement> candidates_ = new CopyOnWriteArrayList<>();

        private int optimalVideoSetAmount = 0;
        private int optimalVideoSetTime = 0;
        private CopyOnWriteArrayList<Advertisement> optimalVideoSet;


        public OptimalVideoSet(List<Advertisement> candidates, int toShowTimeSeconds) {
            this.toShowTimeSeconds = toShowTimeSeconds;
            this.candidates_.addAll(candidates);
        }

        public List<Advertisement> getOptimalVideoSet() {
            Sortout(candidates_);
            List<Advertisement> result = new ArrayList<>();
            result.addAll(optimalVideoSet);
            return result;
        }

        private void Sortout(CopyOnWriteArrayList<Advertisement> candidates) {
            CopyOnWriteArrayList<Advertisement> currentList = candidates;
            int amountOfAd = 0;
            int sumOfAdTime = 0;
            for (Advertisement ad : currentList) {
                amountOfAd += ad.getAmountPerOneDisplaying();
                sumOfAdTime += ad.getDuration();
            }
            if (sumOfAdTime > toShowTimeSeconds)
                for (int i = currentList.size() - 1; i >= 0; i--) {
                    candidates = (CopyOnWriteArrayList<Advertisement>) currentList.clone();
                    candidates.remove(i);
                    Sortout(candidates);
                }
            else {
                if (amountOfAd > optimalVideoSetAmount) {
                    optimalVideoSet = currentList;
                } else if (amountOfAd == optimalVideoSetAmount)
                    if (sumOfAdTime > optimalVideoSetTime)
                        optimalVideoSet = currentList;
                    else if (sumOfAdTime == optimalVideoSetTime)
                        if (currentList.size() < optimalVideoSet.size())
                            optimalVideoSet = currentList;
                if (optimalVideoSet == currentList) {
                    optimalVideoSetAmount = amountOfAd;
                    optimalVideoSetTime = sumOfAdTime;
                }
            }
        }

        public int getOptimalVideoSetDuration() {
            return optimalVideoSetTime;
        }

        public int getOptimalVideoSetAmount() {
            return optimalVideoSetAmount;
        }
    }
}