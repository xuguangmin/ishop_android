package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.model.ReturnGoodsModel;

/**
 * Created by Su on 2016/5/10.
 */
public interface ReturnView {
    void onError(String msg);

    void onApplyResponse(BaseModel response);

    void onGetResponse(ReturnGoodsModel returnGoodsModel);

}
