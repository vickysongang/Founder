package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.elecprod.CmsElecProdTVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.domain.ClobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class ElecProdScriptManagedBean extends CustomManagedBean {
    public ElecProdScriptManagedBean() {
    }

    public void preScriptElecProd() {
        oracle.jbo.domain.Number docId = null;
        docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getElecProdAM().preScriptElecprod(docId);
    }

    public void saveActionListener(ClientEvent clientEvent) {
        String inputFileId = (String)clientEvent.getParameters().get("inputFileId");
        RichInputFile inputFile = (RichInputFile)clientEvent.getComponent().findComponent(inputFileId);
        UploadedFile uploadedFile = (UploadedFile)inputFile.getValue();
        if (uploadedFile != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(uploadedFile.getInputStream()));
                String line = "";
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                CmsElecProdTVORowImpl newRow =
                    (CmsElecProdTVORowImpl)CommonUtil.getElecProdAM().getCmsElecProdTVO().getCurrentRow();
                newRow.setScript(new ClobDomain(sb.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CommonUtil.getAudioAM().getDBTransaction().commit();
        this.navToOutCome("toReturn");
    }
    
    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getElecProdAM().getDBTransaction().rollback();
    }
}
