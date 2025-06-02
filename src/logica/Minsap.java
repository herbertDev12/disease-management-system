package logica;
import java.util.ArrayList;

public class Minsap {
	
	private ArrayList<Paciente> pacientes;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<String> nombresNacionalesEnfermedadBuscada;//ver que hacer con esta lista
	private int totalEnfermos;
	private int totalPacientesEnfermedadX;

	public Minsap(){
		pacientes = new ArrayList<Paciente>();
		enfermedades = new ArrayList<Enfermedad>();
		nombresNacionalesEnfermedadBuscada= new ArrayList<String>();
		
	}
	
	public int getTotalEnfermos() {
		return totalEnfermos;
	}
	public void setTotalEnfermos(int totalEnfermos) {
	    if (totalEnfermos < 0) {
	        throw new IllegalArgumentException("El total de enfermos no puede ser negativo");
	    }
	    this.totalEnfermos = totalEnfermos;
	}


	public int getTotalPacientesEnfermedadX() {
		return totalPacientesEnfermedadX;
	}
	public void setTotalPacientesEnfermedadX(int totalPacientesEnfermedadX) {
	    if (totalPacientesEnfermedadX < 0 || totalPacientesEnfermedadX > totalEnfermos) {
	        throw new IllegalArgumentException("El total de pacientes con Enfermedad X debe estar entre 0 y " + totalEnfermos);
	    }
	    this.totalPacientesEnfermedadX = totalPacientesEnfermedadX;
	}





	public void addPaciente(Paciente Paciente){
        if(Paciente == null){
        	throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        pacientes.add(Paciente);
    }
        
        public Paciente getPaciente(String id) {
        	Paciente pacienteEncontrado = null;
            boolean flag = false;
            int i = 0;
            
            while (i < pacientes.size() && !flag) {
                if (pacientes.get(i).getId().equals(id)) {
                	pacienteEncontrado = pacientes.get(i); 
                    flag = true;
            }
            i++;
        }
        return pacienteEncontrado; 
        }
        
        public ArrayList<Paciente> getAllEnfermosNacionales(){
        return pacientes;
        }

       public void actualizarPaciente(String id, Paciente pacienteActualizado) {
        int i = 0;
        boolean actualizado = false;
        while (i < pacientes.size() && !actualizado) {
            if (pacientes.get(i).getId().equals(id)) {
            	pacientes.set(i, pacienteActualizado); 
                actualizado = true; 
                }
                i++;
            }
        if (!actualizado) {
        	System.err.println("Paciente con ID " + id + " no encontrado");
        	}
        }

       public void deletePaciente(String id) {
    	    int i = 0;
    	    boolean removed = false;

    	    while (i < pacientes.size() && !removed) {
    	        if (pacientes.get(i).getId().equals(id)) {
    	        	pacientes.remove(i);
    	            removed = true;
    	        } else {
    	            i++;  
    	        }
    	    }

    	    if (!removed) {
    	        System.err.println("Paciente con ID " + id + " no encontrado.");
    	    }
       }
    	
       
    	public void addEnfermedad(Enfermedad enfermedad) {
    	    if (enfermedad == null) {
    	        throw new IllegalArgumentException("La enfermedad no puede ser nula");
    	    }
    	    enfermedades.add(enfermedad);
    	}

    	public Enfermedad getEnfermedad(String id) {
    	    Enfermedad enfermedadEncontrada = null;
    	    boolean flag = false;
    	    int i = 0;

    	    while (i < enfermedades.size() && !flag) {
    	        if (enfermedades.get(i).getNombreCientifico().equals(id)) {
    	            enfermedadEncontrada = enfermedades.get(i);
    	            flag = true;
    	        }
    	        i++;
    	    }
    	    return enfermedadEncontrada;
    	}

    	public ArrayList<Enfermedad> getAllEnfermedades() {
    	    return enfermedades;
    	}

    	public void actualizarEnfermedad(String id, Enfermedad enfermedadActualizada) {
    	    int i = 0;
    	    boolean actualizado = false;

    	    while (i < enfermedades.size() && !actualizado) {
    	        if (enfermedades.get(i).getNombreCientifico().equals(id)) {
    	            enfermedades.set(i, enfermedadActualizada);
    	            actualizado = true;
    	        }
    	        i++;
    	    }

    	    if (!actualizado) {
    	        System.err.println("Enfermedad con ID " + id + " no encontrada.");
    	    }
    	}
    	
    	//-------------Reporte #1 Frank ---------------------------------------------------------------
    	public ArrayList<Enfermedad> enfermedadMayoresMuertos(){
    		ArrayList<Enfermedad> enfermedadMayoresMuertos = new ArrayList<Enfermedad>();
    		Enfermedad mayorEnfermedad = enfermedades.get(0);
    		for(Enfermedad enfermedad: enfermedades){
    			if(mayorEnfermedad.getMuertos() < enfermedad.getMuertos()){
    				mayorEnfermedad = enfermedad;
    			}
    		}
    		for(Enfermedad enfermedad: enfermedades){
    			if (enfermedad.getMuertos() == mayorEnfermedad.getMuertos()) {
    	            enfermedadMayoresMuertos.add(enfermedad);
    	        }
    	    }
    		return enfermedadMayoresMuertos;
    	}
    	
    	public ArrayList<Enfermedad>  enfermedadMayoresCurados(){
    		ArrayList<Enfermedad> enfermedadMayoresCurados = new ArrayList<Enfermedad>();
    		Enfermedad mayorEnfermedad = enfermedades.get(0);
    		for(Enfermedad enfermedad: enfermedades){
    			if(mayorEnfermedad.getCurados() < enfermedad.getCurados()){
    				mayorEnfermedad = enfermedad;
    			}
    		}
    		for(Enfermedad enfermedad: enfermedades){
    			if (enfermedad.getCurados() == mayorEnfermedad.getCurados()) {
    	            enfermedadMayoresCurados.add(enfermedad);
    	        }
    	    }
    		return enfermedadMayoresCurados;
    	}
    	
    	public ArrayList<Enfermedad> enfermedadMayoresActivos(){
    		ArrayList<Enfermedad> enfermedadMayoresActivos = new ArrayList<Enfermedad>();
    		Enfermedad mayorEnfermedad = enfermedades.get(0);
    		for(Enfermedad enfermedad: enfermedades){
    			if(mayorEnfermedad.getActivos() < enfermedad.getActivos()){
    				mayorEnfermedad = enfermedad;
    			}
    		}
    		for(Enfermedad enfermedad: enfermedades){
    			if (enfermedad.getActivos() == mayorEnfermedad.getActivos()) {
    	            enfermedadMayoresActivos.add(enfermedad);
    	        }
    	    }
    		return enfermedadMayoresActivos;
    	}
	//---------------------------------------------------------------------------------------------------
}
