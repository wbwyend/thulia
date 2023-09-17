package com.wyer.server.controller;

import com.wyer.server.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/09/16 16:58
 **/
@RestController
public class webController {
    @RequestMapping
    public Result get() {
        return Result.success();
    }

}
