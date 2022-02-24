package com.zcom.hashcode.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import com.zcom.hashcode.domain.Contributor;
import com.zcom.hashcode.domain.OutputContent;
import com.zcom.hashcode.domain.Project;

public class HashCodeFileWriter {

	public void writeToOutputFile(OutputContent output, String filePath) {
		try(final FileWriter fw = new FileWriter(filePath)) {

			fw.write(String.valueOf(output.getProjects().size()));
			fw.write("\n");

			for (Project p : output.getProjects()) {
				fw.write(String.valueOf(p.getName()));
				fw.write("\n");
				fw.write(p.getAssignedContributors().stream().map(Contributor::getName).collect(Collectors.joining(" ")));
				fw.write("\n");
			}
			
		} catch (IOException e) {
			// ignore
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
