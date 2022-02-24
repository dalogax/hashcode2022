package com.zcom.hashcode.service;


import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

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
	}

	public void resolve() {

		System.out.println(parsedContent);
		
		output = new OutputContent();
		output.setProjects(new ArrayList<Project>());
	}

}
