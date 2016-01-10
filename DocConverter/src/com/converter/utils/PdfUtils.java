package com.converter.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfUtils {
	public static int getPdfPageNum(String pdfPath) {
		List allPages = new ArrayList();
		try {
			PDDocument pdd = PDDocument.load(pdfPath);
			allPages = pdd.getDocumentCatalog().getAllPages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allPages.size();
	}

	public static int getPdfPageNumWithPercent(String pdfPath, int percent) {
		int allPageNum = getPdfPageNum(pdfPath);
		Double d = allPageNum * percent / 100.0;
		System.out.println(d);
		if (d < 1) {
			return 1;
		}
		return d.intValue();
	}

	public static void main(String[] args) {
		String pdfPath = "C:\\Users\\vicky\\Desktop\\工作\\5625-17649-1-PB.pdf";
		System.out.println(PdfUtils.getPdfPageNumWithPercent(pdfPath,50));
	}
}
