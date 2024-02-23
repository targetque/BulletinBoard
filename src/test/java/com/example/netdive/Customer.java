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

import static org.assertj.core.api.Assertions.assertThat;

public class Customer implements Runnable {

    private Table table;

    @Override
    public void run() {

    }

    class Table {
        String[] dishNames = {"donut","donut","burger"};


    }
}
