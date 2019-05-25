package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.LoginModel;

/**
 * Created by Administrator on 2015/8/31.
 */
public interface LoginView {
    void login(LoginModel model);
    void error();
}
