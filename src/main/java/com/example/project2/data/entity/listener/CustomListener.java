package com.example.project2.data.entity.listener;

import com.example.project2.data.entity.ListenerEntity;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomListener {


    @PostLoad
    public void postLoad(ListenerEntity entity){
        log.info("[postLoad] called");
    }

    @PrePersist
    public void prePersist(ListenerEntity entity){
        log.info("[prePersist] called");
    }

    @PostPersist
    public void PostPersist(ListenerEntity entity){
        log.info("[postPersist] called");
    }

    @PreUpdate
    public void preUpdate(ListenerEntity entity){
        log.info("[preUpdate] called");
    }

    @PostUpdate
    public void postUpdate(ListenerEntity entity){
        log.info("[postUpdate] called");
    }

    @PreRemove
    public void preRemove(ListenerEntity entity){
        log.info("[preRemove] called");
    }

    @PostRemove
    public void postRemove(ListenerEntity entity){
        log.info("[postRemove] called");
    }

}
