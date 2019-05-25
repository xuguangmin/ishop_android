package com.yocai.mobileAPP.activity.me;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yocai.mobileAPP.R;
import com.yocai.mobileAPP.activity.BaseActivity;
import com.yocai.mobileAPP.config.ConfigValue;
import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.presenter.ChangeUserInfoPresenter;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.ChangeUserInfoView;
import com.yocai.shopping.uilibrary.ClearEditText;
import com.umeng.socialize.facebook.controller.utils.ToastUtil;

/**
 * Created by sks on 2015/9/21.
 */
public class NicknameActivity extends BaseActivity implements View.OnClickListener, ChangeUserInfoView {
    private ClearEditText txtNickname;
    private ChangeUserInfoPresenter changeUserInfoPresenter;
    private String birthday,newNickName;
    private String sex;

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_nickname);
        birthday = getIntent().getStringExtra("birthday");
        sex = getIntent().getStringExtra("sex");
        changeUserInfoPresenter = new ChangeUserInfoPresenter(this);
        txtNickname = (ClearEditText) findViewById(R.id.txtNickname);
    }

    @Override
    public void initTitle() {
        findViewById(R.id.fl_Left).setOnClickListener(this);
        ((TextView) findViewById(R.id.title)).setText("昵称");
        ((TextView) findViewById(R.id.ensure)).setText("保存");
        (findViewById(R.id.fl_right)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_Left:
                Utils.finishActivityWithAnimation(this);
                break;
            case R.id.fl_right:
                newNickName = txtNickname.getText().toString().trim();
                if (TextUtils.isEmpty(newNickName)) {
                    ToastUtil.showToast(this, "请输入昵称后保存！");
                    return;
                }
                changeUserInfoPresenter.getUserInfo(this, sex, birthday,newNickName);
                break;
        }
    }

    @Override
    public void result(BaseModel model) {
        if (null != model && Integer.parseInt(model.getCode()) > 0) {
            if (model.getCode().equals("1")) {
                ConfigValue.uInfor.setNick_name(newNickName);
                Utils.finishActivityWithAnimation(this);
            }
            Utils.showToast(this, model.getMsg());
        } else
            Utils.showToast(this, "提交失败");
    }
}
