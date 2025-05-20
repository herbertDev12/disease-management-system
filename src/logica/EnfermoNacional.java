package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EnfermoNacional extends PacienteEnfermo{

	public EnfermoNacional(String nombre, String id,ArrayList<String> personasContactos, int edad, char sexo,
			String direccion, LocalDateTime fechaDiagnosticado,ArrayList<String> tratamiento, ArrayList<Enfermedad> enfermedades) {
		
		super(nombre, id, personasContactos, edad, sexo, direccion, fechaDiagnosticado,tratamiento, enfermedades);
	}
	
	
}
