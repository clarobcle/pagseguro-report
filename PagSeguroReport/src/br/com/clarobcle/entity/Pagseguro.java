package br.com.clarobcle.entity;

import java.io.Serializable;

public class Pagseguro implements Serializable {

	private String data;
	private Float grossAmount;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Float getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(Float grossAmount) {
		this.grossAmount = grossAmount;
	}
	@Override
	public String toString() {
		return "Pagseguro [data=" + data + ", grossAmount=" + grossAmount + "]";
	}
	
	
}
