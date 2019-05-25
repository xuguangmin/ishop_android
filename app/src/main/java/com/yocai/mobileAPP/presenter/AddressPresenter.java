package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.AddressModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.AddressView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by sks on 2015/9/24.
 * 地址列表网络请求
 */
public class AddressPresenter extends BasePresenter {
    private AddressView addressView;
    public AddressPresenter(AddressView addressView){
        this.addressView = addressView;
    }
    public void setAddressData(final Context context){
        Map<String, String> params = getDefaultMD5Params("goods", "addresslist");
        params.put("key", ConfigValue.DATA_KEY);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "goods/addresslist",
                params, new BaseDelegate.ResultCallback<AddressModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(AddressModel response, Object tag) {
                        addressView.getAddressList(response);
                    }
                },true);
    }
}
