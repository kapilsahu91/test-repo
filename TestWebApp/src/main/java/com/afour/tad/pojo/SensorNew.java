package com.afour.tad.pojo;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexes;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.Property;
 
@Entity
@XmlRootElement
@Indexes(@Index(name = "aAndB", value = "a, b"))
public class SensorNew extends BaseEntity {
	@Property("b")
	private String channel_id;
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	private String created_at;
	@Property("a")
	private String entry_id;
	
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}
}