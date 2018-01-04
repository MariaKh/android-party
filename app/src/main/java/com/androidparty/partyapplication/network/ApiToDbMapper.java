package com.androidparty.partyapplication.network;

import com.androidparty.partyapplication.data.model.Content;
import com.androidparty.partyapplication.network.entities.response.DataResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 1/3/2018.
 */

public class ApiToDbMapper {
    public static List<Content> map(List<DataResponse> responses) {
        List<Content> content = new ArrayList<>();
        if (responses != null) {
            for (DataResponse data : responses) {
                content.add(new Content(data.getName(), data.getDistance()));
            }
        }
        return content;
    }
}
