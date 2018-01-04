package com.androidparty.partyapplication.ui.presentation.contracts;

import com.androidparty.partyapplication.data.model.Content;

import java.util.List;

/**
 * Created by 1 on 1/3/2018.
 */

public class ContentContract {

    public interface View {
        void updateContent(List<Content> contents);
    }

    public interface Presenter{

        void attachView(ContentContract.View view);

        void detachView(ContentContract.View view);
    }
}
