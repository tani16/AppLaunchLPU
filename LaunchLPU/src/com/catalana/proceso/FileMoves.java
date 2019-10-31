package com.catalana.proceso;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import com.catalana.utils.Constantes;
import com.catalana.utils.ExceptionLPU;
import com.catalana.utils.TratamientoFicheros;

public class FileMoves {
	
	private FileMoves() {
	    throw new IllegalStateException("Utility class");
	}

	public static void execute(String tipo) throws ExceptionLPU {
		
		//Obtenemos el módulo a probar
		ArrayList<String> rawData = TratamientoFicheros.getArrayFromFile(Constantes.FILE_RAWDATA);
		String modulo = getModulo(rawData);
		
		if("old".equals(tipo)) {
			
			//movemos la dll modificada para su próxima ejecución
			TratamientoFicheros.moveDll(modulo, "After");
			
			//Limpiamos la carpeta temporal
			deleteTemporalFiles();
		}
		
		//Movemos el archivo SYSOUT a la carpeta temporal
		moveFile(tipo);
		
		//movemos la dll modificada para su próxima ejecución
		TratamientoFicheros.moveDll(modulo, "Before");
	}
	

	/**
	 * Mueve el fichero SYSOUT a la carpeta temporal renombrándolo
	 * @throws ExceptionLPU 
	 */
	private static void moveFile(String tipo) throws ExceptionLPU {
		
		Path origenPath = FileSystems.getDefault().getPath(Constantes.FILE_SYSOUT);
		Path destinoPath;
		if("old".equals(tipo)) {			
			destinoPath = FileSystems.getDefault().getPath(Constantes.FILE_TEST_BEFORE);
		}else {
			destinoPath = FileSystems.getDefault().getPath(Constantes.FILE_TEST_AFTER);
		}
		
		TratamientoFicheros.moveFile(origenPath, destinoPath);
			
	}


	/**
	 * Elimina los ficheros de pruebas anteriores en la carpeta temporal
	 * @throws ExceptionLPU 
	 */
	private static void deleteTemporalFiles() throws ExceptionLPU {
		
		Path file = FileSystems.getDefault().getPath(Constantes.FILE_TEST_AFTER);
		TratamientoFicheros.deteleFile(file);
		
		file = FileSystems.getDefault().getPath(Constantes.FILE_TEST_BEFORE);
		TratamientoFicheros.deteleFile(file);

		//file = FileSystems.getDefault().getPath(Constantes.FILE_RAWDATA);
		//TratamientoFicheros.deteleFile(file);
		
		file = FileSystems.getDefault().getPath(Constantes.FILE_RESULT_AFTER);
		TratamientoFicheros.deteleFile(file);
		
		file = FileSystems.getDefault().getPath(Constantes.FILE_RESULT_BEFORE);
		TratamientoFicheros.deteleFile(file);
		

	}
	
	/**
	 * Obtiene el nombre del programa que se está ejecutando.
	 * @param rawData
	 * @return
	 */
	public static String getModulo(ArrayList<String> rawData) {
		
		String modulo = null;
				
		String linea = rawData.get(0);
		modulo = linea.substring(linea.indexOf('-') + 1);
		
		return modulo;
	}
}
