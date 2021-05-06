package com.mariasara.demo.entity;

import java.time.LocalDateTime;

public class Factura {

	private long cedula;
	private LocalDateTime fecha;
	private String direccion;
	private String productos[];
	private int precios[];
	private float total;
	private float valorDomicilio;
	private float valorIva;
	
	private final float IVA=0.19f;
	
	//instancia
	public Factura(long cedula,String direccion,String productos[],int precios[],float valorDomicilio) {
		this.cedula=cedula;
		this.direccion=direccion;
		this.productos=productos;
		this.precios=precios;
		this.valorDomicilio=valorDomicilio;
		this.total=totalCompra();
	}
	
	//Metodos clase
	public float totalCompra() {
		float totalCompra=0;
		for(int i=0;i<precios.length;i++) {
			totalCompra+=precios[i];
		}
		
		if (totalCompra>70000 && totalCompra<100000) {
			valorIva=totalCompra*IVA;
			totalCompra=totalCompra+valorIva+valorDomicilio;
		}else if(totalCompra>100000) {
			setValorDomicilio(0);
			valorIva=totalCompra*IVA;
			totalCompra=totalCompra+valorIva;
		}else {
			valorIva=0;
		}
		return totalCompra;
	}
	
	//Metodos set y get

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String[] getProductos() {
		return productos;
	}

	public void setProductos(String productos[]) {
		this.productos = productos;
	}

	public int[] getPrecios() {
		return precios;
	}

	public void setPrecios(int precios[]) {
		this.precios = precios;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getValorDomicilio() {
		return valorDomicilio;
	}

	public void setValorDomicilio(float valorDomicilio) {
		this.valorDomicilio = valorDomicilio;
	}
	
	public float getValorIva() {
		return valorIva;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	
}
