package com.jzqh.account;

import lombok.Data;

import java.util.Map;

@Data
public class MenuBo {
    private String path;
    private String component;
    private String redirect;
    private MenuBo children;
    private Map<String, String> meta;
}
