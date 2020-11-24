package com.serdyukov.agileengine.dto.from_server;

import lombok.Data;

@Data
public class AuthResponse {

    private boolean auth;
    private String token;

    public boolean isAuthorized() {
        return token != null;
    }

}
