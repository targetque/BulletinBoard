package com.example.netdive.service;

import com.example.netdive.dto.Stock;
import com.example.netdive.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public synchronized void decrease(final Long id, final Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.save(stock);
    }
    public Stock getStock() {
        return stockRepository.findById(1L).orElseThrow();
    }

    @Transactional
    public void setQuantity(final Long id, final Long quantity) {
        Stock stock = new Stock(1L, 100L);

        stockRepository.save(stock);
    }

}
