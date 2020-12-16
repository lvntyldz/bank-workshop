package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemUserDTO {

    private Long id;

    private String username;

    private String password;

    private String fullname;

    private String email;

    private String phone;

    private boolean isActive;

    private Date createdDate;

}
