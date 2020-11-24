package com.serdyukov.agileengine.repository;

import com.serdyukov.agileengine.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(String id);

    List<Item> findAllByPage(Integer page);

    List<Item> findByAuthorContainingIgnoreCaseOrCameraContainingIgnoreCaseOrTagsContainingIgnoreCase
            (String searchTerm1, String searchTerm2, String searchTerm3);

}
