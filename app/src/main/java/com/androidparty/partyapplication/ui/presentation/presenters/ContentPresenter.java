package com.androidparty.partyapplication.ui.presentation.presenters;

import com.androidparty.partyapplication.Application;
import com.androidparty.partyapplication.data.AppRepository;
import com.androidparty.partyapplication.data.dao.ContentDao;
import com.androidparty.partyapplication.ui.presentation.contracts.ContentContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 1 on 1/3/2018.
 */

public class ContentPresenter implements ContentContract.Presenter {

    @Inject
    ContentDao contentDao;
    @Inject
    AppRepository appRepository;
    Disposable contentDbSubscription;

    private ContentContract.View view;

    public ContentPresenter() {
        Application.getAppComponent().inject(this);
    }

    @Override
    public void attachView(ContentContract.View view) {
        this.view = view;
        appRepository.getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        if (contentDbSubscription != null) {
            contentDbSubscription.dispose();
        }
        contentDbSubscription = contentDao.getContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(content -> this.view.updateContent(content), throwable -> {
                    throw new RuntimeException(throwable);
                });
    }

    @Override
    public void detachView(ContentContract.View view) {
        this.view = null;
        if (contentDbSubscription != null) {
            contentDbSubscription.dispose();
        }
    }
}
