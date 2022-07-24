package com.gl.employeeapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = "roles")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;

	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER )
	@Column(name="ROLES")
	private Set<Role> roles;
	
	public void addRole(Role role) {
		if(this.roles == null) {
			this.roles = new HashSet<Role>();
		}
		
		this.roles.add(role);
		role.getUsers().add(this);
	}
}
