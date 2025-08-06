package com.delhivery.core.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = 2;

    AtomicInteger count = new AtomicInteger(MAX_RETRY_COUNT);

    public boolean isRetryAvailable() {
        return (count.intValue() > 0);
    }

    @Override
    public boolean retry(ITestResult result) {
        boolean retry = false;
        if (isRetryAvailable()) {
            retry = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.decrementAndGet();
        }
        return retry;
    }
}