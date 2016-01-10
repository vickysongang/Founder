package com.zypg.cms.work.view.validator;


import com.zypg.cms.work.view.util.CommonUtil;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookImportValidator {

    private List<Map<String, Object>> importDatas;
    private String coverType;

    public BookImportValidator(List<Map<String, Object>> importDatas, String coverType, String compCode) {
        this.importDatas = importDatas;
        this.coverType = coverType;
    }

    /**
     * 去重处理
     * @return
     */
    public List<Map<String, Object>> coverHandle() {
        List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
        Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        if ("cover".equalsIgnoreCase(coverType)) {
            for (Map<String, Object> row : importDatas) {
                String key =
                    row.get("书名").toString() + row.get("ISBN").toString() + row.get("版次").toString() + row.get("印次").toString();
                map.put(key, row);
            }
        } else {
            //忽略
            for (int i = 0; i < importDatas.size(); i++) {
                Map<String, Object> row = importDatas.get(i);
                String key =
                    row.get("书名").toString() + row.get("ISBN").toString() + row.get("版次").toString() + row.get("印次").toString();
                if (!map.containsKey(key)) {
                    map.put(key, row);
                }
            }
        }

        Collection<Map<String, Object>> values = map.values();
        for (Object obj : values) {
            Map<String, Object> book = (Map<String, Object>)obj;
            retList.add(book);
        }
        return retList;
    }

    public List<String> validate() {
        List<String> errorMsgList = new ArrayList<String>();
        //1、进行校验
        if (importDatas != null && importDatas.size() > 0) {
            int i = 1;
            for (Map<String, Object> row : importDatas) {
                String startStr = "第" + i + "行: ";
                String errorMsg = "第" + i + "行: ";
                //产品编码  校验 不能为空
                //                Object productCode = row.get("产品编码");
                //                if (productCode == null) {
                //                    errorMsg += "产品编号不能为空" + "  ";
                //                }
                //书名不能为空
                Object bookName = row.get("书名");
                if (bookName == null) {
                    errorMsg += "书名不能为空" + "  ";
                }
                //著者不能为空
                Object author = row.get("著者");
                if (author == null) {
                    errorMsg += "著者不能为空" + "  ";
                }
                //ISBN不能为空
                Object isbn = row.get("ISBN");
                if (isbn == null) {
                    errorMsg += "ISBN不能为空" + "  ";
                } else if (!CommonUtil.validateIsbn(isbn.toString())) {
                    errorMsg += "ISBN不合法" + "  ";
                }
                //出版年月   不能为空 时间格式
                Object publishDate = row.get("出版年月");
                if (publishDate == null) {
                    errorMsg += "出版年月不能为空" + "  ";
                } else {
                    //校验出版年月格式 2006-3-1
                    SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        adf.parse(publishDate.toString());
                    } catch (ParseException e) {
                        errorMsg += "出版年月格式不对，支持的时间格式例如: 2014-05-30 " + "  ";
                    }
                }
                //出版社不能为空
                Object publisherHouse = row.get("出版社");
                if (publisherHouse == null) {
                    errorMsg += "出版社不能为空" + "  ";
                }
                //版次不能为空
                Object edition = row.get("版次");
                if (edition == null) {
                    errorMsg += "版次不能为空" + "  ";
                }
                //印次不能为空
                Object impression = row.get("印次");
                if (impression == null) {
                    errorMsg += "印次不能为空" + "  ";
                }
                if (!startStr.equals(errorMsg)) {
                    System.out.println(errorMsg);
                    errorMsgList.add(errorMsg);
                }
                i++;
            }
        }
        return errorMsgList;
    }


    public List<String> validateCopyright() {
        List<String> errorMsgList = new ArrayList<String>();
        //1、进行校验
        if (importDatas != null && importDatas.size() > 0) {
            int i = 1;
            for (Map<String, Object> row : importDatas) {
                String startStr = "第" + i + "行: ";
                String errorMsg = "第" + i + "行: ";
                //书名不能为空
                Object bookName = row.get("书名");
                if (bookName == null) {
                    errorMsg += "书名不能为空" + "  ";
                }
                //著者不能为空
                Object author = row.get("作者");
                if (author == null) {
                    errorMsg += "作者不能为空" + "  ";
                }
                //ISBN不能为空
                Object isbn = row.get("ISBN");
                if (isbn == null) {
                    errorMsg += "ISBN不能为空" + "  ";
                } else if (!CommonUtil.validateIsbn(isbn.toString())) {
                    errorMsg += "ISBN不合法" + "  ";
                }
                //出版时间   不能为空 时间格式
                Object pubTime = row.get("出版时间");
                if (pubTime == null) {
                    errorMsg += "出版时间不能为空" + "  ";
                } else {
                    //校验出版年月格式2006-3-1
                    SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        adf.parse(pubTime.toString());
                    } catch (ParseException e) {
                        errorMsg += "出版时间格式不对，支持的时间格式例如: 2014-05-30 " + "  ";
                    }
                }
                //出版社 不能为空
                Object publisherHouse = row.get("出版社");
                if (publisherHouse == null) {
                    errorMsg += "出版社不能为空" + "  ";
                }
                //版次不能为空
                Object versionNumber = row.get("版次");
                if (versionNumber == null) {
                    errorMsg += "版次不能为空" + "  ";
                }
                //印次不能为空
                Object yiciNumber = row.get("印次");
                if (yiciNumber == null) {
                    errorMsg += "印次不能为空" + "  ";
                }
                if (!startStr.equals(errorMsg)) {
                    System.out.println(errorMsg);
                    errorMsgList.add(errorMsg);
                }
                i++;
            }
        }
        return errorMsgList;
    }
}
