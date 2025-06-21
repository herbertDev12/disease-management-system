package utils;

import javax.swing.JTextField;

public class Validacion {
	
	// Método para validar números positivos
    public static int validarEdad(JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim();
        
        // Validar que no esté vacío
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vacío");
        }
        
        try {
            int valor = Integer.parseInt(texto);
            
            // Validar que sea mayor que cero
            if (valor <= 0 || valor > 120) {
                throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe tomar un valor en el rango de 1 a 120.");
            }
            
            return valor;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El campo '" + nombreCampo + "' debe ser un número entero válido");
        }
    }
    
    //Método para validar sexo de una persona
    public static String validarSexoPersona(JTextField campo, String nombreCampo){
    	String texto = campo.getText().trim();
    	
    	// Validar que no esté vacío
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vacío");
        }
        
        //Para que sea siempre valida la palabra sin importar si hay letras en mayusculas o minusculas
        String sexoNormalizado = texto.toLowerCase();
        
        //Validar que sea masculino o femenino 
        if (!sexoNormalizado.equals("masculino") && !sexoNormalizado.equals("femenino")){
        	throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser masculino o femenino.");
        }
        
        return texto;
    }
    
    //Método para validar id de una persona
    public static String validarIdPersona(JTextField campo, String nombreCampo){
    	String texto = campo.getText().trim();
    	
    	// Validar que no esté vacío
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vacío");
        }
        
        //Validar que solo sean digitos 11 digitos 
        //[0-9]Es una expresion regular que representa cualquier dígito del 0 al 9
        // y {11} al final indica que solo puede ser de 11 digitos
        if (!texto.matches("[0-9]{11}")){
        	throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe contener 11 dígitos(0 al 9).");
        }
    	
        return texto;
    }
    
    public static String validarNombre(JTextField campo, String nombreCampo){
    	String texto = campo.getText().trim();

    	//Validar que empiece con letra mayuscula
    	//seguido de al menos una letra minuscula 
    	//^[A-Z] - significa que el primer caracter tendra que ser una letra mayuscula de la A a la Z
    	if(!texto.matches("^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+$")){
    		throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe empezar con letra mayúscula y las restantes minúsculas.");
    	}
    	return texto;
    }
    
    
  //Retornar un boolean en relacion con lo que el usuario ingreso en diagnostico
    public static boolean volverBooleanADiagnostico(JTextField campo, String nombreCampo){
    	
    	boolean isDiagnosticado = false;
    	
    		String texto = campo.getText().trim();
    	
    		//Para que sea siempre valida la palabra sin importar si hay letras en mayusculas o minusculas
    		String diagnosticoNormalizado = texto.toLowerCase();
    	
    		if (diagnosticoNormalizado.equals("sí") || diagnosticoNormalizado.equals("si")){
    			isDiagnosticado = true;
    		}else if (diagnosticoNormalizado.equals("no")){
    			isDiagnosticado = false;
    		}
    		return isDiagnosticado;
    		
    }

}
