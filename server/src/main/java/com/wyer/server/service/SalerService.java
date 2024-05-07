package com.wyer.server.service;

import com.wyer.server.model.entity.Saler;

import java.util.List;

/**
 * Name: SalerService
 * Writer: wyer
 **/
public interface SalerService {
    Saler login(Saler saler);

    List<Saler> getBySid(Integer sid);

    List<Saler> batchDelete(List<Saler> salerList);

    List<Saler> delete(Saler saler);

    List<Saler> update(Saler saler);
}
