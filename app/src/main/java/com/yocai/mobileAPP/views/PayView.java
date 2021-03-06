package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.PayModel;
import com.squareup.okhttp.Request;

/**
 * Created by sks on 2015/10/22.
 */
public interface PayView {

    void aliPayResponse(PayModel model);

    void wxPayResponse(String response);

    void wxPayError(Request request, Object tag, Exception e);
}
