package logica;
import java.util.ArrayList;

<<<<<<< HEAD
public class Minsap {
	
	private ArrayList<EnfermoNacional> enfermosNacionales;
	private ArrayList<EnfermoEnExtranjero> enfermosEnExtranjero;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<String> nombresNacionalesEnfermedadBuscada;//ver que hacer con esta lista
	private int totalEnfermos;
	private int totalPacientesEnfermedadX;

=======
import java.util.ArrayList;

public class Minsap {
	
	private ArrayList<EnfermoNacional> enfermosNacionales;
	private ArrayList<EnfermoEnExtranjero> enfermosEnExtranjero;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<String> nombresNacionalesEnfermedadBuscada;//ver que hacer con esta lista
	private int totalEnfermos;
	private int totalPacientesEnfermedadX;

>>>>>>> feat/add-insert-deseases-window
	public Minsap(){
		enfermosNacionales = new ArrayList<>();
		enfermedades = new ArrayList<>();
		enfermosEnExtranjero = new ArrayList<>();
		nombresNacionalesEnfermedadBuscada= new ArrayList<>();
		
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





	public void addEnfermoNacional(EnfermoNacional enfermoNacional){
        if(enfermoNacional == null){
        	throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
            enfermosNacionales.add(enfermoNacional);
    }
        
        public EnfermoNacional getEnfermoNacional(String id) {
        	EnfermoNacional enfermoNacionalEncontrado = null;
            boolean flag = false;
            int i = 0;
            
            while (i < enfermosNacionales.size() && !flag) {
                if (enfermosNacionales.get(i).getId().equals(id)) {
                	enfermoNacionalEncontrado = enfermosNacionales.get(i); 
                    flag = true;
            }
            i++;
        }
        return enfermoNacionalEncontrado; 
        }
        
        public ArrayList<EnfermoNacional> getAllEnfermosNacionales(){
        return enfermosNacionales;
        }

       public void actualizarEnfermoNacional(String id, EnfermoNacional enfermoNacionalActualizado) {
        int i = 0;
        boolean actualizado = false;
        while (i < enfermosNacionales.size() && !actualizado) {
            if (enfermosNacionales.get(i).getId().equals(id)) {
            	enfermosNacionales.set(i, enfermoNacionalActualizado); 
                actualizado = true; 
                }
                i++;
            }
            if (!actualizado) {
                System.err.println("Enfermo Nacional con ID " + id + " no encontrado");
            }
        }

       public void deleteEnfermoNacional(String id) {
    	    int i = 0;
    	    boolean removed = false;

    	    while (i < enfermosNacionales.size() && !removed) {
    	        if (enfermosNacionales.get(i).getId().equals(id)) {
    	            enfermosNacionales.remove(i);
    	            removed = true;
    	        } else {
    	            i++; 
    	        }
    	    }

    	    if (!removed) {
    	        System.err.println("Error: Cliente con ID " + id + " no encontrado.");
    	    }
    	}
       
       
       
       public void addEnfermoEnExtranjero(EnfermoEnExtranjero enfermoEnExtranjero) {
    	    if (enfermoEnExtranjero == null) {
    	        throw new IllegalArgumentException("Error: El paciente no puede ser nulo");
    	    }
    	    enfermosEnExtranjero.add(enfermoEnExtranjero);
    	}

    	public EnfermoEnExtranjero getEnfermoEnExtranjero(String id) {
    	    EnfermoEnExtranjero enfermoEncontrado = null;
    	    boolean flag = false;
    	    int i = 0;

    	    while (i < enfermosEnExtranjero.size() && !flag) {
    	        if (enfermosEnExtranjero.get(i).getId().equals(id)) {
    	            enfermoEncontrado = enfermosEnExtranjero.get(i);
    	            flag = true;
    	        }
    	        i++;
    	    }
    	    return enfermoEncontrado;
    	}

    	public ArrayList<EnfermoEnExtranjero> getAllEnfermosEnExtranjero() {
    	    return enfermosEnExtranjero;
    	}

    	public void actualizarEnfermoEnExtranjero(String id, EnfermoEnExtranjero enfermoEnExtranjeroActualizado) {
    	    int i = 0;
    	    boolean actualizado = false;

    	    while (i < enfermosEnExtranjero.size() && !actualizado) {
    	        if (enfermosEnExtranjero.get(i).getId().equals(id)) {
    	            enfermosEnExtranjero.set(i, enfermoEnExtranjeroActualizado);
    	            actualizado = true;
    	        }
    	        i++;
    	    }

    	    if (!actualizado) {
    	        System.err.println("Enfermo en el extranjero con ID " + id + " no encontrado.");
    	    }
    	}

    	public void deleteEnfermoEnExtranjero(String id) {
    	    int i = 0;
    	    boolean removed = false;

    	    while (i < enfermosEnExtranjero.size() && !removed) {
    	        if (enfermosEnExtranjero.get(i).getId().equals(id)) {
    	            enfermosEnExtranjero.remove(i);
    	            removed = true;
    	        } else {
    	            i++;
    	        }
    	    }

    	    if (!removed) {
    	        System.err.println("Enfermo en el extranjero con ID " + id + " no encontrado.");
    	    }
    	}
    	
    	
    	public void addEnfermedad(Enfermedad enfermedad) {
    	    if (enfermedad == null) {
    	        throw new IllegalArgumentException("La enfermedad no puede ser nula");
    	    }
    	    enfermedades.add(enfermedad);
    	}
<<<<<<< HEAD

=======
/*
>>>>>>> feat/add-insert-deseases-window
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
<<<<<<< HEAD
	
=======
	*/
>>>>>>> feat/add-insert-deseases-window
}
