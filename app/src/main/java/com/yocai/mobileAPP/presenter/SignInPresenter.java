package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.ExceptionHelper;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.model.SignInModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.SignInView;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by Su on 2016/6/21.
 */
public class SignInPresenter extends BasePresenter {

    private SignInView mSignInView;

    public SignInPresenter(SignInView signInView) {
        mSignInView = signInView;
    }

    //签到接口，用参数key
    public void signIn(final Context context) {
        initLoadDialog(context);
        mLoadingDialog.show();
        Map<String, String> params = getDefaultMD5Params("attendance", "qiandao");
        params.put("key", ConfigValue.DATA_KEY);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "attendance/qiandao",
                params, new BaseDelegate.ResultCallback<BaseModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        mLoadingDialog.dismiss();
                        mSignInView.onFailure(e);
                        Utils.showToast(context, ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(BaseModel response, Object tag) {
                        mLoadingDialog.dismiss();
                        mSignInView.onSuccess(response);
                    }
                }, true);
    }
    //获得积分列表
    public void getSignInRecordList(final Context context){

        Map<String, String> params = getDefaultMD5Params("attendance", "index");
        params.put("key", ConfigValue.DATA_KEY);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "attendance/index",
                params, new BaseDelegate.ResultCallback<SignInModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        mSignInView.onFailure(e);
                        Utils.showToast(context, ExceptionHelper.getMessage(e, context));
                    }

                    @Override
                    public void onResponse(SignInModel response, Object tag) {
                        mSignInView.onSignInRecordResponse(response);
                    }
                }, true);

    }
}
