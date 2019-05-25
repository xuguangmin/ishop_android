package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.model.ShopCartModel;

/**
 * Created by Pisces on 2015/10/9.
 */
public interface CartGoodsView {
    void onShopCartList(ShopCartModel shopCartModel);

    void alterCartGoodsNumber(BaseModel baseModel);

    void deleteCartGoods(BaseModel baseModel);
}
