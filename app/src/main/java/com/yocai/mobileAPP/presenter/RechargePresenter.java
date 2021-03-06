package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.PayModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.PayView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by sks on 2015/10/28.
 * 充值请求
 */
public class RechargePresenter extends BasePresenter {

    private PayView payView;

    public RechargePresenter(PayView payView) {
        this.payView = payView;
    }

    //支付宝支付
    public void requestAliPay(final Context context, String money, int type) {
        initLoadDialog(context);
        mLoadingDialog.show();
        Map<String, String> params = getDefaultMD5Params("user", "recharge");
        params.put("key", ConfigValue.DATA_KEY);
        params.put("price", money);
        params.put("type",type+"");
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "user/recharge",
                params, new BaseDelegate.ResultCallback<PayModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        mLoadingDialog.dismiss();
                        Utils.showToast(context, ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(PayModel response, Object tag) {
                        mLoadingDialog.dismiss();
                        payView.aliPayResponse(response);
                    }
                }, true);
    }

    //微信支付
    public void requestWXPay(final Context context, String money, int type) {
        initLoadDialog(context);
        mLoadingDialog.show();
        Map<String, String> params = getDefaultMD5Params("user", "recharge");
        params.put("key", ConfigValue.DATA_KEY);
        params.put("price", money);
        params.put("type",type+"");
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "user/recharge",
                params, new BaseDelegate.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        mLoadingDialog.dismiss();
                        payView.wxPayError(request,tag,e);
                        Utils.showToast(context, ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(String response, Object tag) {
                        mLoadingDialog.dismiss();
                        payView.wxPayResponse(response);
                    }
                }, true);

    }
}
