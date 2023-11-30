package com.example.netdive.repository;

import com.example.netdive.dto.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockRepository extends JpaRepository<Stock, Long> {
}
