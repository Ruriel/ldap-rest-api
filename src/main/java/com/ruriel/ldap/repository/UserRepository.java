package com.ruriel.ldap.repository;

import org.springframework.data.ldap.repository.support.SimpleLdapRepository;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.odm.core.ObjectDirectoryMapper;
import org.springframework.stereotype.Repository;

import com.ruriel.ldap.model.User;

@Repository
public class UserRepository extends SimpleLdapRepository<User> {

	public UserRepository(LdapOperations ldapOperations, ObjectDirectoryMapper odm, Class<User> entityType) {
		super(ldapOperations, odm, entityType);
	}

}
