package com.ruriel.ldap.model;

import javax.naming.Name;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.domain.Persistable;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = { "inetOrgPerson" })
public final class User implements Persistable<String> {

	@Id
	private Name dn;
	@Attribute(name = "uid")
	private String uid;
	@Attribute(name = "cn")
	private String cn;
	@Attribute(name = "sn")
	private String sn;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDn() {
		if (dn == null)
			return null;
		return dn.toString();
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "uid=" + uid + ",cn=" + cn + ",sn=" + sn;
	}

	@JsonIgnore
	@Override
	public String getId() {
		return getDn();
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if (dn == null)
			return false;
		return true;
	}

}
