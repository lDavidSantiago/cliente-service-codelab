package com.denkitronik.clienteservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message ="no puede estar vacio")
	@Size(min=2, max=20, message="el tamaño tiene que estar entre 2 y 20")
	@Column(nullable=false)
	private String nombre;

	@NotEmpty(message ="no puede estar vacio")
	private String apellido;

	@NotEmpty(message ="no puede estar vacio")
	@Email(message="no es una dirección de correo bien formada")
	@Column(nullable=false, unique=true)
	private String email;

	@Column(name="create_at")
	@Temporal(TemporalType.DATE) // Anotación que indica que el campo de la base de datos es de tipo fecha
	private Date createAt;

	private String foto;

	@NotNull(message="La región no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Anotación que se utiliza para ignorar propiedades específicas de la serialización y deserialización JSON.
	private Region region;

	@PrePersist // Anotación que se utiliza para especificar un metodo que se debe ejecutar antes de que se persista la entidad.
	public void prePersist() {
		createAt = new Date();
	}

}
