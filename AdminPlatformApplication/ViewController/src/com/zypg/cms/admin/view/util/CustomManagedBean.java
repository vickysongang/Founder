package com.zypg.cms.admin.view.util;

import com.honythink.applicationframework.hadf.util.ADFUtils;

import com.zypg.cms.admin.model.AdminAMImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowId;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.ControlBinding;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

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
            "var div = document.createElement('div');" + "div.id='test';" + "div.style.height='61px';" +
            "div.style.width='131px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-yes.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('test'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }

    public void showLongMessage(String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='test';" + "div.style.height='61px';" +
            "div.style.width='149px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-yes.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.right='15px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('test'));" + "},3000);";
        erks.addScript(fctx, myJavaScriptCode);
    }
    
    public void showAutoErrorMessage(String message,int lenght) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String myJavaScriptCode =
            "var div = document.createElement('div');" + "div.id='test';" + "div.style.height='61px';" +
            "div.style.width='"+lenght+"px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-no.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.right='15px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('test'));" + "},3000);";
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
            "var div = document.createElement('div');" + "div.id='test';" + "div.style.height='61px';" +
            "div.style.width='131px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-warn.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('test'));" + "},3000);";
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
            "var div = document.createElement('div');" + "div.id='test';" + "div.style.height='61px';" +
            "div.style.width='131px';" + "div.style.background='#fff';" + "div.style.position='absolute';" +
            "div.style.bottom='100px';" + "div.style.margin='0 0 0 -65px';" + "div.style.left='50%';" +
            "div.style.border='5px solid #dfdfdf';" + "div.style.zIndex='9999';" + "document.body.appendChild(div);" +
            "var img = document.createElement('img');" + "img.src='../resources/images/pop-no.png';" +
            "img.style.position='relative';" + "img.style.left='16px';" + "img.style.top='20px';" +
            "img.style.width='24px';" + "img.style.height='24px';" + "var span = document.createElement('span');" +
            "span.innerHTML='" + message + "';" + "span.style.position='relative';" + "span.style.left='17px';" +
            "span.style.top='13px';" + "span.style.fontSize='14px';" + "span.style.color='#666666';" +
            "div.appendChild(img);" + "div.appendChild(span);" +
            "setTimeout(function() {document.body.removeChild(document.getElementById('test'));" + "},3000);";
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

    public AdminAMImpl getAdminAM() {
        return (AdminAMImpl)ADFUtils.getAM("AdminAMDataControl", null);
    }

    public boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            String str = (String)o;
            str = str.trim();
            if (str.length() == 0) {
                return true;
            }
        }
        return false;
    }
}
