package omicron.app.datatypes;

import omicron.app.MainActivity;
import omicron.app.dbManagement.RazaDbAdapter;

/**
 * Class for instances from razas table
 * 
 * @author aaguilar
 *
 */
public class Raza {
	private int id;

	private String codigo;
	private String descripcion;
	
	private RazaDbAdapter dba;

	public Raza(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		
		dba = new RazaDbAdapter(MainActivity.getAppContext());
	}

	// Builder pattern: since we have a bunch of parameters, we will use this
	// pattern for creators

	public static class RazaBuilder {
		private String codigo;
		private String descripcion;

		public RazaBuilder() {
		}

		public Raza buildRaza() {
			return new Raza(codigo, descripcion);
		}

		public RazaBuilder basics(String codigo) {
			this.codigo = codigo;
			return this;
		}

		public RazaBuilder descripcion(String descripcion) {
			this.descripcion = descripcion;
			return this;
		}
	}
	public int getId() {
		return id;
	}
}