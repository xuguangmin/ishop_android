package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.NewAddressView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by sks on 2015/9/30.
 * 提交新地址
 */
public class NewAddressPresenter extends BasePresenter {
    private NewAddressView newAddressView;

    public NewAddressPresenter(NewAddressView newAddressView){
        this.newAddressView = newAddressView;
    }

    public void setData(final Context context,String username,String address,
                        String number,String provence,String city,
                        String district,String type){
        initLoadDialog(context);
        mLoadingDialog.show();
        Map<String, String > params = getDefaultMD5Params("goods","address");
        params.put("key", ConfigValue.DATA_KEY);
        params.put("city",city);
        params.put("province",provence);
        params.put("username",username);
        params.put("address_p",address);
        params.put("telnumber",number);
        params.put("address_id",type);
        params.put("district",district);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "goods/address",
                params, new BaseDelegate.ResultCallback<BaseModel>() {
            @Override
            public void onError(Request request, Object tag, Exception e) {
                mLoadingDialog.dismiss();
                Utils.showToast(context, ExceptionHelper.getMessage(e, context));
            }

            @Override
            public void onResponse(BaseModel response, Object tag) {
                mLoadingDialog.dismiss();
                newAddressView.getData(response);
            }
        },true);
    }
}
