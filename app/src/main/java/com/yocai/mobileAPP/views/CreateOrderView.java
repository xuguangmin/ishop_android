package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.CreateOrderModel;
import com.yocai.mobileAPP.model.SubmitOrderModel;

/**
 * Created by sks on 2015/10/8.
 */
public interface CreateOrderView {
    void getOrderInfor(CreateOrderModel model);
    void result(SubmitOrderModel model);
}
