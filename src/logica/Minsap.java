package logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    // Método para cargar todos los pacientes
    public void cargarTodosLosPacientes(String rutaNacionales, String rutaExtranjeros) {
        cargarPacientes(rutaNacionales);   // Carga pacientes nacionales
        cargarPacientes(rutaExtranjeros);   // Carga pacientes extranjeros
    }
    
    //Método para agregar enfermedades si no existen
    private void agregarEnfermedadSiNoExiste(Enfermedad nuevaEnfermedad) {
        for (Enfermedad enfermedad : enfermedades) {
            if (enfermedad.getNombreComun().equalsIgnoreCase(nuevaEnfermedad.getNombreComun())) {
                // Inicializar si es necesario
                if (enfermedad.getRangoEdades() == null) {
                    enfermedad.setRangoEdades(new HashMap<String,Integer>());
                }
                if (nuevaEnfermedad.getRangoEdades() == null) {
                    nuevaEnfermedad.setRangoEdades(new HashMap<String,Integer>());
                }
                
                actualizarEstadisticasEnfermedad(enfermedad, nuevaEnfermedad);
                return;
            }
        }
        // Si no existe, agregar la nueva enfermedad
        if (nuevaEnfermedad.getRangoEdades() == null) {
            nuevaEnfermedad.setRangoEdades(new HashMap<String,Integer>());
        }
        enfermedades.add(nuevaEnfermedad);
    }

    
    // Método para actualizar estadísticas de una enfermedad existente
    private void actualizarEstadisticasEnfermedad(Enfermedad existente, Enfermedad nueva) {
        if (existente == null || nueva == null) return;
        
        // Inicializar si es necesario
        if (existente.getRangoEdades() == null) {
            existente.setRangoEdades(new HashMap<String,Integer>());
        }
        if (nueva.getRangoEdades() == null) {
            nueva.setRangoEdades(new HashMap<String,Integer>());
        }
        
        // Actualizar estadísticas básicas
        existente.setCantidadPacientesHombres(existente.getCantidadPacientesHombres() + nueva.getCantidadPacientesHombres());
        existente.setCantidadPacientesMujeres(existente.getCantidadPacientesMujeres() + nueva.getCantidadPacientesMujeres());
        existente.setCurados(existente.getCurados() + nueva.getCurados());
        existente.setMuertos(existente.getMuertos() + nueva.getMuertos());
        existente.setActivos(existente.getActivos() + nueva.getActivos());
        
        // Actualizar rangos de edad
        HashMap<String, Integer> rangosExistentes = existente.getRangoEdades();
        for (Map.Entry<String, Integer> entry : nueva.getRangoEdades().entrySet()) {
            String rango = entry.getKey();
            int cantidad = entry.getValue();
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
            if (paciente.getCodigo().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    
    public EnfermoEnExtranjero getEnfermoExtranjero(String id) {
        for (EnfermoEnExtranjero paciente : enfermadosEnExterior) {
            if (paciente.getCodigo().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    
    // Métodos para actualizar pacientes
    public void actualizarEnfermoNacional(String id, EnfermoNacional pacienteActualizado) {
        for (int i = 0; i < enfermadosEnCuba.size(); i++) {
            if (enfermadosEnCuba.get(i).getCodigo().equals(id)) {
                enfermadosEnCuba.set(i, pacienteActualizado);
                return;
            }
        }
        System.err.println("Enfermo Nacional con ID " + id + " no encontrado");
    }
    
    public void actualizarEnfermoExtranjero(String id, EnfermoEnExtranjero pacienteActualizado) {
        for (int i = 0; i < enfermadosEnExterior.size(); i++) {
            if (enfermadosEnExterior.get(i).getCodigo().equals(id)) {
                enfermadosEnExterior.set(i, pacienteActualizado);
                return;
            }
        }
        System.err.println("Enfermo Extranjero con ID " + id + " no encontrado");
    }
    
    // Métodos para eliminar pacientes
    public void deleteEnfermoNacional(String id) {
        for (int i = 0; i < enfermadosEnCuba.size(); i++) {
            if (enfermadosEnCuba.get(i).getCodigo().equals(id)) {
                enfermadosEnCuba.remove(i);
                return;
            }
        }
        System.err.println("Error: Enfermo Nacional con ID " + id + " no encontrado.");
    }
    
    public void deleteEnfermoExtranjero(String id) {
        for (int i = 0; i < enfermadosEnExterior.size(); i++) {
            if (enfermadosEnExterior.get(i).getCodigo().equals(id)) {
                enfermadosEnExterior.remove(i);
                return;
            }
        }
        System.err.println("Error: Enfermo Extranjero con ID " + id + " no encontrado.");
    }
    
    // Reportes (se mantienen iguales)
  //Reportes
    public ArrayList<Enfermedad> enfermedadMayoresMuertos() {
        ArrayList<Enfermedad> resultado = new ArrayList<>();

        if (!enfermedades.isEmpty()) {
            int maxMuertos = 0;

            for (Enfermedad enfermedad : enfermedades) {
                if (enfermedad.getMuertos() > maxMuertos) {
                    maxMuertos = enfermedad.getMuertos();
                    resultado.clear();
                    resultado.add(enfermedad);
                } else if (enfermedad.getMuertos() == maxMuertos) {
                    resultado.add(enfermedad);
                }
            }
        }

        return resultado;
    }


    public ArrayList<Enfermedad> enfermedadMayoresCurados() {
        ArrayList<Enfermedad> resultado = new ArrayList<>();

        if (!enfermedades.isEmpty()) {
            int maxCurados = 0;

            for (Enfermedad enfermedad : enfermedades) {
                if (enfermedad.getCurados() > maxCurados) {
                    maxCurados = enfermedad.getCurados();
                    resultado.clear();
                    resultado.add(enfermedad);
                } else if (enfermedad.getCurados() == maxCurados) {
                    resultado.add(enfermedad);
                }
            }
        }

        return resultado;
    }

    
    public ArrayList<Enfermedad> enfermedadMayoresActivos() {
        ArrayList<Enfermedad> resultado = new ArrayList<>();

        if (!enfermedades.isEmpty()) {
            int maxActivos = 0;

            for (Enfermedad enfermedad : enfermedades) {
                if (enfermedad.getActivos() > maxActivos) {
                    maxActivos = enfermedad.getActivos();
                    resultado.clear();
                    resultado.add(enfermedad);
                } else if (enfermedad.getActivos() == maxActivos) {
                    resultado.add(enfermedad);
                }
            }
        }

        return resultado;
    }

  	
  	public ArrayList<EnfermoEnExtranjero> filtroEnfermosEnExtranjero(String enfermedadFiltrar) {
  	    ArrayList<EnfermoEnExtranjero> filtrados = new ArrayList<>();

  	    for (EnfermoEnExtranjero enf : enfermadosEnExterior) {
  	        boolean coincide = false;
  	        for (Enfermedad enfermedad : enf.getEnfermedades()) {
  	            if (!coincide && enfermedad.getNombreComun().equalsIgnoreCase(enfermedadFiltrar)) {
  	                coincide = true;
  	            }
  	        }
  	        if (coincide) {
  	            filtrados.add(enf);
  	        }
  	    }

  	    return filtrados;
  	}
  	
  	public ArrayList<EnfermoNacional> getPacientesNacionalesConEnfermedad(String nombreEnfermedad) {
  	    ArrayList<EnfermoNacional> pacientesFiltrados = new ArrayList<>();
  	    
  	    for (EnfermoNacional paciente : enfermadosEnCuba) {
  	        boolean encontrado = false;
  	        ArrayList<Enfermedad> enfermedades = paciente.getEnfermedades();
  	        
  	        for (int i = 0; i < enfermedades.size() && !encontrado; i++) {
  	            if (enfermedades.get(i).getNombreComun().equalsIgnoreCase(nombreEnfermedad)) {
  	                pacientesFiltrados.add(paciente);
  	                encontrado = true;
  	            }
  	        }
  	    }
  	    return pacientesFiltrados;
  	}

  
 }