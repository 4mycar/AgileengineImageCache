package com.serdyukov.agileengine.services.internal;


import com.serdyukov.agileengine.dto.to_user.ItemDto;
import com.serdyukov.agileengine.entity.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {

    List searchItems(String searchTerm);
}
