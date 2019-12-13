package com.ruriel.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.odm.core.ObjectDirectoryMapper;
import org.springframework.ldap.odm.core.impl.DefaultObjectDirectoryMapper;

import com.ruriel.ldap.model.User;


@Configuration
@EnableLdapRepositories("com.ruriel.ldap.**")
public class LdapConfig {

	@Autowired
    private Environment env;

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(env.getRequiredProperty("spring.ldap.url"));
        contextSource.setBase(env.getRequiredProperty("spring.ldap.base"));
        contextSource.setUserDn(env.getRequiredProperty("spring.ldap.principal"));
        contextSource.setPassword(env.getRequiredProperty("spring.ldap.password"));
        return contextSource;
    }

    @Bean
    public ObjectDirectoryMapper odm() {
        return new DefaultObjectDirectoryMapper();
    };
    
    @Bean
    public Class<User> entityType(){
    	return User.class;
    }
}
