package com.healthmonitoringapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthmonitoringapi.dto.ParentDTO;
import com.healthmonitoringapi.dto.UserDTO;
import com.healthmonitoringapi.entity.Parent;
import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.service.UserService;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	private final String URL = "/user";

	private final Integer 	ID 				= 1;
	private final String 	USER 			= "user";
	private final String 	EMAIL 			= "user@test.com";
	private final String 	INVALID_EMAIL	= "user";
	private final String 	PASSWORD 		= "123456";

	private final String 	PARENT_FIRST_NAME 	= "João";
	private final String 	PARENT_LAST_NAME 	= "Silva";
	private final String 	PARENT_USERID 		= "1234651561816";
	private final String 	PARENT_PHONE 		= "83999998888";
	
	private final String INVALID_EMAIL_MSG = "Email can't be empty";

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void saveUser() throws Exception {
		BDDMockito.given(userService.save(Mockito.any(User.class)))
		.willReturn(getMockUser());
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(ID,	USER, EMAIL, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.username").value(USER))
		.andExpect(jsonPath("$.data.email").value(EMAIL))
		.andExpect(jsonPath("$.data.password").doesNotExist());
	}
	
	@Test
	public void saveInvalidUser() throws Exception {
		BDDMockito.given(userService.save(Mockito.any(User.class)))
		.willReturn(getMockUser());
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(ID,	USER, INVALID_EMAIL, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value(INVALID_EMAIL_MSG));
	}

	private String getJsonPayload(Integer id, String username, String email, String password)
			throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setUsername(username);
		dto.setEmail(email);
		dto.setPassword(password);
		
		ParentDTO parentDTO = new ParentDTO();
		parentDTO.setFirstName(PARENT_FIRST_NAME);
		parentDTO.setLastName(PARENT_LAST_NAME);
		parentDTO.setPhone(PARENT_PHONE);
		parentDTO.setUserID(PARENT_USERID);
		dto.setParent(parentDTO);
		return (new ObjectMapper()).writeValueAsString(dto);
	}

	public User getMockUser() {
		Parent parent = new Parent();
		parent.setId(ID);
		parent.setFirstName(PARENT_FIRST_NAME);
		parent.setLastName(PARENT_LAST_NAME);
		parent.setPhone(PARENT_PHONE);
		parent.setUserID(PARENT_USERID);

		User user = new User();
		user.setId(ID);
		user.setEmail(EMAIL);
		user.setUsername(USER);
		user.setPassword(PASSWORD);
		user.setParent(parent);

		return user;
	}

}
