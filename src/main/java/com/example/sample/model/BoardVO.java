package com.example.sample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("BT_BOARD")
public class BoardVO {

    @Id
    private int NO_SEQ;
    private String ID_USER;
    private String DS_TITLE;
    private String DS_CONTENT;
    private LocalDateTime DT_INSERT;
    private LocalDateTime DT_UPDATE;
}
