package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.repository.MovingRepository;
import com.nocountry.movenow.service.MovingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MovingServiceImpl implements MovingService {

    @Autowired
    private MovingRepository movingRepository;

    @Override
    public Optional<Moving> getMoving(Long movingId) {
        return movingRepository.findById(movingId);
    }

    @Override
    public Moving save(Moving moving) {
        //save
        return movingRepository.save(moving);
    }

    @Override
    public Moving update(Moving moving) {
            //validar atributos
        try{
            this.delete(moving.getId());
            return  movingRepository.save(moving);

        }catch(Exception e){
            System.out.println(e);
            return  movingRepository.save(moving);
        }

    }

    @Override
    public boolean delete(Long movingId) {
        Optional<Moving> movingOptinal = movingRepository.findById(movingId);

        if(movingOptinal.isPresent()){
            Moving moving = movingOptinal.get() ;
            moving.setSoftDelete(true);
            movingRepository.save(moving);
            return true;
        }else{
            return false;
        }

    }
}
