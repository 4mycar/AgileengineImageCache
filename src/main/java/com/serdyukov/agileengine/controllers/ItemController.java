package com.serdyukov.agileengine.controllers;

import com.serdyukov.agileengine.services.internal.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final IItemService IItemService;

    @GetMapping("/search/{searchTerm}")
    public List search(@PathVariable String searchTerm) {
        return IItemService.searchItems(searchTerm);
    }
}
