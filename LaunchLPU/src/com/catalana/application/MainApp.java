package com.catalana.application;

import com.catalana.proceso.CompareFiles;
import com.catalana.proceso.FileMoves;
import com.catalana.utils.ExceptionLPU;

public class MainApp {

	public static void main(String[] args) throws ExceptionLPU {

		System.out.println(args[0]);
		if(args[0].equals("copyFile")) {
			FileMoves.execute("old");
		}else {
			FileMoves.execute("new");
			CompareFiles.execute();
		}
			
	}

}
