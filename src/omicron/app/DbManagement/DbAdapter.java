package omicron.app.DbManagement;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {
	
    private static final String DATABASE_NAME = "omicron.db";
    private static final int DATABASE_VERSION = 1;
	
//    Log Tag
    private static final String TAG = "DbAdapter";
    
//    Database commons
    private static final String DATABASE_CREATE_STATEMENT_START = "create table ";
    private static final String DATABASE_CREATE_STATEMENT_END = ");";
    
//  Table: explotacion  
    private static final String DATABASE_TABLE_EXPLOTACION = "explotacion";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_ID = "_id";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_CODIGO = "codigo";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_CIF_NIF = "cif_nif";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_TITULAR= "nombre_titular";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_FAPERTURA = "fecha_apertura";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_ESPECIE = "cod_especie";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_EN = "no_animales_entrada";
    private static final String DATABASE_TABLE_EXPLOTACION_KEY_N_ANIMALES_SA = "no_animales_salida";
//    Create Table statement
    private static final String DATABASE_CREATE_EXPLOTACION =
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
            + DATABASE_CREATE_STATEMENT_END;

    //  Table: cebadero  
    private static final String DATABASE_TABLE_CEBADERO = "cebadero";
    private static final String DATABASE_TABLE_CEBADERO_KEY_ID = "_id";
    private static final String DATABASE_TABLE_CEBADERO_KEY_CODIGO = "codigo";
    private static final String DATABASE_TABLE_CEBADERO_KEY_NOMBRE = "nombre";
    private static final String DATABASE_TABLE_CEBADERO_KEY_NOMBRE2= "nombre2";
    private static final String DATABASE_TABLE_CEBADERO_KEY_DIRECCION = "direccion";
    private static final String DATABASE_TABLE_CEBADERO_KEY_DIRECCION2 = "direccion2";
    private static final String DATABASE_TABLE_CEBADERO_KEY_POBLACION = "poblacion";
    private static final String DATABASE_TABLE_CEBADERO_KEY_TELEFONO = "no_telefono";
    private static final String DATABASE_TABLE_CEBADERO_KEY_TELEFONO2 = "no_telefono2";
    private static final String DATABASE_TABLE_CEBADERO_KEY_FAX = "no_fax";
    private static final String DATABASE_TABLE_CEBADERO_KEY_CONTACTO = "contacto";
    private static final String DATABASE_TABLE_CEBADERO_KEY_CP = "cp";
    private static final String DATABASE_TABLE_CEBADERO_KEY_PROVINCIA = "provincia";
    private static final String DATABASE_TABLE_CEBADERO_KEY_EMAIL = "correo_electronico";
    private static final String DATABASE_TABLE_CEBADERO_KEY_WEB = "pagina_web";
    private static final String DATABASE_TABLE_CEBADERO_KEY_CPR = "cod_pais_region";
    private static final String DATABASE_TABLE_CEBADERO_KEY_PLAZAS_D = "no_plazas_disponibles";
    private static final String DATABASE_TABLE_CEBADERO_KEY_PLAZAS_O = "no_plazas_ocupadas";
//  Create Table statement
  private static final String DATABASE_CREATE_CEBADERO =
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
          + DATABASE_TABLE_CEBADERO_KEY_PLAZAS_D + " integer, "
          + DATABASE_TABLE_CEBADERO_KEY_PLAZAS_O + " integer"
          + DATABASE_CREATE_STATEMENT_END;
    
//Table: ceba_cebadero
  private static final String DATABASE_TABLE_CEBA_CEBADERO = "ceba_cebadero";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_ID = "_id";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CEBADERO = "codigo_cebadero";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CODIGO = "codigo";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CNP= "compra_a_no_proveedor";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_CN= "compra_a_nombre";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_TRANSPORTISTA = "cod_transportista";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_COSTE_TRANSPORTE_ANIMAL = "conste_transporte_animal";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_N_ANIMALES = "no_animales";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_EXPLOTACION = "con_explotacion";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_FECHA_E = "fecha_entrada";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_MOTIVO_E = "motivo_entrada";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_M = "peso_medio_entrada_machos";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PME_H = "peso_medio_entrada_hembras";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_M = "precio_compra_machos";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_PCOMPRA_H = "precio_compra_hembras";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_FILTRO_NO_GUIA = "filtro_no_guia";
  private static final String DATABASE_TABLE_CEBA_CEBADERO_KEY_NO_GUIA_E = "no_guia_entrada";
//Create Table statement
  private static final String DATABASE_CREATE_CEBA_CEBADERO =
        DATABASE_CREATE_STATEMENT_START + DATABASE_TABLE_CEBA_CEBADERO  
        +" (" + DATABASE_TABLE_CEBA_CEBADERO_KEY_ID + 
        " integer primary key autoincrement, "
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_CEBADERO + " text, "
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
        + DATABASE_TABLE_CEBA_CEBADERO_KEY_NO_GUIA_E + " text, "
        + "FOREIGN KEY("+DATABASE_TABLE_CEBA_CEBADERO_KEY_CEBADERO+") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID + ")"
//        + "FOREIGN KEY("+DATABASE_TABLE_CEBA_CEBADERO_KEY_TRANSPORTISTA+") REFERENCES " + DATABASE_TABLE_CEBADERO + "( "+DATABASE_TABLE_CEBADERO_KEY_ID
        + DATABASE_CREATE_STATEMENT_END;
    
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    private final Context mCtx;
    
    private static class DatabaseHelper extends SQLiteOpenHelper {
    	
        private static DatabaseHelper instance;
    	
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public static synchronized DatabaseHelper getHelper(Context context)
        {
            if (instance == null)
                instance = new DatabaseHelper(context);

            return instance;
        }
        
        
		@Override
		public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE_EXPLOTACION);
            db.execSQL(DATABASE_CREATE_CEBADERO);
            db.execSQL(DATABASE_CREATE_CEBA_CEBADERO);
        }

		@Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS explotacion");
            onCreate(db);
        }
    }
    
    public DbAdapter (Context ctx)
    {
            this.mCtx = ctx;
    }
    
    public DbAdapter open() throws SQLException {
        mDbHelper = DatabaseHelper.getHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
}
