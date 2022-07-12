package com.example.sample.model;

import lombok.Data;

@Data
public class MenuVO {
    //메뉴 번호
    private int MENU_NUM;
    //메뉴 이름
    private String MENU_NAME;
    //메뉴 아이콘
    private String MENU_ICON;
    //메뉴 순서
    private int MENU_SEQ;
    //메뉴 구분선
    private Boolean MENU_SEPARATOR;
    //메뉴 삭제 유무
    private String MENU_DEL_YN;
    //메뉴 경로
    private String MENU_PATH;
    //메뉴 파일 위치
    private String MENU_FILE_PATH;
    //PATH와 비교할 메뉴 이름
    private String MENU_COM_PATH;
}
