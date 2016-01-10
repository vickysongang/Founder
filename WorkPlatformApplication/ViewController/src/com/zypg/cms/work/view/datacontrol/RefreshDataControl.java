package com.zypg.cms.work.view.datacontrol;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import oracle.adf.view.rich.context.AdfFacesContext;

public class RefreshDataControl {
    public RefreshDataControl() {
        super();
    }

    /**
     * 刷新jsff页面 方法 传入类名和方法名
     * @param className
     * @param methodName
     */
    public void refresh(String className, String methodName) {
        if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            Class classType = null;
            try {
                classType = Class.forName(className);
                Method method = classType.getMethod(methodName, null);
                method.invoke(classType.newInstance(), null);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
