package com.example.netdive.repository;

import com.example.netdive.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    boolean existsByCode(String code);

    Long countByCode(String code);
}
