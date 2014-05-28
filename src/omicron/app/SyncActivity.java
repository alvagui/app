package omicron.app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import omicron.app.DbManagement.DbAdapter;
import omicron.app.DbManagement.RazaDbAdapter;
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

public class SyncActivity extends ActionBarActivity {

	// Log Tag
	private static final String TAG = "Synchronization Activity";

	ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sync, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void downloadData(View view) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String serverURLPref = preferences.getString("serverURL", "");
		Log.d(TAG, serverURLPref);
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			pDialog = ProgressDialog.show(this, "",
					getString(R.string.DOWNLOADING), true);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

			PopulateDatabaseTask dt = new PopulateDatabaseTask(this);
			dt.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, serverURLPref);

		} else {

			Log.d(TAG, "No Network Connection Available.");
			(Toast.makeText(this, R.string.noNetworkConnectionFound,
					Toast.LENGTH_LONG)).show();
		}

	}

	private class PopulateDatabaseTask extends
			AsyncTask<String, Integer, Boolean> {

		private static final String POST_PARAMETER = "table";
		private static final int READ_TIMEOUT = 10000 /* milliseconds */;
		private static final int CONNECT_TIMEOUT = 15000 /* milliseconds */;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d(TAG, "Got into onPreExecute()");
		}

		private Context context;

		public PopulateDatabaseTask(Context context) {
			this.context = context;

		}

		// Get data from server database.
		@Override
		protected Boolean doInBackground(String... urls) {

			Log.d(TAG, "Got into doInBackground() with " + urls[0]);
			// params comes from the execute() call: params[0] is the url.
			try {
				// Download data from server
				ArrayList<ArrayList<String>> downloadedData = downloadDataFromServer(urls[0]);
				// Populate db with fresh data from server
				if (downloadedData != null)
					return (populateDatabase(downloadedData));
				else
					return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		private boolean populateDatabase(
				ArrayList<ArrayList<String>> downloadDataFromServer) {
			// Populates local db with data retrieved from server
			// Empties current database
			DbAdapter dba = new DbAdapter(this.context);
			dba.resetDB();

			Iterator<ArrayList<String>> rowIt = downloadDataFromServer
					.iterator();
			ArrayList<String> headers = rowIt.next();
			ArrayList<String> currentRow;
			RazaDbAdapter razadba = new RazaDbAdapter(context);
			while (rowIt.hasNext()) {
				currentRow = rowIt.next();
				// TODO: Temporal, borrar
				insertRow(currentRow.get(headers.indexOf("codigo")),
								currentRow.get(headers.indexOf("descripcion"))
								, razadba);
			}

			return true;
		}

		// Given a URL, establishes an HttpUrlConnection and retrieves
		// the web page content as a InputStream, which it returns as
		// a string.
		private ArrayList<ArrayList<String>> downloadDataFromServer(String myurl)
				throws IOException {
			CSVReader reader = null;
			try {
				
								// Post construction
						        Map<String,Object> params = new LinkedHashMap<String, Object>();
								params.put(POST_PARAMETER, "raza");
								
						      
						        StringBuilder postData = new StringBuilder();
						        for (Map.Entry<String,Object> param : params.entrySet()) {
						            if (postData.length() != 0) postData.append('&');
						            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
						            postData.append('=');
						            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
						        }
						        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
								 
				
				
				// Request construction
				URL url = new URL(myurl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setReadTimeout(READ_TIMEOUT);
				conn.setConnectTimeout(CONNECT_TIMEOUT);
				
							conn.setRequestMethod("POST");
						    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
						    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
						    conn.setDoOutput(true);
						    conn.getOutputStream().write(postDataBytes);
				
						    conn.connect();
						    int response = conn.getResponseCode();
						    Log.d(TAG, "Response code: "+ Integer.toString(response));
						    
//				reader = new CSVReader(new InputStreamReader(url.openStream()),
//						',', '"', 2);
						    reader = new CSVReader (new InputStreamReader(conn.getInputStream()),',', '"', 2);
				String[] nextLine;
				// ArrayList<String> headers = new ArrayList<String>();
				ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();

				// nextLine = reader.readNext();

				// Reads headers from CSV
				// headers = new ArrayList<String>(Arrays.asList(nextLine));

				// Iterator<String> it = headers.iterator();
				// while (it.hasNext())
				// Log.d(TAG, it.next().toString());
				//
				// Reads data and put them into DB
				ArrayList<String> row;
				while ((nextLine = reader.readNext()) != null) {
					row = new ArrayList<String>(Arrays.asList(nextLine));
					Log.d(TAG, "Row: " + row.toString());
					values.add(row);
				}
				Log.d(TAG, "Values: " + values.toString());
				if (values.isEmpty())
					return null;
				else
					return values;
					
			} catch (Exception e) {
				e.printStackTrace();
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

		private boolean insertRow(String value1,String value2, RazaDbAdapter dba) {
			Log.d(TAG, "Insert with values : " + value1 + " - " + value2);
			return dba.insert(value1,value2);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result == true) {

				(Toast.makeText(context, R.string.ackDownloaded,
						Toast.LENGTH_LONG)).show();
			} else {

				(Toast.makeText(context, R.string.urlNotFound,
						Toast.LENGTH_LONG)).show();
			}
			Log.d(TAG, "Got into onPostExecute() with " + result);
			// Log.d("Response: ", result);
			pDialog.dismiss();
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_sync, container,
					false);

			return rootView;
		}
	}
}
