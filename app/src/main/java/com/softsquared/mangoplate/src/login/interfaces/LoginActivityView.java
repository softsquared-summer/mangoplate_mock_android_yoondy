package com.softsquared.mangoplate.src.login.interfaces;

import com.softsquared.mangoplate.src.login.models.LoginInfo;

public interface LoginActivityView {
    void onSuccessLogin(LoginInfo jwt);

    void onFailureLogin();
}
