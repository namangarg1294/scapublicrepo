package com.intenum.jdbc.backup;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.intenum.util.db.DataTable;
import com.intenum.util.db.Row;

public class Backup {
	private Connection connection;
	private TableCollection tables;
	private boolean addEmptyTable;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyy-MM-dd");
	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
	private static final DateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");

	public Backup(Connection connection) {
		this.addEmptyTable = true;
		this.connection = connection;
	}

	public boolean isAddEmptyTable() {
		return addEmptyTable;
	}

	/**
	 * add or not <code>delete * from table</code>
	 * 
	 * @param addEmptyTable
	 */
	public void setAddEmptyTable(boolean addEmptyTable) {
		this.addEmptyTable = addEmptyTable;
	}

	public TableCollection getTables() {
		return tables;
	}

	public void execute(PrintStream stream) throws SQLException {
		DatabaseMetaData metaData = connection.getMetaData();
		String quote = metaData.getIdentifierQuoteString();

		DataTable dataTable = null;

		// get tables
		tables = new TableCollection();
		dataTable = DataTable.parse(metaData.getTables(null, null, null, null));
		for (Row row : dataTable) {
			tables.add(new Table(row.getString("TABLE_NAME")));
		}

		// get columns
		for (Table table : tables) {
			dataTable = DataTable.parse(metaData.getColumns(null, null,
					table.getName(), null));
			//System.out.print(dataTable);
			for (Row row : dataTable) {
				table.getColumns().add(
						new Column(row.getString("COLUMN_NAME"), row
								.getString("TYPE_NAME"), row
								.getInteger("DATA_TYPE")));
			}
		}

		// get constraints
		for (int i = 0; i < tables.size(); i++) {
			for (int j = 0; j < tables.size(); j++) {
				if (i != j) {
					dataTable = DataTable.parse(metaData.getCrossReference(
							null, null, tables.get(i).getName(), null, null,
							tables.get(j).getName()));
					if (dataTable.size() > 0) {
						Table src = tables.get(j);
						for (Row row : dataTable) {
							src.getConstraints()
									.add(new FK(row.getString("FKCOLUMN_NAME"),
											tables.get(i), row
													.getString("PKCOLUMN_NAME")));
						}
					}
				}
			}
		}

		tables.sort();

		if (addEmptyTable) {
			printInfo(stream, "clearing tables");
                        System.out.println("clearing tables");
			for (int i = tables.size() - 1; i >= 0; i--) {
				stream.println("delete from " + quote + tables.get(i).getName()
						+ quote + ";");
                                System.out.println("delete from " + quote + tables.get(i).getName()
						+ quote + ";");
			}
		}

		printInfo(stream, "inserting tables");
                System.out.println("inserting tables");
		for (Table table : tables) {
			dataTable = DataTable.execute(connection, "select * from " + quote
					+ table.getName() + quote);
			if (dataTable.size() > 0) {
				printInfo(stream, table.getName());

				String str = "insert into " + quote + table.getName() + quote
						+ " (" + quote + table.getColumns().get(0).getName()
						+ quote;
				for (int i = 1; i < table.getColumns().size(); i++) {
					str += ", " + quote + table.getColumns().get(i).getName()
							+ quote;
				}
				str += ") values ";
				stream.println(str);
                                System.out.println(str);
				for (int k = 0; k < dataTable.size(); k++) {
					Row row = dataTable.get(k);
					str = "(" + getSQLValue(table, row, 0);
					for (int i = 1; i < dataTable.getColumns().size(); i++) {
						str += ", " + getSQLValue(table, row, i);
					}
					str += ")";
					if (k < dataTable.size() - 1) {
						str += ",";
					} else {
						str += ";";
					}
					stream.println(str);
                                        System.out.println(str);
				}
				stream.println();
			}
		}
	}

	private void printInfo(PrintStream stream, String message) {
		stream.println("-- -------------------------------------------");
		stream.println("-- " + message);
		stream.println("-- -------------------------------------------");
	}

	public String getSQLValue(Table table, Row row, int index) {
		com.intenum.jdbc.backup.Column column = table.getColumns().get(index);
		int type = column.getDataType();
		if (row.get(index) == null) {
			return "null";
		} else {
			switch (type) {
			case Types.CHAR:
			case Types.VARCHAR:
			case Types.LONGNVARCHAR:
			case Types.NCHAR:
			case Types.NVARCHAR:
			case Types.LONGVARCHAR:
				return "\"" + row.getString(index) + "\"";

			case Types.DATE:
				return "\"" +  DATE_FORMAT.format(row.getDate(index)) + "\"";
				
			case Types.TIME:
				return "\"" + TIME_FORMAT.format(row.getDate(index)) + "\"";
				
			case Types.TIMESTAMP:
				return "\"" + DATE_TIME_FORMAT.format(row.getDate(index)) + "\"";

			default:
				return row.getString(index);
			}

		}
	}
}
