package adas.g3.pg30000.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class SimpleField {
	final String field;
	final Type type;
	public SimpleField(String field, Type type) {
		this.field = field;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Field [field=" + field + ", type=" + type + "]";
	}
	
	public String getSource() {
		return "public static SimpleField FILED_%s = new SimpleField(%s, Type.%s);\n".formatted(field, field, type);
	}

	public static enum Type {
		bool(Boolean.class, "bool"),
		
		bpchar(String.class),
		varchar(String.class),
		text(String.class),
		json(String.class),
		jsonb(String.class),
		
		int2(Short.class, "short"),
		int4(Integer.class, "int"),
		int8(Long.class, "long"),
		float4(Float.class, "float"),
		float8(Double.class, "double"),
		numeric(BigDecimal.class),

		date(Date.class),
		time(Time.class),
		timetz(Time.class),
		timestamp(Timestamp.class),
		timestamptz(Timestamp.class),

		inet(Object.class),
		UNKNOWN(Object.class)
		;
		
		public Class<?> c;
		public String p;
		
		private Type(Class<?> c) {
			this.c = c;
		}

		private Type(Class<?> c, String p) {
			this.c = c;
			this.p = p;
		}
		
		public static Type getType(String s) {
			for(Type t : Type.values()) {
				if (t.name().equals(s)) {
					return t;
				}
			}
			return UNKNOWN;
		}
	}
}
