package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.OrderModelInfo;

/**
 * Created by sks on 2015/9/29.
 */
public interface MyOrderView {
    void error();
    void orderList(OrderModelInfo model);
}
