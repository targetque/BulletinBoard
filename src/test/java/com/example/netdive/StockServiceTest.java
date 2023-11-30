package com.example.netdive;

import com.example.netdive.dto.Stock;
import com.example.netdive.repository.StockRepository;
import com.example.netdive.service.StockService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void beforeSetUp() {
        stockService.setQuantity(1L, 100L);
    }

/*@Test
    public void 동시에_100개의_요청() throws InterruptedException, ExecutionException {
        int threadCount = 100;
        //멀티스레드 이용 ExecutoreService : 비동기를 단순하게 처리할 수 있도록 해주는 java api
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                stockService.decrease(1L, 1L);
                return null;
            }
        };

        Future<Void> future = executorService.submit(callable);

        // 다른 스레드에서 수행이 완료될 때까지 대기할 수 있도록 오돠주는 API - 요청이 끝날 때까지 기다림

        Stock stock = future.get();

        assertThat(stock.getQuantity()).isEqualTo(0L);
    }*/


    @Test
    public void 동시에_100개의_요청() throws InterruptedException {


        int threadCount = 100;
        //멀티스레드 이용 ExecutoreService : 비동기를 단순하게 처리할 수 있도록 해주는 java api
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);

        for(int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
               try {
                   stockService.decrease(1L, 1L);
               } finally {
                   latch.countDown();
               }
            });
        }

        latch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                stockService.decrease(1L, 1L);
                return null;
            }
        };

        Future<Void> future = executorService.submit(callable);

        // 다른 스레드에서 수행이 완료될 때까지 대기할 수 있도록 오돠주는 API - 요청이 끝날 때까지 기다림

        //Stock stock = future.get();


        assertThat(stock.getQuantity()).isEqualTo(0L);
    }
}
