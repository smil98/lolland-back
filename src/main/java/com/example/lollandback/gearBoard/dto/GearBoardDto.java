package com.example.lollandback.gearBoard.dto;

import com.example.lollandback.gearBoard.domain.GearFile;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GearBoardDto {
    private Integer gear_id;
    private String gear_title;
    private String gear_content;
    private String category;
    private LocalDateTime gear_inserted;
    private Integer gear_views;
    private Integer countFile;
    private Integer commentcount;
    private Integer countLike;
    private String member_id;
    private String member_introduce;
    private String member_name;
    private String member_email;
    private String file_name;
    private String file_url;
    private List<GearFile> files;
}
