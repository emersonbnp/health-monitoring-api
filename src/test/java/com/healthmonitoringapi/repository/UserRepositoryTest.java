package com.healthmonitoringapi.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthmonitoringapi.entity.Parent;
import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.repository.ParentRepository;
import com.healthmonitoringapi.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	private final String USER 		= "user";
	private final String EMAIL 		= "user@test.com";
	private final String PASSWORD 	= "123456";
	
	private final String PARENT_FIRST_NAME 	= "João";
	private final String PARENT_LAST_NAME 	= "Silva";
	private final String PARENT_USERID 		= "1234651561816";
	private final String PARENT_PHONE 		= "83999998888";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Before
	public void before () {
		Parent parent = new Parent();
		parent.setFirstName(PARENT_FIRST_NAME);
		parent.setLastName(PARENT_LAST_NAME);
		parent.setPhone(PARENT_PHONE);
		parent.setUserID(PARENT_USERID);
		
		parentRepository.save(parent);
		
		User user = new User();
		user.setUsername(USER);
		user.setPassword(PASSWORD);
		user.setEmail(EMAIL);
		user.setParent(parent);
		
		userRepository.save(user);
	}
	
	@Test
	public void userWasSaved() {
		Optional<User> savedUser = userRepository.findByUsernameAndPassword(USER, PASSWORD);
		assertTrue(savedUser.isPresent());
		
		User user = savedUser.get();
		assertNotNull(user.getParent());
		
		assertEquals(user.getParent().getFirstName(), PARENT_FIRST_NAME);
		assertEquals(user.getParent().getLastName(), PARENT_LAST_NAME);
		assertEquals(user.getParent().getPhone(), PARENT_PHONE);
		assertEquals(user.getParent().getUserID(), PARENT_USERID);
	}
	
}
