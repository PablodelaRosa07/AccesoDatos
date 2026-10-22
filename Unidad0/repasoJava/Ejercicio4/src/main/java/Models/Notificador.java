package Models;

import java.util.Objects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Repository.RepoNotificador;

public class Notificador {
	
	private static final Logger logger = LogManager.getLogger(Notificador.class);
	
	
	private Pedido pedido;

	
	public Notificador(Pedido pedido) {
		super();
		this.pedido = pedido;
	}

	
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	
	
	public void enviarEmail(String direccion, String asunto, String cuerpo, RepoNotificador repoNotificador) {
		logger.debug("Email a: " +direccion+ ". Con asunto: " +asunto+ ". Cuerpo: " +cuerpo);
		String logger2 = String.valueOf(logger);
		repoNotificador.getListaNotificaciones().add(logger2);
	}
	
	
	
	


	@Override
	public int hashCode() {
		return Objects.hash(pedido);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notificador other = (Notificador) obj;
		return Objects.equals(pedido, other.pedido);
	}



	@Override
	public String toString() {
		return "Notificador [pedido=" + pedido + "]";
	}
	
	
	
}
