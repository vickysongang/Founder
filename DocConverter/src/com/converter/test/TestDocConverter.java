package com.converter.test;

import java.io.File;

import com.converter.docConverter.DocConverter;
import com.converter.pdfConverter.Html2PdfConverter;
import com.converter.pdfConverter.JComPDFConverter;
import com.converter.pdfConverter.JacobPDFConverter;
import com.converter.pdfConverter.OpenOfficePDFConverter;
import com.converter.pdfConverter.PDFConverter;
import com.converter.swfConverter.SWFConverter;
import com.converter.swfConverter.SWFToolsSWFConverter;

public class TestDocConverter {
	public static void main(String[]args){
		PDFConverter pdfConverter = new OpenOfficePDFConverter();
//		pdfConverter.convert2PDF("C:\\Users\\vicky\\Desktop\\Test\\3.pptx");
		//PDFConverter pdfConverter = new JacobPDFConverter();
//		System.out.println(System.getProperty("java.library.path"));
//		PDFConverter pdfConverter = new JacobPDFConverter();
		SWFConverter swfConverter = new SWFToolsSWFConverter();
		DocConverter converter = new DocConverter(pdfConverter,swfConverter);
		String fileName = "C:\\Users\\vicky\\Desktop\\Test\\《概率论》第2章§4连续型随机变量及其密度函数.ppt";
		
		String result = converter.convert(fileName);
		System.out.println(result);

//		swfConverter.convert2SWF(fileName,"C:\\Users\\vicky\\Desktop\\Test\\1.swf");
	}
}
