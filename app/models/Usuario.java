package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model {
    
	@Required
	public String nickName;
	
	@Required
	public String password;
	
	public Usuario(String nickName, String password){
		this.nickName = nickName;
		this.password = password;
	}
}
