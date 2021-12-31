package com.cch.demojwt.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// @Table(name = "user", uniqueConstraints = {
//     @UniqueConstraint(columnNames = "username"),
// 	@UniqueConstraint(columnNames = "email") 
// })
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false, length = 30)
	private String account;

    @Column(nullable = false, length=20)
	private String username;

    @Column(unique = true, nullable = false)
	private String email;
    
    @Column(nullable = false)
	private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles", 
                    joinColumns = @JoinColumn(name = "users_id"), 
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
