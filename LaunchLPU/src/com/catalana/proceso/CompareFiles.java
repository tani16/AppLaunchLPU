package com.catalana.proceso;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.catalana.method.LoadMethods;
import com.catalana.utils.Constantes;
import com.catalana.utils.ExceptionLPU;
import com.catalana.utils.TratamientoFicheros;

public class CompareFiles {
	
	private CompareFiles() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void execute() throws ExceptionLPU {
		
		if(startCheck()) {
			JOptionPane.showMessageDialog(null,"Las Pruebas Unitarias han finalizado correctamente","Bien",JOptionPane.INFORMATION_MESSAGE); 
		}else {
			JOptionPane.showMessageDialog(null,"Resultado de las Pruebas Unitarias: FAIL","Error",JOptionPane.ERROR_MESSAGE); 
			TratamientoFicheros.openNotepad();
		}
			
	}
	
	private static boolean startCheck() throws ExceptionLPU {
		
		boolean result = true;
		
		ArrayList<String> displaysAfter = TratamientoFicheros.getDisplaysFormatted(Constantes.FILE_TEST_AFTER);
		ArrayList<String> displaysBefore = TratamientoFicheros.getDisplaysFormatted(Constantes.FILE_TEST_BEFORE);
				
		for (int i = 0; i < displaysAfter.size(); i++) {
			if(!displaysAfter.get(i).equals(displaysBefore.get(i))) {
				result = false;
				LoadMethods.addDisplayFail(displaysAfter.get(i), displaysBefore.get(i));
			}
		}
		
		return result;
	}

	

}
