package com.zcom.hashcode.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.zcom.hashcode.domain.Contributor;
import com.zcom.hashcode.domain.ParsedContent;
import com.zcom.hashcode.domain.Project;
import com.zcom.hashcode.domain.Skill;


public class FileReader {

	public ParsedContent parseInputFile(File f) {
		try {
			final Set<Contributor> contributors = new HashSet<Contributor>();

			final Set<Project> projects = new HashSet<Project>();
			
			final Scanner sc = new Scanner(f);
			final int numContributors = sc.nextInt();
			final int numProjects = sc.nextInt();
		
			
			for(int c=0; c<numContributors; c++) {
				Contributor contributor = new Contributor();
				contributor.setName(sc.next());
				final int numSkills = sc.nextInt();
				contributor.setSkills(new HashSet<Skill>(numSkills));
				for(int s=0; s<numSkills; s++) {
					contributor.getSkills().add(new Skill(sc.next(), sc.nextInt()));
				}
				contributors.add(contributor);
			}
			
			for(int p=0; p<numProjects; p++) {
				Project project = new Project();
				project.setName(sc.next());
				project.setEffort(sc.nextInt());
				project.setScore(sc.nextInt());
				project.setDeadline(sc.nextInt());
				project.setNRoles(sc.nextInt());
				project.setRequiredSkills(new ArrayList<Skill>(project.getNRoles()));

				for(int r=0; r<project.getNRoles(); r++) {
					project.getRequiredSkills().add(new Skill(sc.next(), sc.nextInt()));
				}
				projects.add(project);
			}
			sc.close();
			return new ParsedContent(numContributors, numProjects, contributors, projects);
		} catch (FileNotFoundException e) {
			// ignore
			throw new RuntimeException(e);
		}
	}
	
}
