package omicron.app.dbManagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class will get the data from remote db before is stored in local sqlite db.
 * @author aaguilar
 *
 */
public class ExportedTableContainer {
	private String tableName;
	private List<List<String>> tableData;
	private List<String> tableHeaders;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String table) {
		this.tableName = table;
	}

	public List<List<String>> getTableData() {
		return tableData;
	}

	public void setTableData(List<List<String>> data) {
		this.tableData = data;
	}

	/**
	 * @param tableName
	 * @param tableData
	 */
	public ExportedTableContainer(String tableName, List<List<String>> tableData) {
		super();
		this.tableName = tableName;
		Iterator<List<String>> it = tableData.iterator();
		this.tableHeaders = it.next();
		this.tableData = new ArrayList<List<String>>();
		while (it.hasNext())
			this.tableData.add(it.next());
	}

	public List<String> getHeaders() {
		return tableHeaders;
	}

}
