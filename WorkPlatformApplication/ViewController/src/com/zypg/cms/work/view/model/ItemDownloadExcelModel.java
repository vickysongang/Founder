package com.zypg.cms.work.view.model;


import com.zypg.cms.excel.model.IExcelModel;
import com.zypg.cms.work.model.viewobject.copyright.query.Cms3rdAuthorizationItemVORowImpl;

import java.util.List;

public class ItemDownloadExcelModel implements IExcelModel {
    private String[] headers;
    private String[] headerAttrs;
    private List<Cms3rdAuthorizationItemVORowImpl> datas;

    public ItemDownloadExcelModel() {
        super();
    }

    public ItemDownloadExcelModel(String[] headers, String[] headerAttrs,
                                  List<Cms3rdAuthorizationItemVORowImpl> datas) {
        super();
        this.headers = headers;
        this.headerAttrs = headerAttrs;
        this.datas = datas;
    }

    @Override
    public int getRowSize() {
        return datas.size();
    }

    @Override
    public String[] getHeaders() {
        if (headers != null) {
            return headers;
        }
        return null;
    }

    @Override
    public Object getCellData(int i, int i1) {
        if (i1 >= 4) {
            return "";
        } else {
            Object obj = ((Cms3rdAuthorizationItemVORowImpl)datas.get(i)).getAttribute(headerAttrs[i1]);
            return obj != null ? obj.toString() : obj;
        }
    }
}
