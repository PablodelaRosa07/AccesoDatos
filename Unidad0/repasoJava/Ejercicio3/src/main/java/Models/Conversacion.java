package Models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Conversacion {
    private String identificador;
    private TipoAgente tipoAgente;
    private String pregunta;
    private String respuesta;
    private LocalDate fechaConversacion;
    private int numValoracionesPositivas;


    public Conversacion(String identificador, TipoAgente tipoAgente, String pregunta, String respuesta,
			LocalDate fechaConversacion, int numValoracionesPositivas) {
		super();
		this.identificador = identificador;
		this.tipoAgente = tipoAgente;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.fechaConversacion = fechaConversacion;
		this.numValoracionesPositivas = numValoracionesPositivas;
	}

    private String calculaIdentificador() {
        Random rand = new Random();
        return fechaConversacion + "-" + rand.nextInt(10000);
    }

    

    public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public TipoAgente getTipoAgente() {
		return tipoAgente;
	}

	public void setTipoAgente(TipoAgente tipoAgente) {
		this.tipoAgente = tipoAgente;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public LocalDate getFechaConversacion() {
		return fechaConversacion;
	}

	public void setFechaConversacion(LocalDate fechaConversacion) {
		this.fechaConversacion = fechaConversacion;
	}

	public int getNumValoracionesPositivas() {
		return numValoracionesPositivas;
	}

	public void setNumValoracionesPositivas(int numValoracionesPositivas) {
		this.numValoracionesPositivas = numValoracionesPositivas;
	}

	public void incrementarValoracion() {
        this.numValoracionesPositivas++;
    }

	@Override
	public int hashCode() {
		return Objects.hash(fechaConversacion, identificador, Integer.valueOf(numValoracionesPositivas), pregunta,
				respuesta, tipoAgente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversacion other = (Conversacion) obj;
		return Objects.equals(fechaConversacion, other.fechaConversacion)
				&& Objects.equals(identificador, other.identificador)
				&& numValoracionesPositivas == other.numValoracionesPositivas
				&& Objects.equals(pregunta, other.pregunta) && Objects.equals(respuesta, other.respuesta)
				&& tipoAgente == other.tipoAgente;
	}

	@Override
	public String toString() {
		return "Conversacion [identificador=" + identificador + ", tipoAgente=" + tipoAgente + ", pregunta=" + pregunta
				+ ", respuesta=" + respuesta + ", fechaConversacion=" + fechaConversacion
				+ ", numValoracionesPositivas=" + numValoracionesPositivas + "]";
	}

	

    
}