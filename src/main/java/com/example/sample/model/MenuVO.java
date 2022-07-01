package com.example.sample.model;

import lombok.Data;

@Data
public class MenuVO {

    private int MENU_NUM;
    private String MENU_LABEL;
    private String MENU_ICON;
    private int MENU_SEQ;
    private Boolean MENU_SEPARATOR;
    private String MENU_DEL_YN;

}
