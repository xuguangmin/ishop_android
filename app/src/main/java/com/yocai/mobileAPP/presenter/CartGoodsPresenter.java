package com.yocai.mobileAPP.presenter;

import android.content.Context;
import android.util.Log;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.config.SPConfig;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.model.ShopCartModel;
import com.yocai.mobileAPP.utils.SPUtils;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.CartGoodsView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by Su on 2015/10/9.
 * 获取购物车商品列表
 */
public class CartGoodsPresenter extends BasePresenter {
    private CartGoodsView cartGoodsView;

    public CartGoodsPresenter(CartGoodsView cartGoodsView) {
        this.cartGoodsView = cartGoodsView;
    }

    /**
     * 获取购物车列表
     *
     * @param context
     */
    public void getCartGoodsData(final Context context) {
        Map<String, String> params = getDefaultMD5Params("goods", "cartlist");
        String key = (String) SPUtils.get(context, "key", "");
        params.put(SPConfig.KEY, key);

        OkHttpClientManager.postAsyn(context, ConfigValue.CartGoodsList, params,
                new BaseDelegate.ResultCallback<ShopCartModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, "alterCartGoodsNumberError" + e);
                    }

                    @Override
                    public void onResponse(ShopCartModel response, Object tag) {
                        cartGoodsView.onShopCartList(response);
                    }
                });

    }

    /**
     * 修改商品数量
     *
     * @param context
     * @param recId       购物车商品唯一标识
     * @param goodsNumber 商品数量
     */
    public void alterCartGoodsNumber(final Context context, String recId, int goodsNumber) {
        Map<String, String> params = getDefaultMD5Params("goods", "charnum");
        String key = (String) SPUtils.get(context, "key", "");
        params.put(SPConfig.KEY, key);
        params.put("rec_id", recId);
        params.put("num", String.valueOf(goodsNumber));

        OkHttpClientManager.postAsyn(context, ConfigValue.AlterGoodsNumber, params,
                new BaseDelegate.ResultCallback<BaseModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, "alterCartGoodsNumberError" + e);
                    }

                    @Override
                    public void onResponse(BaseModel response, Object tag) {
                        Log.d("altergoodsnumber", response.toString());
                        cartGoodsView.alterCartGoodsNumber(response);
                    }
                });

    }

    /**
     * 购物车中删除商品
     *
     * @param context
     * @param recId
     */
    public void deleteCartGoods(final Context context, String recId) {
        Map<String, String> params = getDefaultMD5Params("goods", "delcart");
        String key = (String) SPUtils.get(context, "key", "");
        params.put(SPConfig.KEY, key);
        params.put("rec_id", recId);

        OkHttpClientManager.postAsyn(context, ConfigValue.DeleteCartGoods, params,
                new BaseDelegate.ResultCallback<BaseModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, "deleteCartGoodsNumberError" + ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(BaseModel response, Object tag) {
                        Log.d("deleteCartGoods", response.toString());
                        cartGoodsView.deleteCartGoods(response);
                    }
                });
    }


}
