package omicron.app.dbManagement;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import static omicron.app.dbManagement.DBConsts.*;
import static omicron.app.dbManagement.RemoteDBConsts.*;

public class DbAdapter {
//Main DB Adapter, used on create and update of all dbs. It's intended to 
//	use individual table classes for table operations
	
	private static final String DATABASE_NAME = "omicron.db";
	// Whenever DB structure is changed, must be incremented
	private static final int DATABASE_VERSION = 7;

	/**
	 * Will use this Tag for debugging LogCat.
	 */
	private static final String DEBUG_TAG = "DbAdapter";

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static DatabaseHelper instance;

		// Use getHelper
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// Grants only one instance
		public static synchronized DatabaseHelper getHelper(Context context) {
			if (instance == null) {
				instance = new DatabaseHelper(context);
			}
			return instance;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			createDB(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(DEBUG_TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			dropDB(db);
			createDB(db);
		}

		public void createDB(SQLiteDatabase db) {
			Iterator<String> dbCreatorIterator = CREATE_TABLES_LIST.iterator();
			while (dbCreatorIterator.hasNext())
			db.execSQL(dbCreatorIterator.next());
			Log.d(DEBUG_TAG, "All DB tables created.");
		}

		public void dropDB(SQLiteDatabase db) {
			Iterator<String> dbDropIterator = DROP_TABLES_LIST.iterator();
			while (dbDropIterator.hasNext())
			db.execSQL(dbDropIterator.next());
			Log.d(DEBUG_TAG, "All DB tables deleted.");
		}

		public void resetDB(SQLiteDatabase db) {
			dropDB(db);
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
	}

	public DbAdapter open() throws SQLException {
		mDbHelper = DatabaseHelper.getHelper(mCtx);
		setmDb(mDbHelper.getWritableDatabase());
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

//	public boolean insert(ContentValues values)
//	{
//		return true;
//	};
	
	public void resetDB() {
		mDbHelper.resetDB(getDb());
	}

	protected SQLiteDatabase getDb() {
		return mDb;
	}

	protected void setmDb(SQLiteDatabase mDb) {
		this.mDb = mDb;
	}

	public boolean insert(String table, List<String> headers, List<List<String>> rawValues)
	{
		String localTable = TABLE_PAIRS_MAP.get(table);
		Iterator<String> headersIterator = null;
		List<String> currentRow = null;
		Iterator<List<String>> rowsIterator = rawValues.iterator();
		Iterator<String> currentRowIterator = null;
		while (rowsIterator.hasNext())
		{
			ContentValues values = new ContentValues();
			currentRow = rowsIterator.next();
			headersIterator = headers.iterator();
			currentRowIterator = currentRow.iterator();
			while (headersIterator.hasNext())
			{
				
				String currentHeader = headersIterator.next();
				String currentValue = currentRowIterator.next();
				Log.d(DEBUG_TAG, "Current header is: "+currentHeader);
				String currentLocalHeader = getLocalHeader(table, currentHeader);
				Log.d(DEBUG_TAG, "Current field is: "+ currentLocalHeader);
				Log.d(DEBUG_TAG, "Current value for field "+currentLocalHeader+" is: "+ currentValue);
				values.put(currentLocalHeader, currentValue);
			}
			mDb.insertOrThrow(localTable, null, values);	
		}
//		values.put();
//		db.execSQL();
//		
		return true;
	}
	
	public boolean insert(String code, String descripcion) {
		// TODO Auto-generated method stub
		return false;
	}
}
