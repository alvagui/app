package omicron.app.datatypes;

import java.net.MalformedURLException;
import java.net.URL;

import android.telephony.PhoneNumberUtils;

/**
 * Class for instances from explotacion table
 * 
 * @author aaguilar
 * 
 */
public class Cebadero {
	private int id;
	private String codigo;
	private String nombre;
	private String nombre2;
	private String direccion;
	private String direccion2;
	private String poblacion;
	private String telefono;
	private String telefono2;
	private String fax;
	private String cp;
	private String provincia;
	private String email;
	private URL web;
	private String cod_pais_region;
	private Double plazas_disponibles;
	private int plazas_ocupadas;

	/**
	 * @param id
	 * @param codigo
	 * @param nombre
	 * @param nombre2
	 * @param direccion
	 * @param direccion2
	 * @param poblacion
	 * @param telefono
	 * @param telefono2
	 * @param fax
	 * @param cp
	 * @param provincia
	 * @param email
	 * @param web
	 * @param cod_pais_region
	 * @param plazas_disponibles
	 * @param plazas_ocupadas
	 */
	public Cebadero(int id, String codigo, String nombre, String nombre2,
			String direccion, String direccion2, String poblacion,
			String telefono, String telefono2, String fax, String cp,
			String provincia, String email, String web, String cod_pais_region,
			Double plazas_disponibles, int plazas_ocupadas) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.nombre2 = nombre2;
		this.direccion = direccion;
		this.direccion2 = direccion2;
		this.poblacion = poblacion;
		this.telefono = PhoneNumberUtils.formatNumber(telefono);
		this.telefono2 = PhoneNumberUtils.formatNumber(telefono2);
		this.fax = PhoneNumberUtils.formatNumber(fax);
		this.cp = cp;
		this.provincia = provincia;
		this.email = email;
		try {
			this.web = new URL(web);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cod_pais_region = cod_pais_region;
		this.plazas_disponibles = plazas_disponibles;
		this.plazas_ocupadas = plazas_ocupadas;
	}

	// Builder pattern: since we have a bunch of parameters, we will use this
	// pattern for creators
	public static class CebaderoBuilder {
		private int id;
		private String codigo;
		private String nombre;
		private String nombre2;
		private String direccion;
		private String direccion2;
		private String poblacion;
		private String telefono;
		private String telefono2;
		private String fax;
		private String cp;
		private String provincia;
		private String email;
		private String web;
		private String cod_pais_region;
		private Double plazas_disponibles;
		private int plazas_ocupadas;

		public CebaderoBuilder() {
		}

		public Cebadero buildCebadero() {
			return new Cebadero(id, codigo, nombre, nombre2, direccion,
					direccion2, poblacion, telefono, telefono2, fax, cp,
					provincia, email, web, cod_pais_region, plazas_disponibles,
					plazas_ocupadas);
		}

		public CebaderoBuilder basics(int id, String codigo) {
			this.id = id;
			this.codigo = codigo;
			return this;
		}

		public CebaderoBuilder buildNombre(String nombre) {
			this.nombre = nombre;
			return this;
		}

		public CebaderoBuilder buildNombre2(String nombre2) {
			this.nombre2 = nombre2;
			return this;
		}

		public CebaderoBuilder buildDireccion(String direccion) {
			this.direccion = direccion;
			return this;
		}

		public CebaderoBuilder buildDireccion2(String direccion2) {
			this.direccion2 = direccion2;
			return this;
		}

		public CebaderoBuilder buildPoblacion(String poblacion) {
			this.poblacion = poblacion;
			return this;
		}

		public CebaderoBuilder buildTelefono(String telefono) {
			this.telefono = telefono;
			return this;
		}

		public CebaderoBuilder buildTelefono2(String telefono2) {
			this.telefono2 = telefono2;
			return this;
		}

		public CebaderoBuilder buildFax(String fax) {
			this.fax = fax;
			return this;
		}

		public CebaderoBuilder buildCP(String cp) {
			this.cp = cp;
			return this;
		}

		public CebaderoBuilder buildProvincia(String provincia) {
			this.provincia = provincia;
			return this;
		}

		public CebaderoBuilder buildEmail(String email) {
			this.email = email;
			return this;
		}

		public CebaderoBuilder buildWeb(String web) {
			this.web = web;
			return this;
		}

		public CebaderoBuilder BuildCod_pais_region(String cod_pais_region) {
			this.cod_pais_region = cod_pais_region;
			return this;
		}

		public CebaderoBuilder BuildPlazas_disponibles(Double plazas_disponibles) {
			this.plazas_disponibles = plazas_disponibles;
			return this;
		}

		public CebaderoBuilder BuildPlazas_ocupadas(int plazas_ocupadas) {
			this.plazas_ocupadas = plazas_ocupadas;
			return this;
		}
	}
}