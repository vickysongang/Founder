package com.zypg.cms.work.view.util;

import com.zypg.cms.foldermanager.manager.FolderManager;

import com.zypg.cms.foldermanager.model.FtpFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlaceHolderUtil {
  
    public static boolean isPlaceHolder(FolderManager fm,FtpFile sf){
            boolean isHolder = false;
            String oldPwd = fm.getPWD();
            fm.getConn().sendFtpCommand("CWD "+sf.getFilePath());
            fm.getConn().updatePWD();
            String newPath = System.getProperty("user.dir")+"/"+System.currentTimeMillis();
            File newPathFile = new File(newPath); 
            if(!newPathFile.exists()){
                newPathFile.mkdirs();
            }
            fm.getConn().setLocalPath(newPath);
            fm.getConn().download(sf.getFileName());
            File tempFile = new File(fm.getConn().getLocalPath()+sf.getFileName());
            FileInputStream fin;
            try {
                fin = new FileInputStream(tempFile);
                byte[] bytes = new byte[2048];
                int len = fin.read(bytes);
                if(len!=-1){
                       String str = new String(bytes,0,len,"UTF-8");
                       if(str!=null && str.startsWith("FEILIUZHIXIASANQIANCHIPLACEHOLDER")){
                               isHolder = true;
                           }
                    }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFile.delete();
            newPathFile.delete();
            fm.getConn().sendFtpCommand("CWD "+oldPwd);
            fm.getConn().updatePWD();
            return isHolder;
        }
  
}
