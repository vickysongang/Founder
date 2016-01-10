package com.zypg.cms.work.view.util;

import com.honythink.applicationframework.hadf.util.FileUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowId;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.ControlBinding;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.component.UIXEditableValue;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class CustomManagedBean {
    /**
     * 默认的构造器
     */
    public CustomManagedBean() {

    }

    /**
     * 解析EL表达式，并返回匹配对象或创建出相应对象
     *
     * @param expression
     *            待解析的EL表达式
     * @return 匹配的受管对象
     */
    public Object resolveExpression(String expression) {
        FacesContext facesContext = getFacesContext();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
    }

    /**
     * 设置EL表达式的值
     *
     * @param el
     *            EL表达式 value 要设置的值
     */
    public void setExpressionValue(String el, Object value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExpressionFactory ef = ctx.getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(ctx.getELContext(), el, Object.class);
        ve.setValue(ctx.getELContext(), value);
    }

    /**
     * @param expression
     * @param returnType
     * @param argTypes
     * @param argValues
     * @return
     */
    public static Object resolveMethodExpression(String expression, Class returnType, Class[] argTypes,
                                                 Object[] argValues) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        MethodExpression methodExpression =
            elFactory.createMethodExpression(elContext, expression, returnType, argTypes);
        return methodExpression.invoke(elContext, argValues);
    }

    /**
     * 获取当前FacesContext对象
     */
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * 获取当前页面的BindingContainer对象
     *
     * @return 当前页面的BindingContainer对象
     */
    public BindingContainer getBindingContainer() {
        return (BindingContainer)resolveExpression("#{bindings}");
    }

    /**
     * 获取当前页面对应的DCBindingContainer
     *
     * @return 当前页面对应的DCBindingContainer
     */
    public DCBindingContainer getDCBindingContainer() {
        return (DCBindingContainer)getBindingContainer();
    }

    /**
     * 在当前的DCBindingContainer中获取OperationBinding
     *
     * @param name
     *            待获取的OperationBinding名称
     * @return OperationBinding对象
     */
    public OperationBinding findOperation(String name) {
        OperationBinding operationBinding = getDCBindingContainer().getOperationBinding(name);
        if (operationBinding == null) {
            throw new RuntimeException("Operation '" + name + "' not found");
        }
        return operationBinding;
    }

    /**
     * 在当前BindingContainer中查找Iterator Binding
     *
     * @param iteratorName
     *            Iterator名称
     * @return Iterator Binding
     */
    public DCIteratorBinding findIterator(String iteratorName) {
        DCIteratorBinding iter = getDCBindingContainer().findIteratorBinding(iteratorName);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + iteratorName + "' not found");
        }
        return iter;
    }

    /**
     * A convenience method for getting the value of a bound attribute in the
     * current page context programatically.
     *
     * @param attributeName
     *            of the bound value in the pageDef
     * @return value of the attribute
     */
    public Object getBoundAttributeValue(String attributeName) {
        return findControlBinding(attributeName).getInputValue();
    }

    /**
     * A convenience method for setting the value of a bound attribute in the
     * context of the current page.
     *
     * @param attributeName
     *            of the bound value in the pageDef
     * @param value
     *            to set
     */
    public void setBoundAttributeValue(String attributeName, Object value) {
        findControlBinding(attributeName).setInputValue(value);
    }

    /**
     * Convenience method to find a DCControlBinding as a JUCtrlValueBinding to
     * get able to then call getInputValue() or setInputValue() on it.
     *
     * @param attributeName
     *            name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public AttributeBinding findControlBinding(String attributeName) {
        return findControlBinding(getBindingContainer(), attributeName);
    }

    /**
     * Convenience method to find a DCControlBinding as an AttributeBinding to
     * get able to then call getInputValue() or setInputValue() on it.
     *
     * @param bindingContainer
     *            binding container
     * @param attributeName
     *            name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    private AttributeBinding findControlBinding(BindingContainer bindingContainer, String attributeName) {
        if (attributeName != null) {
            if (bindingContainer != null) {
                ControlBinding ctrlBinding = bindingContainer.getControlBinding(attributeName);
                if (ctrlBinding instanceof AttributeBinding) {
                    return (AttributeBinding)ctrlBinding;
                }
            }
        }
        return null;
    }


    /**
     * 弹出消息提示
     *
     * @param componentId
     *            绑定的组件ID messageText 消息内容 severity 提示级别
     */
    public void addFormattedMessage(String componentId, String messageText, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage();
        message.setDetail(messageText);
        message.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(componentId, message);
    }

    /**
     * 弹出对话框
     *
     * @param popId
     *            绑定的组件ID
     */
    public void showPopup(String popId) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(context, ExtendedRenderKitService.class);
        StringBuilder builder = new StringBuilder();
        builder.append("AdfPage.PAGE.findComponent('");
        builder.append(popId);
        builder.append("').show();");
        erks.addScript(context, builder.toString());
    }

    /**
     * 显示自动保存的时间
     *
     * @param uc
     * @param isAutoSave
     */
    public void showClientSaveTime(UIComponent uc, Boolean isAutoSave, String errorTip) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(context, ExtendedRenderKitService.class);
        // 获取客户端时间并显示
        StringBuffer script = new StringBuffer();
        script.append("var cTime = new Date().toLocaleTimeString();");
        script.append("var currentDateTime = AdfPage.PAGE.findComponent(\"" +
                      uc.getClientId(FacesContext.getCurrentInstance()) + "\");");
        script.append("currentDateTime.setValue(");
        if (isAutoSave) {
            script.append("'系统自动保存成功。保存时间：'+cTime");
        } else {
            script.append("'<font color=\"red\">");
            script.append(errorTip);
            script.append("</font>保存时间：'+cTime");
        }
        script.append(");");
        erks.addScript(context, script.toString());
        AdfFacesContext.getCurrentInstance().addPartialTarget(uc);
    }

    /**
     * 执行JS调用其他组件的事件
     *
     * @param id
     *            绑定的组件ID
     */
    public void executeCompomentAction(String id) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getService(context.getRenderKit(), ExtendedRenderKitService.class);
        String str =
            "var exportCmd = AdfPage.PAGE.findComponentByAbsoluteId('" + id + "');" + "var actionEvent = new AdfActionEvent(exportCmd);" +
            "actionEvent.forceFullSubmit();" + "actionEvent.noResponseExpected();" + "actionEvent.queue();";
        erks.addScript(context, str);
    }

    /**
     * 通过代码的方式来跳转页面
     *
     * @param outCome
     */
    public void navToOutCome(String outCome) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        NavigationHandler navigationHandler = application.getNavigationHandler();
        ControllerContext controllerContext = ControllerContext.getInstance();
        String viewId = controllerContext.getCurrentViewPort().getViewId();
        if (viewId != null) {
            navigationHandler.handleNavigation(facesContext, viewId, outCome);
        }
    }

    /**
     * 执行js弹出提示
     *
     * @param message
     *            显示的提示内容
     */
    public void showMessage(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='infoTip';" + "div.style.height='61px';" +
            "div.style.width='131px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-yes.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('infoTip'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    public void showLongMessage(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='longInfoTip';" + "div.style.height='61px';" +
            "div.style.width='169px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-yes.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('longInfoTip'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    /**
     *    url打开页面用
     */
    public void showLongMessageOpenURL(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='longOpenInfoTip';" + "div.style.height='61px';" +
            "div.style.width='169px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../../../resources/images/pop-yes.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('longOpenInfoTip'));" +
            "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    /**
     * 执行js弹出提示
     *
     * @param message
     *            显示的提示内容
     */
    public void showWarnMessage(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='warnTip';" + "div.style.height='61px';" +
            "div.style.width='131px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-warn.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('warnTip'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    public void showBigContext(String message) {
        System.out.println("message:" + message);
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode = " var div = document.createElement('div');\n" +
            "		       div.id='bigInfoTip';\n" +
            "                  div.style.height='300px';\n" +
            "                  div.style.width='500px';\n" +
            "		       div.style.background='#fff';\n" +
            "		       div.style.position='absolute';\n" +
            "                  div.style.bottom='200px';  \n" +
            "                  div.style.overflow='auto';  \n" +
            "		       div.style.margin='0 0 0 -65px'; \n" +
            "		       div.style.left='40%'; \n" +
            "                  div.style.border='5px solid #dfdfdf';  \n" +
            "		       div.style.zIndex='9999';  \n" +
            "		       document.body.appendChild(div); \n" +
            "		       var span = document.createElement('span'); \n" +
            "                  span.innerHTML='" + message + "';  \n" +
            "		       span.style.position='relative';  \n" +
            "		       span.style.left='17px'; \n" +
            "                  span.style.top='13px';  \n" +
            "		       span.style.fontSize='12px';  \n" +
            "		       span.style.color='#666666'; \n" +
            "		       var close = document.createElement('close');\n" +
            "		       close.style.position = 'absolute';\n" +
            "		       close.style.right ='1px';\n" +
            "		       close.style.top = '1px';\n" +
            "		       close.innerHTML = '<a style=\"text-decoration:none;\" href=\"javascript:document.body.removeChild(document.getElementById(\\'bigInfoTip\\'));\">×</a>';\n" +
            "		       div.appendChild(span); \n" +
            "		       div.appendChild(close);";
        System.out.println(myJavaScriptCode);
        erks.addScript(fctx, myJavaScriptCode);
    }

    /**
     * 执行js弹出提示
     *
     * @param message
     *            显示的提示内容
     */
    public void showErrorMessage(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='errorTip';" + "div.style.height='61px';" +
            "div.style.width='131px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-no.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('errorTip'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    /**URL  页面
     * */
    public void showErrorMessageOpenURL(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='errorOpenTip';" + "div.style.height='61px';" +
            "div.style.width='131px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../../../resources/images/pop-no.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('errorOpenTip'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    public void showErrorMessageLongAutoOpenURL(String message, int lenght) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='errorLongTip';" + "div.style.height='61px';" +
            "div.style.width='" + lenght + "px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../../../resources/images/pop-no.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.right='15px';" + "span.style.top='13px';" + "span.style.fontSize='14px';" +
            "span.style.color='#666666';" + "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('errorLongTip'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    public void showErrorMessageLongAuto(String message, int lenght) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='autoTip';" + "div.style.height='61px';" +
            "div.style.width='" + lenght + "px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-no.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.right='15px';" + "span.style.top='13px';" + "span.style.fontSize='14px';" +
            "span.style.color='#666666';" + "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('autoTip'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }


    public void showTip(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var style = document.createElement('style');" + "style.setAttribute('type','text/css');" +
            "style.innerHTML='.alert{border:5px solid #ccc;border-radius: 4px;width: 200px;}\n" +
            ".alert h3{border-bottom: 1px solid #ebebeb;background: url(\"dialog-head-bg.png\");padding-left: 15px;height:24px;line-height:26px;margin: 0;font-size: 12px;color: black;}\n" +
            ".alert button{background:#fbfbfb;color:#555555;border:1px solid #c3c3c3;border-radius:2px;width:70px;height:25px;margin-left: 120px;\n" +
            "margin-bottom: 10px;cursor: pointer;}\n" +
            ".alert button:hover{background:#ff7d0b;color:white;border-color:#fa8600;}\n" +
            ".alert p{padding: 0px 10px;font-size: 14px;}';" + "var div = document.createElement('div');" +
            "div.className='alert';" + "div.id= 'divtip';" + "var h3 = ";

        erks.addScript(fctx, myJavaScriptCode);

    }

    public Date convertOraceDateToUtilDate(oracle.jbo.domain.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date returnDate = null;
        try {
            returnDate = sdf.parse(sdf.format(date.dateValue()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    /**
     * 刷新页面UI组件
     *
     * @param component
     */
    public void refreshUIComponent(UIComponent component) {
        AdfFacesContext.getCurrentInstance().addPartialTarget(component);
    }

    public boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

    public String nvl(String argIn, String argOut) {
        return null == argIn ? argOut : argIn;
    }

    public String calculateWordNum(String content, String displayTitle) {
        String result = null;
        if (!this.isEmpty(content)) {
            content = content.replaceAll("[^\\x00-\\xff]", "**");
            if (content.length() > 4000) {
                Double count = Math.ceil(((content.length() - 4000)) / 2.0);
                int overCount = count.intValue();
                result =
                        "<font style = 'color:red;font-size:12px;'><strong>" + displayTitle + "</strong>超过了规定的字数,超出" + overCount +
                        "字！</font></br>";
            }
        }
        return result;
    }

    public Object invokeMethodExpression(String expr, Class returnType, Class[] argTypes, Object[] args) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elctx = fc.getELContext();
        ExpressionFactory elFactory = fc.getApplication().getExpressionFactory();
        MethodExpression methodExpr = elFactory.createMethodExpression(elctx, expr, returnType, argTypes);
        return methodExpr.invoke(elctx, args);
    }

    public Object invokeMethodExpression(String expr, Class returnType, Class argType, Object argument) {
        return invokeMethodExpression(expr, returnType, new Class[] { argType }, new Object[] { argument });
    }

    /**
     * 以浏览器新窗口的形式打开taskflow
     *
     * @param taskflowId
     *            taskflow id, 例如view-appraisal-taskflow
     * @param taskflowDocument
     *            taskflow文档的路径,
     *            例如/WEB-INF/performance/appraisal/view-appraisal-taskflow.xml
     * @param paramMap
     *            taskflow参数map
     */
    public void launchTaskflow(String taskflowId, String taskflowDocument, Map paramMap) {
        String taskflowURL =
            ControllerContext.getInstance().getTaskFlowURL(false, new TaskFlowId(taskflowDocument, taskflowId),
                                                           paramMap);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\"").append(",\"\",\"location=0,scrollbars=1,resizable=1\");");
        this.appendScript(script.toString());
    }

    public void openWindow(String url) {
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + url +
                      "\"").append(",\"\",\"location=0,scrollbars=1,resizable=1,width=1050,height=650\");");
        this.appendScript(script.toString());
    }

    public void openWindow(String url, Integer width, Integer height) {
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + url + "\"").append(",\"\",\"location=0,scrollbars=1,resizable=1,width=" +
                                                            width + ",height=" + height + "\");");
        this.appendScript(script.toString());
    }

    /**
     * 关闭当前窗口
     *
     * @return
     */
    public String closeWindow() {
        StringBuffer script = new StringBuffer();
        script.append("window.close()");
        appendScript(script.toString());
        return null;
    }

    public void appendScript(String script) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fc, ExtendedRenderKitService.class);
        erks.addScript(fc, script);
    }

    public void showNotification(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getService(context.getRenderKit(), ExtendedRenderKitService.class);
        erks.addScript(context, "showNotificationCallback('" + message + "')");
    }

    public void showExtenalWindow(String url, Integer width, Integer height, HashMap param) {
        FacesContext context = FacesContext.getCurrentInstance();
        ViewHandler viewh = context.getApplication().getViewHandler();
        UIViewRoot dialog = viewh.createView(context, url);
        HashMap h = new HashMap();
        h.put("width", width == null ? new Integer(1050) : width);
        h.put("height", height == null ? new Integer(600) : height);
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.launchDialog(dialog, param, null, true, h);
    }

    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        return session;
    }

    public void reFindCategory() {
        String libCode4Filter = CommonUtil.getLibCode();
        String libTypeCode = CommonUtil.getLibTypeCode();
        //当进入教案库或者课件库时，使用图书库的分类树
        if ("TEACHING_PLAN".equals(CommonUtil.getLibCode()) || "COURSEWARE".equals(CommonUtil.getLibCode()) ||
            "ILLUSTRATION".equals(CommonUtil.getLibCode())) {
            libCode4Filter = "BOOK";
        }
        if ("ILLUSTRATION".equals(CommonUtil.getLibCode())) {
            libTypeCode = "END_PROD_LIB";
        }
        CommonUtil.getWorkAM().findCategory(CommonUtil.getCompCode(), CommonUtil.getLibCode(), libCode4Filter,
                                            libTypeCode);
    }

    public void putFileIntoZipEntry(ZipOutputStream out, String tempPath,
                                    String savePath) throws FileNotFoundException, IOException {
        FileInputStream fi = new FileInputStream(tempPath);
        ZipEntry entry = new ZipEntry(savePath);
        out.putNextEntry(entry);
        byte[] bytes = new byte[1024 * 512];
        int len = 0;
        while ((len = fi.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.closeEntry();
        fi.close();
    }

    public void writeStringToFile(String content, String filePath) {
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(filePath);
            byte[] bytes = content.getBytes("UTF-8");
            fo.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.closeOutputStream(fo);
        }
    }

    /**
     * 重置Popup内的数据
     * @param comp
     */
    public static void resetPopupData(UIComponent comp) {
        if (comp instanceof UIXEditableValue) {
            UIXEditableValue uc = (UIXEditableValue)comp;
            uc.resetValue();
        }
        if (comp.getChildren() != null && comp.getChildren().size() > 0) {
            for (UIComponent childComp : comp.getChildren()) {
                resetPopupData(childComp);
            }
        }
        if (comp.getFacetCount() > 0) {
            Map<String, UIComponent> map = comp.getFacets();
            Collection<UIComponent> comps = map.values();
            if (comps != null) {
                for (UIComponent factcomp : comps) {
                    resetPopupData(factcomp);
                }
            }
        }
    }

    public void executeRegionAction(String btnId) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        RichCommandButton button = (RichCommandButton)root.findComponent(btnId);
        ActionEvent actionEvent = new ActionEvent(button);
        actionEvent.queue();
    }
}

