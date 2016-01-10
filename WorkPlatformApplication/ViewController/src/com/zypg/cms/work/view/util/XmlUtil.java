package com.zypg.cms.work.view.util;

import com.honythink.applicationframework.hadf.util.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.transform.stream.StreamSource;

import org.dom4j.XPath;

import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;


public class XmlUtil {
    public XmlUtil() {
        super();
    }

    public static void replaceXmlNodeValue(String xmlPath, String targetPath, String nodeName,
                                           Map<String, String> map) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(xmlPath);
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName(nodeName);
            for (int i = 0; i < list.getLength(); i++) {
                Element ele = (Element)list.item(i);
                String attrName = ele.getAttribute("name");
                System.out.println("attrName:" + attrName);
                String nodeValue = map.get(attrName);
                System.out.println("nodeValue:" + nodeValue);
                if (nodeValue != null) {
                    ele.setTextContent(nodeValue);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            // 设置编码类型
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StreamResult result = new StreamResult(new FileOutputStream(targetPath));
            transformer.transform(domSource, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void replaceXmlNodeAttrValue(String xmlPath, String targetPath, String nodeName,
                                               String attributeName, Map<String, String> map) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(xmlPath);
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName(nodeName);
            for (int i = 0; i < list.getLength(); i++) {
                Element ele = (Element)list.item(i);
                ele.setAttribute(attributeName, map.get(attributeName));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            // 设置编码类型
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StreamResult result = new StreamResult(new FileOutputStream(targetPath));
            transformer.transform(domSource, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void writeDocument(org.dom4j.Document document, String filePath) {
        try {
            //读取文件
            FileOutputStream fos = new FileOutputStream(filePath);
            //设置文件编码
            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setIndent(true);
            xmlFormat.setNewlines(true);
            xmlFormat.setEncoding("UTF-8");
            //创建写文件方法
            XMLWriter xmlWriter = new XMLWriter(fos, xmlFormat);
            //写入文件
            xmlWriter.write(document);
            //关闭
            xmlWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * DOM对象转换
     * @param document
     * @param in
     * @return
     */
    public static org.dom4j.Document styleDocument(org.dom4j.Document document, InputStream in) {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(new StreamSource(in));
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        }
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
        }
        org.dom4j.Document transformedDoc = result.getDocument();
        return transformedDoc;
    }

    /**
     * 把一个XML对象转换为HTML字符串返回
     * @param document
     * @param xslPath
     * @return
     */
    public static String convertXmlToHtml(org.dom4j.Document document, String xslPath) throws IOException {
        String content = null;
        org.dom4j.Document doc = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(xslPath));
            doc = styleDocument(document, in);
            content = doc.getRootElement().asXML();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return content;
    }

    public static void writeHtmlFile(String content, String filePath) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            byte[] bytes = content.getBytes("UTF-8");
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.closeOutputStream(out);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XmlUtil.writeHtmlFile("我们都是好孩子", "D:/a.txt");
    }
}
