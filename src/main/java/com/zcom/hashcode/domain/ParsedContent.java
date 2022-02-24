package com.zcom.hashcode.domain;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParsedContent {

	private int numContributors;
	private int numProjects;
	private Set<Contributor> contributors;
	private Set<Project> projects;
}
