package logica;

import java.util.ArrayList;

public class Minsap {
	
	private ArrayList<Paciente> pacientes;
	private ArrayList<Enfermedad> enfermedades;
	/*
	private ArrayList<String> nombresNacionalesEnfermedadBuscada;//ver que hacer con esta lista
	private int totalEnfermos;
	private int totalPacientesEnfermedadX;
	 */
	private String enfermedadFiltrar;
	

	public Minsap(){
		pacientes = new ArrayList<>();
		enfermedades = new ArrayList<>();
		}
	
	public String getEnfermedadFiltrar() {
		return enfermedadFiltrar;
	}

	public void setEnfermedadFiltrar(String enfermedadFiltrar) {
		this.enfermedadFiltrar = enfermedadFiltrar;
	}
	
	/*
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
	*/



	public void addPacientes(Paciente paciente){
        if(paciente == null){
        	throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        pacientes.add(paciente);
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
        
        public ArrayList<Paciente> getAllPaciente(){
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
                System.err.println("Enfermo Nacional con ID " + id + " no encontrado");
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
    	        System.err.println("Error: Cliente con ID " + id + " no encontrado.");
    	    }
    	}
       
    	
    	
    	public void addEnfermedad(Enfermedad enfermedad) {
    	    if (enfermedad == null) {
    	        throw new IllegalArgumentException("La enfermedad no puede ser nula");
    	    }
    	    enfermedades.add(enfermedad);
    	}
/*
    	public Enfermedad getEnfermedad(String id) {
    	    Enfermedad enfermedadEncontrada = null;
    	    boolean flag = false;
    	    int i = 0;

    	    while (i < enfermedades.size() && !flag) {
    	        if (enfermedades.get(i).getId().equals(id)) {
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
    	        if (enfermedades.get(i).getId().equals(id)) {
    	            enfermedades.set(i, enfermedadActualizada);
    	            actualizado = true;
    	        }
    	        i++;
    	    }

    	    if (!actualizado) {
    	        System.err.println("Enfermedad con ID " + id + " no encontrada.");
    	    }
    	}
	*/
    	//Reportes
    	public ArrayList<Enfermedad> enfermedadMayoresMuertos() {
    	    ArrayList<Enfermedad> enfermedadMayoresMuertos = new ArrayList<>();
    	    
    	    if (enfermedades.isEmpty()) {
    	        return enfermedadMayoresMuertos;
    	    }
    	    
    	    int maxMuertos = 0;

    	    for (Enfermedad enfermedad : enfermedades) {
    	        if (enfermedad.getMuertos() > maxMuertos) {
    	            maxMuertos = enfermedad.getMuertos();
    	            enfermedadMayoresMuertos.clear();
    	            enfermedadMayoresMuertos.add(enfermedad);
    	        } else if (enfermedad.getMuertos() == maxMuertos) {
    	            enfermedadMayoresMuertos.add(enfermedad);
    	        }
    	    }

    	    return enfermedadMayoresMuertos;
    	}

    	
    	public ArrayList<Enfermedad> enfermedadMayoresCurados() {
    	    ArrayList<Enfermedad> enfermedadMayoresCurados = new ArrayList<>();
    	    
    	    if (enfermedades.isEmpty()) {
    	        return enfermedadMayoresCurados;
    	    }
    	    
    	    int maxCurados = 0;

    	    for (Enfermedad enfermedad : enfermedades) {
    	        if (enfermedad.getCurados() > maxCurados) {
    	            maxCurados = enfermedad.getCurados();
    	            enfermedadMayoresCurados.clear();
    	            enfermedadMayoresCurados.add(enfermedad);
    	        } else if (enfermedad.getCurados() == maxCurados) {
    	            enfermedadMayoresCurados.add(enfermedad);
    	        }
    	    }

    	    return enfermedadMayoresCurados;
    	}

    	public ArrayList<Enfermedad> enfermedadMayoresActivos() {
    	    ArrayList<Enfermedad> enfermedadMayoresActivos = new ArrayList<>();
    	    
    	    if (enfermedades.isEmpty()) {
    	        return enfermedadMayoresActivos;
    	    }
    	    
    	    int maxActivos = 0;

    	    for (Enfermedad enfermedad : enfermedades) {
    	        if (enfermedad.getActivos() > maxActivos) {
    	            maxActivos = enfermedad.getActivos();
    	            enfermedadMayoresActivos.clear();
    	            enfermedadMayoresActivos.add(enfermedad);
    	        } else if (enfermedad.getActivos() == maxActivos) {
    	            enfermedadMayoresActivos.add(enfermedad);
    	        }
    	    }

    	    return enfermedadMayoresActivos;
    	}
    	
    	public ArrayList<Paciente> filtroEnfermosEnExtrangero(){
    		ArrayList<Paciente> filtrados = new ArrayList<>();
    		
    		for(Paciente paciente : pacientes){
    			if(paciente instanceof EnfermoEnExtranjero){
    				if(((EnfermoEnExtranjero) paciente).getEnfermedades().equals(enfermedadFiltrar)){
    					filtrados.add(paciente);
    				}
    			}
    		}
    		return filtrados;
    	}
    	
}

