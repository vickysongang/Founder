package com.zypg.cms.work.view.util;

import java.util.Comparator;

import oracle.jbo.Row;

public class RowComparator implements Comparator<Row> {
    private int mod = 1;
    private String propertyName;

    public RowComparator(String propertyName, boolean accending) {
        this.propertyName = propertyName;
        if (!accending) {
            mod = -1;
        }
    }


    @Override
    public int compare(Row o1, Row o2) {
        int value = (o1.getAttribute(propertyName).toString().compareTo(o2.getAttribute(propertyName).toString()));
        if (value > 0) {
            return mod * 1;
        } else if (value < 0) {
            return mod * (-1);
        }
        return 0;
    }
}
