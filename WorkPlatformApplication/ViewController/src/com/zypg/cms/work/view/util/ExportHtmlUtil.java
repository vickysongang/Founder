package com.zypg.cms.work.view.util;

import com.zypg.cms.work.view.model.ExportBookModel;

import com.zypg.cms.work.view.model.ExportCategoryModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

public class ExportHtmlUtil {
    public static String generateLeftHtml(List<ExportCategoryModel> categoryList) {
        String htmlStr1 = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            " <head>\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "  <title>无标题页</title>\n" +
            "  <link type=\"text/css\" rel=\"stylesheet\" href=\"skin/css/style.css\" />\n" +
            "  <script type=\"text/javascript\">\n" +
            "		function displaySubCat(subCatID)\n" +
            "			{\n" +
            "				var subCat = document.getElementById(subCatID);\n" +
            "				if(subCat.style.display=='none')\n" +
            "				{\n" +
            "					subCat.style.display='block';\n" +
            "				}\n" +
            "				else\n" +
            "				{\n" +
            "					subCat.style.display='none'\n" +
            "				}\n" +
            "			}\n" +
            "   </script>\n" +
            "  <style> \n" +
            "body {\n" +
            "	text-align: left;\n" +
            "	font: 75% Verdana, Arial, Helvetica, sans-serif;\n" +
            "	overflow: auto;\n" +
            "}\n" +
            " \n" +
            "a,a:link,a:visited {\n" +
            "	text-decoration: none;\n" +
            "	color: #696969;\n" +
            "}\n" +
            " \n" +
            "a:hover,a:active {\n" +
            "	color: #FC7700;\n" +
            "	text-decoration: underline;\n" +
            "}\n" +
            "</style>\n" +
            " </head>\n" +
            " <body bgproperties=\"fixed\" style=\"background-color: #fff;\">\n" +
            "  <div class=\"leftTitle\">\n" +
            "   <table width=\"100%\" height=\"28px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "    <tbody>\n" +
            "     <tr>\n" +
            "      <td class=\"classlefttree\">导航</td>\n" +
            "     </tr>\n" +
            "    </tbody>\n" +
            "   </table>\n" +
            "  </div>";
        String htmlStr2 = " </body>\n" +
            "</html>";
        StringBuffer sb = new StringBuffer();
        sb.append("<div style=\"width: 183px; text-align:left;\">");
        if (categoryList != null && categoryList.size() > 0) {
            int i = 0;
            for (ExportCategoryModel category : categoryList) {
                i++;
                sb.append(" <div xmlns=\"\" class=\"bookdiv\" onclick=\"displaySubCat('subcat" + i + "')\">\n" +
                        "    <a href=\"" + category.getUrl() + "\" target=\"mainFrame\">" +
                        category.getCategoryName() + "</a>\n" +
                        "   </div>");
                if (category.getChildrens() != null && category.getChildrens().size() > 0) {
                    sb.append("<div xmlns=\"\" style=\"text-align: left; padding-left: 10px;display: none;\" id=\"subcat" +
                              i + "\">");
                    for (ExportCategoryModel model : category.getChildrens()) {
                        sb.append(" <div class=\"catdiv\">\n" +
                                "     <a href=\"" + model.getUrl() + "\" target=\"mainFrame\">" +
                                model.getCategoryName() + "</a>\n" +
                                "    </div>");
                    }
                    sb.append("</div>");
                }
            }
        }
        sb.append("</div>");
        String content = htmlStr1 + "\n" +
            sb.toString() + "\n" +
            htmlStr2;
        return content;
    }

