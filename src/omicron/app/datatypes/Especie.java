package omicron.app.datatypes;

import omicron.app.MainActivity;
import omicron.app.dbManagement.EspecieDbAdapter;
import android.database.Cursor;

/**
 * Class for instances from especies table
 * 
 * @author aaguilar
 * 
 */
public class Especie {
	private int id;
	private String codigo;
	private String descripcion;

	private EspecieDbAdapter dba;

	/**
	 * Makes instance of Especie in DB with selected codigo
	 * 
	 * @param codigo
	 *            selected field codigo from local db
	 */
	protected Especie(String codigo) {
		this.dba = new EspecieDbAdapter(MainActivity.getAppContext());
		Cursor result = dba.especieFromCodigo(codigo);
		// Añadir función dónde insertar
		this.id = result.getInt(0);
		this.codigo = codigo;
		this.descripcion = result.getString(2);
	}

	public Especie(int id, String codigo, String descripcion) {
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	// Builder pattern: since we have a bunch of parameters, we will use this
	// pattern for creators

	public static class EspecieBuilder {
		private int id;
		private String codigo;
		private String descripcion;

		public EspecieBuilder() {
		}

		public Especie buildEspecie() {
			return new Especie(id, codigo, descripcion);
		}

		public EspecieBuilder basics(int id, String codigo) {
			this.id = id;
			this.codigo = codigo;
			return this;
		}

		public EspecieBuilder descripcion(String descripcion) {
			this.descripcion = descripcion;
			return this;
		}

		public Especie buildEspecieFromCodigo(String codigo) {
			return new Especie(codigo);
		}
	}
}