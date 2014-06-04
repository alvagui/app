package omicron.app.dbManagement;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static omicron.app.dbManagement.DBConsts.*;

//Collected remote DB management constants of general utility.
//
//All members of this class are immutable.
//
//DATABASE means local SQLite DB
//REMOTE means remote Navision DB

public class RemoteDBConsts {

	// Date format in server DB
	public static final String REMOTE_DATE_FORMAT = "yyyy-MM-dd";

	// List of databases
	// public static final List<String> REMOTE_TABLES_LIST = Collections
	// .unmodifiableList(Arrays.asList("Especie", "Raza", "Explotacion",
	// "Cebadero", "Ceba-Cebadero", "Corral", "Animales", "Guia",
	// "Patologias", "Lote Cebadero", "Pruebas", "Veterinario",
	// "Tratamiento Preventivo Ceba",
	// "Tratamiento Curativo Animal"));

	public static final List<String> REMOTE_TABLES_LIST = Collections
			.unmodifiableList(Arrays.asList("Especie", "Raza", "Explotacion"));

	// Maps - Key-Value Pairs where are defined the equivalent fields between
	// local and remote DBs.

	// Raza
	public static final Map<String, String> TABLE_RAZA_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�digo", DATABASE_TABLE_RAZA_KEY_CODIGO);
		aMap.put("Descripci�n", DATABASE_TABLE_RAZA_KEY_DESCRIPCION);
		TABLE_RAZA_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Especie
	public static final Map<String, String> TABLE_ESPECIE_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�digo", DATABASE_TABLE_ESPECIE_KEY_CODIGO);
		aMap.put("Descripci�n", DATABASE_TABLE_ESPECIE_KEY_DESCRIPCION);
		TABLE_ESPECIE_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Explotacion
	public static final Map<String, String> TABLE_EXPLOTACION_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�digo", DATABASE_TABLE_EXPLOTACION_KEY_CODIGO);
		aMap.put("CIF/NIF", DATABASE_TABLE_EXPLOTACION_KEY_CIF_NIF);
		aMap.put("Nombre titular", DATABASE_TABLE_EXPLOTACION_KEY_TITULAR);
		aMap.put("Fecha apertura", DATABASE_TABLE_EXPLOTACION_KEY_FAPERTURA);
		aMap.put("C�d. especie", DATABASE_TABLE_EXPLOTACION_KEY_ESPECIE);
		aMap.put("Filtro fecha", DATABASE_TABLE_EXPLOTACION_KEY_FFECHA);
		aMap.put("N� animales entrada",
				DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_EN);
		aMap.put("N� animales salida",
				DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_SA);
		TABLE_EXPLOTACION_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Cebadero
	public static final Map<String, String> TABLE_CEBADERO_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�digo", DATABASE_TABLE_CEBADERO_KEY_CODIGO);
		aMap.put("Nombre", DATABASE_TABLE_CEBADERO_KEY_NOMBRE);
		aMap.put("Nombre 2", DATABASE_TABLE_CEBADERO_KEY_NOMBRE2);
		aMap.put("Direcci�n", DATABASE_TABLE_CEBADERO_KEY_DIRECCION);
		aMap.put("Direcci�n 2", DATABASE_TABLE_CEBADERO_KEY_DIRECCION2);
		aMap.put("Poblaci�n", DATABASE_TABLE_CEBADERO_KEY_POBLACION);
		aMap.put("N� tel�fono", DATABASE_TABLE_CEBADERO_KEY_TELEFONO);
		aMap.put("N� tel�fono 2", DATABASE_TABLE_CEBADERO_KEY_TELEFONO2);
		aMap.put("N� fax", DATABASE_TABLE_CEBADERO_KEY_FAX);
		aMap.put("Contacto", DATABASE_TABLE_CEBADERO_KEY_CONTACTO);
		aMap.put("C.P.", DATABASE_TABLE_CEBADERO_KEY_CP);
		aMap.put("Provincia", DATABASE_TABLE_CEBADERO_KEY_PROVINCIA);
		aMap.put("Correo electr�nico", DATABASE_TABLE_CEBADERO_KEY_EMAIL);
		aMap.put("P�gina Web", DATABASE_TABLE_CEBADERO_KEY_WEB);
		aMap.put("C�d. pa�s/regi�n", DATABASE_TABLE_CEBADERO_KEY_CPR);
		aMap.put("N� plazas disponibles", DATABASE_TABLE_CEBADERO_KEY_PLAZAS_D);
		aMap.put("N� plazas ocupadas", DATABASE_TABLE_CEBADERO_KEY_PLAZAS_O);
		TABLE_CEBADERO_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Guia
	public static final Map<String, String> TABLE_GUIA_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("Tipo", DATABASE_TABLE_GUIA_KEY_TIPO);
		aMap.put("Fecha", DATABASE_TABLE_GUIA_KEY_FECHA);
		aMap.put("N�", DATABASE_TABLE_GUIA_KEY_NUMERO);
		aMap.put("C�d. explotaci�n", DATABASE_TABLE_GUIA_KEY_EXPLOTACION);
		aMap.put("Poblaci�n", DATABASE_TABLE_GUIA_KEY_POBLACION);
		aMap.put("Provincia", DATABASE_TABLE_GUIA_KEY_PROVINCIA);
		aMap.put("C�d. municipio", DATABASE_TABLE_GUIA_KEY_MUNICIPIO);
		aMap.put("C�d. comunidad aut�noma", DATABASE_TABLE_GUIA_KEY_CA);
		aMap.put("C�d. pa�s", DATABASE_TABLE_GUIA_KEY_PAIS);
		aMap.put("C�d. transportista", DATABASE_TABLE_GUIA_KEY_TRANSPORTISTA);
		aMap.put("C�d. explotaci�n procedencia",
				DATABASE_TABLE_GUIA_KEY_EXPLOTACION_PROCEDENCIA);
		aMap.put("Coste transporte/animal",
				DATABASE_TABLE_GUIA_KEY_COSTE_TRANSPORTE_ANIMAL);
		aMap.put("Motivo salida", DATABASE_TABLE_GUIA_KEY_MOTIVO_SALIDA);
		aMap.put("Peso medio salida machos",
				DATABASE_TABLE_GUIA_KEY_PESO_MEDIO_SALIDA_MACHOS);
		aMap.put("Peso medio salida hembras",
				DATABASE_TABLE_GUIA_KEY_PESO_MEDIO_SALIDA_HEMBRAS);
		aMap.put("Destino", DATABASE_TABLE_GUIA_KEY_DESTINO);
		aMap.put("Kg/d�a pienso consumidos macho",
				DATABASE_TABLE_GUIA_KEY_PIENSO_CONSUMIDO_MACHOS);
		aMap.put("Kg/d�a pienso consum. hembra",
				DATABASE_TABLE_GUIA_KEY_PIENSO_CONSUMIDO_HEMBRAS);
		aMap.put("Coste medio kg pienso",
				DATABASE_TABLE_GUIA_KEY_COSTE_MEDIO_PIENSO);
		aMap.put("Kg/d�a paja consumidos animal",
				DATABASE_TABLE_GUIA_KEY_PAJA_CONSUMIDA_ANIMAL);
		aMap.put("Coste medio kg paja",
				DATABASE_TABLE_GUIA_KEY_COSTE_MEDIO_PAJA);
		aMap.put("Coste fijo animal/d�a",
				DATABASE_TABLE_GUIA_KEY_COSTE_FIJO_ANIMAL);
		aMap.put("Coste variable animal/d�a",
				DATABASE_TABLE_GUIA_KEY_COSTE_VARIABLE_ANIMAL);
		aMap.put("Coste M.E.R./animal", DATABASE_TABLE_GUIA_KEY_COSTE_MER);
		aMap.put("N� animales entrada",
				DATABASE_TABLE_GUIA_KEY_N_ANIMALES_ENTRADA);
		aMap.put("N� animales salida",
				DATABASE_TABLE_GUIA_KEY_N_ANIMALES_SALIDA);
		TABLE_GUIA_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Cebadero
	public static final Map<String, String> TABLE_CEBA_CEBADERO_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�d.cebadero", DATABASE_TABLE_CEBA_CEBADERO_KEY_CEBADERO);
		aMap.put("C�digo", DATABASE_TABLE_CEBA_CEBADERO_KEY_CODIGO);
		aMap.put("Compra a-N� proveedor", DATABASE_TABLE_CEBA_CEBADERO_KEY_CNP);
		aMap.put("Compra a-Nombre", DATABASE_TABLE_CEBA_CEBADERO_KEY_CN);
		aMap.put("C�d. transportista",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_TRANSPORTISTA);
		aMap.put("Coste transporte/animal",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_COSTE_TRANSPORTE_ANIMAL);
		aMap.put("N� animales", DATABASE_TABLE_CEBA_CEBADERO_KEY_N_ANIMALES);
		aMap.put("C�d. explotaci�n",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_EXPLOTACION);
		aMap.put("Fecha entrada", DATABASE_TABLE_CEBA_CEBADERO_KEY_FECHA_E);
		aMap.put("Motivo entrada", DATABASE_TABLE_CEBA_CEBADERO_KEY_MOTIVO_E);
		aMap.put("Peso medio entrada machos",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_M);
		aMap.put("Peso medio entrada hembras",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_H);
		aMap.put("Precio compra machos",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_M);
		aMap.put("Precio compra hembras",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_H);
		aMap.put("Filtro n� gu�a",
				DATABASE_TABLE_CEBA_CEBADERO_KEY_FILTRO_NO_GUIA);
		aMap.put("N� gu�a entrada", DATABASE_TABLE_CEBA_CEBADERO_KEY_NO_GUIA_E);
		TABLE_CEBA_CEBADERO_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Corral
	public static final Map<String, String> TABLE_CORRAL_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�d. cebadero", DATABASE_TABLE_CORRAL_KEY_CEBADERO);
		aMap.put("N� corral", DATABASE_TABLE_CORRAL_KEY_N_CORRAL);
		aMap.put("N� plazas disponibles",
				DATABASE_TABLE_CORRAL_KEY_N_PLAZAS_DISP);
		aMap.put("N� plazas ocupadas", DATABASE_TABLE_CORRAL_KEY_N_PLAZAS_OCUP);
		aMap.put("Pesebre con b�scula", DATABASE_TABLE_CORRAL_KEY_BASCULA);
		TABLE_CORRAL_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Lote-cebadero
	public static final Map<String, String> TABLE_LOTE_CEBADERO_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�d. cebadero", DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBADERO);
		aMap.put("C�d. ceba", DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBA);
		aMap.put("N�", DATABASE_TABLE_LOTE_CEBADERO_KEY_NUMERO);
		aMap.put("N� animales", DATABASE_TABLE_LOTE_CEBADERO_KEY_N_ANIMALES);
		aMap.put("Lote con bajas",
				DATABASE_TABLE_LOTE_CEBADERO_KEY_LOTE_CON_BAJAS);
		aMap.put("Kg/d�a pienso consumidos",
				DATABASE_TABLE_LOTE_CEBADERO_KEY_PIENSO_CONSUMIDO);
		aMap.put("Coste medio pienso",
				DATABASE_TABLE_LOTE_CEBADERO_KEY_COSTE_MEDIO_PIENSO);
		TABLE_LOTE_CEBADERO_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Animales
	public static final Map<String, String> TABLE_ANIMALES_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("N� identificaci�n", DATABASE_TABLE_ANIMALES_KEY_N_ID);
		aMap.put("Fecha nacimiento", DATABASE_TABLE_ANIMALES_KEY_F_NAC);
		aMap.put("Sexo", DATABASE_TABLE_ANIMALES_KEY_SEXO);
		aMap.put("C�d. raza", DATABASE_TABLE_ANIMALES_KEY_RAZA);
		aMap.put("N� identificaci�n madre",
				DATABASE_TABLE_ANIMALES_KEY_N_ID_MADRE);
		aMap.put("C�d. explotaci�n nacimiento",
				DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_NACIMIENTO);
		aMap.put("C�d. pa�s nacimiento",
				DATABASE_TABLE_ANIMALES_KEY_PAIS_NACIMIENTO);
		aMap.put("C�d. pa�s engorde",
				DATABASE_TABLE_ANIMALES_KEY_PAIS_NACIMIENTO);
		aMap.put("Alias", DATABASE_TABLE_ANIMALES_KEY_PAIS_ENGORDE);
		aMap.put("Vacunado en origen", DATABASE_TABLE_ANIMALES_KEY_ALIAS);
		aMap.put("C�d. cebadero",
				DATABASE_TABLE_ANIMALES_KEY_VACUNADO_EN_ORIGEN);
		aMap.put("C�d. ceba", DATABASE_TABLE_ANIMALES_KEY_CEBADERO);
		aMap.put("N� corral", DATABASE_TABLE_ANIMALES_KEY_CEBA);
		aMap.put("N� lote cebadero", DATABASE_TABLE_ANIMALES_KEY_CORRAL);
		aMap.put("C�d. especie", DATABASE_TABLE_ANIMALES_KEY_LOTE_CEBADERO);
		aMap.put("C�d. explotaci�n", DATABASE_TABLE_ANIMALES_KEY_ESPECIE);
		aMap.put("Fecha entrada", DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION);
		aMap.put("N� gu�a entrada", DATABASE_TABLE_ANIMALES_KEY_F_ENTRADA);
		aMap.put("Peso individual entrada",
				DATABASE_TABLE_ANIMALES_KEY_N_GUIA_ENTRADA);
		aMap.put("Peso medio entrada",
				DATABASE_TABLE_ANIMALES_KEY_PESO_IND_ENTRADA);
		aMap.put("Precio medio compra",
				DATABASE_TABLE_ANIMALES_KEY_PESO_MED_ENTRADA);
		aMap.put("Motivo entrada", DATABASE_TABLE_ANIMALES_KEY_PMC);
		aMap.put("C�d. explotaci�n procedencia",
				DATABASE_TABLE_ANIMALES_KEY_MOTIVO_ENTRADA);
		aMap.put("Edad entrada (d�as)",
				DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_PROCEDENCIA);
		aMap.put("Fecha salida", DATABASE_TABLE_ANIMALES_KEY_EDAD_ENTRADA);
		aMap.put("N� gu�a salida", DATABASE_TABLE_ANIMALES_KEY_F_SALIDA);
		aMap.put("Peso individual salida",
				DATABASE_TABLE_ANIMALES_KEY_N_GUIA_SALIDA);
		aMap.put("Peso medio salida",
				DATABASE_TABLE_ANIMALES_KEY_PESO_IND_SALIDA);
		aMap.put("Peso final correguido",
				DATABASE_TABLE_ANIMALES_KEY_PESO_FINAL_CORREGIDO);
		aMap.put("Peso canal", DATABASE_TABLE_ANIMALES_KEY_PESO_CANAL);
		aMap.put("Clasificaci�n canal",
				DATABASE_TABLE_ANIMALES_KEY_CLASIFICACION_CANAL);
		aMap.put("Motivo salida", DATABASE_TABLE_ANIMALES_KEY_MOTIVO_SALIDA);
		aMap.put("Destino", DATABASE_TABLE_ANIMALES_KEY_DESTINO);
		aMap.put("Edad salida (d�as)", DATABASE_TABLE_ANIMALES_KEY_EDAD_SALIDA);
		aMap.put("D�as estancia cebadero",
				DATABASE_TABLE_ANIMALES_KEY_DIAS_ESTANCIA_CEBADERO);
		aMap.put("Kg. engordados", DATABASE_TABLE_ANIMALES_KEY_KG_ENGORDADOS);
		aMap.put("Ganancia media diar�a (kg.)",
				DATABASE_TABLE_ANIMALES_KEY_GANANCIA_MEDIA_DIARIA);
		aMap.put("Coste tratamiento curativo",
				DATABASE_TABLE_ANIMALES_KEY_COSTE_TRATAMIENTO_CURATIVO);
		aMap.put("Indice transformaci�n",
				DATABASE_TABLE_ANIMALES_KEY_INDICE_TRANSFORMACION);

