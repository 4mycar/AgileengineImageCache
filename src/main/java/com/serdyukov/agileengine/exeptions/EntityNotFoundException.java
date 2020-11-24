package com.serdyukov.agileengine.exeptions;


public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException() {

        super("Entity not found");

    }
}
