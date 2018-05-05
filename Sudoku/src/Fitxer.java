import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Fitxer {
	
	private int escrit;
	
	public int getEscrit() {
		return escrit;
	}

	public void setEscrit(int escrit) {
		this.escrit = escrit;
	}

	public Fitxer(){
		
	}
	
	public void llegirFitxer9 (int[][] numbers){

		try {
			File file = new File("9x9.txt");
			BufferedReader input = new BufferedReader(new FileReader(file));
			
			String line = null;
			int row = 0;
			int column = 0;
			
			while((line = input.readLine()) != null) {
				
				column = 0;
				
				for(int i = 0; i < line.length(); i+=2) {
					
					char current = line.charAt(i);
					String toInt = "";
					
					if (current == '-') toInt = "-1";
					else toInt = current + "";
					
					
					numbers[row][column] = Integer.parseInt(toInt);
					
					column++;
				}
				row++;
			}
			
			input.close();
			

			
			
		} catch(FileNotFoundException e) {
			
			System.out.println("Fitxer no trobat");
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		

	}
	
	public void llegirFitxerSamurai (int[][] numbers){

		try {
			File file = new File("samurai.txt");
			BufferedReader input = new BufferedReader(new FileReader(file));
			
			String line = null;
			int row = 0;
			//int numbers[][] = new int[21][21];
			int column = 0;
			
			while((line = input.readLine()) != null) {
				
				column = 0;
				
				for(int i = 0; i < line.length(); i+=2) {
					
					char current = line.charAt(i);
					String toInt = "";
					
					if (current == '-') toInt = "-1";
					else if (current == '*') toInt = "-2";
						else toInt = current + "";
					
					
					numbers[row][column] = Integer.parseInt(toInt);

					column++;
				}
				row++;
			}
			
			input.close();
			
			
		} catch(FileNotFoundException e) {
			
			System.out.println("Fitxer no trobat");
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		

	}
	
	public void llegirFitxer16 (int[][] numbers){

		try {
			File file = new File("16x16.txt");
			BufferedReader input = new BufferedReader(new FileReader(file));
			
			String line = null;
			int row = 0;
			//int numbers[][] = new int[16][16];
			int column = 0;
			
			while((line = input.readLine()) != null) {
				
				column = 0;
				
				for(int i = 0; i < line.length(); i+=2) {
					
					char current = line.charAt(i);
					String toInt = "";
					
					if (current == '-') toInt = "-1";
					else toInt = current + "";
		
					if (i+1 < line.length() && line.charAt(i+1) >= '0' && line.charAt(i+1) <= '6') {
						
						toInt += line.charAt(i+1);
						i++;
					}
										
					//System.out.println("toInt --> " + toInt);
					
					numbers[row][column] = Integer.parseInt(toInt);
					
					column++;
				}
				row++;
			}
			
			input.close();
			

			//SudokuGUI sudokuGUI = new SudokuGUI("Sudoku 16x16", 500, 500, new boolean[16][16]);
			
			//sudokuGUI.updateBoard(numbers);
			
		} catch(FileNotFoundException e) {
			
			System.out.println("Fitxer no trobat");
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		

	}
	
	public void escriuFitxer(int[][] x, int v, String nom){

		try {
			File file = new File(nom);
			BufferedWriter bw;

			if(escrit == 0){
				//comencem a escriure
				escrit++; 
				bw = new BufferedWriter(new FileWriter(nom));
			}else{
				//continuem escrivint
				bw = new BufferedWriter(new FileWriter(nom, true));
			}
			
			for (int i = 0; i < v; i++){
				for(int j = 0; j < v ; j++){
					if(x[i][j] == -1){
						bw.write("* ");
					}else{
						
						bw.write(x[i][j] + " ");
					}
				}
				bw.write ("\n");
			}
			bw.write ("------------------------------------------------"+ "\n");

			bw.close();
		
			
		} catch(FileNotFoundException e) {
			
			System.out.println("Fitxer no trobat");
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}
}