package omicron.app.DbManagement;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {
	
    private static final String DATABASE_NAME = "omicron.db";
//    Whenever DB structure is changed, must be incremented
    private static final int DATABASE_VERSION = 5;
	
//    Log Tag
    private static final String TAG = "DbAdapter";
    
//    Database commons
    protected static final String DATABASE_CREATE_STATEMENT_START = "create table ";
    protected static final String DATABASE_STATEMENT_END = ");";
    
    protected static final String DATABASE_INSERT_STATEMENT_START = "insert into ";
    
//  Table: explotacion  
    protected static final String DATABASE_TABLE_EXPLOTACION = "explotacion";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_ID = "_id";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_CODIGO = "codigo";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_CIF_NIF = "cif_nif";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_TITULAR= "nombre_titular";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_FAPERTURA = "fecha_apertura";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_ESPECIE = "cod_especie";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_EN = "no_animales_entrada";
    protected static final String DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_SA = "no_animales_salida";
//    Create Table statement
    protected static final String DATABASE_CREATE_EXPLOTACION =
            DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_EXPLOTACION  
            +" (" + DATABASE_TABLE_EXPLOTACION_KEY_ID + 
            " integer primary key autoincrement, "
            + DATABASE_TABLE_EXPLOTACION_KEY_CODIGO + " text, "
            + DATABASE_TABLE_EXPLOTACION_KEY_CIF_NIF + " text, "
            + DATABASE_TABLE_EXPLOTACION_KEY_TITULAR + " text, "
            + DATABASE_TABLE_EXPLOTACION_KEY_FAPERTURA + " date, "
            + DATABASE_TABLE_EXPLOTACION_KEY_ESPECIE + " text, "
            + DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_EN + " integer, "
            + DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_SA + " integer"
            + DATABASE_STATEMENT_END;

//TODO: decidir cómo se quiere implementar esta tabla. Preferencias con pares K-V 
    //  Table: configuracion_cebadero
//    private static final String DATABASE_TABLE_CONFIGURACION_CEBADERO = "configuracion_cebadero";
//    private static final String DATABASE_TABLE_CONFIGURACION_CEBADERO_KEY_ID = "_id";
//    private static final String DATABASE_TABLE_CONFIGURACION_CEBADERO_KEY_PK = "clave_primaria";
//    private static final String DATABASE_TABLE_CONFIGURACION_CEBADERO_KEY_PCTC_PS = "porcentaje_corrector_peso_salida";
//    private static final String DATABASE_TABLE_CONFIGURACION_CEBADERO_KEY_VETERINARIO_RECETAS = "cod_veterinario_recetas";

    //  Table: cebadero  
    protected static final String DATABASE_TABLE_CEBADERO = "cebadero";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_ID = "_id";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_CODIGO = "codigo";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_NOMBRE = "nombre";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_NOMBRE2= "nombre2";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_DIRECCION = "direccion";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_DIRECCION2 = "direccion2";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_POBLACION = "poblacion";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_TELEFONO = "no_telefono";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_TELEFONO2 = "no_telefono2";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_FAX = "no_fax";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_CONTACTO = "contacto";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_CP = "cp";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_PROVINCIA = "provincia";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_EMAIL = "correo_electronico";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_WEB = "pagina_web";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_CPR = "cod_pais_region";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_PLAZAS_D = "no_plazas_disponibles";
    protected static final String DATABASE_TABLE_CEBADERO_KEY_PLAZAS_O = "no_plazas_ocupadas";
  //Create Table statement
    protected static final String DATABASE_CREATE_CEBADERO =
          DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_CEBADERO  
          +" (" + DATABASE_TABLE_CEBADERO_KEY_ID + 
          " integer primary key autoincrement, "
          + DATABASE_TABLE_CEBADERO_KEY_CODIGO + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_NOMBRE + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_NOMBRE2 + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_DIRECCION + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_DIRECCION2 + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_POBLACION + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_TELEFONO + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_TELEFONO2 + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_FAX + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_CONTACTO + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_CP + " integer, "
          + DATABASE_TABLE_CEBADERO_KEY_PROVINCIA + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_EMAIL + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_WEB + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_CPR + " text, "
          + DATABASE_TABLE_CEBADERO_KEY_PLAZAS_D + " real, "
          + DATABASE_TABLE_CEBADERO_KEY_PLAZAS_O + " integer"
          + DATABASE_STATEMENT_END;

    
    //  Table: guia
  	protected static final String DATABASE_TABLE_GUIA = "guia";
    protected static final String DATABASE_TABLE_GUIA_KEY_ID = "_id";
	protected static final String DATABASE_TABLE_GUIA_KEY_TIPO = "tipo";
	protected static final String DATABASE_TABLE_GUIA_KEY_FECHA = "fecha";
	protected static final String DATABASE_TABLE_GUIA_KEY_NUMERO = "numero";
	protected static final String DATABASE_TABLE_GUIA_KEY_EXPLOTACION = "explotacion";
	protected static final String DATABASE_TABLE_GUIA_KEY_POBLACION = "poblacion";
	protected static final String DATABASE_TABLE_GUIA_KEY_PROVINCIA = "provincia";
	protected static final String DATABASE_TABLE_GUIA_KEY_MUNICIPIO = "cod_municipio";
	protected static final String DATABASE_TABLE_GUIA_KEY_CA = "comunidad_autonoma";
	protected static final String DATABASE_TABLE_GUIA_KEY_PAIS = "cod_pais";
	protected static final String DATABASE_TABLE_GUIA_KEY_TRANSPORTISTA = "cod_transportista";
	protected static final String DATABASE_TABLE_GUIA_KEY_EXPLOTACION_PROCEDENCIA = "cod_explotacion_procedencia";
	protected static final String DATABASE_TABLE_GUIA_KEY_COSTE_TRANSPORTE_ANIMAL = "coste_transporte_animal";
	protected static final String DATABASE_TABLE_GUIA_KEY_MOTIVO_SALIDA = "motivo_salida";
	protected static final String DATABASE_TABLE_GUIA_KEY_PESO_MEDIO_SALIDA_MACHOS = "peso_medio_salida_machos";
	protected static final String DATABASE_TABLE_GUIA_KEY_PESO_MEDIO_SALIDA_HEMBRAS = "peso_medio_salida_hembras";
	protected static final String DATABASE_TABLE_GUIA_KEY_DESTINO = "destino";
	protected static final String DATABASE_TABLE_GUIA_KEY_PIENSO_CONSUMIDO_MACHOS = "kg_dia_pienso_consumido_macho";
	protected static final String DATABASE_TABLE_GUIA_KEY_PIENSO_CONSUMIDO_HEMBRAS = "kg_dia_pienso_consumido_hembras";
	protected static final String DATABASE_TABLE_GUIA_KEY_COSTE_MEDIO_PIENSO = "coste_medio_kg_pienso";
	protected static final String DATABASE_TABLE_GUIA_KEY_PAJA_CONSUMIDA_ANIMAL = "kg_dia_paja_consumidos_animal";
	protected static final String DATABASE_TABLE_GUIA_KEY_COSTE_MEDIO_PAJA = "coste_medio_kg_paja";
	protected static final String DATABASE_TABLE_GUIA_KEY_COSTE_FIJO_ANIMAL = "coste_fijo_animal_dia";
	protected static final String DATABASE_TABLE_GUIA_KEY_COSTE_VARIABLE_ANIMAL = "coste_variable_animal_dia";
	protected static final String DATABASE_TABLE_GUIA_KEY_COSTE_MER = "coste_mer_animal";
	protected static final String DATABASE_TABLE_GUIA_KEY_N_ANIMALES_ENTRADA = "no_animales_entrada";
	protected static final String DATABASE_TABLE_GUIA_KEY_N_ANIMALES_SALIDA = "no_animales_salida";
	//Create Table statement
	protected static final String DATABASE_CREATE_GUIA =
		  DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_GUIA 
		  +" (" + DATABASE_TABLE_GUIA_KEY_ID + 
		  " integer primary key autoincrement, "
		  + DATABASE_TABLE_GUIA_KEY_TIPO + " text, "
		  + DATABASE_TABLE_GUIA_KEY_FECHA + " date, "
		  + DATABASE_TABLE_GUIA_KEY_NUMERO + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_EXPLOTACION + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_POBLACION + " text, "
		  + DATABASE_TABLE_GUIA_KEY_PROVINCIA + " text, "
		  + DATABASE_TABLE_GUIA_KEY_MUNICIPIO + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_CA + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_PAIS + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_TRANSPORTISTA + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_EXPLOTACION_PROCEDENCIA + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_COSTE_TRANSPORTE_ANIMAL + " real, "
		  + DATABASE_TABLE_GUIA_KEY_MOTIVO_SALIDA + " text, "
		  + DATABASE_TABLE_GUIA_KEY_PESO_MEDIO_SALIDA_MACHOS + " real, "
		  + DATABASE_TABLE_GUIA_KEY_PESO_MEDIO_SALIDA_HEMBRAS + " real, "
		  + DATABASE_TABLE_GUIA_KEY_DESTINO + " text, "
		  + DATABASE_TABLE_GUIA_KEY_PIENSO_CONSUMIDO_MACHOS + " real, "
		  + DATABASE_TABLE_GUIA_KEY_PIENSO_CONSUMIDO_HEMBRAS + " real, "
		  + DATABASE_TABLE_GUIA_KEY_COSTE_MEDIO_PIENSO + " real, "
		  + DATABASE_TABLE_GUIA_KEY_PAJA_CONSUMIDA_ANIMAL + " real, "
		  + DATABASE_TABLE_GUIA_KEY_COSTE_MEDIO_PAJA + " real, "
		  + DATABASE_TABLE_GUIA_KEY_COSTE_FIJO_ANIMAL + " real, "
		  + DATABASE_TABLE_GUIA_KEY_COSTE_VARIABLE_ANIMAL + " real, "
		  + DATABASE_TABLE_GUIA_KEY_COSTE_MER + " real, "
		  + DATABASE_TABLE_GUIA_KEY_N_ANIMALES_ENTRADA + " integer, "
		  + DATABASE_TABLE_GUIA_KEY_N_ANIMALES_SALIDA + " integer, "
		  + "FOREIGN KEY("+DATABASE_TABLE_GUIA_KEY_EXPLOTACION +") REFERENCES " + DATABASE_TABLE_EXPLOTACION + "( "+DATABASE_TABLE_EXPLOTACION_KEY_ID + "), "
		  + "FOREIGN KEY("+DATABASE_TABLE_GUIA_KEY_EXPLOTACION_PROCEDENCIA +") REFERENCES " + DATABASE_TABLE_EXPLOTACION + "( "+DATABASE_TABLE_EXPLOTACION_KEY_ID + ")"
		  + DATABASE_STATEMENT_END;
        
//Table: ceba_cebadero
  protected static final String DATABASE_TABLE_CEBA_CEBADERO = "ceba_cebadero";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CEBADERO = "codigo_cebadero";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CODIGO = "codigo";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CNP= "compra_a_no_proveedor";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CN= "compra_a_nombre";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_TRANSPORTISTA = "cod_transportista";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_COSTE_TRANSPORTE_ANIMAL = "conste_transporte_animal";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_N_ANIMALES = "no_animales";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_EXPLOTACION = "con_explotacion";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_FECHA_E = "fecha_entrada";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_MOTIVO_E = "motivo_entrada";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_M = "peso_medio_entrada_machos";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_H = "peso_medio_entrada_hembras";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_M = "precio_compra_machos";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_H = "precio_compra_hembras";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_FILTRO_NO_GUIA = "filtro_no_guia";
  protected static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_NO_GUIA_E = "no_guia_entrada";
//Create Table statement
  protected static final String DATABASE_CREATE_CEBA_CEBADERO =
        DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_CEBA_CEBADERO  
        +" (" + DATABASE_TABLE_CEBA_CEBADERO_KEY_ID + 
        " integer primary key autoincrement, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_CEBADERO + " integer, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_CODIGO + " text, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_CNP + " text, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_CN + " text, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_TRANSPORTISTA + " text, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_COSTE_TRANSPORTE_ANIMAL + " real, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_N_ANIMALES + " integer, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_EXPLOTACION + " text, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_FECHA_E + " date, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_MOTIVO_E + " text, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_M + " real, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_H + " real, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_M + " real, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_H + " real, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_FILTRO_NO_GUIA + " text, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_NO_GUIA_E + " integer, "
        + "FOREIGN KEY("+DATABASE_TABLE_CEBA_CEBADERO_KEY_CEBADERO+") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_CEBA_CEBADERO_KEY_NO_GUIA_E+") REFERENCES " + DATABASE_TABLE_GUIA + "( "+DATABASE_TABLE_GUIA_KEY_ID + ")"
        + DATABASE_STATEMENT_END;

  //  Table: corral
  protected static final String DATABASE_TABLE_CORRAL = "corral";
  protected static final String DATABASE_TABLE_CORRAL_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_CORRAL_KEY_CEBADERO = "cod_cebadero";
  protected static final String DATABASE_TABLE_CORRAL_KEY_N_CORRAL = "no_corral";
  protected static final String DATABASE_TABLE_CORRAL_KEY_N_PLAZAS_DISP = "no_plazas_disponibles";
  protected static final String DATABASE_TABLE_CORRAL_KEY_N_PLAZAS_OCUP = "no_plazas_ocupadas";
  protected static final String DATABASE_TABLE_CORRAL_KEY_BASCULA = "pesebre_con_bascula";
