package org.example.Concurrency.WebScrapperMultiThreaded;

import java.util.concurrent.Callable;

public class ScrapURLRunnable implements Runnable {
    private String url;

    public ScrapURLRunnable(String url){
        this.url = url;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
