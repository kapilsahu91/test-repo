package com.afour.tad.pojo;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.Entity;
 
@Entity
@XmlRootElement
public class Sensor extends BaseEntity {
	private String sensor_id;
	private String sensor_data;
	private String device_id;
	
	public String getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(String sensor_id) {
		this.sensor_id = sensor_id;
	}
	public String getSensor_data() {
		return sensor_data;
	}
	public void setSensor_data(String sensor_data) {
		this.sensor_data = sensor_data;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
}