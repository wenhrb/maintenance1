package com.casciences.maintenance.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lijie
 * @date 2020-10-17 15:59
 */
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "";
    }
}