		TABLE_ANIMALES_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Patolog�as
	public static final Map<String, String> TABLE_PATOLOGIAS_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�digo", DATABASE_TABLE_PATOLOGIAS_KEY_CODIGO);
		aMap.put("Descripci�n", DATABASE_TABLE_PATOLOGIAS_KEY_DESCRIPCION);
		aMap.put("Enfermedad infecciosa",
				DATABASE_TABLE_PATOLOGIAS_KEY_ENFERMEDAD_INFECCIOSA);
		aMap.put("Enfermedad parasitaria",
				DATABASE_TABLE_PATOLOGIAS_KEY_ENFERMEDAD_PARASITARIA);
		TABLE_PATOLOGIAS_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Pruebas
	public static final Map<String, String> TABLE_PRUEBAS_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�digo", DATABASE_TABLE_PRUEBAS_KEY_CODIGO);
		aMap.put("Descripci�n", DATABASE_TABLE_PRUEBAS_KEY_DESCRIPCION);
		TABLE_PRUEBAS_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Veterinario
	public static final Map<String, String> TABLE_VETERINARIO_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�digo", DATABASE_TABLE_VETERINARIO_KEY_CODIGO);
		aMap.put("Nombre", DATABASE_TABLE_VETERINARIO_KEY_NOMBRE);
		aMap.put("Direcci�n", DATABASE_TABLE_VETERINARIO_KEY_DIRECCION);
		aMap.put("Poblaci�n", DATABASE_TABLE_VETERINARIO_KEY_POBLACION);
		aMap.put("C. P.", DATABASE_TABLE_VETERINARIO_KEY_CP);
		aMap.put("Provincia", DATABASE_TABLE_VETERINARIO_KEY_PROVINCIA);
		aMap.put("CIF/NIF", DATABASE_TABLE_VETERINARIO_KEY_CIF_NIF);
		aMap.put("N� colegiado", DATABASE_TABLE_VETERINARIO_KEY_N_COLEGIADO);
		aMap.put("Provincia colegiaci�n",
				DATABASE_TABLE_VETERINARIO_KEY_PROVINCIA_COLEGIACION);
		aMap.put("N� serie receta",
				DATABASE_TABLE_VETERINARIO_KEY_N_SERIE_RECETA);
		TABLE_VETERINARIO_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Tratamiento preventivo ceba
	public static final Map<String, String> TABLE_TRAT_PREV_CEBA_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�d. cebadero", DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBADERO);
		aMap.put("C�d. ceba", DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBA);
		aMap.put("N� producto", DATABASE_TABLE_TRAT_PREV_CEBA_KEY_N_PRODUCTO);
		aMap.put("C�d. prueba", DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRUEBA);
		aMap.put("Precio unitario",
				DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRECIO_UNITARIO);
		aMap.put("Precio/ml", DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRECIO_ML);
		aMap.put("N� dosis", DATABASE_TABLE_TRAT_PREV_CEBA_KEY_N_DOSIS);
		aMap.put("Fecha", DATABASE_TABLE_TRAT_PREV_CEBA_KEY_FECHA);
		TABLE_TRAT_PREV_CEBA_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// Tratamiento curativo animal
	public static final Map<String, String> TABLE_TRAT_CUR_ANIMAL_PAIRS_MAP;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("C�d. cebadero", DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBADERO);
		aMap.put("C�d. ceba", DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBA);
		aMap.put("N� identificaci�n animal",
				DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_ANIMAL);
		aMap.put("N� producto", DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRODUCTO);
		aMap.put("Fecha", DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_FECHA);
		aMap.put("C�d. explotaci�n",
				DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_EXPLOTACION);
		aMap.put("C�d. patolog�a", DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PATOLOGIA);
		aMap.put("Precio unitario",
				DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRECIO_UNITARIO);
		aMap.put("Precio/ml", DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRECIO_ML);
		aMap.put("N� dosis", DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_N_DOSIS);
		TABLE_TRAT_CUR_ANIMAL_PAIRS_MAP = Collections.unmodifiableMap(aMap);
	}

	// PRIVATE //

	/**
	 * The caller references the constants using <tt>DBConsts.STRING</tt>, and
	 * so on. Thus, the caller should be prevented from constructing objects of
	 * this class, by declaring this private constructor.
	 */
	private RemoteDBConsts() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}
}
