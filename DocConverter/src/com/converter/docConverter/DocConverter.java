package com.converter.docConverter;

import com.converter.pdfConverter.PDFConverter;
import com.converter.swfConverter.SWFConverter;
import com.converter.utils.FileUtils;

public class DocConverter {
	private PDFConverter pdfConverter;
	private SWFConverter swfConverter;

	public DocConverter(PDFConverter pdfConverter, SWFConverter swfConverter) {
		super();
		this.pdfConverter = pdfConverter;
		this.swfConverter = swfConverter;
	}

	public String convert(String inputFile, String swfFile) {
		String result = null;
		result = this.pdfConverter.convert2PDF(inputFile);
		if (!"SUCCESS".equals(result)) {
			return result;
		}
		String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
		result = this.swfConverter.convert2SWF(pdfFile, swfFile);
		return result;
	}

	public String convert(String inputFile) {
		String result = null;
		result = this.pdfConverter.convert2PDF(inputFile);
		if (!"SUCCESS".equals(result)) {
			return result;
		}
		String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
		result = this.swfConverter.convert2SWF(pdfFile);
		return result;
	}
}
