package com.mariasara.demo.rest;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariasara.demo.DAO.ProductsDAO;
import com.mariasara.demo.entity.Factura;
import com.mariasara.demo.entity.Product;

@RestController
@RequestMapping("/")
public class ProductREST {
	
	@Autowired
	private ProductsDAO productDAO;
	Factura facturaPorCliente;
	long cedulaCliente=0;
	String direccionCliente="";
	
	@RequestMapping(value="/user/{cedula}/{direccion}",method=RequestMethod.GET)
	public ResponseEntity<Void> obtenerLogIn(@PathVariable("cedula") Long cedula,@PathVariable("direccion") String direccion){
		cedulaCliente=cedula;
		direccionCliente=direccion;
		return ResponseEntity.ok(null);
	}
	

	//para ingresar los datos
	@RequestMapping(value="usuario",method=RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		product.setFecha(LocalDateTime.now());
		Product newProduct=productDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	//sumar datos
	//Historia 2.1
	@RequestMapping(value="usuario",method=RequestMethod.GET)
	public Factura getTotal(){
		List<Product>products=productDAO.findAll();
		Iterator<Product> iterador = products.iterator();
		Product temp=null;
		String[] productos=new String[products.size()];
		int precios[]=new int[productos.length];
		int valorIteracion=0;
		while(iterador.hasNext()){
            temp = iterador.next();
            productos[valorIteracion]=temp.getProducto();
            precios[valorIteracion]=temp.getPrecio();
            valorIteracion++;
		}
		facturaPorCliente=new Factura(cedulaCliente,direccionCliente,productos,precios,2000);
		facturaPorCliente.setFecha(LocalDateTime.now());
		return facturaPorCliente;
	}
	
	//Historia 2.2---------------------------------------------------------------
	//editar pedido si fue creado no antes de 5 horas
	
	//1.verificar si se confirmo en menos de 5 horas
	public boolean menosdeCincoHoras() {
		LocalDateTime tiempo =LocalDateTime.now();
		int deltaHora=tiempo.getHour()-facturaPorCliente.getFecha().getHour();
		System.out.println(deltaHora);
		boolean estado=true;
		if(deltaHora>=0 && deltaHora<=5) {
			estado=true;
		}
		else if(deltaHora>=-5 && deltaHora<=0) {
			estado=true;
		}
		else{
			estado=false;
		}
		return estado;
	}
	
	//2. editar si cumple con la condicion
	//3. para actualizar el valor total solo se debe llamar "/total"
	@RequestMapping(value="usuario",method=RequestMethod.PUT)
	public ResponseEntity<Product> editarProducto(@RequestBody Product product){
		boolean puedeEditar=false;
		//recorrer lista
		Optional<Product> optionalProduct=productDAO.findById(product.getId());
		Product productoAModificar=optionalProduct.get();
		puedeEditar= (productoAModificar.getPrecio()<=product.getPrecio());
		if(menosdeCincoHoras()&&puedeEditar) {
			//Optional<Product> optionalProduct=productDAO.findById(product.getId());
				if (optionalProduct.isPresent()) {
					Product updateProduct=optionalProduct.get();
					updateProduct.setPrecio(product.getPrecio());
					//updateProduct.setFecha(product.getFecha());
					updateProduct.setProducto(product.getProducto());
					updateProduct.setFecha(LocalDateTime.now());
					productDAO.save(updateProduct);
					return ResponseEntity.ok(updateProduct);
				}else {
					return ResponseEntity.noContent().build();
		}}else {
		return ResponseEntity.noContent().build();
		}
	}
	
	
	//Historia 2.3 
	//verificar el tiempo
	//1.verificar si se confirmo en menos de 12 horas
		public boolean masdeDoceHoras() {
			LocalDateTime tiempo =LocalDateTime.now();
			int deltaHora=tiempo.getHour()-facturaPorCliente.getFecha().getHour();
			boolean estado=true;
			if(deltaHora>=0 && deltaHora<=12) {
				estado=true;
			}
			else if(deltaHora>=-12 && deltaHora<=0) {
				estado=true;
			}
			else{
				estado=true;
			}
			return estado;
		}
	//Borrar el pedido dependiendo de las condiciones
		@RequestMapping(value="usuario",method=RequestMethod.DELETE)
		public ResponseEntity<Void> deleteProduct(){
			productDAO.deleteAll();
			if(masdeDoceHoras()) {//verificar esta condicion 
				Product comisionPorCancelacion=new Product();
				comisionPorCancelacion.setPrecio((int)(facturaPorCliente.getTotal()*0.1));
				comisionPorCancelacion.setProducto("Comision por cancelacion");
				comisionPorCancelacion.setCedula(cedulaCliente);
				comisionPorCancelacion.setDireccion(direccionCliente);
				productDAO.save(comisionPorCancelacion);
			}
			return ResponseEntity.ok(null);
		}
	

}
