package org.example.Concurrency.BankAccountSynchronized;

import java.lang.module.FindException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        ExecutorService es = Executors.newFixedThreadPool(5);

        // Lambda
        Runnable depositTask = () -> {
            for(long i=0;i<100;i++){
                bankAccount.deposit(i);
            }
        };

        Runnable withdrawTask = () -> {
            for(long i=0;i<100;i++){
                bankAccount.withdraw(i);
            }
        };

        es.submit(depositTask);
        es.submit(withdrawTask);


        es.shutdown(); // It initiates the shutdown of tasks,
                        // basically it doesn't except any new task
                            //but waits for already assigned task to get over

        es.awaitTermination(2, TimeUnit.MINUTES); //Not waiting at this line
        System.out.println("Final balance : " + bankAccount.getBalance());

    }
}
