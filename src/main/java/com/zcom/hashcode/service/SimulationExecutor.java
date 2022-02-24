package com.zcom.hashcode.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.sound.sampled.SourceDataLine;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.zcom.hashcode.domain.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimulationExecutor {

	OutputContent output;
	ParsedContent parsedContent;

	public SimulationExecutor(ParsedContent p) {
		super();
		this.parsedContent = p;
		this.output = new OutputContent();
	}

	public void resolve() {
		Comparator<Project> comparator = Comparator.comparing(Project::getDeadline,(d1, d2) -> { return d2.compareTo(d1);});
		Multimap<String, Contributor> skillIndex = indexSkills(parsedContent);

		output.setProjects(parsedContent.getProjects().stream()
			.sorted(comparator)
			.peek(p -> p.setAssignedContributors(parsedContent.getContributors()
				.stream()
				.limit(p.getNRoles())
				.map(Contributor::getName)
				.collect(Collectors.toList())))
			.collect(Collectors.toList()));
	}


	private Multimap<String,Contributor> indexSkills(ParsedContent content){
		Multimap<String, Contributor> index = ArrayListMultimap.create();

		for(Contributor c : content.getContributors()) {
			for (Skill s : c.getSkills()) {
				index.put(s.getName(), c);
			}
		}

		return index;
	}

}
