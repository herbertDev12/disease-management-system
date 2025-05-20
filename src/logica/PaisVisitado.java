package logica;

import java.time.LocalDateTime;

public class PaisVisitado {
	private String nombre;
	private LocalDateTime tiempoEstancia;
	
	public PaisVisitado(String nombre, LocalDateTime tiempoEstancia) {
		 setNombre(nombre);
		 setTiempoEstancia(tiempoEstancia);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getTiempoEstancia() {
		return tiempoEstancia;
	}

	public void setTiempoEstancia(LocalDateTime tiempoEstancia) {
		this.tiempoEstancia = tiempoEstancia;
	}
	
	
}
