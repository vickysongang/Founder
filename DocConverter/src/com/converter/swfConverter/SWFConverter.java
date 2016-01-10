package com.converter.swfConverter;

public interface SWFConverter {
	public String convert2SWF(String inputFile, String swfFile);

	public String convert2SWF(String inputFile);
	
	public String convert2SWFWithPages(String inputFile, String swfFile);
}
