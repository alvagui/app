package omicron.app.dbManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;

public class DBBackupUtils {

	private static final String DEBUG_TAG = "DBBackupUtils";

	private static boolean copyFile(String srcFilePath, String dstFilePath) {

		Log.d(DEBUG_TAG, "Copying file from " + srcFilePath + " to "+ dstFilePath+".");
		FileInputStream fis;
		try {
			fis = new FileInputStream(srcFilePath);

			// Open the empty db as the output stream
			OutputStream output = new FileOutputStream(dstFilePath);

			// Transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			// Close the streams
			output.flush();
			output.close();
			fis.close();
			Log.d(DEBUG_TAG, "File copied.");
			return true;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean backupDB(Context context) {

		String inFileName = context
				.getDatabasePath(DbAdapter.getDatabaseName()).getAbsolutePath();
		// File dbFile = new File(inFileName);
		File outDir = new File(context.getExternalFilesDir(null) + "/backup/");
		Log.d(DEBUG_TAG, "File being created in :" + outDir.getAbsolutePath());
		if (!outDir.isDirectory()) {
			if (!outDir.mkdirs())
				Log.d(DEBUG_TAG, "Directory non-existent, not created.");
		}
		String outFileName = outDir.getAbsolutePath() + "/"
				+ DbAdapter.getDatabaseName();

		return copyFile(inFileName, outFileName);

	}

	public static boolean restoreDB(Context context) {

		Log.d(DEBUG_TAG, "Restoring DB.");
		return copyFile(context.getExternalFilesDir(null) + "/backup/"
				+ DbAdapter.getDatabaseName(),
				context.getDatabasePath(DbAdapter.getDatabaseName())
						.getAbsolutePath());
		// try {
		// File currentDB = new File(context.getDatabasePath(
		// DbAdapter.getDatabaseName()).getAbsolutePath());
		// File backupDB = new File(context.getExternalFilesDir(null)
		// + "/backup/" + DbAdapter.getDatabaseName());
		//
		// if (currentDB.exists()) {
		// FileChannel src = new FileInputStream(backupDB).getChannel();
		// FileChannel dst = new FileOutputStream(currentDB).getChannel();
		// Log.d(DEBUG_TAG, "Restoring DB");
		// dst.transferFrom(src, 0, src.size());
		// }
		// return true;
		// } catch (Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		//
		// return false;
	}
}