//Create Table statement
  protected static final String DATABASE_CREATE_CORRAL =
        DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_CORRAL  
        +" (" + DATABASE_TABLE_CORRAL_KEY_ID + 
        " integer primary key autoincrement, "
        + DATABASE_TABLE_CORRAL_KEY_CEBADERO + " text, "
        + DATABASE_TABLE_CORRAL_KEY_N_CORRAL + " text, "
        + DATABASE_TABLE_CORRAL_KEY_N_PLAZAS_DISP + " real, "
        + DATABASE_TABLE_CORRAL_KEY_N_PLAZAS_OCUP + " integer, "
        + DATABASE_TABLE_CORRAL_KEY_BASCULA + " integer, "
        + "FOREIGN KEY("+DATABASE_TABLE_CORRAL_KEY_CEBADERO +") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID + ")"
        + DATABASE_STATEMENT_END;

  //  Table: lote_cebadero
  protected static final String DATABASE_TABLE_LOTE_CEBADERO = "lote_cebadero";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBADERO = "cod_cebadero";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBA = "cod_ceba";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_NUMERO = "numero";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_N_ANIMALES = "no_animales";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_LOTE_CON_BAJAS = "lote_con_bajas";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_PIENSO_CONSUMIDO = "kg_dia_pienso_consumidos";
  protected static final String DATABASE_TABLE_LOTE_CEBADERO_KEY_COSTE_MEDIO_PIENSO = "coste_medio_pienso";
  //Create Table statement
  protected static final String DATABASE_CREATE_LOTE_CEBADERO =
		  DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_LOTE_CEBADERO
		  +" (" + DATABASE_TABLE_LOTE_CEBADERO_KEY_ID + 
		  " integer primary key autoincrement, "
		  + DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBADERO + " integer, "
		  + DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBA + " integer, "
		  + DATABASE_TABLE_LOTE_CEBADERO_KEY_NUMERO + " text, "
		  + DATABASE_TABLE_LOTE_CEBADERO_KEY_N_ANIMALES + " integer, "
		  + DATABASE_TABLE_LOTE_CEBADERO_KEY_LOTE_CON_BAJAS + " integer, "
		  + DATABASE_TABLE_LOTE_CEBADERO_KEY_PIENSO_CONSUMIDO + " real, "
		  + DATABASE_TABLE_LOTE_CEBADERO_KEY_COSTE_MEDIO_PIENSO + " real, "
		  + "FOREIGN KEY("+DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBADERO +") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID + "), "		  
		  + "FOREIGN KEY("+DATABASE_TABLE_LOTE_CEBADERO_KEY_CEBA +") REFERENCES " + DATABASE_TABLE_CEBA_CEBADERO + "( "+DATABASE_TABLE_CEBA_CEBADERO_KEY_ID + ") "		  
		  + DATABASE_STATEMENT_END;

  //  Table: raza
  protected static final String DATABASE_TABLE_RAZA = "raza";
  protected static final String DATABASE_TABLE_RAZA_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_RAZA_KEY_CODIGO = "codigo";
  protected static final String DATABASE_TABLE_RAZA_KEY_DESCRIPCION = "descripcion";
