package br.com.clarobcle.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToFile {
	
	public ToFile() {
		String path = "C:\\SecegPagSeguro";
		File dir = new File(path);
		dir.mkdirs();
	}

	public String createFile(String url, String email) {

		String content = url;
		
		{

			// If the file doesn't exists, create and write to it
			// If the file exists, truncate (remove all content) and write to it
			try (FileWriter writer = new FileWriter("C:\\SecegPagSeguro\\"+email+".xml");
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(content);

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}

		}

		return content;

	}
}
