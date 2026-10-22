package Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import Models.Estado;
import Models.Pedido;
import Models.EnviadorSms; 
import Repository.RepoNotificador;
import org.apache.logging.log4j.Logger;

public class RepoPedidos {
	
	private Set<Pedido> listaPedidos;
   
	
	
	public RepoPedidos() {
		super();
		this.listaPedidos = new HashSet<Pedido>();
	}




	public RepoPedidos(Set<Pedido> listaPedidos) {
		super();
		this.listaPedidos = listaPedidos;
	}

	
	
	
	public Set<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(Set<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	
	
	
	public void confirmarPedido(Pedido pedido, EnviadorSms enviadorSms, RepoNotificador repoNotificador) {
		pedido.setEstado(Estado.CONFIRMADO);
		enviadorSms.enviarSms(String.valueOf(pedido.getCliente().getNumTelefono()), "Se ha recibido el pedido");

	}
	
	public void enviarPedido(Pedido pedido, EnviadorSms enviadorSms, RepoNotificador repoNotificador) {
		pedido.setEstado(Estado.ENVIADO);
		enviadorSms.enviarSms(String.valueOf(pedido.getCliente().getNumTelefono()), "Se ha enviado el pedido");

	}
	
	public void cancelarPedido(Pedido pedido, EnviadorSms enviadorSms, RepoNotificador repoNotificador) {
		pedido.setEstado(Estado.CANCELADO);
		enviadorSms.enviarSms(String.valueOf(pedido.getCliente().getNumTelefono()), "Se ha cancelado el pedido");

	}
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(listaPedidos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;    
		if (getClass() != obj.getClass())
			return false;
		RepoPedidos other = (RepoPedidos) obj;
		return Objects.equals(listaPedidos, other.listaPedidos);
	}

	@Override
	public String toString() {
		return "RepoPedidos [listaPedidos=" + listaPedidos + "]";
	}
	
	
}
