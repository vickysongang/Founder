package com.converter.swfConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Pattern;

import com.converter.utils.FileUtils;
import com.converter.utils.PdfUtils;

public class SWFToolsSWFConverter implements SWFConverter {
	public String convert2SWF(String inputFile, String swfFile) {
		Process pid;
		File pdfFile = new File(inputFile);
		File outFile = new File(swfFile);
		if (!inputFile.toLowerCase().endsWith(".pdf")) {
			return inputFile + "的文件格式非PDF!";
		}
		if (!pdfFile.exists()) {
			return inputFile + "不存在!";
		}
		if (outFile.exists()) {
			return swfFile + "已存在!";
		}
		// String command = getPdf2SwfPath() + " -o \"" + swfFile
		// + "\" -f -s flashversion=9  \"" + inputFile + "\"";
		String command = "/home/oracle/cms/bin/pdf2swf.sh " + swfFile + " "
				+ inputFile.replaceAll(" ", "@#^");
		System.out.println(command);
		try {
			StringBuffer cmdout = new StringBuffer();
			pid = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(pid
					.getErrorStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println("pdf转swf-->line：" + line);
				cmdout.append(line)
						.append(System.getProperty("line.separator"));
				if (line.toLowerCase().contains("error")) {
					break;
				}
			}
			System.out.println("cmdout:" + cmdout.toString());
			if ((cmdout != null && cmdout.toString().toLowerCase().contains(
					"error"))
					|| !(new File(swfFile).exists())) {
				command = "/home/oracle/cms/bin/bigpdf2swf.sh " + swfFile + " "
						+ inputFile.replaceAll(" ", "@#^");
				System.out.println(command);
				Process pid1 = Runtime.getRuntime().exec(command);
				BufferedReader br1 = new BufferedReader(new InputStreamReader(
						pid1.getErrorStream()));
				String line1 = null;
				StringBuffer cmdout1 = new StringBuffer();
				while ((line1 = br1.readLine()) != null) {
					cmdout1.append(line1).append(
							System.getProperty("line.separator"));
					if (line1.toLowerCase().contains("error")) {
						break;
					}
				}
				if (line1 != null) {
					System.out.println("转换失败，" + line1);
					if (line1.contains("Couldn't create a font")) {
						line1 = "PDF文件内字体异常，请将低精度PDF转换后再继续";
					}
					return "转换文档为swf文件失败，原因为：" + line1;
				}
			} else {
				System.out.println("成功转换为SWF文件！");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "转换文档为swf文件失败,原因为：" + e.getMessage();
		}
		return "SUCCESS";
	}

	public String convert2SWFWithPages(String inputFile, String swfFile) {
		Process pid;
		File pdfFile = new File(inputFile);
		File outFile = new File(swfFile);
		if (!inputFile.toLowerCase().endsWith(".pdf")) {
			return inputFile + "的文件格式非PDF!";
		}
		if (!pdfFile.exists()) {
			return inputFile + "不存在!";
		}
		if (outFile.exists()) {
			return swfFile + "已存在!";
		}
		int pages = PdfUtils.getPdfPageNumWithPercent(inputFile, 10);
		String pageRange = null;
		if (pages <= 1) {
			pageRange = "1";
		} else {
			pageRange = "1-" + pages;
		}
		String command = "/home/oracle/cms/bin/pdf2swf4Pages.sh " + swfFile
				+ " " + inputFile.replaceAll(" ", "@#^") + " " + pageRange;
		System.out.println(command);
		try {
			StringBuffer cmdout = new StringBuffer();
			pid = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(pid
					.getErrorStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				cmdout.append(line)
						.append(System.getProperty("line.separator"));
				if (line.toLowerCase().contains("error")) {
					break;
				}
			}
			System.out.println("cmdout:" + cmdout.toString());
			if ((cmdout != null && cmdout.toString().toLowerCase().contains(
					"error"))
					|| !(new File(swfFile).exists())) {
				command = "/home/oracle/cms/bin/bigpdf2swf4Pages.sh " + swfFile
						+ " " + inputFile.replaceAll(" ", "@#^") + " "
						+ pageRange;
				System.out.println(command);
				Process pid1 = Runtime.getRuntime().exec(command);
				BufferedReader br1 = new BufferedReader(new InputStreamReader(
						pid1.getErrorStream()));
				String line1 = null;
				StringBuffer cmdout1 = new StringBuffer();
				while ((line1 = br1.readLine()) != null) {
					cmdout1.append(line1).append(
							System.getProperty("line.separator"));
					if (line1.toLowerCase().contains("error")) {
						break;
					}
				}
				if (line1 != null) {
					System.out.println("转换失败，" + line1);
					if (line1.contains("Couldn't create a font")) {
						line1 = "PDF文件内字体异常，请将低精度PDF转换后再继续";
					}
					return "转换部分文档为swf失败，原因为：" + line1;
				}
			} else {
				System.out.println("部分成功转换为SWF文件！");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "转换部分文档为swf失败,原因为：" + e.getMessage();
		}
		return "SUCCESS";
	}

	public String convert2SWF(String inputFile) {
		String swfFile = FileUtils.getFilePrefix(inputFile) + ".swf";
		return convert2SWF(inputFile, swfFile);
	}

	public static String getPdf2SwfPath() {
		String osName = System.getProperty("os.name");
		Properties pro = System.getProperties();
		if (Pattern.matches("Linux.*", osName)) {
			return "/usr/local/pdf2swf/bin/pdf2swf";
		} else if (Pattern.matches("Windows.*", osName)) {
			return "D:\\Program Files\\SWFTools\\pdf2swf.exe";
		}
		return null;
	}
}