    public static String generateMainHtml(List<ExportBookModel> bookList) {
        String htmlStr1 = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            " <head>\n" +
            "  <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\" />\n" +
            "  <title>无标题页</title>\n" +
            "  <script src=\"./js/jquery.js\" type=\"text/javascript\">1</script>\n" +
            "  <script src=\"./js/jquery.pager.js\" type=\"text/javascript\">1</script>\n" +
            "  <script src=\"./js/show.js\" type=\"text/javascript\">1</script>\n" +
            "  <script type=\"text/javascript\" src=\"./js/pager.js\">1</script>\n" +
            "  <link href=\"skin/css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "  <link type=\"text/css\" rel=\"stylesheet\" href=\"skin/css/Pager.css\" />\n" +
            "  <script type=\"text/javascript\">\n" +
            "			$(document).ready(function(){\n" +
            "				PageClick(1);\n" +
            "				BodyLoad();\n" +
            "			});\n" +
            "		</script>\n" +
            "  <style> \n" +
            "a {\n" +
            "	color: black;\n" +
            "	text-decoration: none;\n" +
            "}\n" +
            " \n" +
            "a:hover {\n" +
            "	color: black;\n" +
            "	text-decoration: none;\n" +
            "}\n" +
            ".STYLE1 {\n" +
            "	color: #666666;\n" +
            "	font-weight: bold;\n" +
            "}\n" +
            ".STYLE2 {color: #666666}\n" +
            "</style>\n" +
            " </head>\n" +
            " <body style=\"overflow: hidden;\" bgproperties=\"fixed\">\n" +
            "  <div xmlns=\"\" style=\"width: 100%; padding: 0; margin: top;\">\n" +
            "   <div class=\"centerFrameTitle\">\n" +
            "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"27px\" width=\"100%\">\n" +
            "     <tbody>\n" +
            "      <tr>\n" +
            "       <td class=\"leftSpan\"></td>\n" +
            "       <td class=\"centerSpan\">全部</td>\n" +
            "       <td class=\"classMore\"></td>\n" +
            "       <td class=\"rightSpan\"></td>\n" +
            "      </tr>\n" +
            "     </tbody>\n" +
            "    </table>\n" +
            "   </div>\n" +
            "   <div id=\"authPage1\" class=\"pager\" style=\"margin-left:10px;\">\n" +
            "    1\n" +
            "   </div>\n" +
            "   <div class=\"pageLine\">\n" +
            "    &nbsp;\n" +
            "   </div>\n" +
            "   <div id=\"contentMainDiv\" style=\"width: 720;margin-top:5px;\">\n" +
            "    <table cellpadding=\"5\" cellspacing=\"5\" border=\"0\" style=\"font-size: 14px; text-indent: 2px\">\n" +
            "     <tbody>\n";
        StringBuffer sb = new StringBuffer();
        if (bookList != null && bookList.size() > 0) {
            int i = 0;
            for (ExportBookModel model : bookList) {
                i++;
                if (i % 2 == 1) {
                    sb.append("<tr>");
                }
                sb.append("       <td id=\"section" + i + "\" class=\"centerFrameBookTD\">\n" +
                        "        <table height=\"160\" width=\"200\" border=\"0\" cellspacing=\"5\" cellpadding=\"0\">\n" +
                        "         <tbody>\n" +
                        "          <tr>\n" +
                        "           <td valign=\"middle\" align=\"center\" height=\"160\" width=\"150\"><a target=\"_blank\" href=\"" +
                        model.getDetailHtmlUrl() + "\"><img border=\"0\" src=\"" + model.getThumbnailUrl() +
                        "\" /></a><a target=\"_blank\" href=\"" + model.getPdfUrl() +
                        "\"><img border=\"0\" src=\"./skin/images/button_view_sample.jpg\" /></a></td>\n" +
                        "           <td valign=\"top\" align=\"right\">\n" +
                        "            <table cellspacing=\"4\" cellpadding=\"0\" border=\"0\" width=\"200\">\n" +
                        "             <tbody>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE1\">" + model.getBookName() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">作者：" + model.getAuthor() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">出版时间：" + model.getPubTime() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">版次：" + model.getEdition() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">印次：" + model.getImpression() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">ISBN:" + model.getIsbn() + "</span></td>\n" +
                        "              </tr>\n" +
                        "             </tbody>\n" +
                        "            </table></td></tr>\n" +
                        "         </tbody>\n" +
                        "        </table></td>\n");
                if (i % 2 == 0 || i == bookList.size()) {
                    sb.append("</tr>");
                }
            }
        }
        String htmlStr2 = "     </tbody>\n" +
            "    </table>\n" +
            "   </div>\n" +
            "   <div class=\"pageLine\">\n" +
            "    &nbsp;\n" +
            "   </div>\n" +
            "   <div id=\"authPage2\" class=\"pager\" style=\"margin-left:10px;\">\n" +
            "    2\n" +
            "   </div>\n" +
            "  </div>\n" +
            " </body>\n" +
            "</html>";
        String content = htmlStr1 + "\n" +
            sb.toString() + "\n" +
            htmlStr2;
        return content;
    }

