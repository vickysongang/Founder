package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ElecProdViewManagedBean extends CustomManagedBean {
    public ElecProdViewManagedBean() {
    }

    public void initInfo4View() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        if (docIdStr != null) {
            try {
                oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
                String previewUrl = CommonUtil.getElecProdAM().initInfo4View(docId);
                HttpServletRequest request =
                    (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession session = request.getSession();
                session.setAttribute("flashUrl", previewUrl);
            } catch (SQLException e) {
                System.out.println("docIdStr:" + docIdStr);
            }
        }
    }
}
