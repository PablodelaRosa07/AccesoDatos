package Models;

public class ResultadoOperacionModel {
    private String paso;
    private boolean exito;
    private String mensaje;

    public ResultadoOperacionModel(String paso, boolean exito, String mensaje) {
        this.paso = paso;
        this.exito = exito;
        this.mensaje = mensaje;
    }

	public String getPaso() {
		return paso;
	}

	public void setPaso(String paso) {
		this.paso = paso;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

    
}