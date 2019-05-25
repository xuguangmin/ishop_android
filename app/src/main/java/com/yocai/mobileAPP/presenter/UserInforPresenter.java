package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.UserInforModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.UserInforView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by sks on 2015/9/29.
 * 用户信息网络请求
 */
public class UserInforPresenter extends BasePresenter {
    private UserInforView userInforView;
    public UserInforPresenter(UserInforView userInforView){
        this.userInforView = userInforView;
    }
    public void loadInfor(final Context context){
        Map<String,String> params = getDefaultMD5Params("user","infomation");
        params.put("key", ConfigValue.DATA_KEY);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "user/userinfo", params,
                new BaseDelegate.ResultCallback<UserInforModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        Utils.showToast(context, ExceptionHelper.getMessage(e,context));
                    }

                    @Override
                    public void onResponse(UserInforModel response, Object tag) {
                        userInforView.inforData(response);
                    }
                },true);
    }
}
