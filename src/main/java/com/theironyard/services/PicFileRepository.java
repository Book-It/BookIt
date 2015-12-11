package com.theironyard.services;

import com.theironyard.entities.PicFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alhanger on 12/10/15.
 */
public interface PicFileRepository extends CrudRepository<PicFile, Integer> {
}
