package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.model.CollectGoodsModel;
import com.yocai.mobileAPP.model.CollectShopModel;

/**
 * Created by Su on 2015/10/20.
 */
public interface CollectGoodsView {
    void getCollectList(CollectGoodsModel collectGoodsModel);
    void getCollectShopList(CollectShopModel collectShopModel);
    void delCollectList(BaseModel baseModel);
}
