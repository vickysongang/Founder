package com.converter.test;

import com.handler.docHandler.DocHandler;
import com.handler.docHandler.PDFHandler;

public class TestHandler {
	public static void main(String[]args) throws Exception{
		DocHandler dh = new PDFHandler();
		dh.getThumbnail("D:\\1.pdf");
	}
}
