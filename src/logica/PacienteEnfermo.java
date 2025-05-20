package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PacienteEnfermo extends Paciente{
	private LocalDateTime fechaDiagnosticado;
	private ArrayList<String> tratamiento;
	private ArrayList<Enfermedad> enfermedades;
	
	public PacienteEnfermo(String nombre, String id,ArrayList<String> personasContactos, int edad, char sexo,
			String direccion, LocalDateTime fechaDiagnosticado,ArrayList<String> tratamiento, ArrayList<Enfermedad> enfermedades) {
		
		super(nombre, id, personasContactos, edad, sexo, direccion);
		setFechaDiagnosticado(fechaDiagnosticado);
		setTratamiento(tratamiento);
		setEnfermedades(enfermedades);
	}

	public LocalDateTime getFechaDiagnosticado() {
		return fechaDiagnosticado;
	}

	public void setFechaDiagnosticado(LocalDateTime fechaDiagnosticado) {
		this.fechaDiagnosticado = fechaDiagnosticado;
	}

	public ArrayList<String> getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(ArrayList<String> tratamiento) {
		this.tratamiento = tratamiento;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	
}
