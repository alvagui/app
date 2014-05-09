package omicron.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class SyncActivity extends ActionBarActivity {

	private ProgressDialog pDialog;

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
			return rootView;
		}
	}

	// Receive data from server
//	public void receiveData(View view) {
//        pDialog = new ProgressDialog(this);
//        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pDialog.setMessage(R.string.DOWNLOADING);
//        pDialog.setCancelable(true);
//        pDialog.setMax(100); 
//		String stringUrl = Constants.WEB_SERVICE_URL;
//		// Gets the URL from the UI's text field.
//		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//		if (networkInfo != null && networkInfo.isConnected()) {
//			new DownloadTask().execute(stringUrl);
//		} else {
//			Log.d("Network: ", "Conexión de datos no disponible.");
//		}
//	}
		
	}

//	private class DownloadTask extends AsyncTask<void, Integer, String> {
//
//		private Context context;
//		private PowerManager.WakeLock mWakeLock;
//
//		public DownloadTask(Context context) {
//			this.context = context;
//		}
//
//		
//		// Get data from server database.
//		@Override
//		protected String doInBackground(String... sUrl) {
//			return null;}
//		
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            int progreso = values[0].intValue();
// 
//            pDialog.setProgress(progreso);
//        }
//	}
//
//	private class PopulateDBTask extends AsyncTask<String, Integer, String> {
//
//		private Context context;
//		private PowerManager.WakeLock mWakeLock;
//
//		public PopulateDBTask(Context context) {
//			this.context = context;
//		}
//
//		
//		// Populate device's SQLite database
//		@Override
//		protected String doInBackground(String... sUrl) {
//			return null;}
//	}
//}
