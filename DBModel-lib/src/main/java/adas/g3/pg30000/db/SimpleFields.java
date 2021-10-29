package adas.g3.pg30000.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class SimpleFields extends ArrayList<SimpleField> {

	public List<String> getFields() {
		final List<String> list = new ArrayList<>();

		this.forEach(s -> {
			list.add("\tpublic static SimpleField Field_%s = new SimpleField(\"%s\", Type.%s);".formatted(s.field,
					s.field, s.type.name()));
		});

		List<String> l = this.stream().map(s -> s.field).collect(Collectors.toList());

		list.add("\n\tpublic static String ALL_FIELDS = \"%s\";".formatted(String.join(",", l)));

		return list;
	}

	public List<String> getProperties() {
		final List<String> list = new ArrayList<>();
		this.forEach(s -> {
			list.add("\tprivate %s %s;".formatted(s.type.p != null ? s.type.p : s.type.c.getName(), s.field));
		});

		return list;
	}

	public List<String> getSet() {
		final List<String> list = new ArrayList<>();
		this.forEach(s -> {
			list.add("\tpublic %s get%s(){\n\t\treturn %s;\n\t}\n\n"
					.formatted(s.type.p != null ? s.type.p : s.type.c.getName(), capitalize(s.field), s.field));
			list.add("\tpublic void set%s(%s %s){\n\t\t this.%s = %s;\n\t}\n\n".formatted(capitalize(s.field),
					s.type.p != null ? s.type.p : s.type.c.getName(), s.field, s.field, s.field));
		});

		return list;
	}

	private String capitalize(String str) {
		if (str == null)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
