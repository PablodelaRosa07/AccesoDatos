
public class Prueba1 {
	public static void main(String[] args)
	{
			Runtime rt = Runtime.getRuntime();
			System.out.println("Memoria Libre: " + rt.freeMemory());
			System.out.println("Total Memoria: " + rt.totalMemory());
			System.out.println("Max Memoria: " + rt.maxMemory());
			System.out.println("Nº Procesadores: " + rt.availableProcessors());
			System.out.println("Terminando programa...");
			rt.exit(1); 
	}

}
