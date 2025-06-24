package logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import utils.ExtractorDatos;

public class Minsap {
    
    private ArrayList<EnfermoNacional> enfermadosEnCuba;
    private ArrayList<EnfermoEnExtranjero> enfermadosEnExterior;
    private ArrayList<Enfermedad> enfermedades;
    
    public Minsap() {
        enfermadosEnCuba = new ArrayList<>();
        enfermadosEnExterior = new ArrayList<>();
        enfermedades = new ArrayList<>();
    }
    
    // Método para cargar pacientes desde un archivo
    public void cargarPacientes(String rutaArchivo) {
        ExtractorDatos extractor = new ExtractorDatos();
        try {
            List<Object> pacientes = extractor.extraerPacientes(rutaArchivo);
            for (Object paciente : pacientes) {
                if (paciente instanceof EnfermoNacional) {
                    enfermadosEnCuba.add((EnfermoNacional) paciente);
                    // Añadir enfermedad si no existe
                    agregarEnfermedadSiNoExiste(((EnfermoNacional) paciente).getEnfermedades().get(0));
                } else if (paciente instanceof EnfermoEnExtranjero) {
                    enfermadosEnExterior.add((EnfermoEnExtranjero) paciente);
                    // Añadir enfermedad si no existe
                    agregarEnfermedadSiNoExiste(((EnfermoEnExtranjero) paciente).getEnfermedades().get(0));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar pacientes: " + e.getMessage());
        }
    }
    
    // Método auxiliar para agregar enfermedades sin duplicados
    private void agregarEnfermedadSiNoExiste(Enfermedad nuevaEnfermedad) {
        boolean existe = false;
        for (Enfermedad enfermedad : enfermedades) {
            if (enfermedad.getNombreComun().equals(nuevaEnfermedad.getNombreComun())) {
                // Actualizar estadísticas de la enfermedad existente
                actualizarEstadisticasEnfermedad(enfermedad, nuevaEnfermedad);
                existe = true;
                break;
            }
        }
        if (!existe) {
            enfermedades.add(nuevaEnfermedad);
        }
    }
    
    // Método para actualizar estadísticas de una enfermedad existente
    private void actualizarEstadisticasEnfermedad(Enfermedad existente, Enfermedad nueva) {
    	
    	if (existente == null || nueva == null) {
            throw new IllegalArgumentException("Enfermedad no puede ser nula");
        }
        
        // Inicializar si es necesario
        if (existente.getRangoEdades() == null) {
        	existente.setRangoEdades(new HashMap<String, Integer>());
        }
        if (nueva.getRangoEdades() == null) {
            return; // o inicializar según necesidad
        }
        
        existente.setCantidadPacientesHombres(existente.getCantidadPacientesHombres() + nueva.getCantidadPacientesHombres());
        existente.setCantidadPacientesMujeres(existente.getCantidadPacientesMujeres() + nueva.getCantidadPacientesMujeres());
        existente.setCurados(existente.getCurados() + nueva.getCurados());
        existente.setMuertos(existente.getMuertos() + nueva.getMuertos());
        existente.setActivos(existente.getActivos() + nueva.getActivos());
        
        // Actualizar rangos de edad
        HashMap<String, Integer> rangosExistentes = existente.getRangoEdades();
        HashMap<String, Integer> rangosNuevos = nueva.getRangoEdades();
        
        for (String rango : rangosNuevos.keySet()) {
            int cantidad = rangosNuevos.get(rango);
            rangosExistentes.put(rango, rangosExistentes.getOrDefault(rango, 0) + cantidad);
        }
    }
    
    public static boolean realizarAnalisis() {
        try {
            TimeUnit.SECONDS.sleep(8);
            return Math.random() < 0.5;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    // Métodos para añadir pacientes individualmente
    public void addEnfermoNacional(EnfermoNacional paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        enfermadosEnCuba.add(paciente);
        agregarEnfermedadSiNoExiste(paciente.getEnfermedades().get(0));
    }
    
    public void addEnfermoExtranjero(EnfermoEnExtranjero paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        enfermadosEnExterior.add(paciente);
        agregarEnfermedadSiNoExiste(paciente.getEnfermedades().get(0));
    }
    
    // Métodos para obtener todos los pacientes
    public ArrayList<EnfermoNacional> getAllEnfermosNacionales() {
        return enfermadosEnCuba;
    }
    
    public ArrayList<EnfermoEnExtranjero> getAllEnfermosExtranjeros() {
        return enfermadosEnExterior;
    }
    
    public ArrayList<Enfermedad> getAllEnfermedades() {
        return enfermedades;
    }
    
    // Métodos para buscar pacientes por ID
    public EnfermoNacional getEnfermoNacional(String id) {
        for (EnfermoNacional paciente : enfermadosEnCuba) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    
    public EnfermoEnExtranjero getEnfermoExtranjero(String id) {
        for (EnfermoEnExtranjero paciente : enfermadosEnExterior) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    
    // Métodos para actualizar pacientes
    public void actualizarEnfermoNacional(String id, EnfermoNacional pacienteActualizado) {
        for (int i = 0; i < enfermadosEnCuba.size(); i++) {
            if (enfermadosEnCuba.get(i).getId().equals(id)) {
                enfermadosEnCuba.set(i, pacienteActualizado);
                return;
            }
        }
        System.err.println("Enfermo Nacional con ID " + id + " no encontrado");
    }
    
    public void actualizarEnfermoExtranjero(String id, EnfermoEnExtranjero pacienteActualizado) {
        for (int i = 0; i < enfermadosEnExterior.size(); i++) {
            if (enfermadosEnExterior.get(i).getId().equals(id)) {
                enfermadosEnExterior.set(i, pacienteActualizado);
                return;
            }
        }
        System.err.println("Enfermo Extranjero con ID " + id + " no encontrado");
    }
    
    // Métodos para eliminar pacientes
    public void deleteEnfermoNacional(String id) {
        for (int i = 0; i < enfermadosEnCuba.size(); i++) {
            if (enfermadosEnCuba.get(i).getId().equals(id)) {
                enfermadosEnCuba.remove(i);
                return;
            }
        }
        System.err.println("Error: Enfermo Nacional con ID " + id + " no encontrado.");
    }
    
    public void deleteEnfermoExtranjero(String id) {
        for (int i = 0; i < enfermadosEnExterior.size(); i++) {
            if (enfermadosEnExterior.get(i).getId().equals(id)) {
                enfermadosEnExterior.remove(i);
                return;
            }
        }
        System.err.println("Error: Enfermo Extranjero con ID " + id + " no encontrado.");
    }
    
    // Reportes (se mantienen iguales)
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
  	/*
  	public ArrayList<Paciente> filtroEnfermosEnExtrangero(String enfermedadFiltrar){
  		ArrayList<Paciente> filtrados = new ArrayList<>();
  		
  		for(Paciente paciente :){
  			if(paciente instanceof EnfermoEnExtranjero){
  				if(((EnfermoEnExtranjero) paciente).getEnfermedades().equals(enfermedadFiltrar)){
  					filtrados.add(paciente);
  				}
  			}
  		}
  		return filtrados;
  	}
    */
    public ArrayList<EnfermoEnExtranjero> filtroEnfermosEnExtranjero(String enfermedadFiltrar) {
        ArrayList<EnfermoEnExtranjero> filtrados = new ArrayList<>();
        for (EnfermoEnExtranjero paciente : enfermadosEnExterior) {
            for (Enfermedad enf : paciente.getEnfermedades()) {
                if (enf.getNombreComun().equalsIgnoreCase(enfermedadFiltrar)) {
                    filtrados.add(paciente);
                    break;
                }
            }
        }
        return filtrados;
    }
}