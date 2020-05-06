package com.softsquared.mangoplate.src.main.interfaces;

import com.softsquared.mangoplate.src.main.models.UserInfo;

public interface MainActivityView {
    void onSuccessGetUser(UserInfo myInfo);

    void onFailureGetUser();
}
