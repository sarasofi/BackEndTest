package com.mariasara.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="products")
public class Product {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //hace que el id se incremente 
	private long id;
	
	@Column(name="direccion",nullable=false,length=30)//JPA crea la tabla por nosotros
	private String direccion;
	
	@Column(name="cedula")
	private long cedula;
	
	@Column(name="producto")
	private String producto;
	
	@Column(name="precio")
	private int precio;
	
	@Column(name="Fecha")
	private LocalDateTime fecha;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int f) {
		this.precio = f;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public long getCedula() {
		return cedula;
	}
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
}
