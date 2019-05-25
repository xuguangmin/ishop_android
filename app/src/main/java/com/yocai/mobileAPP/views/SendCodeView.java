package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.BaseModel;

/**
 * Created by sks on 2015/9/29.
 */
public interface SendCodeView {
    void delCode(BaseModel model);
    void getCode(BaseModel model);
    void checkCode(BaseModel model);
}
