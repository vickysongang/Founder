package com.zypg.cms.work.view.util;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;

public class DocumentHelper {
    public static Document createDocument() {
        DocumentFactory factory = new DocumentFactory();
        Document document = factory.createDocument();
        return document;
    }
}
