package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.GoodsListFiltrateModel;
import com.yocai.mobileAPP.model.GoodsListModel;

/**
 * 商品筛选 V
 */
public interface GoodsFilterView {

    /**
     * 0 : linear model
     * 1 : gride model
     *//*
    void onLayoutSwitch(int model,int firstPosition);

    void onFilterGoodsData(@Nullable GoodsInfoModel model ,@Nullable Exception e);


    void onDrawerData(@Nullable FilterInfoModel model, @Nullable Exception e);*/

    void onResponse(GoodsListModel goodsListModel);

    void onFiltrateResponse(GoodsListFiltrateModel filtrateModel);

}
