package com.example.netdive;

import com.example.netdive.dto.Coupon;
import com.example.netdive.repository.CouponRepository;
import com.example.netdive.service.CouponDecreaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CouponDecreaseLockTest {

    private Coupon coupon;

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponDecreaseService couponDecreaseService;

    @BeforeEach
    public void setUp() {
        coupon = new Coupon("KURLY_001",100L);
        couponRepository.save(coupon);
    }


    /*
    * Feature: 쿠폰 차감 동시성 테스트
    * Background
    *   Given KURL_001라는 이름의 쿠폰 100장이 등록되어 있음
    * <p>
    * Scenario: 100장의 쿠폰을 100명의 사용자가 동시에 접근해 발급 요청함
    *           Lock의 이름은 쿠폰명으로 설정함
    * <p>
    * Then 사용자들의 요청만큼 정확히 쿠폰의 개수가 차감되어야함.
    *
    * */
    @Test
    public void 쿠폰차감_분산락_적용_동시성100명_테스트() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i =0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
               try {
                   //분산락 적용 메서드 호출(락의 key는 쿠폰의 name으로 설정)
                   couponDecreaseService.couponDecrease(coupon.getName(), coupon.getId());
               } finally {
                   latch.countDown();
               }
            });
        }

        latch.await();

        Coupon persistCoupon = couponRepository.findById(coupon.getId())
                .orElseThrow(IllegalAccessError::new);

        assertThat(persistCoupon.getAvailableStock()).isZero();

        System.out.println("잔여 쿠폰 개수 = " + persistCoupon.getAvailableStock());

    }

}
