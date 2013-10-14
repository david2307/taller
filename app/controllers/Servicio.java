package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Foto;
import models.Persona;
import models.Usuario;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.mvc.Controller;


public class Servicio extends Controller{
	
	public static void login(@Required String nickName, @Required String password){
		Map<String, Object> resultado = new HashMap<String, Object>();
		
		if (validation.hasErrors()){
			resultado.put("result", "false");
			resultado.put("mensaje", "Necesitamos todos los campos!");
		} else {
			Usuario usuario = Usuario.find("byNickNameAndPassword", nickName, password).first();
			if (usuario!=null){
				resultado.put("id", usuario.id);
				resultado.put("result", "true");
				resultado.put("mensaje", "Y va pa dentro");
			} else {
				resultado.put("result", "false");
				resultado.put("mensaje", "Piiiiii, se equivoco!");
			}
		}
		renderJSON(resultado);
	}
	
	public static void usuario(@Required Long id){
		Map<String, Object> resultado = new HashMap<String, Object>();
		
		if (validation.hasErrors()){
			resultado.put("result", "false");
			resultado.put("mensaje", "Necesitamos todos los campos!");
		} else {
			Usuario usuario = Usuario.findById(id);
			if (usuario != null){
				Persona persona = Persona.find("byUsuario", usuario).first();
				if (persona != null){
					Foto foto = Foto.find("byPersona", persona).first();
					resultado.put("nombre", persona.nombre);
					resultado.put("apellido", persona.apellido);
					resultado.put("edad", persona.edad);
					resultado.put("quienSoy", persona.quienSoy);
					resultado.put("foto", foto.id);
					resultado.put("UUID", foto.foto.getUUID());
					resultado.put("result", "true");
					resultado.put("mensaje", "Estes sos tu");
				} else {
					resultado.put("result", "false");
					resultado.put("mensaje", "Piiiii, No existen su informacion!");
				}
			} else {
				resultado.put("result", "false");
				resultado.put("mensaje", "Piiiii, no existe!");
			}
		}
		renderJSON(resultado);
	}
	
	public static void foto(@Required Long idFoto, @Required Blob foto){
		Map<String, Object> resultado = new HashMap<String, Object>();
		System.out.println(idFoto);
		if (validation.hasErrors()){
			resultado.put("result", "false");
			resultado.put("mensaje", "Necesitamos todos los campos!");
			System.out.println(resultado);
		} else {
			Foto pic = Foto.findById(idFoto);
			System.out.println("campos:" + pic);
			if (pic != null){
				pic.foto = foto;
				if (pic.validateAndSave()){
					resultado.put("UUID", pic.foto.getUUID());
					resultado.put("result", "true");
					resultado.put("mensaje", "Listo, Ya tienes nueva foto");
					System.out.println(resultado);
				} else {
					resultado.put("result", "false");
					resultado.put("mensaje", "Ooops algo salio mal, quizas estas demasiado feo!");
					System.out.println(resultado);
				}
			} else {
				resultado.put("result", "false");
				resultado.put("mensaje", "No existe la foto");
				System.out.println(resultado);
			}
		}
		System.out.println(resultado);
		renderJSON(resultado);
	}
}
