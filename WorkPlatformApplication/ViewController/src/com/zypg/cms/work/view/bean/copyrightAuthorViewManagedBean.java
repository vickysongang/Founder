package com.zypg.cms.work.view.bean;


import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import oracle.jbo.domain.Number;


public class copyrightAuthorViewManagedBean extends CustomManagedBean {
    public copyrightAuthorViewManagedBean() {
    }

    public void init4View() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        try {
            oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
            CommonUtil.getCopyrightAM().initAuthor4View(docId);
        } catch (SQLException e) {
            System.out.println("docIdStr:" + docIdStr);
        }
    }

    public void downloadListener(FacesContext facesContext, OutputStream outputStream) {
        Number ucmDid = (Number)this.resolveExpression("#{pageFlowScope.ucmDid}");
        System.out.println("ucmDid=" + ucmDid);
        InputStream file;
        try {
            file = DocService.getInstance().getFile(ucmDid + "");
            byte[] buffer = new byte[1024];
            int len = file.read(buffer);
            while (len != -1) {
                outputStream.write(buffer, 0, len);
                len = file.read(buffer);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
