package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EnfermoEnExtranjero extends PacienteEnfermo{
	private ArrayList<PaisVisitado> paisesVisitados;

	public EnfermoEnExtranjero(String nombre, String id,ArrayList<String> personasContactos, int edad, char sexo,
			String direccion, LocalDateTime fechaDiagnosticado,ArrayList<String> tratamiento, ArrayList<Enfermedad> enfermedades,
			ArrayList<PaisVisitado> paisesVisitados) {
		
		super(nombre, id, personasContactos, edad, sexo, direccion,
				fechaDiagnosticado, tratamiento, enfermedades);
		setPaisesVisitados(paisesVisitados);
	}

	public ArrayList<PaisVisitado> getPaisesVisitados() {
		return paisesVisitados;
	}

	public void setPaisesVisitados(ArrayList<PaisVisitado> paisesVisitados) {
		this.paisesVisitados = paisesVisitados;
	}
	
	
	
}
