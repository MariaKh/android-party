package com.androidparty.partyapplication;

import com.androidparty.partyapplication.data.DatabaseModule;
import com.androidparty.partyapplication.data.PreferenceModule;
import com.androidparty.partyapplication.network.NetworkModule;
import com.androidparty.partyapplication.ui.activities.MainActivity;
import com.androidparty.partyapplication.ui.activities.RegistrationActivity;
import com.androidparty.partyapplication.ui.fragments.LoginFragment;
import com.androidparty.partyapplication.ui.fragments.MainFragment;
import com.androidparty.partyapplication.ui.presentation.presenters.ContentPresenter;
import com.androidparty.partyapplication.ui.presentation.presenters.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 1 on 12/20/2017.
 */
@Component(modules = {ApplicationModule.class, NetworkModule.class, PreferenceModule.class, DatabaseModule.class})
@Singleton
public interface ApplicationComponent {
    void inject(Application application);

    void inject(MainFragment mainFragment);

    void inject(RegistrationActivity registrationActivity);

    void inject(MainActivity mainActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(ContentPresenter contentPresenter);
}
