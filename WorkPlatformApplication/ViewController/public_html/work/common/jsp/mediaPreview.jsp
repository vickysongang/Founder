<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title></title>
    </head>
    <body>
        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
                codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
                width="450" height="250">
            <param name="movie" value="../../../resources/flash/flvplayer.swf"/>
            <param name="quality" value="high"/>
            <param name="allowFullScreen" value="true"/>
            <param name="flashvars" value="vcastr_file=${sessionScope.flashUrl}&amp;IsAutoPlay=1&amp;IsShowBar=1&amp;LogoText=founder"/>
            <embed src="../../../resources/flash/flvplayer.swf" allowfullscreen="true" quality="high"
                   flashvars="vcastr_file=${sessionScope.flashUrl}&amp;IsAutoPlay=1&amp;IsShowBar=1&amp;LogoText=founder"
                   pluginspage="http://www.macromedia.com/go/getflashplayer" id="s" type="application/x-shockwave-flash"
                   width="450" height="300">
            </embed>
        </object>
    </body>
</html>