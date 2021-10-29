package adas.g3.pg30000.db.model;

import adas.g3.pg30000.db.SimpleField;
import adas.g3.pg30000.db.SimpleField.Type;
import adas.g3.pg30000.db.SimpleTable;

public class staff extends SimpleTable {
	public static SimpleField Field_id = new SimpleField("id", Type.int4);
	public static SimpleField Field_name = new SimpleField("name", Type.text);
	public static SimpleField Field_sex = new SimpleField("sex", Type.bpchar);
	public static SimpleField Field_addess = new SimpleField("addess", Type.varchar);
	public static SimpleField Field_info = new SimpleField("info", Type.jsonb);
	public static SimpleField Field_birth = new SimpleField("birth", Type.timestamp);

	public static String ALL_FIELDS = "id,name,sex,addess,info,birth";
	public static String TABLE_NAME = "staff";

	private int id;
	private java.lang.String name;
	private java.lang.String sex;
	private java.lang.String addess;
	private java.lang.String info;
	private java.sql.Timestamp birth;

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


	public java.lang.String getAddess(){
		return addess;
	}


	public void setAddess(java.lang.String addess){
		 this.addess = addess;
	}


	public java.lang.String getInfo(){
		return info;
	}


	public void setInfo(java.lang.String info){
		 this.info = info;
	}


	public java.sql.Timestamp getBirth(){
		return birth;
	}


	public void setBirth(java.sql.Timestamp birth){
		 this.birth = birth;
	}


}
