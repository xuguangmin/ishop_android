package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.AddressInforModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.NewAddressView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by sks on 2015/10/10.
 */
public class AddressInforPresenter extends BasePresenter {

    private NewAddressView newAddressView;
    public AddressInforPresenter(NewAddressView newAddressView){
        this.newAddressView = newAddressView;
    }
    public void loadAddressInfor(final Context context,String id){
        Map<String, String> params = getDefaultMD5Params("goods", "addrdetail");
        params.put("key", ConfigValue.DATA_KEY);
        params.put("address_id",id);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "goods/addrdetail",
                params, new BaseDelegate.ResultCallback<AddressInforModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(AddressInforModel response, Object tag) {
                        newAddressView.getAddressInfor(response);
                    }
                },true);
    }
}
