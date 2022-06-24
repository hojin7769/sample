package com.example.sample.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardVO {

    private int NO_SEQ;
    private String ID_USER;
    private String DS_TITLE;
    private String DS_CONTENT;
    private LocalDateTime DT_INSERT;
    private LocalDateTime DT_UPDATE;


}
