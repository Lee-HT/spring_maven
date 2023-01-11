package com.example.project2.service.Impl;

import com.example.project2.data.entity.ListenerEntity;
import com.example.project2.data.repository.ListenerRepository;
import com.example.project2.service.ListenerService;
import org.springframework.stereotype.Service;

@Service
public class ListenerServiceImpl implements ListenerService {

    private ListenerRepository listenerRepository;

    public ListenerServiceImpl(ListenerRepository listenerRepository){
        this.listenerRepository = listenerRepository;
    }

    @Override
    public ListenerEntity getEntity(Long id){
        return listenerRepository.findById(id).get();
    }

    @Override
    public void saveEntity(ListenerEntity listenerEntity){
        listenerRepository.save(listenerEntity);
    }

    @Override
    public void updateEntity(ListenerEntity listenerEntity){
        ListenerEntity foundListener = listenerRepository.findById(listenerEntity.getId()).get();
        foundListener.setName(listenerEntity.getName());

        listenerRepository.save(foundListener);
    }

    @Override
    public void removeEntity(ListenerEntity listenerEntity){
        listenerRepository.delete(listenerEntity);
    }

}
