package com.adultapp.controller;

import com.adultapp.model.User;
import com.adultapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public List<User> list() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "users", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userRepository.saveAndFlush(user);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	public User get(@PathVariable UUID id) {
		return userRepository.findOne(id);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
	public User update(@PathVariable UUID id, @RequestBody User user) {
		User existingUser = userRepository.findOne(id);
		BeanUtils.copyProperties(user, existingUser);
		return userRepository.saveAndFlush(existingUser);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
	public User delete(@PathVariable UUID id) {
		User existingUser = userRepository.findOne(id);
		userRepository.delete(existingUser);
		return existingUser;
	}
}
