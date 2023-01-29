package com.formacionbdi.springboot.app.usuarios.commons.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

	private Boolean enabled;
	private String nombre;
	private String apellido;

	@Column(unique = true, length = 100)
	private String email;
	/***
	 * Tipo de fetch recomendado
	 * FetchType.LAZY -> Carga peresosa va a traer los datos hasta que se le mande a llamar
	 * FetchType.EAGER -> fetch por default trae toda la info de una sola vez
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	/*** opcional si queremos customizar los nombres de tabla y columnas, llaves.
	 * joinColumns = @JoinColumn para indicar llave foranea de clase principal dueña de la relacion
	 * inverseJoinColumns = @JoinColumn para indicar la llave foranea de la relacion contraparte
	 * uniqueConstraints = {@UniqueConstraint -> para poner restriccion de que un usuario no tenga el rol repetido
	 */
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"), 
	uniqueConstraints = {@UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
	private List<com.formacionbdi.springboot.app.usuarios.models.entity.Role> roles;

	public List<com.formacionbdi.springboot.app.usuarios.models.entity.Role> getRoles() {
		return roles;
	}

	public void setRoles(List<com.formacionbdi.springboot.app.usuarios.models.entity.Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final long serialVersionUID = 4002221912401133094L;

}
