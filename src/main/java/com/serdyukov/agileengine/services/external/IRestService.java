package com.serdyukov.agileengine.services.external;

import com.serdyukov.agileengine.dto.from_server.AuthResponse;
import com.serdyukov.agileengine.entity.Item;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface IRestService {
    AuthResponse getAuth();
    Map<Integer, List> getImages(String token) throws URISyntaxException;
    Item getImageInfo(String id, String token);
    void updateData(String token);
}
