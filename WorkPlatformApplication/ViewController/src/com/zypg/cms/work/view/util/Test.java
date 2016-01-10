package com.zypg.cms.work.view.util;


import java.io.File;
import java.io.IOException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;


public class Test {
    public Test() {
        super();
    }

    public void listDirectory(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                listDirectory(files[i].getAbsolutePath());
            } else {
                System.out.println(files[i].getAbsolutePath() + "   " + files[i].getName());
            }
        }
    }

    public String getIP(String name) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("获取失败");
        }
        return address.getHostAddress().toString();
    }

    public static Document generateXml(List<String> list) {
        DocumentFactory factory = new DocumentFactory();
        Document document = factory.createDocument();
        Element root = document.addElement("学生集合");
        for (int i = 0; i < list.size(); i++) {
            Element person = root.addElement("学生");
            person.setText("I am " + list.get(i));
            person.addAttribute("id", "a" + Math.random() * 100);
            Element e = person.addElement("家属");
            e.setText("田小小");
        }
        return document;
    }

    public static void main(String[] args) throws IOException, DocumentException {
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("钱七");
        list.add("孙八");
        Document doc = Test.generateXml(list);
        System.out.println(doc.asXML());
    }
}
