package com.efanzyhang.mi.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.efanzyhang.mi.core.fragment.MishopFragment;
import com.efanzyhang.mi.core.net.RestClient;
import com.efanzyhang.mi.core.net.callback.IError;
import com.efanzyhang.mi.core.net.callback.IFailure;
import com.efanzyhang.mi.core.net.callback.ISuccess;
import com.efanzyhang.mi.core.util.log.LUitl;
import com.efanzyhang.mi.core.util.toast.TUtil;
import com.efanzyhang.mi.ec.R;
import com.efanzyhang.mi.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.sign
 * 文件名：SignInDelegate
 * 创建者：efan.zyhang
 * 创建时间：2018/9/14 18:12
 * 描述： TODO
 */
public class SignInDelegate extends MishopFragment {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEdtEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mEdtPassword;

    private ISignListener mISignListener;

    /**
     * SupportFragment中的onAttach
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("user_profile.json")
                    .params("email", mEdtEmail.getText().toString())
                    .params("password", mEdtPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String responds) {
                            LUitl.json("USER_PROFILE", responds);
//                            SignHandler.onSignUp(responds,mISignListener);
                            TUtil.success(getContext(),"登录成功");
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            LUitl.w("USER_PROFILE", "Failure");
                            TUtil.warning(getContext(),"登录失败");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String message) {
                            LUitl.e("USER_PROFILE", message);
                            TUtil.error(getContext(),message);
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.icon_sign_up_wechat)
    void onClickWeChat() {

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEdtEmail.getText().toString();
        final String password = mEdtPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEdtEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEdtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mEdtPassword.setError("密码长度少于6位");
            isPass = false;
        } else {
            mEdtPassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
