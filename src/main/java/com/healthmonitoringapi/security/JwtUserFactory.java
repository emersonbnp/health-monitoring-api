package com.healthmonitoringapi.security;

import com.healthmonitoringapi.entity.User;
import com.healthmonitoringapi.util.BCryptUtil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtUserFactory {

	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), BCryptUtil.getHash(user.getPassword()));
	}

}
