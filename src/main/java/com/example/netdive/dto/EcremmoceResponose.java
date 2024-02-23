package com.example.netdive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
public class EcremmoceResponose {
    private int Page;
    private int PageSize;
    private int TotalCount;
    private Data Data;


}
