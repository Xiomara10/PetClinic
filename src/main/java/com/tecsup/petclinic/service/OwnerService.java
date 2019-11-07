package com.tecsup.petclinic.service;

import java.util.List;
import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

/**
 * 
 * @author jgomezm
 *
 */
public interface OwnerService {

	/**
	 * 
	 * @param pet
	 * @return
	 */
	Owner create(Owner owner);

	/**
	 * 
	 * @param pet
	 * @return
	 */
	Owner update(Owner owner);

	/**
	 * 
	 * @param id
	 * @throws OwnerNotFoundException
	 */
	void delete(Long id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	Owner findById(long id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Owner> findByName(String first_name);

	/**
	 * 
	 * @param typeId
	 * @return
	 */
	List<Owner> findByTypeId(int id);

	/**
	 * 
	 * @return
	 */
	Iterable<Owner> findAll();

}