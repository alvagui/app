package omicron.app.DbManagement;

import java.util.Iterator;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static omicron.app.DbManagement.DBConsts.*;

public class DbAdapter {
//Main DB Adapter, used on create and update of all dbs. It's intended to 
//	use individual table classes for table operations
	
	private static final String DATABASE_NAME = "omicron.db";
	// Whenever DB structure is changed, must be incremented
	private static final int DATABASE_VERSION = 6;

	// Log Tag
	private static final String TAG = "DbAdapter";

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

			dropDB(db);
			onCreate(db);
		}

		public void createDB(SQLiteDatabase db) {
			Iterator<String> dbCreatorIterator = CREATE_TABLES_LIST.iterator();
			while (dbCreatorIterator.hasNext())
			db.execSQL(dbCreatorIterator.next());
			Log.d(TAG, "All DB tables created.");
		}

		public void dropDB(SQLiteDatabase db) {
			Iterator<String> dbDropIterator = DROP_TABLES_LIST.iterator();
			while (dbDropIterator.hasNext())
			db.execSQL(dbDropIterator.next());
			Log.d(TAG, "All DB tables deleted.");
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

	public void resetDB() {
		mDbHelper.resetDB(getDb());
	}

	protected SQLiteDatabase getDb() {
		return mDb;
	}

	protected void setmDb(SQLiteDatabase mDb) {
		this.mDb = mDb;
	}
}
