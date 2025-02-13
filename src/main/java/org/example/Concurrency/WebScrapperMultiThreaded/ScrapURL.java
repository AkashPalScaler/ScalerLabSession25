package org.example.Concurrency.WebScrapperMultiThreaded;

import java.util.concurrent.Callable;

public class ScrapURL implements Callable<Void> {
    private String url;

    public ScrapURL(String url){
        this.url = url;
    }

    @Override
    public Void call() throws Exception {
//        System.out.println("Scraping website :"+ this.url);
        Thread.sleep(500);
        return null;
    }
}
