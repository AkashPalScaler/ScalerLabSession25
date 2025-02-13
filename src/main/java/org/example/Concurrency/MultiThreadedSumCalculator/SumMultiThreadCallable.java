package org.example.Concurrency.MultiThreadedSumCalculator;

import java.util.List;
import java.util.concurrent.Callable;

public class SumMultiThreadCallable implements Callable<Long> {
    List<Integer> arr;
    Integer start, end;

    public SumMultiThreadCallable(List<Integer> arr, Integer start, Integer end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() throws Exception {
        Long sum = 0L;
        for(int i=start; i < end;i++){
            sum += arr.get(i);
        }
        return sum;
    }
}
