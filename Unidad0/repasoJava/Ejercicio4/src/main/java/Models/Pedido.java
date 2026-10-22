package Models;

import java.util.Objects;

public class Pedido {

	
	private String identificador;
	private Cliente cliente;
	private double importe;
	private Estado estado;
	
	public Pedido(String identificador, Cliente cliente, double importe) {
		super();
		this.identificador = identificador;
		this.cliente = cliente;
		this.importe = importe;
		this.estado = estado.PENDIENTE;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, estado, identificador, importe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(cliente, other.cliente) && estado == other.estado
				&& Objects.equals(identificador, other.identificador)
				&& Double.doubleToLongBits(importe) == Double.doubleToLongBits(other.importe);
	}

	@Override
	public String toString() {
		return "Pedido [identificador=" + identificador + ", cliente=" + cliente + ", importe=" + importe + ", estado="
				+ estado + "]";
	}
	
	
}
