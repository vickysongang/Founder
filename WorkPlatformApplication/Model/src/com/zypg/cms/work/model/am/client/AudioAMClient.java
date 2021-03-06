package com.zypg.cms.work.model.am.client;

import com.zypg.cms.work.model.am.common.AudioAM;
import oracle.jbo.client.remote.ApplicationModuleImpl;

import oracle.stellent.ridc.model.DataObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 06 15:52:09 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AudioAMClient extends ApplicationModuleImpl implements AudioAM {
    /**
     * This is the default constructor (do not remove).
     */
    public AudioAMClient() {
    }


    public void insertDocUcmRel(DataObject data, oracle.jbo.domain.Number newDocId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"insertDocUcmRel",new String [] {"oracle.stellent.ridc.model.DataObject","oracle.jbo.domain.Number"},new Object[] {data, newDocId});
        return;
    }


    public oracle.jbo.domain.Number preEditAudio(String mode, oracle.jbo.domain.Number docId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"preEditAudio",new String [] {"java.lang.String","oracle.jbo.domain.Number"},new Object[] {mode, docId});
        return (oracle.jbo.domain.Number)_ret;
    }

    public oracle.jbo.domain.Number preScriptAudio(oracle.jbo.domain.Number docId) {
        Object _ret = this.riInvokeExportedMethod(this,"preScriptAudio",new String [] {"oracle.jbo.domain.Number"},new Object[] {docId});
        return (oracle.jbo.domain.Number)_ret;
    }

    public oracle.jbo.domain.Number preEditAudio(String mode, oracle.jbo.domain.Number docId, String compCode) {
        Object _ret =
            this.riInvokeExportedMethod(this,"preEditAudio",new String [] {"java.lang.String","oracle.jbo.domain.Number","java.lang.String"},new Object[] {mode, docId, compCode});
        return (oracle.jbo.domain.Number)_ret;
    }
}
