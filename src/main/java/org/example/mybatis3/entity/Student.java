package org.example.mybatis3.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private Long id; // wrapper -> null
    private String name;
}
