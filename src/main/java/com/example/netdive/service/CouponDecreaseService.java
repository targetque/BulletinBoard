package com.example.netdive.service;

import com.example.netdive.config.DistributedLock;
import com.example.netdive.dto.Coupon;
import com.example.netdive.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CouponDecreaseService {
    private final CouponRepository couponRepository;

    @Transactional
    public void couponDecrease(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(IllegalArgumentException::new);

        coupon.decrease();;
    }

    @DistributedLock(key = "#lockName")
    public void couponDecrease(String lockName, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }


}
