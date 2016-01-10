package com.zypg.cms.work.view.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;

public class IECompatibilityPhaseListener implements PhaseListener {
    @SuppressWarnings("compatibility:2058151076165347740")
    private static final long serialVersionUID = 1L;
    private final String BROWSER_CHECK_KEY = "____$browser-compatibility-checked-key$____";
    private final String IS_IE9_CHECK_KEY = "____$IS-IE9-check-key$____";

    public IECompatibilityPhaseListener() {
        super();
    }

    public void afterPhase(PhaseEvent phaseEvent) {
        //check if browser is IE 9 in RestoreView phase if this
        //hasn’t been checked before
        if (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW && !this.isBrowserChecked()) {
            //which browser does the user use
            RequestContext trinidadContext = RequestContext.getCurrentInstance();
            Agent agent = trinidadContext.getAgent();
            String browserName = agent.getAgentName();
            String browserVersion = agent.getAgentVersion();
            //is it IE
            if (browserName.toLowerCase().indexOf("ie") > -1) {
                if (browserVersion.equalsIgnoreCase("9.0")) {
                    this.setIsIE9(true);
                } else {
                    this.setIsIE9(false);
                }
            } else {
                this.setIsIE9(false);
            }
            //browser has been checked
            this.setBrowserChecked(true);
        }
    }

    public void beforePhase(PhaseEvent phaseEvent) {
        //check render response
        if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE && this.isIsIE9()) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = fctx.getExternalContext();
            HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
            response.addHeader("X-UA-Compatible", "IE=8");
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private void setIsIE9(boolean isIE9) {
        setBooleanSessionKeyValue(IS_IE9_CHECK_KEY, isIE9);
    }

    private boolean isIsIE9() {
        return getBooleanSessionKeyValue(IS_IE9_CHECK_KEY);
    }

    private void setBrowserChecked(boolean browserChecked) {
        setBooleanSessionKeyValue(BROWSER_CHECK_KEY, new Boolean(browserChecked));
    }

    private boolean isBrowserChecked() {
        return getBooleanSessionKeyValue(BROWSER_CHECK_KEY);
    }

    private boolean getBooleanSessionKeyValue(String _key) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        //get user session
        HttpSession userSession = (HttpSession)ectx.getSession(true);
        Object browserCheckObject = userSession.getAttribute(_key);
        if (browserCheckObject == null) {
            return false;
        } else {
            return ((Boolean)browserCheckObject).booleanValue();
        }
    }

    private void setBooleanSessionKeyValue(String _key, Object _value) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        //get user session
        HttpSession userSession = (HttpSession)ectx.getSession(true);
        userSession.setAttribute(_key, _value);
    }
}
