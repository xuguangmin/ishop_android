package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.config.SPConfig;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.ShopsInfoModel;
import com.yocai.mobileAPP.utils.SPUtils;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.ShopsFilterView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * 搜索店铺
 * Created by Su on 2016/4/14.
 */
public class ShopsFilterPresenter extends BasePresenter {
    private ShopsFilterView shopsFilterView;

    public ShopsFilterPresenter(ShopsFilterView shopsFilterView) {
        this.shopsFilterView = shopsFilterView;
    }

    /**
     * 索索店铺信息
     *
     * @param context  上下文信息
     * @param page     请求页码
     * @param keyword 请求关键字
     */
    public void getShopsFilterData(final Context context, String page, String keyword) {
        Map<String, String> params = getDefaultMD5Params("First", "shop_sear");
        String key = (String) SPUtils.get(context, "key", "");
        params.put(SPConfig.KEY, key);
        params.put("page",page);
        params.put("keyword",keyword);
        OkHttpClientManager.postAsyn(context, ConfigValue.SHOPS_FILTER, params,
                new BaseDelegate.ResultCallback<ShopsInfoModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, "getShopsFilterData" + ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(ShopsInfoModel response, Object tag) {
                        shopsFilterView.onResponse(response);
                    }
                   /* @Override
                    public void onError(Request request, Object tag, Exception e) {
                        if (mLoadingDialog.isShowing())
                            mLoadingDialog.dismiss();
                        Utils.showToast(context, "getCartGoodsDataError" + ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onApplyResponse(CartGoodsModel response, Object tag) {
                        if (mLoadingDialog.isShowing())
                            mLoadingDialog.dismiss();
                        cartGoodsView.getCartGoodsList(response);
                    }*/
                });

    }

}
