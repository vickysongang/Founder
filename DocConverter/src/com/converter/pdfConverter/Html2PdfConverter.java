package com.converter.pdfConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.converter.utils.FileUtils;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class Html2PdfConverter implements PDFConverter {

	public String convert2PDF(String inputFile, String pdfFile) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(pdfFile);
			ITextRenderer renderer = new ITextRenderer();
			String url = new File(inputFile).toURI().toURL().toString();
			renderer.setDocument(url);
			// 解决中文支持问题
			ITextFontResolver fontResolver = renderer.getFontResolver();
			fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			// // 解决图片的相对路径问题
			// renderer.getSharedContext().setBaseURL("file:/D:/");
			renderer.layout();
			renderer.createPDF(os);
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "FAIL";
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "FAIL";
		} catch (DocumentException e) {
			e.printStackTrace();
			return "FAIL";
		} catch (IOException e) {
			e.printStackTrace();
			return "FAIL";
		}
		return "SUCCESS";
	}

	public String convert2PDF(String inputFile) {
		String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
		return convert2PDF(inputFile, pdfFile);
	}
}
