package omicron.app.DbManagement;

import android.content.Context;
import android.util.Log;
import static omicron.app.DbManagement.DBConsts.*;

public class RazaDbAdapter extends DbAdapter {

	public RazaDbAdapter(Context ctx) {
		super(ctx);
	}

	public boolean insert (String code, String descripcion)
	{
		String query = DATABASE_INSERT_STATEMENT_START +DATABASE_TABLE_RAZA+" ("+DATABASE_TABLE_RAZA_KEY_CODIGO+","+ DATABASE_TABLE_RAZA_KEY_DESCRIPCION+") VALUES ( '"+code+"','" +descripcion +"'"+DATABASE_STATEMENT_END;
		Log.d("SQLite statement: ", query);
		try{
		getDb().execSQL(query);
		return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean insertionTest (String values)
	{
		String query = DATABASE_INSERT_STATEMENT_START +DATABASE_TABLE_RAZA+" ("+DATABASE_TABLE_RAZA_KEY_CODIGO+","+ DATABASE_TABLE_RAZA_KEY_DESCRIPCION+") VALUES ( "+values+ DATABASE_STATEMENT_END;
		Log.d("SQLite statement: ", query);
		try{
		getDb().execSQL(query);
		return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
