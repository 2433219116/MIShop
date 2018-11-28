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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.sign
 * 文件名：SignUpDelegate
 * 创建者：efan.zyhang
 * 创建时间：2018/9/14 15:32
 * 描述： TODO
 */
public class SignUpDelegate extends MishopFragment {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mEdtName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEdtEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mEdtPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mEdtPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mEdtRePassword;

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


    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("user_profile.json")
                    .params("name", mEdtName.getText().toString())
                    .params("email", mEdtEmail.getText().toString())
                    .params("phone", mEdtPhone.getText().toString())
                    .params("password", mEdtPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String responds) {
                            LUitl.json("USER_PROFILE", responds);
                            SignHandler.onSignUp(responds,mISignListener);
                            TUtil.success(getContext(),"注册成功");
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure(String e) {
                            LUitl.w("USER_PROFILE", "Failure");
                            TUtil.warning(getContext(),"注册失败");
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

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mEdtName.getText().toString();
        final String email = mEdtEmail.getText().toString();
        final String phone = mEdtPhone.getText().toString();
        final String password = mEdtPassword.getText().toString();
        final String rePassword = mEdtRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mEdtName.setError("请输入姓名");
            isPass = false;
        } else {
            mEdtName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEdtEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEdtEmail.setError(null);
        }

        if (phone.isEmpty() || !isMobileNO(phone)) {
            mEdtPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mEdtPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mEdtPassword.setError("密码长度少于6位");
            isPass = false;
        } else {
            mEdtPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
            mEdtRePassword.setError("两次密码长度不一致");
            isPass = false;
        } else {
            mEdtRePassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    /**
     * 验证手机号是否为正确手机号
     */

    public static boolean isMobileNO(String mobiles) {

        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobiles);
        return matcher.matches();
    }
}
