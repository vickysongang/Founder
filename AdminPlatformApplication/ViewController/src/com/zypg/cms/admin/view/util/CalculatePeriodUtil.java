package com.zypg.cms.admin.view.util;

import com.zypg.cms.admin.view.model.BacthCreateModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import oracle.jbo.domain.Number;

public class CalculatePeriodUtil {
    public CalculatePeriodUtil() {
        super();
    }

    public List<BacthCreateModel> getAllMONTHLY(List<BacthCreateModel> list, String categoryname, Number startYear,
                                                Number endYear, String type, String[] date, List numberList,
                                                Number categoryNum) {


        int n = 1;
        for (int i = startYear.intValue(); i <= endYear.intValue(); i++) {
            for (int j = 1; j < 13; j++) {

                if (type.equals("MONTHLY")) {
                    BacthCreateModel bc = new BacthCreateModel();
                    bc.setName(categoryname);

                    if (j < 10) {
                        if (Integer.parseInt(date[0]) < 10) {
                            bc.setDate(i + "-0" + j + "-0" + date[0]);
                        } else {
                            bc.setDate(i + "-0" + j + "-" + date[0]);

                        }

                    } else {
                        if (Integer.parseInt(date[0]) < 10) {
                            bc.setDate(i + "-" + j + "-0" + date[0]);

                        } else {
                            bc.setDate(i + "-" + j + "-" + date[0]);

                        }
                    }

                    bc.setPeriodNum(n);
                    bc.setYear(i);
                    if (numberList.contains(n)) {
                        bc.setIsExist("刊期已存在");
                    }

                    n++;
                    if (n >= categoryNum.intValue()+1) {
                        list.add(bc);
                    }

                } else if (type.equals("SEMIMONTHLY")) {
                    for (String da : date) {
                        BacthCreateModel bc = new BacthCreateModel();
                        bc.setName(categoryname);

                        if (j < 10) {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-0" + j + "-0" + da);
                            } else {
                                bc.setDate(i + "-0" + j + "-" + da);

                            }

                        } else {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-" + j + "-0" + da);

                            } else {
                                bc.setDate(i + "-" + j + "-" + da);

                            }
                        }
                        bc.setPeriodNum(n);
                        bc.setYear(i);
                        if (numberList.contains(n)) {
                            bc.setIsExist("刊期已存在");
                        }

                        n++;
                        if (n >= categoryNum.intValue()+1) {
                            list.add(bc);
                        }
                    }

                } else if (type.equals("BIMONTHLY")) {
                    if (Integer.parseInt(date[0]) == 1 && ((j % 2) == 1)) {
                        BacthCreateModel bc = new BacthCreateModel();
                        bc.setName(categoryname);

                        if (j < 10) {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-0" + j + "-0" + date[1]);
                            } else {
                                bc.setDate(i + "-0" + j + "-" + date[1]);

                            }

                        } else {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-" + j + "-0" + date[1]);

                            } else {
                                bc.setDate(i + "-" + j + "-" + date[1]);

                            }
                        }
                        bc.setPeriodNum(n);
                        bc.setYear(i);

                        if (numberList.contains(n)) {
                            bc.setIsExist("刊期已存在");
                        }


                        n++;
                        if (n >= categoryNum.intValue()+1) {
                            list.add(bc);
                        }
                    } else if (Integer.parseInt(date[0]) == 2 && ((j % 2) == 0)) {
                        BacthCreateModel bc = new BacthCreateModel();
                        bc.setName(categoryname);

                        if (j < 10) {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-0" + j + "-0" + date[1]);
                            } else {
                                bc.setDate(i + "-0" + j + "-" + date[1]);
                            }

                        } else {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-" + j + "-0" + date[1]);

                            } else {
                                bc.setDate(i + "-" + j + "-" + date[1]);
                            }
                        }
                        bc.setPeriodNum(n);
                        bc.setYear(i);

                        if (numberList.contains(n)) {
                            bc.setIsExist("刊期已存在");
                        }


                        n++;
                        if (n >= categoryNum.intValue()+1) {
                            list.add(bc);
                        }
                    }
                }
                if (type.equals("TEN_DAYS")) {
                    for (String da : date) {
                        BacthCreateModel bc = new BacthCreateModel();
                        bc.setName(categoryname);

                        if (j < 10) {
                            if (Integer.parseInt(da) < 10) {
                                bc.setDate(i + "-0" + j + "-0" + da);
                            } else {
                                bc.setDate(i + "-0" + j + "-" + da);

                            }

                        } else {
                            if (Integer.parseInt(da) < 10) {
                                bc.setDate(i + "-" + j + "-0" + da);

                            } else {
                                bc.setDate(i + "-" + j + "-" + da);

                            }
                        }
                        bc.setPeriodNum(n);
                        bc.setYear(i);

                        if (numberList.contains(n)) {
                            bc.setIsExist("刊期已存在");
                        }


                        n++;
                        if (n >= categoryNum.intValue()+1) {
                            list.add(bc);
                        }
                    }

                }
            }
        }


        return list;

    }


    public List<BacthCreateModel> getAllWeekly(List<BacthCreateModel> list, String categoryname, Number startYear,
                                               Number endYear, String type, String[] date, List numberList,Number categoryNum) {
        int n = 1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int start = 0;
        try {
            start = df.parse(startYear + "-01-01").getDay() + 1;
        } catch (ParseException e) {
        }

        for (int i = startYear.intValue(); i <= endYear.intValue(); i++) {
            Calendar calendar = new GregorianCalendar(i, 0, 1);
            int a = 1;
            while (calendar.get(Calendar.YEAR) < i + 1) {
                calendar.set(Calendar.WEEK_OF_YEAR, a++);

                if (type.equals("WEEKLY1")) {
                    if (Integer.parseInt(date[0]) == 7) {
                        calendar.set(Calendar.DAY_OF_WEEK, 1);
                    } else {
                        calendar.set(Calendar.DAY_OF_WEEK, (Integer.parseInt(date[0]) + 1));
                    }
                    if (calendar.get(Calendar.YEAR) == i) {
                        BacthCreateModel bc = new BacthCreateModel();
                        bc.setName(categoryname);
                        bc.setDate(df.format(calendar.getTime()));

                        bc.setPeriodNum(n);
                        bc.setYear(i);

                        if (numberList.contains(n)) {
                            bc.setIsExist("刊期已存在");
                        }


                        n++;
                        if (n >= categoryNum.intValue()+1) {
                            list.add(bc);
                        }
                    }
                } else if (type.equals("BIWEEKLY")) {
                    if (Integer.parseInt(date[1]) == 7) {
                        calendar.set(Calendar.DAY_OF_WEEK, 1);
                    } else {
                        calendar.set(Calendar.DAY_OF_WEEK, (Integer.parseInt(date[1]) + 1));
                    }
                    if (Integer.parseInt(date[0]) == 1) {

                        if (start >= Integer.parseInt(date[1])) {
                            if (calendar.get(Calendar.YEAR) == i && ((a % 2) == 0)) {
                                BacthCreateModel bc = new BacthCreateModel();
                                bc.setName(categoryname);
                                bc.setDate(df.format(calendar.getTime()));

                                bc.setPeriodNum(n);
                                bc.setYear(i);

                                if (numberList.contains(n)) {
                                    bc.setIsExist("刊期已存在");
                                }


                                n++;
                                if (n >= categoryNum.intValue()+1) {
                                    list.add(bc);
                                }
                            }
                        } else {
                            if (calendar.get(Calendar.YEAR) == i && ((a % 2) == 1)) {
                                BacthCreateModel bc = new BacthCreateModel();
                                bc.setName(categoryname);
                                bc.setDate(df.format(calendar.getTime()));

                                bc.setPeriodNum(n);
                                bc.setYear(i);

                                if (numberList.contains(n)) {
                                    bc.setIsExist("刊期已存在");
                                }


                                n++;
                                if (n >= categoryNum.intValue()+1) {
                                    list.add(bc);
                                }
                            }
                        }

                    } else {
                        if (start >= Integer.parseInt(date[1])) {
                            if (calendar.get(Calendar.YEAR) == i && ((a % 2) == 1)) {
                                BacthCreateModel bc = new BacthCreateModel();
                                bc.setName(categoryname);
                                bc.setDate(df.format(calendar.getTime()));

                                bc.setPeriodNum(n);
                                bc.setYear(i);

                                if (numberList.contains(n)) {
                                    bc.setIsExist("刊期已存在");
                                }


                                n++;
                                if (n >= categoryNum.intValue()+1) {
                                    list.add(bc);
                                }
                            }
                        } else {
                            if (calendar.get(Calendar.YEAR) == i && ((a % 2) == 0)) {
                                BacthCreateModel bc = new BacthCreateModel();
                                bc.setName(categoryname);
                                bc.setDate(df.format(calendar.getTime()));

                                bc.setPeriodNum(n);
                                bc.setYear(i);

                                if (numberList.contains(n)) {
                                    bc.setIsExist("刊期已存在");
                                }


                                n++;
                                if (n >= categoryNum.intValue()+1) {
                                    list.add(bc);
                                }
                            }
                        }

                    }

                } else if (type.equals("WEEKLY2")) {
                    for (int j = 0; j < date.length; j++) {
                        if (Integer.parseInt(date[j]) == 7) {
                            calendar.set(Calendar.DAY_OF_WEEK, 1);
                        } else {
                            calendar.set(Calendar.DAY_OF_WEEK, (Integer.parseInt(date[j]) + 1));
                        }

                        if (calendar.get(Calendar.YEAR) == i) {
                            BacthCreateModel bc = new BacthCreateModel();
                            bc.setName(categoryname);
                            bc.setDate(df.format(calendar.getTime()));

                            bc.setPeriodNum(n);
                            bc.setYear(i);

                            if (numberList.contains(n)) {
                                bc.setIsExist("刊期已存在");
                            }


                            n++;
                            if (n >= categoryNum.intValue()+1) {
                                list.add(bc);
                            }
                        }
                    }
                }
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

            }
        }
        return list;
    }
}