    public static String generateCategoryHtml(String categoryName, List<ExportBookModel> bookList) {
        String htmlStr1 = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            " <head>\n" +
            "  <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\" />\n" +
            "  <title>无标题页</title>\n" +
            "  <script src=\"./js/jquery.js\" type=\"text/javascript\">1</script>\n" +
            "  <script src=\"./js/jquery.pager.js\" type=\"text/javascript\">1</script>\n" +
            "  <script src=\"./js/show.js\" type=\"text/javascript\">1</script>\n" +
            "  <script type=\"text/javascript\" src=\"./js/pager.js\">1</script>\n" +
            "  <link href=\"skin/css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "  <link type=\"text/css\" rel=\"stylesheet\" href=\"skin/css/Pager.css\" />\n" +
            "  <script type=\"text/javascript\">\n" +
            "			$(document).ready(function(){\n" +
            "				PageClick(1);\n" +
            "				BodyLoad();\n" +
            "			});\n" +
            "		</script>\n" +
            "  <style> \n" +
            "a {\n" +
            "	color: black;\n" +
            "	text-decoration: none;\n" +
            "}\n" +
            " \n" +
            "a:hover {\n" +
            "	color: black;\n" +
            "	text-decoration: none;\n" +
            "}\n" +
            ".STYLE1 {\n" +
            "	color: #666666;\n" +
            "	font-weight: bold;\n" +
            "}\n" +
            ".STYLE2 {color: #666666}\n" +
            "</style>\n" +
            " </head>\n" +
            " <body style=\"overflow: hidden;\" bgproperties=\"fixed\">\n" +
            "  <div xmlns=\"\" style=\"width: 100%; padding: 0; margin: top;\">\n" +
            "   <div class=\"centerFrameTitle\">\n" +
            "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"27px\" width=\"100%\">\n" +
            "     <tbody>\n" +
            "      <tr>\n" +
            "       <td class=\"leftSpan\"></td>\n" +
            "       <td class=\"centerSpan\">" + categoryName + "</td>\n" +
            "       <td class=\"classMore\"></td>\n" +
            "       <td class=\"rightSpan\"></td>\n" +
            "      </tr>\n" +
            "     </tbody>\n" +
            "    </table>\n" +
            "   </div>\n" +
            "   <div id=\"authPage1\" class=\"pager\" style=\"margin-left:10px;\">\n" +
            "    1\n" +
            "   </div>\n" +
            "   <div class=\"pageLine\">\n" +
            "    &nbsp;\n" +
            "   </div>\n" +
            "   <div id=\"contentMainDiv\" style=\"width: 720;margin-top:5px;\">\n" +
            "    <table cellpadding=\"5\" cellspacing=\"5\" border=\"0\" style=\"font-size: 14px; text-indent: 2px\">\n" +
            "     <tbody>";
        String htmlStr2 = "</tbody>\n" +
            "    </table>\n" +
            "   </div>\n" +
            "   <div class=\"pageLine\">\n" +
            "    &nbsp;\n" +
            "   </div>\n" +
            "   <div id=\"authPage2\" class=\"pager\" style=\"margin-left:10px;\">\n" +
            "    1\n" +
            "   </div>\n" +
            "  </div>\n" +
            " </body>\n" +
            "</html>";
        StringBuffer sb = new StringBuffer();
        if (bookList != null && bookList.size() > 0) {
            int i = 0;
            for (ExportBookModel model : bookList) {
                i++;
                if (i % 2 == 1) {
                    sb.append(" <tr>\n");
                }
                sb.append("       <td id=\"section" + i + "\" class=\"centerFrameBookTD\">\n" +
                        "        <table height=\"160\" width=\"200\" border=\"0\" cellspacing=\"5\" cellpadding=\"0\">\n" +
                        "         <tbody>\n" +
                        "          <tr>\n" +
                        "           <td valign=\"middle\" align=\"center\" height=\"160\" width=\"150\"><a target=\"_blank\" href=\"" +
                        model.getDetailHtmlUrl() + "\"><img border=\"0\" src=\"" + model.getThumbnailUrl() +
                        "\" /></a><a target=\"_blank\" href=\"" + model.getPdfUrl() +
                        "\"><img border=\"0\" src=\"./skin/images/button_view_sample.jpg\" /></a></td>\n" +
                        "           <td valign=\"top\" align=\"right\">\n" +
                        "            <table cellspacing=\"4\" cellpadding=\"0\" border=\"0\" width=\"200\">\n" +
                        "             <tbody>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE1\">" + model.getBookName() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">作者：" + model.getAuthor() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">出版时间：" + model.getPubTime() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">版次：" + model.getEdition() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">印次：" + model.getImpression() + "</span></td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "               <td><span class=\"STYLE2\">ISBN:" + model.getIsbn() + "</span></td>\n" +
                        "              </tr>\n" +
                        "             </tbody>\n" +
                        "            </table></td>\n");
                if (i % 2 == 0 || i == bookList.size()) {
                    sb.append(" </tr>\n");
                }
            }
        }
        String content = htmlStr1 + "\n" +
            sb.toString() + "\n" +
            htmlStr2;
        return content;
    }