//Create Table statement
  protected static final String DATABASE_CREATE_RAZA =
		  DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_RAZA
		  +" (" + DATABASE_TABLE_RAZA_KEY_ID + 
		  " integer primary key autoincrement, "
		  + DATABASE_TABLE_RAZA_KEY_CODIGO + " text, "
		  + DATABASE_TABLE_RAZA_KEY_DESCRIPCION + " text"
		  + DATABASE_STATEMENT_END;
  
  //  Table: especie
  protected static final String DATABASE_TABLE_ESPECIE = "especie";
  protected static final String DATABASE_TABLE_ESPECIE_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_ESPECIE_KEY_CODIGO = "codigo";
  protected static final String DATABASE_TABLE_ESPECIE_KEY_DESCRIPCION = "descripcion";
//Create Table statement
  protected static final String DATABASE_CREATE_ESPECIE =
		  DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_ESPECIE
		  +" (" + DATABASE_TABLE_ESPECIE_KEY_ID + 
		  " integer primary key autoincrement, "
		  + DATABASE_TABLE_ESPECIE_KEY_CODIGO + " text, "
		  + DATABASE_TABLE_ESPECIE_KEY_DESCRIPCION + " text"
		  + DATABASE_STATEMENT_END;

  //  Table: animales
  protected static final String DATABASE_TABLE_ANIMALES = "animales";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_N_ID = "no_identificacion";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_F_NAC = "fecha_nacimiento";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_SEXO = "sexo";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_RAZA = "cod_raza";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_N_ID_MADRE = "no_identificacion_madre";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_NACIMIENTO = "cod_explotacion_nacimiento";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PAIS_NACIMIENTO = "cod_pais_nacimiento";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PAIS_ENGORDE = "cod_pais_engorde";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_ALIAS = "alias";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_VACUNADO_EN_ORIGEN = "vacunado_en_origen";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_CEBADERO = "cod_cebadero";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_CEBA = "cod_ceba";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_CORRAL = "no_corral";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_LOTE_CEBADERO = "no_lote_cebadero";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_ESPECIE = "cod_especie";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION = "cod_explotacion";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_F_ENTRADA = "fecha_entrada";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_N_GUIA_ENTRADA = "no_guia_entrada";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PESO_IND_ENTRADA = "peso_individual_entrada";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PESO_MED_ENTRADA = "peso_medio_entrada";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PMC = "precio_medio_compra";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_MOTIVO_ENTRADA = "motivo_entrada";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_PROCEDENCIA = "cod_explotacion_procedencia";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_EDAD_ENTRADA = "edad_entrada";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_F_SALIDA = "fecha_salida";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_N_GUIA_SALIDA = "no_guia_salida";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PESO_IND_SALIDA = "peso_individual_salida";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PESO_MED_SALIDA = "peso_medio_salida";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PESO_FINAL_CORREGIDO = "peso_final_corregido";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_PESO_CANAL = "peso_canal";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_CLASIFICACION_CANAL = "clasificacion_canal";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_MOTIVO_SALIDA = "motivo_salida";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_DESTINO = "destino";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_EDAD_SALIDA = "edad_salida";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_DIAS_ESTANCIA_CEBADERO = "dias_estancia_cebadero";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_KG_ENGORDADOS = "kg_engordados";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_GANANCIA_MEDIA_DIARIA = "ganancia_media_diaria";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_COSTE_TRATAMIENTO_CURATIVO = "coste_tratamiento_curativo";
  protected static final String DATABASE_TABLE_ANIMALES_KEY_INDICE_TRANSFORMACION = "indice_transformacion";
