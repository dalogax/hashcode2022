package com.zcom.hashcode.domain;

import java.util.List;

import lombok.Data;

@Data
public class Project {
    private String name;
    private int deadline;
    private int effort;
    private int score;
    private int nRoles;
    private List<Skill> requiredSkills;

    private List<String> assignedContributors;
}
