package com.androidparty.partyapplication.ui.presentation.contracts;

import com.androidparty.partyapplication.network.entities.response.ApiError;

/**
 * Created by 1 on 1/3/2018.
 */

public class LoginContract {

    public interface View {

        boolean validateLoginFields(String userName, String password);

        void login();

        void onFetchingList();

        void goToMainScreen();

        void onErrorResponse(ApiError error);
    }

    public interface Presenter{

        void attachView(LoginContract.View view);

        void detachView(LoginContract.View view);

        void fetchList();

        void login(String userName, String password);
    }
}
