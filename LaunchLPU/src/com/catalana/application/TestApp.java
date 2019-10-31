package com.catalana.application;

import java.util.ArrayList;

import com.catalana.proceso.CompareFiles;
import com.catalana.proceso.FileMoves;
import com.catalana.utils.Constantes;
import com.catalana.utils.ExceptionLPU;
import com.catalana.utils.TratamientoFicheros;

public class TestApp {

	public static void main(String[] args) throws ExceptionLPU {
		// TODO Auto-generated method stub
		//CompareFiles.execute();
		
		//FileMoves.execute("old");
		
		//System.out.println(args[0]); 
		//if(args[0].equals("copyFile")) {
			//FileMoves.execute("old");
		//}else {
			//FileMoves.execute("new");
			//CompareFiles.execute();
		//}
		FileMoves.execute("old");
		
	}

}
