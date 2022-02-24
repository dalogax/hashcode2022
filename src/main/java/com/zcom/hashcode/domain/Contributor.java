package com.zcom.hashcode.domain;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Contributor {
    private String name;
    private Set<Skill> skills;
    private int busyUntil;
}
