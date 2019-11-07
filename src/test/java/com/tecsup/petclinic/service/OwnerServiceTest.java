package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;

import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	 * 
	 */
	@Test
	public void testFindOwnerById() {

		long ID = 5;
		String FIRST_NAME = "Peter";
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);

		assertEquals(FIRST_NAME, owner.getFirst_name());

	}
	
	@Test
	public void testCreateOwner() {
		
		String FIRST_NAME = "Xiomara";
		String LAST_NAME = "Torres";
		String ADDRES = "Av.Elias Aguirre 123";
		String CITY = "Lima";
		String TELEPHONE = "999991234";
		
		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRES,CITY, TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);
		
		assertThat(owner.getId()).isNotNull();
		assertEquals(FIRST_NAME, owner.getFirst_name());
		assertEquals(LAST_NAME, owner.getLast_name());
		assertEquals(ADDRES, owner.getAddress());
		assertEquals(CITY, owner.getCity());
		assertEquals(TELEPHONE, owner.getTelephone());	
		
	}
	
	@Test
	public void testUpdateOwner() {

		String FIRST_NAME = "Xiomara";
		String LAST_NAME = "Torres";
		String ADDRESS = "Av.Elias Aguirre 123";
		String CITY = "Lima";
		String TELEPHONE = "999991234";
		long create_id = -1;

		String UP_FIRST_NAME = "Xiomara";
		String UP_LAST_NAME = "Torres";
		String UP_ADDRESS = "Av. Miguel Grau 102";
		String UP_CITY = "Lima";
		String UP_TELEPHONE = "999991234";

		Owner owner = new Owner(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);

		// Create record
		logger.info(">" + owner);
		Owner readOwner = ownerService.create(owner);
		logger.info(">>" + readOwner);

		create_id = readOwner.getId();

		// Prepare data for update
		readOwner.setFirst_name(UP_FIRST_NAME);
		readOwner.setLast_name(UP_LAST_NAME);
		readOwner.setAddress(UP_ADDRESS);
		readOwner.setCity(UP_CITY);
		readOwner.setTelephone(UP_TELEPHONE);

		// Execute update
		Owner upgradeOwner = ownerService.update(readOwner);
		logger.info(">>>>" + upgradeOwner);

		assertThat(create_id).isNotNull();
		assertEquals(create_id, upgradeOwner.getId());
		assertEquals(UP_FIRST_NAME, upgradeOwner.getFirst_name());
		assertEquals(UP_LAST_NAME, upgradeOwner.getLast_name());
		assertEquals(UP_ADDRESS, upgradeOwner.getAddress());
		assertEquals(UP_CITY, upgradeOwner.getCity());
		assertEquals(UP_TELEPHONE, upgradeOwner.getTelephone());
	}
	
	@Test
	public void testDeleteOwner() {

		String FIRST_NAME = "Xiomara";
		String LAST_NAME = "Torres";
		String ADDRESS = "Av. Miguel Grau 102";
		String CITY = "Lima";
		String TELEPHONE = "999991234";

		Owner owner = new Owner(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);

		try {
			ownerService.delete(owner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
			
		try {
			ownerService.findById(owner.getId());
			assertTrue(false);
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		} 
	}
}
