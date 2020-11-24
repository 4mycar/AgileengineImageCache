package com.serdyukov.agileengine.exeptions;

public class NotAuthorizedExeption extends RuntimeException{


        public NotAuthorizedExeption(int errorCode, String reason) {

            super(String.format("Error during authorizing. Code: %s, reason: %s", errorCode, reason));

        }

}
