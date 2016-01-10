package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.common.CmsDocUcmRelTVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocUcmRelTVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class OnlineReadManagedBean extends CustomManagedBean {
    public OnlineReadManagedBean() {

    }

    public void initData4OnlineRead() {
        this.setExpressionValue("#{sessionScope.swfUrl}", null);
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        if (docIdStr != null) {
            try {
                Number docId = new Number(docIdStr);
                Row row = CommonUtil.getCommonAM().initOnlineReadInfo(docId);
                if (row != null) {
                    CmsDocUcmRelTVORowImpl relRow = (CmsDocUcmRelTVORowImpl)row;
                    this.setExpressionValue("#{sessionScope.swfUrl}", relRow.getAttribute1());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
