package com.springproject.blogger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.springproject.blogger.settings.AuthorityType;

import lombok.Data;

@Entity
@Data
public class Authority {
	@Id
	private long id;
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private AuthorityType authorityType;

	public Authority() {
	};

	public Authority(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}
}
