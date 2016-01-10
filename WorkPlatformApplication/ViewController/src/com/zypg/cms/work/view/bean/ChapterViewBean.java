package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;

import java.sql.SQLException;

import javax.faces.event.ActionEvent;
import oracle.jbo.domain.Number;
import oracle.adf.view.rich.context.AdfFacesContext;

public class ChapterViewBean {
    public ChapterViewBean() {
    }

    public void init() {
        // Add event code here...
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        Object docIdObj = adfFacesContext.getPageFlowScope().get("pDocId");
        Number docId;

        try {
            docId = (docIdObj == null ? null : new Number(docIdObj.toString()));
            String bookName = CommonUtil.getBookAM().getBookName(docId);
            adfFacesContext.getPageFlowScope().put("bookName", bookName);
            CommonUtil.getBookAM().initChapter(docId);
            adfFacesContext.getPageFlowScope().put("chapterContent", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void chapterSelectActionListener(ActionEvent actionEvent) {
        // Add event code here...
        String content = (String)actionEvent.getComponent().getAttributes().get("content");
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.getPageFlowScope().put("chapterContent", content);
        
    }
}
