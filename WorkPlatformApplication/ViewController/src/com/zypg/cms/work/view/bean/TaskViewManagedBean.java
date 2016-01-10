package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

public class TaskViewManagedBean extends CustomManagedBean {
    public TaskViewManagedBean() {
    }

    public void initTaskView() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.taskId}");
        System.out.println(docIdStr);
        try {
            oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
            CommonUtil.getOfflineAM().initTaskMonitorLog(docId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
