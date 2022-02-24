package com.zcom.hashcode.service;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

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
	Multimap<String, Contributor> skillIndex;

	public SimulationExecutor(ParsedContent p) {
		super();
		this.parsedContent = p;
		this.output = new OutputContent();
		this.skillIndex = indexSkills(parsedContent);
	}

	public void resolve() {
		Comparator<Project> comparator = Comparator.comparing(Project::getDeadline,(d1, d2) -> { return d2.compareTo(d1);});

		output.setProjects(parsedContent.getProjects().stream()
			.sorted(comparator)
			.peek(p -> {
				List<String> cs = new ArrayList<>();

				for (Skill rs : p.getRequiredSkills()) {
					Optional<Contributor> c = findContributor(rs, p, cs);

					c.ifPresent(contributor -> cs.add(contributor.getName()));
				}

				p.setAssignedContributors(new ArrayList<>(cs));
			})
			.filter(p -> p.getRequiredSkills().size() == p.getAssignedContributors().size())
			.collect(Collectors.toList()));
	}

	private Optional<Contributor> findContributor(Skill skill, Project p, List<String> cs) {
		return skillIndex.get(skill.getName()).stream()
				.filter(c -> !cs.contains(c.getName()))
				.filter(c -> hasSkillContributor(c, skill))
				.sorted(Comparator.comparing(c -> getSkillLevel(c, skill)))
				.findFirst();
	}

	private int getSkillLevel(Contributor c, Skill k) {
		return c.getSkills().stream()
				.filter(kk -> kk.getName().equals(k.getName())).findFirst()
				.get().getLevel();
	}

	private boolean hasSkillContributor(Contributor c, Skill k) {
		return getSkillLevel(c, k) >= k.getLevel();
	}

//	private Contributor findContributor(Skill skill, Project p) {
//		int busyUntil = 0;
//		while(true) {
//
//			Optional<Contributor> r = cs.stream().filter(c -> c.getBusyUntil() >= busyUntil).findFirst();
//
//			if (r.isPresent())
//				return r.get();
//
//			busyUntil++;
//		}
//	}


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
