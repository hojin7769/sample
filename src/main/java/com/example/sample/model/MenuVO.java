package com.example.sample.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("BT_MENU")
public class MenuVO {

    private String MENU_LABLE;
    private String MENU_ICON;
    private int MENU_SEQ;
    private String MENU_SEPARATOR;
    private int MENU_DEL_YN;

}
