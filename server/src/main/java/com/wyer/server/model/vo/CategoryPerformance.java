package com.wyer.server.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Name: CategoryPerformance
 * Writer: wyer
 **/
@Data
public class CategoryPerformance {
    private int cid;
    private String name;
    private BigDecimal total;
}
