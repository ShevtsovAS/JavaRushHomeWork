package com.javarush.test.level25.lesson05.home01;

public class LoggingStateThread extends Thread {
    private Thread thread;
    private State state;

    public LoggingStateThread(Thread target) {
        setDaemon(true);
        this.thread = target;
        this.state = target.getState();
    }

    @Override
    public void run() {
        System.out.println(state);
        while (state != State.TERMINATED) {
            if (state != thread.getState()) {
                state = thread.getState();
                System.out.println(state);
            }
        }
    }
}