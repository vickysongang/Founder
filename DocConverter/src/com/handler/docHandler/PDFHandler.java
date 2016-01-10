package com.handler.docHandler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.converter.utils.FileUtils;

public class PDFHandler implements DocHandler {

	public void getThumbnail(String inputFile, String picFile) {
		try {
			PDDocument doc = PDDocument.load(inputFile);
			int pageCount = doc.getNumberOfPages();
			System.out.println("pageCount:" + pageCount);
			List pages = doc.getDocumentCatalog().getAllPages();
			PDPage page = (PDPage) pages.get(0);
			BufferedImage image = page.convertToImage();
			Iterator iter = ImageIO.getImageWritersBySuffix("JPG");
			ImageWriter writer = (ImageWriter) iter.next();
			File outFile = new File(picFile);
			FileOutputStream out = new FileOutputStream(outFile);
			ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
			writer.setOutput(outImage);
			writer.write(new IIOImage(image, null, null));
			doc.close();
			System.out.println("获取缩略图结束");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getThumbnail(String inputFile) {
		String picFile = FileUtils.getFilePrefix(inputFile) + ".jpg";
		System.out.println("picFile:" + picFile);
		getThumbnail(inputFile, picFile);
	}
}