//Create Table statement
  protected static final String DATABASE_CREATE_ANIMALES =
        DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_ANIMALES 
        +" (" + DATABASE_TABLE_ANIMALES_KEY_ID + 
        " integer primary key autoincrement, "
        + DATABASE_TABLE_ANIMALES_KEY_N_ID + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_F_NAC + " date, "
        + DATABASE_TABLE_ANIMALES_KEY_SEXO + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_RAZA + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_N_ID_MADRE + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_NACIMIENTO + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_PAIS_NACIMIENTO + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_PAIS_ENGORDE + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_ALIAS + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_VACUNADO_EN_ORIGEN + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_CEBADERO + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_CEBA + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_CORRAL + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_LOTE_CEBADERO + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_ESPECIE + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_F_ENTRADA + " date, "
        + DATABASE_TABLE_ANIMALES_KEY_N_GUIA_ENTRADA + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_PESO_IND_ENTRADA + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_PESO_MED_ENTRADA + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_PMC + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_MOTIVO_ENTRADA + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_PROCEDENCIA + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_EDAD_ENTRADA + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_F_SALIDA + " date, "
        + DATABASE_TABLE_ANIMALES_KEY_N_GUIA_SALIDA + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_PESO_IND_SALIDA + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_PESO_MED_SALIDA + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_PESO_FINAL_CORREGIDO + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_PESO_CANAL + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_CLASIFICACION_CANAL + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_MOTIVO_SALIDA + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_DESTINO + " text, "
        + DATABASE_TABLE_ANIMALES_KEY_EDAD_SALIDA + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_DIAS_ESTANCIA_CEBADERO + " integer, "
        + DATABASE_TABLE_ANIMALES_KEY_KG_ENGORDADOS + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_GANANCIA_MEDIA_DIARIA + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_COSTE_TRATAMIENTO_CURATIVO + " real, "
        + DATABASE_TABLE_ANIMALES_KEY_INDICE_TRANSFORMACION + " real, "            
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_RAZA +") REFERENCES " + DATABASE_TABLE_RAZA + "( "+DATABASE_TABLE_RAZA_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_N_ID_MADRE +") REFERENCES " + DATABASE_TABLE_ANIMALES + "( "+DATABASE_TABLE_ANIMALES_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_NACIMIENTO +") REFERENCES " + DATABASE_TABLE_EXPLOTACION + "( "+DATABASE_TABLE_EXPLOTACION_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_CEBADERO +") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_CEBA +") REFERENCES " + DATABASE_TABLE_CEBA_CEBADERO + "( "+DATABASE_TABLE_CEBA_CEBADERO_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_CORRAL +") REFERENCES " + DATABASE_TABLE_CORRAL + "( "+DATABASE_TABLE_CORRAL_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_LOTE_CEBADERO +") REFERENCES " + DATABASE_TABLE_LOTE_CEBADERO + "( "+DATABASE_TABLE_LOTE_CEBADERO_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_ESPECIE +") REFERENCES " + DATABASE_TABLE_ESPECIE + "( "+DATABASE_TABLE_ESPECIE_KEY_ID + "),"
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION +") REFERENCES " + DATABASE_TABLE_EXPLOTACION + "( "+DATABASE_TABLE_EXPLOTACION_KEY_ID + "),"        
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_N_GUIA_ENTRADA +") REFERENCES " + DATABASE_TABLE_GUIA + "( "+DATABASE_TABLE_GUIA_KEY_ID + "),"        
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_EXPLOTACION_PROCEDENCIA +") REFERENCES " + DATABASE_TABLE_EXPLOTACION + "( "+DATABASE_TABLE_EXPLOTACION_KEY_ID + "),"        
        + "FOREIGN KEY("+DATABASE_TABLE_ANIMALES_KEY_N_GUIA_SALIDA +") REFERENCES " + DATABASE_TABLE_GUIA + "( "+DATABASE_TABLE_GUIA_KEY_ID + ")"        
        + DATABASE_STATEMENT_END;
  
  //  Table: patologias
  protected static final String DATABASE_TABLE_PATOLOGIAS = "patologias";
  protected static final String DATABASE_TABLE_PATOLOGIAS_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_PATOLOGIAS_KEY_CODIGO = "codigo";
  protected static final String DATABASE_TABLE_PATOLOGIAS_KEY_DESCRIPCION = "descripcion";
  protected static final String DATABASE_TABLE_PATOLOGIAS_KEY_ENFERMEDAD_INFECCIOSA = "enfermedad_infecciosa";
  protected static final String DATABASE_TABLE_PATOLOGIAS_KEY_ENFERMEDAD_PARASITARIA = "enfermedad_parasitaria";