    public static String generateBookDetailHtml(ExportBookModel book) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                " <head>\n" +
                "  <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\" />\n" +
                "  <link type=\"text/css\" rel=\"stylesheet\" href=\"../../skin/css/style.css\" />\n" +
                "  <script src=\"../../js/show.js\" type=\"text/javascript\">1</script>\n" +
                "  <script type=\"text/javascript\">\n" +
                "		function seeMore(src){\n" +
                "			var divEle = src.parentNode.parentNode.parentNode.parentNode;\n" +
                "			if(divEle.className == 'shortContent')\n" +
                "			{\n" +
                "				divEle.className=\"allContent\";\n" +
                "			}else if(divEle.className == 'allContent')\n" +
                "			{\n" +
                "				divEle.className=\"shortContent\";\n" +
                "			}\n" +
                "		}\n" +
                "		</script>\n" +
                "  <style> \n" +
                "		a {\n" +
                "			color: black;\n" +
                "			text-decoration: none;\n" +
                "		}\n" +
                "		\n" +
                "		a:hover,a:hover,a:visited,a:active,a:link {\n" +
                "			color: black;\n" +
                "			text-decoration: none;\n" +
                "		}	\n" +
                " \n" +
                "</style>\n" +
                " </head>\n" +
                " <body onload=\"BodyLoad()\" bgcolor=\"#EFEFEC\" style=\"text-align: center; margin: 0;\">\n" +
                "  <div class=\"div_center\">\n" +
                "   <div style=\"width: 950px; height: 89px; background: url(../../skin/images/top_banner.jpg) no-repeat;\"> \n" +
                "   </div>\n" +
                "   <div style=\"width: 950px; height: 30px; background: url(../../skin/images/top_map.jpg) no-repeat; line-height:30px;text-align:left;padding-left:10px;\">\n" +
                "     方正智汇出版资源管理系统&gt;&gt;图书详细信息 \n" +
                "   </div>\n" +
                "   <div xmlns=\"\" id=\"contentMainDiv\">\n" +
                "    <table width=\"100%\">\n" +
                "     <tbody>\n" +
                "      <tr>\n" +
                "       <td class=\"bookCover\" valign=\"middle\" align=\"center\"><img src=\"" +
                book.getThumbnailName() + "\" /></td>\n" +
                "       <td class=\"blank\"> </td>\n" +
                "       <td class=\"bookInfo\" valign=\"middle\" align=\"left\">\n" +
                "        <table>\n" +
                "         <tbody>\n" +
                "          <tr>\n" +
                "           <td>\n" +
                "            <table>\n" +
                "             <tbody>");
        //基本信息
        Map<String, Object> baseInfoFieldMap = (Map<String, Object>)book.getFieldsMap().get("baseInfoField");
        if (baseInfoFieldMap != null && baseInfoFieldMap.size() > 0) {
            Map<String, Object> baseInfoDataMap = (Map<String, Object>)book.getDatasMap().get("baseInfoData");
            int i = 0;
            for (Iterator it = baseInfoFieldMap.keySet().iterator(); it.hasNext(); ) {
                i++;
                String key = (String)it.next();
                Object displayName = baseInfoFieldMap.get(key);
                Object displayValue = baseInfoDataMap.get(key);
                if (i % 2 == 1) {
                    sb.append("<tr>");
                }
                sb.append("<td width=\"200px\">" + displayName + "：&nbsp;&nbsp; " + displayValue + "</td>");
                if (i % 2 == 0 || i == baseInfoFieldMap.size()) {
                    sb.append("</tr>");
                }
            }
        }
        sb.append(" </tbody>\n" +
                "            </table></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td align=\"center\"><a target=\"\" href=\"" + book.getPdfName() +
                "\"><img border=\"0\" src=\"../../skin/images/button_view_diable.jpg\" /></a></td>\n" +
                "      </tr>\n" +
                "     </tbody>\n" +
                "    </table>\n" +
                "   </div>");
        sb.append("<div xmlns=\"\">");
        //精编信息
        Map<String, Object> jbInfoFieldMap = (Map<String, Object>)book.getFieldsMap().get("jbInfoField");
        if (jbInfoFieldMap != null && jbInfoFieldMap.size() > 0) {
            Map<String, Object> jbInfoDataMap = (Map<String, Object>)book.getDatasMap().get("jbInfoData");
            for (Iterator it = jbInfoDataMap.keySet().iterator(); it.hasNext(); ) {
                String key = (String)it.next();
                Object displayName = jbInfoFieldMap.get(key);
                Object displayValue = jbInfoDataMap.get(key);
                sb.append("<div class=\"shortContent\">\n" +
                        "     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"27px\" width=\"100%\">\n" +
                        "      <tbody>\n" +
                        "       <tr>\n" +
                        "        <td class=\"leftSpan\"> </td>\n" +
                        "        <td class=\"centerSpan\"><img src=\"../../skin/images/list_ico.gif\" height=\"13\" width=\"13\" />&nbsp;" +
                        displayName + "</td>\n" +
                        "        <td onclick=\"seeMore(this)\" class=\"classMore\">展开</td>\n" +
                        "        <td class=\"rightSpan\"></td>\n" +
                        "       </tr>\n" +
                        "      </tbody>\n" +
                        "     </table>\n" +
                        "     <span class=\"jbclass\">\n" +
                        "      <div>\n" +
                        displayValue + "      </div> \n" +
                        "      <div> \n" +
                        "      </div> \n" +
                        "      <div> \n" +
                        "      </div> </span>\n" +
                        "    </div>");

            }
        }
        //宣传信息
        Map<String, Object> xcInfoFieldMap = (Map<String, Object>)book.getFieldsMap().get("xcInfoField");
        if (xcInfoFieldMap != null && xcInfoFieldMap.size() > 0) {
            Map<String, Object> xcInfoDataMap = (Map<String, Object>)book.getDatasMap().get("xcInfoData");
            for (Iterator it = xcInfoFieldMap.keySet().iterator(); it.hasNext(); ) {
                String key = (String)it.next();
                Object displayName = xcInfoFieldMap.get(key);
                List<String> displayValueList = (List<String>)xcInfoDataMap.get(key);
                if (displayValueList != null && displayValueList.size() > 0) {
                    sb.append(" <div class=\"shortContent\">\n" +
                            "     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"27px\" width=\"100%\">\n" +
                            "      <tbody>\n" +
                            "       <tr>\n" +
                            "        <td class=\"leftSpan\"> </td>\n" +
                            "        <td class=\"centerSpan\"><img src=\"../../skin/images/list_ico.gif\" height=\"13\" width=\"13\" />&nbsp;" +
                            displayName + "</td>\n" +
                            "        <td onclick=\"seeMore(this)\" class=\"classMore\">展开</td>\n" +
                            "        <td class=\"rightSpan\"></td>\n" +
                            "       </tr>\n" +
                            "      </tbody>\n" +
                            "     </table>");

                    for (String xcContent : displayValueList) {
                        sb.append(" <span class=\"jbclass\">\n" +
                                "      <div>\n" +
                                xcContent + "      </div> \n" +
                                "      <div> \n" +
                                "      </div> \n" +
                                "      <div> \n" +
                                "      </div> </span>");
                    }
                    sb.append("   </div>");
                }
            }
        }
        sb.append(" </div>\n" +
                "   <div class=\"bottom\"></div>\n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>");
        return sb.toString();
    }

    public static String toString(Object o) {
        String result = (o == null ? "" : o.toString());
        return result;
    }

    public static Document generateDataXml(List<ExportBookModel> list) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("books");
        if (list != null && list.size() > 0) {
            for (ExportBookModel model : list) {
                Map<String, Object> baseInfoDataMap = (Map<String, Object>)model.getDatasMap().get("baseInfoData");
                Element bookNode = root.addElement("book");
                bookNode.addElement("SYS_TOPIC").addText(toString(baseInfoDataMap.get("BOOK_NAME")));
                bookNode.addElement("ISBN").addText(toString(baseInfoDataMap.get("ISBN")));
                bookNode.addElement("MATERIAL_ID").addText(toString(baseInfoDataMap.get("ITEM_CODE")));
                bookNode.addElement("SERIES_NAME").addText(toString(baseInfoDataMap.get("SERIES")));
                bookNode.addElement("PRESS_NAME").addText(toString(baseInfoDataMap.get("PUBLISHING_HOUSE")));
                bookNode.addElement("BOOK_CAT").addText(model.getCategoryName());
                bookNode.addElement("WEBSITE_CAT").addText("");
                bookNode.addElement("LANGUAGE").addText(toString(baseInfoDataMap.get("LANG")));
                bookNode.addElement("EDITOR").addText(toString(baseInfoDataMap.get("EDITOR")));
                bookNode.addElement("COPY_DESIGNER").addText(toString(baseInfoDataMap.get("")));
                bookNode.addElement("SYS_AUTHORS").addText(toString(baseInfoDataMap.get("AUTHOR")));
                bookNode.addElement("COVER_DESIGNER").addText(toString(baseInfoDataMap.get("COVER_DESIGN")));
                bookNode.addElement("MAJOR_EDITOR").addText(toString(baseInfoDataMap.get("CHIEF_EDITOR")));
                bookNode.addElement("TRANSLATOR").addText(toString(baseInfoDataMap.get("TRANSLATOR")));
                bookNode.addElement("PRINT_VERSION").addText(toString(baseInfoDataMap.get("IMPRESSION")));
                bookNode.addElement("BOOK_VERSION").addText(toString(baseInfoDataMap.get("EDITION")));
                bookNode.addElement("PUBDATE").addText(toString(baseInfoDataMap.get("PUB_TIME")));
                bookNode.addElement("TEXT_NUM").addText(toString(baseInfoDataMap.get("BOOK_SIZE")));
                bookNode.addElement("PRICE").addText(toString(baseInfoDataMap.get("PRICE")));
                bookNode.addElement("READER_OBJECT").addText(toString(baseInfoDataMap.get("READER_GROUP")));
                bookNode.addElement("SHELF_ADVICE").addText(toString(baseInfoDataMap.get("SUGGESTION")));
                bookNode.addElement("FORMAT").addText(toString(baseInfoDataMap.get("FORMAT_DESIGN")));
                bookNode.addElement("PRODUCT_SIZE").addText(toString(baseInfoDataMap.get("PROD_SIZE")));
                bookNode.addElement("TEXT_TYPE").addText(toString(baseInfoDataMap.get("MAIN_PAGE")));
                bookNode.addElement("TEXT_COLOR").addText(toString(baseInfoDataMap.get("COLOR_NUM")));
                bookNode.addElement("TEXT_SOFT").addText(toString(baseInfoDataMap.get("MAIN_TYPESET")));
                bookNode.addElement("COVER_SOFT").addText(toString(baseInfoDataMap.get("")));
                bookNode.addElement("FLAT_PLATE").addText(toString(baseInfoDataMap.get("")));
                bookNode.addElement("SUPPORT_PRODUCT").addText(toString(baseInfoDataMap.get("ASSORT_PROD")));
                bookNode.addElement("DEPART").addText("");
                bookNode.addElement("KEYWORDS").addText(toString(baseInfoDataMap.get("KEYWORD")));
                bookNode.addElement("REMARK").addText(toString(baseInfoDataMap.get("REMARKS")));
                bookNode.addElement("cover").addText(model.getThumbnailUrl());
                if (model.getPdfUrl() == null) {
                    bookNode.addElement("pdf").addText("./skin/images/button_view_diable.jpg;#");
                } else {
                    bookNode.addElement("pdf").addText("_blank-./skin/images/button_view_sample.jpg;" +
                                                       model.getPdfUrl());
                }
                bookNode.addElement("bookUrl").addText(model.getDetailHtmlUrl());
            }
        }
        return document;
    }

    public static void main(String[] args) {
        List<ExportBookModel> bookList = new ArrayList<ExportBookModel>();
        ExportBookModel model1 =
            new ExportBookModel("我们都是好孩子1", "宋昂", "2014-09-16", "1", "1", "3244343434", "23434", "sdsd", "sdsdsd");
        ExportBookModel model2 =
            new ExportBookModel("我们都是好孩子2", "宋昂", "2014-09-16", "1", "1", "3244343434", "23434", "sdsd", "sdsdsd");
        ExportBookModel model3 =
            new ExportBookModel("我们都是好孩子3", "宋昂", "2014-09-16", "1", "1", "3244343434", "23434", "sdsd", "sdsdsd");
        bookList.add(model1);
        bookList.add(model2);
        bookList.add(model3);

        //        ExportHtmlUtil.generateCategoryHtml("1111", "测试分类", bookList, "D:");
    }
}
