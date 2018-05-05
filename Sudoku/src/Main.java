import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		
		Fitxer fitxer = new Fitxer();
		Fitxer fitxerSortida = new Fitxer();
		
		Scanner scanner = new Scanner(System.in);
		Timer timer;
		int opcioSortida = 0;
		int marcatge = 1;
		String FitxerE;
		String nomFitxerS;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
		/*
		do{
			System.out.println("");
			System.out.println("Benvingut al solucionador de Sudokus.");
			System.out.println("");
			System.out.println("Tria una Opcio: ");
			System.out.println("0. No es volen visualitzar les solucions.");
			System.out.println("1. Es volen mostrar les solucions per linia de comandes.");
			System.out.println("2. Es volen mostrar les solucions de forma grafica.");
			System.out.println("3. Es volen guardar les solucions en el fitxer de sortida.");
			System.out.println("4. Exit.");
			
			opcio_Menu = scanner.nextInt();
			scanner.nextLine();
			
			if(opcio_Menu!=4){

				System.out.println("Nom fitxer: (9x9)(16x16)(samurai)");
				String FitxerE = scanner.nextLine();
				
				File file = new File(FitxerE+".txt");
				BufferedReader input = new BufferedReader(new FileReader(file));
				
				System.out.println("Vols utilitzar marcatge? (Si 1/ No 0)");
				Marcatge = scanner.nextInt();
				scanner.nextLine();
				
				if(Marcatge != 0 && Marcatge != 1){
					Marcatge = 0;
				}*/
		//sempre hi haura marcatge
		marcatge = 1;
		//opcio de sortida
		opcioSortida = Integer.parseInt(args[1]);
		//llegim una linea per saber quin sudoku es
		String line = null;
		int i= 0;
		FitxerE = args[0];
		File file = new File(FitxerE);
		BufferedReader input = new BufferedReader(new FileReader(file));
		
		while((line = input.readLine()) != null) {
			i++;
		}
		
		nomFitxerS = args[2];
		
		fitxerSortida.setEscrit(0);
		
		switch(i){
		
			case 9:
				Backtracking b9 = new Backtracking();
				timer = new Timer();
				timer.start();
				
				int numbers9[][] = new int[9][9];
				
				fitxer.llegirFitxer9(numbers9);
				b9.initMarcatge(numbers9);
				
				Date dateIni9 = new Date();
				System.out.println("Data Inici: "+ dateFormat.format(dateIni9));
				System.out.println("");
				
				b9.setSolucions(0);
				b9.Backtracking9(numbers9, 0, 0, timer, opcioSortida, fitxerSortida, marcatge, nomFitxerS);
				
				Date dateFi9 = new Date();
				System.out.println("Data Final: "+ dateFormat.format(dateFi9));
				System.out.println("");
				System.out.println("Temps transcorregut: "+ (dateFi9.getTime()-dateIni9.getTime())  + " ms");
				System.out.println("");
				
				System.out.println("Numero de Solucions: " + b9.getSolucions());
				break;
			
			case 16:
				Backtracking16 b16 = new Backtracking16();
				timer = new Timer();
				timer.start();
				
				int numbers16[][] = new int[16][16];
				
				//llegim fitxer
				fitxer.llegirFitxer16 (numbers16);
				
				//dibuixem fitxer
				
				
				//solucionem sudoku
				
				Date dateIni16 = new Date();
				System.out.println("Data Inici: "+ dateFormat.format(dateIni16));
				System.out.println("");
				
				b16.setSolucions(0);
				b16.initMarcatge(numbers16);
				b16.Backtracking(numbers16, 0, 0, timer, opcioSortida, fitxerSortida, marcatge, nomFitxerS);
				
				Date dateFi16 = new Date();
				System.out.println("Data Final: "+ dateFormat.format(dateFi16));
				System.out.println("");
				System.out.println("Temps transcorregut: "+ (dateFi16.getTime()-dateIni16.getTime())  + " ms");
				System.out.println("");
				
				//printem solucio
				
				System.out.println("Numero de Solucions: " + b16.getSolucions());

				break;
			
			case 21:
				Samurai bs = new Samurai(nomFitxerS);
				timer = new Timer();
				timer.start();
				int numbers21[][] = new int[21][21];
				
				//llegim fitxer
				fitxer.llegirFitxerSamurai(numbers21);
				//dibuixem fitxer
			
				
				//solucionem sudoku
				Date dateIniS = new Date();
				System.out.println("Data Inici: "+ dateFormat.format(dateIniS));
				System.out.println("");
				
				bs.setSolucions(0);
				bs.initSamurai(numbers21, 0);
				bs.initMarcatge();
				bs.BacktrackingSam(timer, 0, 0, 0, opcioSortida, fitxerSortida, marcatge);
				
				Date dateFiS = new Date();
				System.out.println("Data Final: "+ dateFormat.format(dateFiS));
				System.out.println("");
				System.out.println("Temps transcorregut: "+ (dateFiS.getTime()-dateIniS.getTime())  + " ms");
				System.out.println("");
				
				System.out.println("Numero de Solucions: " + bs.getSolucions());

				//printem solucio
				//sudokuGUI21.updateBoard(numbers21);
				break;
			default: 
				break;		
		}
		System.out.println("no hi ha mes solucions");
		System.out.println("");
		System.out.println("------------------------------");
		
	}
}