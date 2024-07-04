package com.vignesh.healthcare.models;

import lombok.Data;

@Data
public class Patient {
    private Long id;
    private String name;
    private Long age;
    private String emailId;
}
