package com.androidparty.partyapplication.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidparty.partyapplication.Application;
import com.androidparty.partyapplication.R;
import com.androidparty.partyapplication.data.dao.ContentDao;
import com.androidparty.partyapplication.network.entities.response.DataResponse;
import com.androidparty.partyapplication.ui.SeparatorDecoration;
import com.androidparty.partyapplication.ui.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 1 on 12/20/2017.
 */

public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getName();

    @Inject
    ContentDao contentDao;
    Disposable contentDbSubscription;
    private ListAdapter mAdapter;
    private RecyclerView mList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Application.getAppComponent().inject(this);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mList = rootView.findViewById(R.id.main_list);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ListAdapter(getContext(), null);
        mList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mList.setHasFixedSize(true);
        mList.addItemDecoration(new SeparatorDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        mList.setAdapter(mAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        getContentFromDb();
    }

    @Override
    public void onPause() {
        if (contentDbSubscription != null) {
            contentDbSubscription.dispose();
        }
        super.onPause();
    }

    private void getContentFromDb() {
        if (contentDbSubscription != null) {
            contentDbSubscription.dispose();
        }
        contentDbSubscription = contentDao.getContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateContent, throwable -> {
                    throw new RuntimeException(throwable);
                });
    }

    private void updateContent(List<DataResponse> contents) {
        mAdapter.updateData((ArrayList<DataResponse>) contents);
    }
}
