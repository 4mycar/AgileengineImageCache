package com.serdyukov.agileengine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "items")
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private UUID uuid;

    private String id;

    private String author;

    private String camera;

    private String tags;

    private String cropped_picture;

    private String full_picture;

    private Integer page;

    public Item(String id) {
        this.id = id;
    }

}
