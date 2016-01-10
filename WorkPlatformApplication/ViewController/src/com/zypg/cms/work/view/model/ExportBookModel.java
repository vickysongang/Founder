package com.zypg.cms.work.view.model;

import java.util.HashMap;
import java.util.Map;

public class ExportBookModel {
    private String docId;
    private String categoryName;
    private String catetoryId;
    private String bookName;
    private String author;
    private String pubTime;
    private String edition;
    private String impression;
    private String isbn;
    private String detailHtmlUrl;
    private String thumbnailName;
    private String thumbnailUrl;
    private String pdfUrl;
    private String pdfName;
    private Map<String, Object> fieldsMap = new HashMap<String, Object>(); //选择的属性
    private Map<String, Object> datasMap = new HashMap<String, Object>(); //数据

    public ExportBookModel() {

    }

    public ExportBookModel(String bookName, String author, String pubTime, String edition, String impression,
                           String isbn, String detailHtmlUrl, String thumbnailUrl, String pdfUrl) {
        super();
        this.bookName = bookName;
        this.author = author;
        this.pubTime = pubTime;
        this.edition = edition;
        this.impression = impression;
        this.isbn = isbn;
        this.detailHtmlUrl = detailHtmlUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.pdfUrl = pdfUrl;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEdition() {
        return edition;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public String getImpression() {
        return impression;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setDetailHtmlUrl(String detailHtmlUrl) {
        this.detailHtmlUrl = detailHtmlUrl;
    }

    public String getDetailHtmlUrl() {
        return detailHtmlUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setFieldsMap(Map<String, Object> fieldsMap) {
        this.fieldsMap = fieldsMap;
    }

    public Map<String, Object> getFieldsMap() {
        return fieldsMap;
    }

    public void setDatasMap(Map<String, Object> datasMap) {
        this.datasMap = datasMap;
    }

    public Map<String, Object> getDatasMap() {
        return datasMap;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocId() {
        return docId;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCatetoryId(String catetoryId) {
        this.catetoryId = catetoryId;
    }

    public String getCatetoryId() {
        return catetoryId;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfName() {
        return pdfName;
    }
}
