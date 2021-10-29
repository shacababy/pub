package adas.g3.pg30000.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelCreator {

	public static void main(String[] args) throws SQLException, IOException {
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/czd00", "db20", "db20");
		String sql = "select table_name, column_name, udt_name from information_schema.columns where table_catalog='czd00' and table_schema='public' order by table_name, ordinal_position";
		String BLANK_LINE = "";
		
		Map<String, SimpleFields> tables = new HashMap<>();
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while(rs.next()) {
				if (!tables.containsKey(rs.getString(1))) {
					tables.put(rs.getString(1), new SimpleFields());
				}
				tables.get(rs.getString(1)).add(new SimpleField(rs.getString(2), SimpleField.Type.getType(rs.getString(3))));
			}
		}
		
		File folder = new File("./model");
		folder.mkdir();
		if (!folder.exists()) {
			return;
		}
		for(Map.Entry<String, SimpleFields> e : tables.entrySet()) {
			String classname = e.getKey();
			SimpleFields fields = e.getValue();
			
			File f = new File("./model/" + classname + ".java");
			f.delete();
			f.createNewFile();
			
			List<String> lines = new ArrayList<>();
			lines.add("package adas.g3.pg30000.db.model;");
			lines.add(BLANK_LINE);
			
			lines.add("import " + SimpleField.class.getName() + ";");
			lines.add("import " + SimpleField.class.getName() + ".Type;");
			lines.add("import " + SimpleTable.class.getName() + ";");
			lines.add(BLANK_LINE);
			
			lines.add("public class %s extends %s {".formatted(classname, SimpleTable.class.getSimpleName()));
			lines.addAll(fields.getFields());
			lines.add("\tpublic static String TABLE_NAME = \"%s\";".formatted(classname));
			
			lines.add(BLANK_LINE);
			lines.addAll(fields.getProperties());
			lines.add(BLANK_LINE);
			lines.addAll(fields.getSet());
			lines.add("}");
			
			Files.write(f.toPath(), lines, StandardOpenOption.WRITE);
		}
	}
}