//Create Table statement
  protected static final String DATABASE_CREATE_PATOLOGIAS =
        DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_PATOLOGIAS
        +" (" + DATABASE_TABLE_PATOLOGIAS_KEY_ID + 
        " integer primary key autoincrement, "
        + DATABASE_TABLE_PATOLOGIAS_KEY_CODIGO + " text, "
        + DATABASE_TABLE_PATOLOGIAS_KEY_DESCRIPCION + " text, "
        + DATABASE_TABLE_PATOLOGIAS_KEY_ENFERMEDAD_INFECCIOSA + " integer, "
        + DATABASE_TABLE_PATOLOGIAS_KEY_ENFERMEDAD_PARASITARIA + " integer "
        + DATABASE_STATEMENT_END;


  //  Table: pruebas
  protected static final String DATABASE_TABLE_PRUEBAS = "pruebas";
  protected static final String DATABASE_TABLE_PRUEBAS_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_PRUEBAS_KEY_CODIGO = "codigo";
  protected static final String DATABASE_TABLE_PRUEBAS_KEY_DESCRIPCION = "descripcion";
//Create Table statement
  protected static final String DATABASE_CREATE_PRUEBAS =
        DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_PRUEBAS
        +" (" + DATABASE_TABLE_PRUEBAS_KEY_ID + 
        " integer primary key autoincrement, "
        + DATABASE_TABLE_PRUEBAS_KEY_CODIGO + " text, "
        + DATABASE_TABLE_PRUEBAS_KEY_DESCRIPCION + " text"
        + DATABASE_STATEMENT_END;


  //  Table: veterinario
  protected static final String DATABASE_TABLE_VETERINARIO = "veterinario";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_CODIGO = "codigo";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_NOMBRE = "nombre";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_DIRECCION = "direccion";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_POBLACION = "poblacion";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_CP = "cp";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_PROVINCIA = "provincia";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_CIF_NIF = "cif_nif";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_N_COLEGIADO = "no_colegiado";
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_PROVINCIA_COLEGIACION = "provincia_colegiacion";  
  protected static final String DATABASE_TABLE_VETERINARIO_KEY_N_SERIE_RECETA = "no_serie_receta";  
