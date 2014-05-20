package omicron.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

public class SyncActivity extends ActionBarActivity {

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
		Log.d("DEBUGGING", serverURLPref);
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			pDialog = ProgressDialog.show(this, "",
					getString(R.string.DOWNLOADING), true);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

			DownloadTask dt = new DownloadTask(this);
			dt.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, serverURLPref);

		} else {

			Log.d("Network: ", "No Network Connection Available.");
			(Toast.makeText(this, R.string.noNetworkConnectionFound,
					Toast.LENGTH_LONG)).show();
		}

	}

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
//				(Toast.makeText(this.context, R.string.urlNotFound,
//						Toast.LENGTH_LONG)).show();
				e.printStackTrace();

//				return getString(R.string.urlNotFound);
				return null;
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

				in = new BufferedReader(new InputStreamReader(url.openStream()));
				String str;
				String reader = null;
				while ((str = in.readLine()) != null) {
					reader += str;
				}
				in.close();
				String contentAsString = reader.toString();
				return contentAsString;
			}
			// Makes sure that the BufferReader is closed after the app
			// is
			// finished using it.
			finally {
				if (in != null) {
					in.close();
				}

			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (result!=null)
			{

				(Toast.makeText(context, R.string.ackDownloaded,
						Toast.LENGTH_LONG)).show();
			}
			else
			{

				(Toast.makeText(context, R.string.urlNotFound,
						Toast.LENGTH_LONG)).show();
			}
			Log.d("Debugging", "Got into onPostExecute() with " + result);
//			Log.d("Response: ", result);
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

			// pDialog = new ProgressDialog(getActivity());

			//
			// pDialog.setCancelable(true);
			// pDialog.setMax(100);
			// Gets the URL from the UI's text field.

			return rootView;
		}
	}
}
