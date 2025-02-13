package org.example.Concurrency.WebScrapperMultiThreaded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> urls = new ArrayList<>();

        for(int i=0;i<100;i++){
            urls.add("http://example.com/page" + i);
        }

        ExecutorService es1 = Executors.newFixedThreadPool(10);
        ExecutorService es2 = Executors.newCachedThreadPool();

        long startTime = System.currentTimeMillis();
        executeWebScraping(urls, es1); // Fixed thread pool
        long endTime = System.currentTimeMillis();

        System.out.println("Fixed Thread pool time to scrap urls : " + (endTime-startTime));

        startTime = System.currentTimeMillis();
        executeWebScraping(urls, es2); // Cached thread pool
        endTime = System.currentTimeMillis();

        System.out.println("Cached Thread pool time to scrap urls : " + (endTime-startTime));

    }

    public static void executeWebScraping(List<String> urls, ExecutorService es) throws ExecutionException, InterruptedException {
        List<Future<Void>> futures = new ArrayList<>();

        for(String url : urls){
            futures.add(es.submit(new ScrapURL(url))); //
        }



        for(Future<Void> future: futures){
            future.get(); // wait
        }

        return;
    }

    public static void executeWebScrapingRunnable(List<String> urls, ExecutorService es) throws ExecutionException, InterruptedException {

        for(String url : urls){
            es.submit(new ScrapURL(url));
        }
        return;
    }
}
