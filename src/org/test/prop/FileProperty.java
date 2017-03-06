package org.test.prop;

import java.io.StringWriter;

/**
 * 
 */
public class FileProperty {
	
	private StringWriter writer;
	private String destinationDirectory;
	private String outputFile;
	private String outputFileExt=".java";
	private String templateName;
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public StringWriter getWriter() {
		return writer;
	}
	public void setWriter(StringWriter writer) {
		this.writer = writer;
	}
	public String getDestinationDirectory() {
		return destinationDirectory;
	}
	public void setDestinationDirectory(String destinationDirectory) {
		this.destinationDirectory = destinationDirectory;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	public String getOutputFileExt() {
		return outputFileExt;
	}
	public void setOutputFileExt(String outputFileExt) {
		this.outputFileExt = outputFileExt;
	}
	
   }
