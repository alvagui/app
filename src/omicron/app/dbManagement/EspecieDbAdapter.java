package omicron.app.dbManagement;

import java.lang.reflect.Array;

import android.content.Context;
import android.database.Cursor;
import static omicron.app.dbManagement.DBConsts.*;

public class EspecieDbAdapter extends DbAdapter {

	public EspecieDbAdapter(Context ctx) {
		super(ctx);
	}
	
	public Cursor especieFromCodigo (String codigo)
	{
		//TODO: cómo se devuelven campos?
//		getDb().query
		String param[] = new String[1];
		param[0]= codigo;
		Cursor rs = getDb().rawQuery("SELECT * FROM "+DATABASE_TABLE_ESPECIE+"' where codigo='?';",param);
//		Object rs = getDb().execSQL("select * from '"+DATABASE_TABLE_ESPECIE+"' where codigo='"+codigo+"';");
		return rs;
	}
}
