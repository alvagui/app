package omicron.app.dbManagement;

import android.content.Context;
import android.util.Log;
import static omicron.app.dbManagement.DBConsts.*;

public class RazaDbAdapter extends DbAdapter {

	public RazaDbAdapter(Context ctx) {
		super(ctx);
	}

//	@Override
//	public boolean insert(ContentValues values)
//	{
//		
//		getDb().insertOrThrow(DATABASE_TABLE_RAZA, null, values);
//		return false;
//	}
	
	@Override
	public boolean insert (String code, String descripcion)
	{
//		TODO: refinar
		String query = DATABASE_INSERT_STATEMENT_START +DATABASE_TABLE_RAZA+" ("+DATABASE_TABLE_RAZA_KEY_CODIGO+","+ DATABASE_TABLE_RAZA_KEY_DESCRIPCION+") VALUES ( '"+code+"','" +descripcion +"'"+DATABASE_STATEMENT_END;
		Log.d("SQLite statement: ", query);
		try{
		open();
		getDb().execSQL(query);
		return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			close();
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
