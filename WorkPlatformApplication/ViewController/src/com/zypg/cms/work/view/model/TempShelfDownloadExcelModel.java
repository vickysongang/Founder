package com.zypg.cms.work.view.model;

import com.zypg.cms.excel.model.IExcelModel;

import java.util.List;
import java.util.Map;

public class TempShelfDownloadExcelModel implements IExcelModel {
    private List<String> headers;
    private List<Map<String,String>> datas;

    public TempShelfDownloadExcelModel(List<Map<String,String>> datas,List<String> headers) {
          this.datas = datas;
          this.headers = headers;
    }

    @Override
    public int getRowSize() {
        return datas.size();
    }

    @Override
    public String[] getHeaders() {
        if (headers != null) {
            String[] sheaders = new String[headers.size()];
            int i = 0;
            for (Object obj : headers) {
                String header = obj.toString();
                sheaders[i] = header;
                i++;
            }
            return sheaders;
        }

        return null;
    }

    @Override
    public Object getCellData(int i, int j) {
        return datas.get(i).get(headers.get(j));
    }
}
