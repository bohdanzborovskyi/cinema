package com.zbodya.model.Repositories;

import com.zbodya.model.Entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservatioRepo extends CrudRepository<Reservation, Long> {

}
