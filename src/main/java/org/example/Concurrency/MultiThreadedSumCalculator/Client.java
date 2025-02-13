package org.example.Concurrency.MultiThreadedSumCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int n = 1_000_000;
        List<Integer> arr = new ArrayList<>();

        for(int i=0;i<n;i++){
            arr.add(i%100);
        }

        int numThreads = Runtime.getRuntime().availableProcessors();

        int chunkSize = n/numThreads;

        ExecutorService es = Executors.newFixedThreadPool(numThreads);

        //Task -> task
        // Future<Long> taskFuture = es.submit(task); //Processing already starts
        // Long result = taskFuture.get();

        List<Future<Long>> sumFutures = new ArrayList<>();
        for(int i=0;i<numThreads; i++){
//            SumMultiThreadCallable tempTask =
//                    new SumMultiThreadCallable(arr, i * chunkSize, (i+1) * chunkSize);
//            Future<Long> tempFuture = es.submit(tempTask);
//            sumFutures.add(tempFuture);
            sumFutures.add(es.submit(new SumMultiThreadCallable(arr, i * chunkSize, (i+1) * chunkSize)));
        }

        Long sum = 0L;

        for(Future<Long> future : sumFutures){
            sum += future.get();
        }
        System.out.println("Sum of the array : " + sum);
    }
}
