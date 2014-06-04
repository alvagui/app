package omicron.app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import omicron.app.datatypes.Raza;
import omicron.app.dbManagement.DbAdapter;
import omicron.app.dbManagement.RazaDbAdapter;
import omicron.app.dbManagement.ExportedTableContainer;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
//
import au.com.bytecode.opencsv.CSVReader;
import static omicron.app.dbManagement.RemoteDBConsts.*;

/**
 * This activity is responsible of data sync management, whether downloading
 * from either uploading to server.
 * 
 * @version 1, 28/05/14
 * @author AAGUILAR
 */
public class SyncActivity extends ActionBarActivity {

	/**
	 * Will use this Tag for debugging LogCat.
	 */
	private static final String DEBUG_TAG = "Synchronization Activity";

	/**
	 * Populating progress indicator.
	 */
	private transient ProgressDialog pDialog;

	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public final boolean onCreateOptionsMenu(final Menu menu) {
		//
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sync, menu);
		return true;
	}

	@Override
	public final boolean onOptionsItemSelected(final MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId(); // NOPMD by aaguilar on 28/05/14 15:38
		if (id == R.id.action_settings) {
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Starts download data from remote server and populate local database
	 * action.
	 * 
	 * @param view
	 *            current Android view.
	 */
	@SuppressWarnings("ucd")
	public final void downloadData(final View view) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String serverURLPref = preferences.getString("serverURL", "");
		Log.d(DEBUG_TAG, serverURLPref);
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			pDialog = ProgressDialog.show(this, "",
					getString(R.string.DOWNLOADING), true);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

			PopulateDatabaseTask dt = new PopulateDatabaseTask(this);
			dt.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, serverURLPref);

		} else {

			Log.d(DEBUG_TAG, "No Network Connection Available.");
			(Toast.makeText(this, R.string.noNetworkConnectionFound,
					Toast.LENGTH_LONG)).show();
		}

	}

	/**
	 * This async task contacts server, download DB and populate local DB with
	 * this data.
	 * 
	 * @version 1, 28/05/14
	 * @author AAGUILAR
	 */
	private class PopulateDatabaseTask extends
			AsyncTask<String, Integer, Boolean> {

		/**
		 * Sets the key for POST parameters in HTTP connection.
		 */
		private static final String HTTP_POST_PARAM_NAME = "table";
		/**
		 * Sets the read timeout for HTTP connection.
		 */
		private static final int READ_TIMEOUT = 10000 /* milliseconds */;
		/**
		 * Sets the connection timeout for HTTP connection.
		 */
		private static final int CONNECT_TIMEOUT = 15000 /* milliseconds */;

		/**
		 * Current Android context.
		 */
		private Context context;

		/**
		 * Constructor for PopulateDatabaseTask.
		 * 
		 * @param context
		 *            current Android context.
		 */
		public PopulateDatabaseTask(Context context) {
			super();
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d(DEBUG_TAG, "Got into onPreExecute()");
		}

		// Get data from server database.
		@Override
		protected Boolean doInBackground(final String... urls) {

			Log.d(DEBUG_TAG, "Got into doInBackground() with " + urls[0]);
			ExportedTableContainer container = null;
			// params comes from the execute() call: params[0] is the url.
			try {
				// Download data from server
				List<ExportedTableContainer> downloadedData = new ArrayList<ExportedTableContainer>();
				Iterator<String> tablesIterator = TABLE_PAIRS_MAP.keySet()
						.iterator();

				// Read all the tables
				while (tablesIterator.hasNext()) {
					String currentTable = tablesIterator.next();
					Log.d(DEBUG_TAG, "Current table is: " + currentTable);
					List<List<String>> downloadedTable = downloadDataFromServer(
							urls[0], currentTable);
					if (downloadedTable != null) {
						container = new ExportedTableContainer(currentTable,
								downloadedTable);
						// TODO: TEMP DEBUGGING. BORRAR
						Log.d(DEBUG_TAG,
								"Container for " + container.getTableName()
										+ " table.");
						if (container == null) {
							Log.e(DEBUG_TAG, "Nothing in container");
						}
						downloadedData.add(container);
					} else {
						Log.e(DEBUG_TAG, "Downloaded table == NULL!!!");
						throw new EmptyStackException();
					}
				}
				// Populate db with fresh data from server

				return populateDatabase(downloadedData);
			} catch (IOException e) {
				Log.d(DEBUG_TAG, Arrays.toString(e.getStackTrace()));
				return false;
			} catch (Exception e) {
				Log.d(DEBUG_TAG, Arrays.toString(e.getStackTrace()));
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean result) {
			if (result) {

				(Toast.makeText(context, R.string.ackDownloaded,
						Toast.LENGTH_LONG)).show();
			} else {

				(Toast.makeText(context, R.string.urlNotFound,
						Toast.LENGTH_LONG)).show();
			}
			Log.d(DEBUG_TAG, "Got into onPostExecute() with " + result);
			// Log.d("Response: ", result);
			pDialog.dismiss();
		}

		/**
		 * // NOPMD by aaguilar on 28/05/14 15:36 Given a URL, establishes an
		 * HttpUrlConnection and retrieves the web page content as a
		 * InputStream, which it returns as a string.
		 * 
		 * @param myurl
		 *            Server URL string.
		 * @return Contains the whole db information downloaded from server.
		 * @throws IOException
		 *             if non reachable URL.
		 */

		private List<List<String>> downloadDataFromServer(String myurl,
				String table) throws IOException {
			CSVReader reader = null;
			try {

				// Post construction
				Map<String, Object> params = new LinkedHashMap<String, Object>();
				params.put(HTTP_POST_PARAM_NAME, table);

				StringBuilder postData = new StringBuilder();
				for (Map.Entry<String, Object> param : params.entrySet()) {
					if (postData.length() != 0) {
						postData.append('&');
					}
					postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
					postData.append('=');
					postData.append(URLEncoder.encode(
							String.valueOf(param.getValue()), "UTF-8"));
				}
				byte[] postDataBytes = postData.toString().getBytes("UTF-8");

				// Request construction
				final URL url = new URL(myurl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setReadTimeout(READ_TIMEOUT);
				conn.setConnectTimeout(CONNECT_TIMEOUT);

				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Content-Length",
						String.valueOf(postDataBytes.length));
				conn.setDoOutput(true);
				conn.getOutputStream().write(postDataBytes);

				conn.connect();
				int response = conn.getResponseCode();
				Log.d(DEBUG_TAG, "Response code: " + Integer.toString(response));

				// reader = new CSVReader(new
				// InputStreamReader(url.openStream()),
				// ',', '"', 2);
				reader = new CSVReader(new InputStreamReader(
						conn.getInputStream()), ',', '"', 2);
				String[] nextLine;
				// ArrayList<String> headers = new ArrayList<String>();
				List<List<String>> values = new ArrayList<List<String>>();

				// Reads data and saves them into an array
				ArrayList<String> row;
				while ((nextLine = reader.readNext()) != null) {
					row = new ArrayList<String>(Arrays.asList(nextLine));
					Log.d(DEBUG_TAG, "Row: " + row.toString());
					values.add(row);
				}
				Log.d(DEBUG_TAG, "Values: " + values.toString());
				if (values.isEmpty()) {
					return null;
				} else {
					return values;
				}

			} catch (IOException e) {
				Log.d(DEBUG_TAG, Arrays.toString(e.getStackTrace()));
				return null;
			}
			// Makes sure that the BufferReader is closed after the app
			// is
			// finished using it.
			finally {

				if (reader != null) {
					reader.close();
				}
			}
		}

		/**
		 * Populate local DB with data downloaded from remote server.
		 * 
		 * @param retrievedData
		 *            Contains the whole db information downloaded from server.
		 * @return True if operation completed succesfully, false if not.
		 */

		private boolean populateDatabase(List<List<String>> retrievedData,
				String table) {

			return true;
		}

		private boolean populateDatabase(List<ExportedTableContainer> retrievedData)
				throws Exception {
			// Populates local db with data retrieved from server
			// Empties current database
			DbAdapter dba = new DbAdapter(this.context);
			dba.open();
			dba.resetDB();

			Iterator<ExportedTableContainer> tablesIterator = retrievedData.iterator();
			List<String> currentRow;
			Raza currentRaza = null;
			while (tablesIterator.hasNext()) {
				ExportedTableContainer currentTable = tablesIterator.next();
				boolean inserted = dba.insert(currentTable.getTableName(),
						currentTable.getHeaders(), currentTable.getTableData());
				Log.d(DEBUG_TAG, "Inserted "+currentTable.getTableName()+" table:" + inserted);
				if (!inserted) {
					throw new Exception();
				}
			}
			return true;
		}

		// TODO: temporal, borrar
		private boolean insertRow(final String value1, final String value2,
				final RazaDbAdapter dba) {
			Log.d(DEBUG_TAG, "Insert with values : " + value1 + " - " + value2);
			return dba.insert(value1, value2);
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		@Override
		public final View onCreateView(final LayoutInflater inflater,
				final ViewGroup container, final Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_sync, container, // NOPMD
																				// by
																				// aaguilar
																				// on
																				// 28/05/14
																				// 15:34
					false);
			MainActivity.getAppContext();
			return rootView;
		}
	}
}
