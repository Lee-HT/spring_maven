package com.example.project2.service;

import com.example.project2.data.entity.ListenerEntity;

public interface ListenerService {
    ListenerEntity getEntity(Long id);

    void saveEntity(ListenerEntity listenerEntity);

    void updateEntity(ListenerEntity listenerEntity);

    void removeEntity(ListenerEntity listenerEntity);

}
