###  Introduction

The following project consists in a Restful API written in Java for creation, search and dele operation in a LDAP server. It was developed using SpringBoot.

### Requirements

The project has been developed and tested with the following:

    -Maven 3.6
    -Java 1.8.0-221
    -Docker 2.1.0.5
To see the results, install OpenLDAP Server (LDAPAdmin in case you're using Windows)

### API Setup
##### 1-LDAP Server Setup
Clone the following repository  https://github.com/osixia/docker-openldap and run it on docker with the following command:

	docker run --env LDAP_ORGANISATION="My Company" --env LDAP_DOMAIN="techinterview.com" --env LDAP_ADMIN_PASSWORD="123456" -p 389:389 --detach osixia/openldap:1.3.0

##### 2-OU creation on the domain:
Create a **create_ou_users.ldif** file and fill it with the following content:

	dn: ou=Users,dc=techinterview,dc=com
	changetype: add
	objectClass: organizationalUnit
	objectClass: top
	ou: Users
	<blank line at the end of the file>

Run the following command:

	ldapmodify -h localhost -p 389 -w '123456' -D 'cn=admin,dc=techinterview,dc=com'  <  create_ou_users.ldif

Check the Open LDAP Server to see if the OU has been created.

#### 3-API Deployment
On the root folder of the project, run the following:

	mvn package

A .jar file will be created inside the target folder. To start it, run the following command:

	java -jar target\ldap-rest-api-0.0.1-SNAPSHOT.jar

The API will be available on localhost at port 8080.

### API Usage

**Create user**
Creates an user on the **OU Users.**
Method: POST
Endpoint: /Users
Request Payload:

	{ 
		"uid":"marry",
		"cn":"Marry",
		"sn":"Doe"
	}
Response Payload:

	{
		"message": "Usuário persistido com sucesso.",
		"users": [
			{
				"dn": "uid=marry",
				"uid": "marry",
				"cn": "Marry",
				"sn": "Doe"
			}
		]
	}

**Return all users**
Returns all users on the **OU Users.**
Method: GET
Endpoint: /Users
Response Payload:

	{
		"message": "Foi encontrado 1 usuário.",
		"users": [
			{
				"dn": "uid=marry",
				"uid": "marry",
				"cn": "Marry",
				"sn": "Doe"
			}
		]
	}

**Search a user**
Searches a users according to the specified uid.
Method: GET
Endpoint: /Users/{uid}
Response Payload:

	{
		"message": "Usuário encontrado com sucesso.",
		"users": [
			{
				"dn": "uid=marry",
				"uid": "marry",
				"cn": "Marry",
				"sn": "Doe"
			}
		]
	}

**Remove user**
Removes the specified user.
Method: DELETE
Endpoint: /Users/{uid}
Response Payload:

	{
		"message": "Usuário removido.",
		"users": []
	}

