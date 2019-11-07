package com.tecsup.petclinic.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author jgomezm
 *
 */
@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

		// Fetch pets by typeId
		List<Owner> findById(int id);


}
