package com.yocai.mobileAPP.activity.me;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yocai.mobileAPP.R;
import com.yocai.mobileAPP.activity.BaseActivity;
import com.yocai.mobileAPP.model.BaseModel;
import com.yocai.mobileAPP.presenter.SendCodePresenter;
import com.yocai.mobileAPP.utils.Utils;
import com.yocai.mobileAPP.views.SendCodeView;
import com.umeng.socialize.facebook.controller.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sks on 2015/9/17.
 * 注册
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, SendCodeView {
    private EditText mEditTextUserName, mEditTextVerifyCode;
    private TextView mTextViewGetVerifyCode;
    private SendCodePresenter mSendCodePresenter;

    private String mUserName;
    private String mVerifyCode;

    private Timer mTimer;
    private TimerTask mTimerTask;
    private int OVER_SECONDS = 60;//60秒过期时间


    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        mSendCodePresenter = new SendCodePresenter(this);
        findViewById(R.id.mImageViewCancel).setOnClickListener(this);

        mTextViewGetVerifyCode = (TextView) findViewById(R.id.mTextViewGetVerifyCode);
        mTextViewGetVerifyCode.setOnClickListener(this);

        mEditTextUserName = (EditText) findViewById(R.id.mEditTextUserName);
        mEditTextVerifyCode = (EditText) findViewById(R.id.mEditTextVerifyCode);

        findViewById(R.id.mTextViewNext).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mImageViewCancel:
                Utils.finishActivityWithAnimation(this);
                break;
            case R.id.mTextViewGetVerifyCode:
                mUserName = mEditTextUserName.getText().toString().trim();
                if (TextUtils.isEmpty(mUserName)) {
                    ToastUtil.showToast(this, "请输入手机号！");
                    return;
                }
                // ToastUtil.showToast(this, mUserName + "验证用户名并获取验证码");
                mSendCodePresenter.delCode(this, mUserName);
                //leftSeconds();
                break;
            case R.id.mTextViewNext:

                //拿到用户输入的验证码，跟用户名一起验证是否存在，存在则进入下一步
                mVerifyCode = mEditTextVerifyCode.getText().toString().trim();

                mUserName = mEditTextUserName.getText().toString().trim();
                if (TextUtils.isEmpty(mUserName)) {
                    ToastUtil.showToast(this, "请输入手机号！");
                    return;
                }

                if (TextUtils.isEmpty(mVerifyCode)) {
                    ToastUtil.showToast(this, "验证码为空!");
                    return;
                }

                // mRegisterPresenter.checkCode(this, 0, mVerifyCode, mUserName);
                mSendCodePresenter.checkCode(this, mVerifyCode, mUserName);

                break;
        }

    }

    //倒计时
    private void leftSeconds() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewGetVerifyCode.setText(OVER_SECONDS + "S");
                        if (OVER_SECONDS == 0) {
                            mTimer.cancel();
                            mTimerTask.cancel();
                            mTextViewGetVerifyCode.setText("获取");
                            OVER_SECONDS = 60;
                        } else
                            OVER_SECONDS--;
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null && mTimerTask != null) {
            mTimer.cancel();
            mTimerTask.cancel();
        }
    }

    @Override
    public void checkCode(BaseModel baseModel) {
        if (baseModel.getCode().equals("1")) {
            //ToastUtil.showToast(this, "拿到用户输入的验证码，跟用户名一起验证是否存在，存在则进入下一步");
            //请求验证方法

            Intent intent = new Intent(this, FinishRegisterActivity.class);
            intent.putExtra("username", mUserName);
            intent.putExtra("code", mVerifyCode);
            Utils.startActivityWithAnimation(this, intent);
            //下一步之后当前页面finish
            //Utils.finishActivityWithAnimation(this);
            finish();
        } else
            ToastUtil.showToast(this, baseModel.getMsg());
    }

    @Override
    public void delCode(BaseModel model) {
        mSendCodePresenter.setSendCodeView(this, 0, mUserName);
    }

    @Override
    public void getCode(BaseModel model) {
        if (model.getCode().equals("1")) {
            leftSeconds();
        } else
            ToastUtil.showToast(this, model.getMsg());

    }

}
