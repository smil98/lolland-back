package com.example.lollandback.member.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    private Long id;
    private String member_login_id;
    private String member_name;
    private String member_phone_number;
    private String member_email;
    private String member_type;
    private LocalDateTime reg_time;
    private String member_introduce;

    private MemberAddressDto memberAddressDto;

    private MemberImageDto memberImageDto;
}
