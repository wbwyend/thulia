package com.wyer.server.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Name: SalerPerformance
 * Writer: wyer
 **/
@Data
public class SalerPerformance {
    private Integer id;
    private String name;
    private BigDecimal total;
}
