package com.ruriel.ldap.controllers;

import java.util.List;

import javax.naming.InvalidNameException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruriel.ldap.dto.ResponseDTO;
import com.ruriel.ldap.model.User;
import com.ruriel.ldap.repository.UserService;

@RestController
public class UsersController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/Users", method = RequestMethod.POST)
	public ResponseDTO saveUser(@Valid @RequestBody User user) {
		User ret;
		try {
			ret = service.create(user);
		} catch (InvalidNameException e) {
			e.printStackTrace();
			ret = null;
		}
		ResponseDTO dto = new ResponseDTO();
		if (ret == null)
			dto.setMessage("Não foi possível persistir o usuário.");
		else {
			dto.setMessage("Usuário persistido com sucesso.");
			dto.addUser(ret);
		}
		return dto;
	}

	@RequestMapping(value = "/Users", method = RequestMethod.GET)
	public ResponseDTO getUsers() {
		ResponseDTO dto = new ResponseDTO();
		List<User> users = service.findAll();
		if (users == null || users.size() == 0) {
			dto.setMessage("Nenhum usuárioo encontrado");
		} else {
			int size = users.size();
			if (size == 1)
				dto.setMessage("Foi encontrado 1 usuário.");
			else
				dto.setMessage("Foram encontrados " + users.size() + " usuários.");
			dto.setUsers(users);
		}
		return dto;
	}

	@RequestMapping(value = "/Users/{uid}", method = RequestMethod.GET)
	public ResponseDTO getUser(@PathVariable(value = "uid") String uid) {
		ResponseDTO dto = new ResponseDTO();
		User user = service.findByUid(uid);
		if (user == null) {
			dto.setMessage("Nenhum usuário encontrado.");
		} else {
			dto.setMessage("Usuário encontrado com sucesso.");
			dto.addUser(user);
		}
		return dto;
	}

	@RequestMapping(value = "/Users/{uid}", method = RequestMethod.DELETE)
	public ResponseDTO deleteUser(@PathVariable(value = "uid") String uid) {
		ResponseDTO dto = new ResponseDTO();
		User user = service.findByUid(uid);
		if (user == null)
			dto.setMessage("Nenhum usuário encontrado.");
		else {
			service.delete(uid);
			dto.setMessage("Usuário removido.");
		}
		return dto;
	}

}
