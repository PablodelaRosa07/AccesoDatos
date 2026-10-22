package Todo;

import java.io.File;
import java.io.FilenameFilter;

public class FiltroExtensionTxt implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
    	boolean devuelve = false;
    	if (name != null && name.toLowerCase().endsWith(".txt")) {
    		devuelve = true;
    	}
        return devuelve;
    }
}
