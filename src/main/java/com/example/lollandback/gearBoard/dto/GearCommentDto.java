package com.example.lollandback.gearBoard.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GearCommentDto {
    private Integer id;
    private Integer boardid;
    private String comment;
    private LocalDateTime inserted;
    private String member_login_id;
    private String member_name;
}
