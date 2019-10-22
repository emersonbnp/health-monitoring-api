package com.healthmonitoringapi.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.exception.EntityNotFoundException;
import com.healthmonitoringapi.repository.UserRepository;
import com.healthmonitoringapi.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
	
	private final String EMAIL = "email@test.com";
	private final String PASSWORD = "123456";

	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Before
	public void setUp() {
		BDDMockito.given(userRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString()))
				.willReturn(Optional.of(new User()));
	}

	@Test
	public void userWasFound() throws EntityNotFoundException {
		User user = userService.findByEmailAndPassword(EMAIL, PASSWORD);
		assertNotNull(user);
	}
	
}
