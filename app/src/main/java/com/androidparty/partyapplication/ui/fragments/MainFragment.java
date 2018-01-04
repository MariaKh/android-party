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
import com.androidparty.partyapplication.data.model.Content;
import com.androidparty.partyapplication.ui.adapters.ListAdapter;
import com.androidparty.partyapplication.ui.presentation.contracts.ContentContract;
import com.androidparty.partyapplication.ui.presentation.presenters.ContentPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 1 on 12/20/2017.
 */

public class MainFragment extends Fragment implements ContentContract.View{

    public static final String TAG = MainFragment.class.getName();

    private ListAdapter mAdapter;
    private RecyclerView mList;
    private ContentContract.Presenter mPresenter;


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
        if (mPresenter == null) mPresenter = new ContentPresenter();
        setUpList();
    }

    private void setUpList(){
        mAdapter = new ListAdapter(getContext(), null);
        mList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mList.setHasFixedSize(true);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        mPresenter.detachView(this);
        super.onPause();
    }

    @Override
    public void updateContent(List<Content> contents) {
        mAdapter.updateData((ArrayList<Content>) contents);
    }
}
