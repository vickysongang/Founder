package com.zypg.cms.work.view.util;

import com.honythink.mw.ucm.factory.UcmServerConfigFactory;
import com.honythink.mw.ucm.service.DocService;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import oracle.stellent.ridc.model.DataObject;

public class TempResMetadataUCMUtil {
  
    public static String checkinToUcm(String fileName,InputStream in){
           UcmServerConfigFactory.setIS_LOCAL_MODE(true);
                String did = null;
                Map map = new HashMap();
                map.put("IdcService", "CHECKIN_UNIVERSAL");
                map.put("dDocTitle", fileName);
                map.put("dDocAuthor","weblogic");
                map.put("dSecurityGroup", "Public");
                map.put("dDocType", "Document");

            DocService docService;
             try {
                docService = DocService.getInstance();
                DataObject retData = docService.checkinUniversal(map, fileName, in);
                  if (retData != null) {
                      did = retData.get("dID");
                  }
              } catch (Exception e) {
                 e.printStackTrace();
              } 
        return did;
        }
  
}
