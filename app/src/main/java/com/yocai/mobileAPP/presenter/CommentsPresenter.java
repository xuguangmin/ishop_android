package com.yocai.mobileAPP.presenter;

import android.content.Context;

import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.http.BaseDelegate;
import com.yocai.mobileAPP.http.OkHttpClientManager;
import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.model.PersonalCommentsModel;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.CommentsView;
import com.squareup.okhttp.Request;
import com.umeng.socialize.facebook.controller.utils.ToastUtil;

import java.util.Map;

/**
 * Created by Su on 2016/5/4.
 */
public class CommentsPresenter extends BasePresenter {
    private CommentsView mCommentsView;

    public CommentsPresenter(CommentsView commentsView) {
        this.mCommentsView = commentsView;
    }

    public CommentsPresenter() {
    }

    public void getCommentsList(final Context context, int type) {
        Map<String, String> params = getDefaultMD5Params("user", "contents");
        params.put("key", ConfigValue.DATA_KEY);
        params.put("type", type + "");
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "user/contents",
                params, new BaseDelegate.ResultCallback<PersonalCommentsModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        mCommentsView.onError(e.toString());
                    }

                    @Override
                    public void onResponse(PersonalCommentsModel response, Object tag) {
                        mCommentsView.onResponse(response);
                    }
                }, true);
    }

    public void submitComment(final Context context, String order_id, String goods_info) {
        initLoadDialog(context);
        mLoadingDialog.show();
        Map<String, String> params = getDefaultMD5Params("integral", "convert");
        params.put("key", ConfigValue.DATA_KEY);
        params.put("order_id", order_id);
        params.put("goods_info", goods_info);
        OkHttpClientManager.postAsyn(context, ConfigValue.APP_IP + "integral/convert", params,
                new BaseDelegate.ResultCallback<BaseModel>() {
                    @Override
                    public void onError(Request request, Object tag, Exception e) {
                        mLoadingDialog.dismiss();
                        ToastUtil.showToast(context, e.getMessage().toString());
                    }

                    @Override
                    public void onResponse(BaseModel response, Object tag) {
                        mLoadingDialog.dismiss();
                        ToastUtil.showToast(context, response.getMsg());
                        if (response.getCode().equals("1")) {
                            Utils.finishActivityWithAnimation(context);
                        }
                    }
                });

    }
}
