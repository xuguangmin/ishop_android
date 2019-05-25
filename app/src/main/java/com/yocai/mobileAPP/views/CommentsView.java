package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.PersonalCommentsModel;

/**
 * Created by Su on 2016/5/4.
 */
public interface CommentsView {
    void onResponse(PersonalCommentsModel model);

    void onError(String s);
}
