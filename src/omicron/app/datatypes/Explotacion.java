package omicron.app.datatypes;

import java.text.SimpleDateFormat;
import java.util.Date;

import static omicron.app.dbManagement.RemoteDBConsts.*;

/**
 * Class for instances from explotacion table
 * 
 * @author aaguilar
 * 
 */
public class Explotacion {
	private int id;
	private String codigo;
	private String cif_nif;
	private String titular;
	private Date fecha_apertura;
	private Especie especie;
	private int numero_animales_entrada;
	private int numero_animales_salida;

	/**
	 * @param id
	 * @param codigo
	 * @param cif_nif
	 * @param titular
	 * @param fecha_apertura
	 * @param cod_especie
	 * @param numero_animales_entrada
	 * @param numero_animales_salida
	 */
	public Explotacion(int id, String codigo, String cif_nif, String titular,
			String fecha_apertura, String cod_especie,
			int numero_animales_entrada, int numero_animales_salida) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.cif_nif = cif_nif;
		this.titular = titular;
		if (fecha_apertura != null) {
			SimpleDateFormat format = new SimpleDateFormat(REMOTE_DATE_FORMAT);
			try {
				this.fecha_apertura = format.parse(fecha_apertura);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.especie = new Especie.EspecieBuilder()
				.buildEspecieFromCodigo(cod_especie);
		this.numero_animales_entrada = numero_animales_entrada;
		this.numero_animales_salida = numero_animales_salida;
	}

	// Builder pattern: since we have a bunch of parameters, we will use this
	// pattern for creators
	public static class ExplotacionBuilder {
		private int id;
		private String codigo;
		private String cif_nif = "";
		private String titular = "";
		private String fecha_apertura = "";
		private String especie = null;
		private int numero_animales_entrada = 0;
		private int numero_animales_salida = 0;

		public ExplotacionBuilder() {
		}

		public Explotacion buildExplotacion() {
			return new Explotacion(id, codigo, cif_nif, titular,
					fecha_apertura, especie, numero_animales_entrada,
					numero_animales_salida);
		}

		public ExplotacionBuilder basics(int id, String codigo) {
			this.id = id;
			this.codigo = codigo;
			return this;
		}

		public ExplotacionBuilder cif_nif(String cif_nif) {
			this.cif_nif = cif_nif;
			return this;
		}

		public ExplotacionBuilder titular(String titular) {
			this.titular = titular;
			return this;
		}

		public ExplotacionBuilder fecha_apertura(String fecha_apertura) {
			this.fecha_apertura = fecha_apertura;
			return this;
		}

		public ExplotacionBuilder especie(String especie) {
			this.especie = especie;
			return this;
		}

		public ExplotacionBuilder numero_animales_entrada(
				int numero_animales_entrada) {
			this.numero_animales_entrada = numero_animales_entrada;
			return this;
		}

		public ExplotacionBuilder numero_animales_salida(
				int numero_animales_salida) {
			this.numero_animales_salida = numero_animales_salida;
			return this;
		}
	}
}