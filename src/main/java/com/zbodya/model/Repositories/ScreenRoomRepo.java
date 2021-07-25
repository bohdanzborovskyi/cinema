package com.zbodya.model.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.model.Entity.ScreenRoom;


@Repository
public interface ScreenRoomRepo extends CrudRepository<ScreenRoom, Long> 
{

}
