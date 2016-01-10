package com.converter.pdfConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.regex.Pattern;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import com.converter.utils.FileUtils;

public class OpenOfficePDFConverter implements PDFConverter {

	private static OfficeManager officeManager;
	private static int port[] = { 8100 };

	public String convert2PDF(String inputFile, String pdfFile) {
		if (inputFile.toLowerCase().endsWith(".txt")) {
			String odtFile = FileUtils.getFilePrefix(inputFile) + ".odt";
			if (new File(odtFile).exists()) {
				System.out.println("odt文件已存在！");
				inputFile = odtFile;
			} else {
				try {
					FileUtils.copyFile(inputFile, odtFile);
					inputFile = odtFile;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return "文档不存在!";
				}
			}
		}
		String result = startService();
		if (!"SUCCESS".equals(result)) {
			return result;
		}
		System.out.println("开始进行文档转换转换:" + inputFile + " --> " + pdfFile);
		OfficeDocumentConverter converter = new OfficeDocumentConverter(
				officeManager);
		String outputFilePath = getOutputFilePath(pdfFile);
		File inputF = new File(inputFile);
		if (inputF.exists()) {// 找不到源文件, 则返回
			File outputFile = new File(outputFilePath);
			if (!outputFile.getParentFile().exists()) { // 假如目标路径不存在, 则新建该路径
				outputFile.getParentFile().mkdirs();
			}
			converter.convert(inputF, outputFile);
		}
		// converter.convert(new File(inputFile),new File(pdfFile));
		stopService();
		return "SUCCESS";
	}

	public static String getOutputFilePath(String inputFilePath) {
		String format = FileUtils.getFileSufix(inputFilePath);
		String outputFilePath = inputFilePath.replaceAll(format, "pdf");
		return outputFilePath;
	}

	public String convert2PDF(String inputFile) {
		String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
		return convert2PDF(inputFile, pdfFile);
	}

	public static String startService() {
		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		try {
			configuration.setOfficeHome(getOfficeHome());// 设置OpenOffice.org安装目录
			configuration.setPortNumbers(port); // 设置转换端口，默认为8100
			configuration.setTaskExecutionTimeout(1000 * 60 * 5L);// 设置任务执行超时为5分钟
			configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// 设置任务队列超时为24小时

			officeManager = configuration.buildOfficeManager();
			officeManager.start(); // 启动服务
			System.out.println("office转换服务启动成功!");
			return "SUCCESS";
		} catch (Exception ce) {
			ce.printStackTrace();
			stopService();
			return "office转换服务启动失败!详细信息:" + ce;
		}
	}

	public static void stopService() {
		if (officeManager != null) {
			officeManager.stop();
		}
	}

	public static String getOfficeHome() {
		String osName = System.getProperty("os.name");
		Properties pro = System.getProperties();
		if (Pattern.matches("Linux.*", osName)) {
			return "/opt/openoffice4";
		} else if (Pattern.matches("Windows.*", osName)) {
			return "C:\\Program Files (x86)\\OpenOffice 4";
		} else if (Pattern.matches("Mac.*", osName)) {
			return "/Application/OpenOffice.org.app/Contents";
		}
		return null;
	}
}
