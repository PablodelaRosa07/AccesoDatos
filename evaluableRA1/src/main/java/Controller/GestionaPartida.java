package Controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.Epoca;
import Repository.MazmorraRepository;
import Service.PartidaService;

public class GestionaPartida {
	public static void main(String[] args) throws Exception {
        String rutaFichero = args[0];
        

        MazmorraRepository repositorio = new MazmorraRepository();
        PartidaService servicio = new PartidaService(repositorio);
        GestionaPartida controlador = new GestionaPartida(servicio);
        
        servicio.procesarYAlmacenarMazmorras(rutaFichero);
        
        repositorio.agruparPorEpoca(Epoca.NINO); 
        repositorio.agruparPorEpoca(Epoca.ADULTO);
        repositorio.agruparPorEpoca(Epoca.AMBAS);
        
        
        /*servicio.completarMazmorra(rutaFichero, "Templo del Fuego", 48.3);
        servicio.completarMazmorra(rutaFichero, "Gran Árbol Deku", 15.0);
        servicio.completarMazmorra(rutaFichero, "Caverna de los Dodongos", 40.0);
        servicio.completarMazmorra(rutaFichero, "Templo de la Luz", 10.0); */     // Lo comento porque me salta error
         
        servicio.exportarCSV("Mazmorras.csv", rutaFichero);

        

    }
	private static final Logger logger = LogManager.getLogger(GestionaPartida.class);
    private PartidaService servicio;

    public GestionaPartida(PartidaService servicio) {
        this.servicio = servicio;
    }
    
    
}
