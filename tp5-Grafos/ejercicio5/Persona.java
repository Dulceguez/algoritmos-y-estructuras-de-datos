package tp5.ejercicio5;

public class Persona {
	private String tipo, nombre, domicilio; // empleado o jubilado

	public Persona(String tipo, String nombre, String domicilio) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.domicilio = domicilio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	@Override
	public String toString() {
		return tipo + ", nombre: " + nombre + " con domicilio en " + domicilio + " ";
	}

}
