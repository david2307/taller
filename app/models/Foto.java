package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Foto extends Model {
    
	@Required
	public Blob foto;
	
	@Required
	@ManyToOne
	public Persona persona;
	
	public Foto(Blob foto, Persona persona){
		this.foto = foto;
		this.persona = persona;
	}
}
