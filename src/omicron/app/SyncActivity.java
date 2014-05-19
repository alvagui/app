package omicron.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class SyncActivity extends ActionBarActivity {

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

			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(getActivity());
			String serverURLPref = preferences.getString("serverURL", "");
			Log.d("DEBUGGING", serverURLPref);

			DownloadTask dt = new DownloadTask(getActivity());
			dt.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, serverURLPref);

			return rootView;
		}

		// Receive data from server
		// public void receiveData(View view) {
		// pDialog = new ProgressDialog(this);
		// pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// pDialog.setMessage(R.string.DOWNLOADING);
		// pDialog.setCancelable(true);
		// pDialog.setMax(100);
		// String stringUrl = Constants.WEB_SERVICE_URL;
		// // Gets the URL from the UI's text field.
		// ConnectivityManager connMgr = (ConnectivityManager)
		// getSystemService(Context.CONNECTIVITY_SERVICE);
		// NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		// if (networkInfo != null && networkInfo.isConnected()) {
		// new DownloadTask().execute(stringUrl);
		// } else {
		// Log.d("Network: ", "Conexión de datos no disponible.");
		// }
		// }

		private class DownloadTask extends AsyncTask<String, Integer, String> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				Log.d("Debugging", "Got into onPreExecute()");
			}

			private Context context;

			public DownloadTask(Context context) {
				this.context = context;
			}

			// Get data from server database.
			protected String doInBackground(String... urls) {

				Log.d("Debugging", "Got into doInBackground() with " + urls[0]);
				// params comes from the execute() call: params[0] is the url.
				try {
					return downloadUrl(urls[0]);
				} catch (IOException e) {
					e.printStackTrace();
					return getString(R.string.urlNotFound);
				}
			}

			// Given a URL, establishes an HttpUrlConnection and retrieves
			// the web page content as a InputStream, which it returns as
			// a string.
			private String downloadUrl(String myurl) throws IOException {
				BufferedReader in = null;
				try {
					// Request construction
					URL url = new URL(myurl);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setReadTimeout(10000 /* milliseconds */);
					conn.setConnectTimeout(15000 /* milliseconds */);

					in = new BufferedReader (new InputStreamReader(url.openStream()));
					String str;
					String reader = null;
					while ((str = in.readLine())!=null)
					{
						reader += str;
					}
					in.close();
					String contentAsString = reader.toString();
					return contentAsString;}
					// Makes sure that the InputStream is closed after the app
					// is
					// finished using it.
					finally {
					if (in != null) {
						in.close();
					}
				}
			}

			public List readJsonStream(InputStream in) throws IOException {
				JsonReader reader = new JsonReader(new InputStreamReader(in,
						"UTF-8"));
				try {
					return readMessagesArray(reader);
				} finally {
					reader.close();
				}
			}

			public String readMessage(JsonReader reader) throws IOException {
				long id = -1;
				String name = null;

				reader.beginObject();
				while (reader.hasNext()) {
					String current = reader.nextName();
					if (current.equals("id")) {
						id = reader.nextLong();
					} else if (current.equals("Name")) {
						name = reader.nextString();
					} else {
						reader.skipValue();
					}
				}
				Log.d("id", Long.toString(id));
				Log.d("name", name);
				reader.endObject();

				return new String(id + " " + name);
			}

			public List readMessagesArray(JsonReader reader) throws IOException {
				List messages = new ArrayList();

				reader.beginArray();
				while (reader.hasNext()) {
					messages.add(readMessage(reader));
				}
				reader.endArray();
				return messages;
			}

			@Override
			protected void onPostExecute(String result) {
				Log.d("Debugging", "Got into onPostExecute() with " + result);
				Log.d("Response: ", result);
			}

		}
	}
}
