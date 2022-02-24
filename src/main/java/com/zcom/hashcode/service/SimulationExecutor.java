package com.zcom.hashcode.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.sound.sampled.SourceDataLine;


import com.zcom.hashcode.domain.Contributor;
import com.zcom.hashcode.domain.OutputContent;
import com.zcom.hashcode.domain.ParsedContent;
import com.zcom.hashcode.domain.Project;

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



		output.setProjects(parsedContent.getProjects().stream()
			.sorted(comparator)
			.map(p -> {
				p.setAssignedContributors(parsedContent.getContributors()
					.stream()
					.limit(p.getNRoles())
					.map( c -> c.getName())
					.collect(Collectors.toList()));
				return p;
				})
			.collect(Collectors.toList()));

	}


	private Multimap<String,Contributor> indexSkills(ParsedContent content){
		Multimap<String,Contributor> index = new ArrayListMultimap<String,Contributor>.create();



	}

}