//Create Table statement
  protected static final String DATABASE_CREATE_VETERINARIO =
		  DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_VETERINARIO
		  +" (" + DATABASE_TABLE_VETERINARIO_KEY_ID + 
		  " integer primary key autoincrement, "
		  + DATABASE_TABLE_VETERINARIO_KEY_CODIGO + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_NOMBRE + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_DIRECCION + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_POBLACION + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_CP + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_PROVINCIA + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_CIF_NIF + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_N_COLEGIADO + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_PROVINCIA_COLEGIACION + " text, "
		  + DATABASE_TABLE_VETERINARIO_KEY_N_SERIE_RECETA + " text"
		  + DATABASE_STATEMENT_END;
  
  //  Table: tratamiento preventivo ceba
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA = "tratamiento_preventivo_ceba";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBADERO = "cod_cebadero";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBA = "cod_ceba";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_N_PRODUCTO = "no_producto";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRUEBA = "cod_prueba";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRECIO_UNITARIO = "precio_unitario";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRECIO_ML = "precio_ml";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_N_DOSIS = "no_dosis";
  protected static final String DATABASE_TABLE_TRAT_PREV_CEBA_KEY_FECHA = "fecha";
//Create Table statement
  protected static final String DATABASE_CREATE_TRAT_PREV_CEBA =
		  DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_TRAT_PREV_CEBA
		  +" (" + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_ID + 
		  " integer primary key autoincrement, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBADERO + " integer, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBA + " integer, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_N_PRODUCTO + " integer, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRUEBA + " integer, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRECIO_UNITARIO + " real, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRECIO_ML + " real, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_N_DOSIS + " real, "
		  + DATABASE_TABLE_TRAT_PREV_CEBA_KEY_FECHA + " date, "
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBADERO +") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID + "),"
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_PREV_CEBA_KEY_CEBA +") REFERENCES " + DATABASE_TABLE_CEBA_CEBADERO + "( "+DATABASE_TABLE_CEBA_CEBADERO_KEY_ID + "),"
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_PREV_CEBA_KEY_PRUEBA +") REFERENCES " + DATABASE_TABLE_PRUEBAS + "( "+DATABASE_TABLE_PRUEBAS_KEY_ID + ")"
		  + DATABASE_STATEMENT_END;
  
  //  Table: tratamiento curativo animal
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL = "tratamiento_curativo_animal";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_ID = "_id";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBADERO = "cod_cebadero";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBA = "cod_ceba";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_ANIMAL = "n_id_animal";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRODUCTO = "no_producto";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_FECHA = "fecha";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_EXPLOTACION = "cod_explotacion";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PATOLOGIA = "cod_patologia";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRECIO_UNITARIO = "precio_unitario";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRECIO_ML = "precio_ml";
  protected static final String DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_N_DOSIS = "no_dosis";
