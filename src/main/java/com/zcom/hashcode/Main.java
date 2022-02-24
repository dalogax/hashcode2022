package com.zcom.hashcode;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import com.zcom.hashcode.domain.ParsedContent;
import com.zcom.hashcode.files.FileReader;
import com.zcom.hashcode.files.HashCodeFileWriter;
import com.zcom.hashcode.service.SimulationExecutor;

public class Main {

	public static void main(String[] args) throws URISyntaxException {
		List<String> filesToProcess = Arrays.asList("a", "b", "c", "d", "e", "f");
		filesToProcess.forEach(Main::processFile);
	}
	
	private static void processFile(String name) {
		final String inputResourceName = "./src/main/resources/" + name + ".txt";
		final String outputFileName = "./out/" + name + ".out";
		final ParsedContent parsedContent = new FileReader()
				.parseInputFile(new File(inputResourceName));
		
		new SimulationExecutor(parsedContent).resolve();
		new HashCodeFileWriter().writeToOutputFile(parsedContent.getIntersections(), outputFileName);
	}
	
}
