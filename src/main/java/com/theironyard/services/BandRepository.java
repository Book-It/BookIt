package com.theironyard.services;

import com.theironyard.entities.Band;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by alhanger on 12/8/15.
 */
public interface BandRepository extends CrudRepository<Band, Integer> {
    List<Band> findAllByUserId(int id);
}
