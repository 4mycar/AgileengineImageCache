package com.serdyukov.agileengine.services.internal;

import com.serdyukov.agileengine.dto.to_user.ItemDto;
import com.serdyukov.agileengine.entity.Item;
import com.serdyukov.agileengine.repository.ItemRepository;
import com.serdyukov.agileengine.services.external.RestService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Data
@Log
public class ItemService implements IItemService {

    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private RestService restService;

    @Override
    public List searchItems(String searchTerm) {
        return itemRepository.findByAuthorContainingIgnoreCaseOrCameraContainingIgnoreCaseOrTagsContainingIgnoreCase
                (searchTerm, searchTerm, searchTerm)
                .stream()
                .map(i -> new ItemDto(i.getAuthor(), i.getCamera(),i.getTags(), i.getCropped_picture(), i.getFull_picture()))
                .collect(Collectors.toList());

    }
}
