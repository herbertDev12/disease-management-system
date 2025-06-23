package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class Enfermedad implements Codificador{

	private String nombreComun;
	private String nombreCientifico;
	private ArrayList<String> viasTransmision;
	private int periodoIncubacion;
	private int cantidadPacientesHombres;
	private int cantidadPacientesMujeres;
	private HashMap<String, Integer> rangoEdades;
	private int curados;
	private int muertos;
	private int activos;
	
	public Enfermedad(String nombreComun, String nombreCientifico,ArrayList<String> viasTransmision, int periodoIncubacion,
			int cantidadPacientesHombres, int cantidadPacientesMujeres, int curados, int muertos,int activos) {
		
		setNombreComun(nombreComun);
		setNombreCientifico(nombreCientifico);
		setViasTransmision(viasTransmision);
		setPeriodoIncubacion(periodoIncubacion);
		setCantidadPacientesHombres(cantidadPacientesHombres);
		setCantidadPacientesMujeres(cantidadPacientesMujeres);
		setCurados(curados);
		setMuertos(muertos);
		setActivos(activos); 
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public ArrayList<String> getViasTransmision() {
		return viasTransmision;
	}

	public void setViasTransmision(ArrayList<String> viasTransmision) {
		this.viasTransmision = viasTransmision;
	}

	public int getPeriodoIncubacion() {
		return periodoIncubacion;
	}

	public void setPeriodoIncubacion(int periodoIncubacion) {
		this.periodoIncubacion = periodoIncubacion;
	}

	public int getCantidadPacientesHombres() {
		return cantidadPacientesHombres;
	}

	public void setCantidadPacientesHombres(int cantidadPacientesHombres) {
		this.cantidadPacientesHombres = cantidadPacientesHombres;
	}

	public int getCantidadPacientesMujeres() {
		return cantidadPacientesMujeres;
	}

	public void setCantidadPacientesMujeres(int cantidadPacientesMujeres) {
		this.cantidadPacientesMujeres = cantidadPacientesMujeres;
	}

	public HashMap<String, Integer> getRangoEdades() {
		return rangoEdades;
	}

	public void setRangoEdades(HashMap<String, Integer> rangoEdades) {
		this.rangoEdades = rangoEdades;
	}

	public int getCurados() {
		return curados;
	}

	public void setCurados(int curados) {
		this.curados = curados;
	}

	public int getMuertos() {
		return muertos;
	}

	public void setMuertos(int muertos) {
		this.muertos = muertos;
	}

	public int getActivos() {
		return activos;
	}

	public void setActivos(int activos) {
		this.activos = activos;
	}


	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
