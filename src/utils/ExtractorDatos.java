package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import logica.Enfermedad;
import logica.EnfermoEnExtranjero;
import logica.EnfermoNacional;
import logica.PaisVisitado;

public class ExtractorDatos {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<Object> extraerPacientes(String rutaArchivo) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));
        List<Object> pacientes = new ArrayList<>();

        for (int i = 0; i < lineas.size(); i++) {
            try {
                pacientes.add(parsearLinea(lineas.get(i)));
            } catch (Exception e) {
                System.err.println("Error en línea " + (i+1) + ": " + lineas.get(i));
                e.printStackTrace();
            }
        }

        return pacientes;
    }

    private Object parsearLinea(String linea) {
        String[] campos = linea.split("\\|", -1);
        
        
        if (campos.length == 14) { 
            return parsearPacienteExtranjero(campos);
        } else if (campos.length == 13) { // Paciente nacional
            return parsearPacienteNacional(campos);
        } else {
            throw new IllegalArgumentException("Formato inválido. Campos encontrados: " + campos.length + " Línea: " + linea);
        }
    }

    private EnfermoEnExtranjero parsearPacienteExtranjero(String[] campos) {
        
        LocalDateTime fechaDiagnosticado = LocalDateTime.parse(campos[0], FORMATO_FECHA);
        String nombre = campos[1];
        String id = campos[2];
        int edad = Integer.parseInt(campos[3]);
        char sexo = campos[4].charAt(0);
        String direccion = campos[5];
        String contacto = campos[6];

        ArrayList<String> contactos = new ArrayList<>();
        contactos.add(contacto);

        Enfermedad enfermedad = parsearEnfermedad(
            campos[7], campos[8], campos[9], campos[10], campos[11]
        );
        
        actualizarEstadisticasEnfermedad(enfermedad, sexo, edad, campos[11]);

        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
        enfermedades.add(enfermedad);

        // Tratamientos ahora está en el índice 12
        ArrayList<String> tratamientos = new ArrayList<>(Arrays.asList(campos[12].split(",")));

        ArrayList<PaisVisitado> paises = new ArrayList<>();
        String[] paisesArray = campos[13].split(",");
        
        for (String paisStr : paisesArray) {
            String paisTrimmed = paisStr.trim();
            if (!paisTrimmed.isEmpty()) {
                paises.add(new PaisVisitado(paisTrimmed));
            }
        }

        return new EnfermoEnExtranjero(
            nombre, id, contactos, edad, sexo, direccion, 
            fechaDiagnosticado, tratamientos, enfermedades, paises
        );
    }

    private EnfermoNacional parsearPacienteNacional(String[] campos) {
        // Campos para paciente nacional (13 elementos) permanecen iguales
        LocalDateTime fechaDiagnosticado = LocalDateTime.parse(campos[0], FORMATO_FECHA);
        String nombre = campos[1];
        String id = campos[2];
        int edad = Integer.parseInt(campos[3]);
        char sexo = campos[4].charAt(0);
        String direccion = campos[5];
        String contacto = campos[6];

        ArrayList<String> contactos = new ArrayList<>();
        contactos.add(contacto);

        Enfermedad enfermedad = parsearEnfermedad(
            campos[7], campos[8], campos[9], campos[10], campos[11]
        );
        
        actualizarEstadisticasEnfermedad(enfermedad, sexo, edad, campos[11]);

        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
        enfermedades.add(enfermedad);

        // Tratamientos en índice 12
        ArrayList<String> tratamientos = new ArrayList<>(Arrays.asList(campos[12].split(",")));

        return new EnfermoNacional(
            nombre, id, contactos, edad, sexo, direccion, 
            fechaDiagnosticado, tratamientos, enfermedades
        );
    }

    private Enfermedad parsearEnfermedad(String nombreComun, String nombreCientifico, 
                                         String viaTransmision, String periodoIncubacionStr, 
                                         String estadoPaciente) {
    	
    	// En parsearEnfermedad
    	if (periodoIncubacionStr == null || periodoIncubacionStr.isEmpty()) {
    	    periodoIncubacionStr = "0"; // Valor por defecto
    	}
    	int periodoIncubacion = Integer.parseInt(periodoIncubacionStr);
        
        ArrayList<String> vias = new ArrayList<>();
        vias.add(viaTransmision);
        
        // Usar el constructor con parámetros
        return new Enfermedad(
            nombreComun, 
            nombreCientifico, 
            vias, 
            periodoIncubacion,  // Ahora es int
            0,  // Hombres (inicial)
            0,  // Mujeres (inicial)
            0,  // Curados (inicial)
            0,  // Muertos (inicial)
            0   // Activos (inicial)
        );
    }

    private void actualizarEstadisticasEnfermedad(Enfermedad enfermedad, char sexo, int edad, String estado) {
        if (sexo == 'M') {
            enfermedad.setCantidadPacientesHombres(enfermedad.getCantidadPacientesHombres() + 1);
        } else if (sexo == 'F') {
            enfermedad.setCantidadPacientesMujeres(enfermedad.getCantidadPacientesMujeres() + 1);
        }
        
        String rangoEdad = determinarRangoEdad(edad);
        HashMap<String, Integer> rangoEdades = enfermedad.getRangoEdades();
        rangoEdades.put(rangoEdad, rangoEdades.getOrDefault(rangoEdad, 0) + 1);
        
        switch (estado.toUpperCase()) {
            case "CURADO":
                enfermedad.setCurados(enfermedad.getCurados() + 1);
                break;
            case "MUERTO":
                enfermedad.setMuertos(enfermedad.getMuertos() + 1);
                break;
            case "ACTIVO":
                enfermedad.setActivos(enfermedad.getActivos() + 1);
                break;
        }
    }

    private String determinarRangoEdad(int edad) {
        if (edad < 18) return "0-17";
        if (edad < 30) return "18-29";
        if (edad < 50) return "30-49";
        return "50+";
    }
}
