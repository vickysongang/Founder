package com.zypg.cms.work.model.am.common;

import oracle.jbo.ApplicationModule;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 06 15:52:09 CST 2014
// ---------------------------------------------------------------------
public interface AudioAM extends ApplicationModule {


    oracle.jbo.domain.Number preEditAudio(String mode, oracle.jbo.domain.Number docId, String compCode);

    oracle.jbo.domain.Number preScriptAudio(oracle.jbo.domain.Number docId);

}
