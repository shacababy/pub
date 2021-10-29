package adas.g3.pg30000.db.model;

import adas.g3.pg30000.db.SimpleField;
import adas.g3.pg30000.db.SimpleField.Type;
import adas.g3.pg30000.db.SimpleTable;

public class staff_view extends SimpleTable {
	public static SimpleField Field_id = new SimpleField("id", Type.int4);
	public static SimpleField Field_name = new SimpleField("name", Type.text);
	public static SimpleField Field_sex = new SimpleField("sex", Type.bpchar);

	public static String ALL_FIELDS = "id,name,sex";
	public static String TABLE_NAME = "staff_view";

	private int id;
	private java.lang.String name;
	private java.lang.String sex;

	public int getId(){
		return id;
	}


	public void setId(int id){
		 this.id = id;
	}


	public java.lang.String getName(){
		return name;
	}


	public void setName(java.lang.String name){
		 this.name = name;
	}


	public java.lang.String getSex(){
		return sex;
	}


	public void setSex(java.lang.String sex){
		 this.sex = sex;
	}


}
