package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Persona extends Model {
    
	@Required
	public String nombre;
	
	@Required
	public String apellido;
	
	@Required
	public int edad;
	
	@Required
	public String quienSoy;
	
	@Required
	@OneToOne
	public Usuario usuario;
	
	public Persona(String nombre, String apellido, int edad, String quienSoy, Usuario usuario){
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.quienSoy = quienSoy;
		this.usuario = usuario;
	}
}
