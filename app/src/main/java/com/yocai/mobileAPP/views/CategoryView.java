package com.yocai.mobileAPP.views;

import android.support.annotation.Nullable;

import com.yocai.mobileAPP.model.CategoryInfoModel;

/**
 * 分类 V layer
 */
public interface CategoryView {


    void onOneLevelData(CategoryInfoModel data , @Nullable Exception e);

    void onTwoLevelData(CategoryInfoModel data,@Nullable Exception e);


}
