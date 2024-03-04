package com.example.netdive;

import com.example.netdive.repository.PurchaseRepository;
import com.example.netdive.service.PurchaseRegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PurchaseRegisterLockTest {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseRegisterService purchaseRegisterService;
    @Test
    void 발주등록_분산락_적용_테스트() throws InterruptedException {
        String 발주_코드 = "KURLY_001";

        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 분산락 적용 메서드 호출
                    purchaseRegisterService.register(발주_코드,발주_코드);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long totalCount = purchaseRepository.countByCode(발주_코드);

        System.out.println("등록된 발주 = " + totalCount);
        assertThat(totalCount).isOne();
    }

    @Test
    void 발주등록_분산락_미적용_테스트() throws InterruptedException {
        String 발주_코드 = "KURLY_001";

        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 분산락 미적용 메서드 호출
                    purchaseRegisterService.register(발주_코드);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long totalCount = purchaseRepository.countByCode(발주_코드);

        System.out.println("등록된 발주 = " + totalCount);
        assertThat(totalCount).isOne();
    }

}
