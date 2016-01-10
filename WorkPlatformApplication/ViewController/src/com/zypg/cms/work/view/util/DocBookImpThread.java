package com.zypg.cms.work.view.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class DocBookImpThread implements Runnable {
    private String jobNames;
    private String jobIds;
    private String pressName;
    private String compCode;

    public DocBookImpThread(String jobNames, String jobIds, String pressName, String compCode) {
        this.jobNames = jobNames;
        this.jobIds = jobIds;
        this.pressName = pressName;
        this.compCode = compCode;
    }

    public void run() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(Constants.DOCBOOK_REST_URL);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("categoryNames", jobNames));
            nvps.add(new BasicNameValuePair("jobIds", jobIds));
            nvps.add(new BasicNameValuePair("pressName", pressName));
            nvps.add(new BasicNameValuePair("compCode", compCode));
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            httpPost.setEntity(formEntity);
            httpclient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJobNames(String jobNames) {
        this.jobNames = jobNames;
    }

    public String getJobNames() {
        return jobNames;
    }

    public void setJobIds(String jobIds) {
        this.jobIds = jobIds;
    }

    public String getJobIds() {
        return jobIds;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompCode() {
        return compCode;
    }
}
