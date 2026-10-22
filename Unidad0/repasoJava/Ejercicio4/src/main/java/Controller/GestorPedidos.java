package Controller;

import Models.Cliente;
import Models.Pedido;
import Repository.RepoPedidos;


public class GestorPedidos {
	
public static void main(String[] args) {
	RepoPedidos repoPedidos = new RepoPedidos();
	
	Cliente cliente1 = new Cliente("Paquito", "abc@gmail.com", 123456789);
	Cliente cliente2 = new Cliente("Paquito", "abc@gmail.com", 123456789);
	
	Pedido pedido1 = new Pedido("123abc", cliente1, 1000);
	Pedido pedido2 = new Pedido("456def", cliente1, 2000);
	Pedido pedido3 = new Pedido("789ghi", cliente2, 3000);
	
	repoPedidos.enviarPedido(pedido1, null, null);
	repoPedidos.enviarPedido(pedido2, null, null);
	repoPedidos.enviarPedido(pedido3, null, null);
}
}
