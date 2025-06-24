package logica;

import java.util.ArrayList;

public abstract class Paciente implements Codificador{
	private String nombre;
	private String id;
	private ArrayList<String> personasContactos;
	private int edad;
	private char sexo;
	private String direccion;
	
	public Paciente(String nombre, String id,ArrayList<String> personasContactos, int edad, char sexo,String direccion) {
		setNombre(nombre);
		setId(id);
		setPersonasContactos(personasContactos);
		setEdad(edad);
		setSexo(sexo);
		setDireccion(direccion);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<String> getPersonasContactos() {
		return personasContactos;
	}
	public void setPersonasContactos(ArrayList<String> personasContactos) {
		this.personasContactos = personasContactos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}