//Create Table statement
  protected static final String DATABASE_CREATE_TRAT_CUR_ANIMAL =
		  DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_TRAT_CUR_ANIMAL
		  +" (" + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_ID + 
		  " integer primary key autoincrement, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBADERO + " integer, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBA + " integer, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_ANIMAL + " integer, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRODUCTO + " text, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_FECHA + " date, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_EXPLOTACION + " integer, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PATOLOGIA + " integer, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRECIO_UNITARIO + " real, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PRECIO_ML + " real, "
		  + DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_N_DOSIS + " real, "
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBADERO +") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID + "),"
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_CEBA +") REFERENCES " + DATABASE_TABLE_CEBA_CEBADERO + "( "+DATABASE_TABLE_CEBA_CEBADERO_KEY_ID + "),"
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_ANIMAL +") REFERENCES " + DATABASE_TABLE_ANIMALES + "( "+DATABASE_TABLE_ANIMALES_KEY_ID + "),"
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_EXPLOTACION +") REFERENCES " + DATABASE_TABLE_EXPLOTACION + "( "+DATABASE_TABLE_EXPLOTACION_KEY_ID + "),"
		  + "FOREIGN KEY("+DATABASE_TABLE_TRAT_CUR_ANIMAL_KEY_PATOLOGIA +") REFERENCES " + DATABASE_TABLE_PATOLOGIAS + "( "+DATABASE_TABLE_PATOLOGIAS_KEY_ID + ")"
		  + DATABASE_STATEMENT_END;
  
  
  	protected static final String DROP_STATEMENT = "DROP TABLE IF EXISTS ";
  
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static DatabaseHelper instance;

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public static synchronized DatabaseHelper getHelper(Context context) {
			if (instance == null)
				instance = new DatabaseHelper(context);
			return instance;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			createDB(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			deleteDB(db);
			onCreate(db);
		}

		public void createDB(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_EXPLOTACION);
			db.execSQL(DATABASE_CREATE_CEBADERO);
			db.execSQL(DATABASE_CREATE_GUIA);
			db.execSQL(DATABASE_CREATE_CEBA_CEBADERO);
			db.execSQL(DATABASE_CREATE_CORRAL);
			db.execSQL(DATABASE_CREATE_LOTE_CEBADERO);
			db.execSQL(DATABASE_CREATE_RAZA);
			db.execSQL(DATABASE_CREATE_ESPECIE);
			db.execSQL(DATABASE_CREATE_ANIMALES);
			db.execSQL(DATABASE_CREATE_PATOLOGIAS);
			db.execSQL(DATABASE_CREATE_PRUEBAS);
			db.execSQL(DATABASE_CREATE_VETERINARIO);
			db.execSQL(DATABASE_CREATE_TRAT_PREV_CEBA);
			db.execSQL(DATABASE_CREATE_TRAT_CUR_ANIMAL);
		}

		public void deleteDB(SQLiteDatabase db) {
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_EXPLOTACION + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_CEBADERO + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_GUIA + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_CEBA_CEBADERO + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_CORRAL + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_LOTE_CEBADERO + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_RAZA + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_ESPECIE + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_ANIMALES + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_PATOLOGIAS + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_PRUEBAS + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_VETERINARIO + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_TRAT_PREV_CEBA + ";");
			db.execSQL(DROP_STATEMENT + DATABASE_TABLE_TRAT_CUR_ANIMAL + ";");
		}
		
		public void resetDB(SQLiteDatabase db)
		{
			deleteDB(db);
			createDB(db);
		}

		@Override
		public void onConfigure(SQLiteDatabase db) {
			super.onConfigure(db);
			if (db.isReadOnly()) {
				db.execSQL("PRAGMA foreign_keys=ON;");
			}
		}
	}

	public DbAdapter(Context ctx) {
		this.mCtx = ctx;
		open();
	}

	public DbAdapter open() throws SQLException {
		mDbHelper = DatabaseHelper.getHelper(mCtx);
		setmDb(mDbHelper.getWritableDatabase());
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
	public void resetDB ()
	{
		mDbHelper.resetDB(getDb());
	}

	protected SQLiteDatabase getDb() {
		return mDb;
	}

	protected void setmDb(SQLiteDatabase mDb) {
		this.mDb = mDb;
	}
}
