package com.zypg.cms.work.model.am.client;

import com.zypg.cms.work.model.am.common.IllustrationAM;

import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun May 31 23:51:44 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class IllustrationAMClient extends ApplicationModuleImpl implements IllustrationAM {
    /**
     * This is the default constructor (do not remove).
     */
    public IllustrationAMClient() {
    }

    public void preDocCategroupRel(oracle.jbo.domain.Number docId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"preDocCategroupRel",new String [] {"oracle.jbo.domain.Number"},new Object[] {docId});
        return;
    }
}