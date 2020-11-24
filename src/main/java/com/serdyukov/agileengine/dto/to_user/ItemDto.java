package com.serdyukov.agileengine.dto.to_user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private String author;
    private String camera;
    private String tags;
    private String croppedPicture;
    private String fullPicture;

}
