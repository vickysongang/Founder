package com.converter.pdfConverter;

import com.converter.utils.FileUtils;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
import jp.ne.so_net.ga2.no_ji.jcom.JComException;
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;

public class JComPDFConverter implements PDFConverter {
	private static final int wdFormatPDF = 17;

	public String convert2PDF(String inputFile, String pdfFile) {
		ReleaseManager rm = new ReleaseManager();
		IDispatch app;
		try {
			app = new IDispatch(rm, "PDFMakerAPI.PDFMakerApp");
			app.method("CreatePDF", new Object[] { inputFile, pdfFile });
			System.out.println("conversion completed!");
		} catch (JComException e) {
			e.printStackTrace();
			return "FAIL";
		} finally {
			app = null;
			rm.release();
			rm = null;
		}
		return "SUCCESS";
	}

	public String convert2PDF(String inputFile) {
		String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
		// convert2PDF(inputFile,pdfFile);
		word2PDF(inputFile, pdfFile);
		return null;
	}

	public static void word2PDF(String inputFile, String pdfFile) {
		ReleaseManager rm = null;
		IDispatch app = null;
		try {
			rm = new ReleaseManager();
			app = new IDispatch(rm, "Word.Application");// 启动word
			app.put("Visible", false); // 设置word不可见
			IDispatch docs = (IDispatch) app.get("Documents"); // 获得word中所有打开的文档
			IDispatch doc = (IDispatch) docs.method("Open", new Object[] {
					inputFile, false, true });// 打开文档
			doc.method("SaveAs", new Object[] { pdfFile, wdFormatPDF });// 转换文档为pdf格式
			doc.method("Close", new Object[] { false });
			app.method("Quit", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				app = null;
				rm.release();
				rm = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void ppt2PDF(String inputFile, String pdfFile) {
		ReleaseManager rm = null;
		IDispatch app = null;
		try {
			rm = new ReleaseManager();
			app = new IDispatch(rm, "PowerPoint.Application");
			app.put("Visible", false);
			IDispatch docs = (IDispatch) app.get("Presentations");
			IDispatch doc = (IDispatch) docs.method("Open", new Object[] {
					inputFile, false, true });
			doc.method("SaveAs", new Object[] { pdfFile, wdFormatPDF });
			doc.method("Close", new Object[] { false });
			app.method("Quit", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				app = null;
				rm.release();
				rm = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void xls2PDF(String inputFile, String pdfFile) {
		ReleaseManager rm = null;
		IDispatch app = null;
		try {
			rm = new ReleaseManager();
			app = new IDispatch(rm, "Excel.Application");
			app.put("Visible", false);
			IDispatch docs = (IDispatch) app.get("Workbooks");
			IDispatch doc = (IDispatch) docs.method("Open", new Object[] {
					inputFile, false, true });
			doc.method("SaveAs", new Object[] { pdfFile, wdFormatPDF });
			doc.method("Close", new Object[] { false });
			app.method("Quit", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				app = null;
				rm.release();
				rm = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
