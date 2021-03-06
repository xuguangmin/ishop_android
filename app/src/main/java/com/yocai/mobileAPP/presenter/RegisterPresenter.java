package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.PayModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.RegisterView;

import java.util.Map;

/**
 * Created by Su on 2016/4/25.
 */
public class RegisterPresenter extends BasePresenter {

    RegisterView mRegisterView;

    public RegisterPresenter(RegisterView registerView) {
        mRegisterView = registerView;
    }


    //提交注册
    public void finishRegister(final Context context, String phone, String passwd,
                               String code) {
        initLoadDialog(context);
        mLoadingDialog.show();
        Map<String, String> map = getDefaultMD5Params("user", "register");
        map.put("phone", phone);
        map.put("passwd", passwd);
        map.put("code", code);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "user/register",
                map, new BaseDelegate.ResultCallback<PayModel>() {
                    @Override
                    public void onError(com.squareup.okhttp.Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e, context));
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(PayModel response, Object tag) {
                        mRegisterView.register(response);
                        mLoadingDialog.dismiss();
                    }
                }, true);
    }

}
