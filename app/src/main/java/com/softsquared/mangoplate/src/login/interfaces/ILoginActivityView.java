package com.softsquared.mangoplate.src.login.interfaces;

import com.softsquared.mangoplate.src.login.models.LoginInfo;

public interface ILoginActivityView {
    void validateSuccess(LoginInfo jwt);

    void validateFailure();
}
