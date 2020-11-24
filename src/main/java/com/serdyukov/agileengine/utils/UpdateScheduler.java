package com.serdyukov.agileengine.utils;

import com.serdyukov.agileengine.dto.from_server.AuthResponse;
import com.serdyukov.agileengine.services.external.IRestService;
import com.serdyukov.agileengine.services.internal.IItemService;
import com.serdyukov.agileengine.services.external.RestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateScheduler {

    @Autowired
    private IRestService restService;
    @Scheduled(fixedDelayString = "${scheduler.update.delay}")
    public void updateData() {
        log.info("updateData");
        AuthResponse authResponse= restService.getAuth();

        if (authResponse.isAuthorized()) {
            restService.updateData(authResponse.getToken());
        }
    }

}
