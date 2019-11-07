package com.tecsup.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.OwnerRepository;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
  
/**
 * 
 * @author jgomezm
 *
 */
@Service
public class OwnerServiceImpl implements OwnerService {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

	@Autowired
	OwnerRepository ownerRepository;

	/**
	 * 
	 * @param pet
	 * @return
	 */
	@Override
	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
		               
	}

	/**
	 * 
	 * @param pet
	 * @return
	 */
	@Override
	public Owner update(Owner owner) {
		return ownerRepository.save(owner);
	}


	/**
	 * 
	 * @param id
	 * @throws OwnerNotFoundException
	 */
	@Override
	public void delete(Long id) throws OwnerNotFoundException{

		Owner owner = findById(id);
		ownerRepository.delete(owner);

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Owner findById(long id) throws OwnerNotFoundException {

		Optional<Owner> owner = ownerRepository.findById(id);

		if ( !owner.isPresent())
			throw new OwnerNotFoundException("Record not found...!");
			
		return owner.get();
	}

	/**
	 * 
	 * @param typeId
	 * @return
	 */
	@Override
	public List<Owner> findByTypeId(int id) {

		List<Owner> owners = ownerRepository.findById(id);

		owners.stream().forEach(owner -> logger.info("" + owner));

		return owners;
	}


	/**
	 * 
	 * @return
	 */
	@Override
	public Iterable<Owner> findAll() {
		
		// TODO Auto-generated 
		return ownerRepository.findAll();
	
	}

	@Override
	public List<Owner> findByName(String first_name) {
		// TODO Auto-generated method stub
		return null;
	}

}