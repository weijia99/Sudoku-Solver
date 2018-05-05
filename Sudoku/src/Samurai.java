
public class Samurai {
		
	private int[][][] Array;
	private int[][] Samurai;
	private int compt;
	private int solucions;
	private int[][][][] marcatge;
	private int[][] S0;
	private int[][] S1;
	private int[][] S2;
	private int[][] S3;
	private int[][] S4;
	private String nomFitxS;
	
	public Samurai(String nomFitxS){
		
		Array = new int[5][9][9];
		Samurai = new int[21][21];
		marcatge = new int[5][3][9][9];
		S0 = new int[9][9];
		S1 = new int[9][9];
		S2 = new int[9][9];
		S3 = new int[9][9];
		S4 = new int[9][9];
		this.nomFitxS = nomFitxS;
		
	}

	public void initSamurai(int[][] samurai, int flag){
		
		this.Samurai = samurai;
		
		switch(flag){
		
		case 1:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[0][i][j] = samurai[i+6][j+6];
					S0[i][j] = samurai[i+6][i+6];
				}
			}
			
			break;
			
		case 2:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[1][i][j] = samurai[i][j];
					S1[i][j] = samurai[i][j];
				}
			}
			
			break;
			
		case 3:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[2][i][j] = samurai[i][j+12];
					S2[i][j] = samurai[i][j+12];
				}
			}
			
			break;
			
		case 4:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[3][i][j] = samurai[i+12][j];
					S3[i][j] = samurai[i+12][j];
				}
			}
			
			break;
			
		case 5:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[4][i][j] = samurai[i+12][j+12];
					S4[i][j] = samurai[i+12][j+12];
				}
			}
			
			break;
			
		case 0:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[0][i][j] = samurai[i+6][j+6];
					S0[i][j] = samurai[i+6][i+6];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[1][i][j] = samurai[i][j];
					S1[i][j] = samurai[i][j];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[2][i][j] = samurai[i][j+12];
					S2[i][j] = samurai[i][j+12];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[3][i][j] = samurai[i+12][j];
					S3[i][j] = samurai[i+12][j];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					Array[4][i][j] = samurai[i+12][j+12];
					S4[i][j] = samurai[i+12][j+12];
				}
			}
			
		}
		
	}
		
	public void BacktrackingSam(Timer timer, int f, int c, int sudoku, int opcio, Fitxer fs, int m){

		
		int fi = 0;
		
		while(Array[sudoku][f][c]!=(-1) && fi == 0){
			
			if(c == 8 && f == 8){
				fi = 1;
				
			}else{
				c++;
				if(c > 8){
					c = 0;
					f++;
				}
			}
		}
		
		if(fi == 0){
			
			//prepara recorregut
			Array[sudoku][f][c] = 0;
			//hi ha successor
			while(Array[sudoku][f][c] < 9){
				//seguent germa		
				Array[sudoku][f][c]++;
				//solucio

				if(c == 8 && f == 8){
					if(bona9(sudoku, Array[sudoku],f,c,Array[sudoku][f][c], m)){
						Marca(sudoku, f, c, Array[sudoku][f][c]);
						if(sudoku == 4){
							
							timer.stop();
							//System.out.println(timer.toString());
							
							UpdateSamurai(sudoku+1);
							//initSamurai(this.Samurai, 0);
							solucions ++;
							
							if(opcio == 1){
								
								System.out.println("Solucio numero: " + solucions);
								System.out.println("");
								for(int i = 0; i< 21; i++){
									for(int j = 0; j< 21; j++){
										
										if(Samurai[i][j] == -2){
											if(j == 20){
												System.out.println("* ");
											}else{
												System.out.print("* ");
											}
											
										}else{
											if(j == 20){
												System.out.println(Samurai[i][j]);
											}else{
												
												System.out.print(Samurai[i][j]);
												System.out.print(" ");
												
											}
										}
										
										
									}
								}
								System.out.println("");
							}
							if(opcio == 2){
								
								SudokuGUI solucio = new SudokuGUI("Solucio num:" + solucions, 350, 350, new boolean[21][21]);
								solucio.updateBoard(Samurai);
								
							}
							if(opcio == 3){
								fs.escriuFitxer(Samurai, 21,nomFitxS);
							}
							//Backtracking9(x,f,c,s);
							
						}else{

							//UpdateSamurai(sudoku+1);
							
							if(sudoku == 0){
								if(Comprovacio(m)){
									InsideOut(m);
									BacktrackingSam(timer, 0, 0, sudoku+1, opcio, fs, m);
									if(m == 1){
										DesmarcatgeIO();
									}
									
								}
								
							}else{
								//System.out.println("EI: " + sudoku);
								BacktrackingSam(timer, 0, 0, sudoku+1, opcio, fs, m);
							}
							
						}
						Desmarca(sudoku, f, c, Array[sudoku][f][c]);
					}
					
				}else{
					//!solucio
					if(bona9(sudoku, Array[sudoku],f,c,Array[sudoku][f][c], m)){
						Marca(sudoku, f, c, Array[sudoku][f][c]);
						if(c == 8){
							
							UpdateSamurai(sudoku+1);
							BacktrackingSam(timer, f+1, 0, sudoku, opcio, fs, m);
						}else{
							
							UpdateSamurai(sudoku+1);
							BacktrackingSam(timer, f, c+1, sudoku, opcio, fs, m);
						}
						Desmarca(sudoku, f, c, Array[sudoku][f][c]);
					}
					
				}
				
			}
			
			Array[sudoku][f][c] = -1;
			
		}else{
			
			if(sudoku == 4){
				
				timer.stop();
				//System.out.println(timer.toString());
				
				UpdateSamurai(sudoku+1);
				//initSamurai(this.Samurai, 0);
				solucions ++;
				
				
				if(opcio == 1){
					
					System.out.println("Solucio numero: " + solucions);
					System.out.println("");
					for(int i = 0; i< 21; i++){
						for(int j = 0; j< 21; j++){
							
							if(Samurai[i][j] == -2){
								if(j == 20){
									System.out.println("* ");
								}else{
									System.out.print("* ");
								}
								
							}else{
								if(j == 20){
									System.out.println(Samurai[i][j]);
								}else{
									
									System.out.print(Samurai[i][j]);
									System.out.print(" ");
									
								}
							}
							
							
						}
					}
					System.out.println("");
				}
				if(opcio == 2){
					
					SudokuGUI solucio = new SudokuGUI("Solucio num:" + solucions, 350, 350, new boolean[21][21]);
					solucio.updateBoard(Samurai);
					
				}
				if(opcio == 3){
					fs.escriuFitxer(Samurai, 21,nomFitxS);
				}
				
			}else{

				//UpdateSamurai(sudoku+1);

				if(sudoku == 0){
					if(Comprovacio(m)){
						InsideOut(m);
						BacktrackingSam(timer, 0, 0, sudoku+1, opcio, fs, m);
						if(m == 1){
							DesmarcatgeIO();
						}
				    }
					
				}else{
					//System.out.println("EI: " + sudoku);
					BacktrackingSam(timer, 0, 0, sudoku+1, opcio, fs, m);
				}
				
				
				
			}
			
		}
		

	}		
	
	public boolean bona9(int s, int[][] aux, int f, int c, int v, int m){
		
		if(m == 0){
			int aux_c=0, aux_f=0;
			boolean bona = true;
			
			for(int i = 0; i <= 8; i++){
				if(aux[f][i] == v && c != i) bona = false;
			}
			
			for(int i = 0; i<= 8; i++){
				if(aux[i][c] == v && f != i)bona = false;
			}
			
			aux_c = (c % 3);
			aux_f = (f % 3);
			
			for(int j = (f-aux_f); j <= (f-aux_f)+ 2;j++){			
				for(int i = (c-aux_c) ; i <= (c-aux_c) + 2;i++){
					if(aux[j][i] == v && (f != j && c != i)) bona = false;
				}
			}

			return bona;
		}else{
			if(marcatge[s][0][f][v-1] == 1){
				return false;
			}
			if(marcatge[s][1][c][v-1] == 1){
				return false;
			}
			if(marcatge[s][2][(f/3)*3+(c/3)][v-1] == 1){
				return false;
			}
			return true;
		}
		
	}

	public void UpdateSamurai(int flag){
		
		switch(flag){
		
		case 1:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i+6][j+6] = Array[flag-1][i][j];
				}
			}
			
			break;
			
		case 2:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i][j] = Array[flag-1][i][j];
				}
			}
			
			break;
			
		case 3:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i][j+12] = Array[flag-1][i][j];
				}
			}
			
			break;
			
		case 4:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i+12][j] = Array[flag-1][i][j];
				}
			}
			
			break;
			
		case 5:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i+12][j+12] = Array[flag-1][i][j];
				}
			}
			
			break;
			
		case 0:
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i+6][j+6] = Array[0][i][j];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i][j] = Array[1][i][j];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i][j+12] = Array[2][i][j];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i+12][j] = Array[3][i][j];
				}
			}
			
			for(int i = 0; i<9; i++){
				for(int j = 0; j<9; j++){
					this.Samurai[i+12][j+12] = Array[4][i][j];
				}
			}
		}
	}
		
	public void InsideOut(int m){
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				Array[1][i+6][j+6] = Array[0][i][j];
				Marca(1, i+6, j+6, Array[0][i][j]);
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				Array[2][i+6][j] = Array[0][i][j+6];
				Marca(2, i+6, j, Array[0][i][j+6]);
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				Array[3][i][j+6] = Array[0][i+6][j];
				Marca(3, i, j+6, Array[0][i+6][j]);
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				Array[4][i][j] = Array[0][i+6][j+6];
				Marca(4, i, j, Array[0][i+6][j+6]);
			}
		}
		
	}
	
	public void Marca(int s, int f, int c, int v){
		marcatge[s][0][f][v-1] = 1;
		marcatge[s][1][c][v-1] = 1;
		marcatge[s][2][(f/3)*3+(c/3)][v-1] = 1;
	}
	
	public boolean Comprovacio(int m){
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(!BonaSamu(m,1,Array[0][i][j], i+6, j+6)){
					return false;
				}
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(!BonaSamu(m,2,Array[0][i][j+6], i+6, j)){
					return false;
				}
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(!BonaSamu(m,3,Array[0][i+6][j], i, j+6)){
					return false;
				}
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				
				if(!BonaSamu(m,4,Array[0][i+6][j+6], i, j)){
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void Desmarca(int s, int f, int c, int v){
		marcatge[s][0][f][v-1] = 0;
		marcatge[s][1][c][v-1] = 0;
		marcatge[s][2][(f/3)*3+(c/3)][v-1] = 0;
	}
	
	public int[][][] getArray() {
		return Array;
	}

	public void setArray(int[][][] array) {
		Array = array;
	}

	public int[][] getSamurai() {
		return Samurai;
	}

	public void setSamurai(int[][] samurai) {
		Samurai = samurai;
	}

	public int getCompt() {
		return compt;
	}

	public void setCompt(int compt) {
		this.compt = compt;
	}

	public int getSolucions() {
		return solucions;
	}

	public void setSolucions(int solucions) {
		this.solucions = solucions;
	}

	public int[][][][] getMarcatge() {
		return marcatge;
	}

	public void setMarcatge(int[][][][] marcatge) {
		this.marcatge = marcatge;
	}

	public int[][] getS0() {
		return S0;
	}

	public void setS0(int[][] s0) {
		S0 = s0;
	}

	public int[][] getS1() {
		return S1;
	}

	public void setS1(int[][] s1) {
		S1 = s1;
	}

	public int[][] getS2() {
		return S2;
	}

	public void setS2(int[][] s2) {
		S2 = s2;
	}

	public int[][] getS3() {
		return S3;
	}

	public void setS3(int[][] s3) {
		S3 = s3;
	}

	public int[][] getS4() {
		return S4;
	}

	public void setS4(int[][] s4) {
		S4 = s4;
	}

	public boolean BonaSamu(int m, int s, int v, int f, int c){
		
		boolean bona = true;

		switch(s){
			
			case 1:
				//if(m == 0){
				
					for(int i = 0; i<9; i++){
						
						if(S1[f][i] == v && i != c){
							return false;
						}
						
					}
					for(int i = 0; i<9; i++){
						
						if(S1[i][c] == v && i != f){
							return false;
						}
						
					}
				/*}else{
					if(marcatge[s][0][f][v-1] == 1){
						return false;
					}
					if(marcatge[s][1][c][v-1] == 1){
						return false;
					}
					
				}*/
				
				break;
				
			case 2:
				
				//if(m == 0){
					
					for(int i = 0; i<9; i++){
						
						if(S2[f][i] == v && i != c){
							return false;
						}
						
					}
					for(int i = 0; i<9; i++){
						
						if(S2[i][c] == v && i != f){
							return false;
						}
						
					}
					
				/*}else{
					if(marcatge[s][0][f][v-1] == 1){
						return false;
					}
					if(marcatge[s][1][c][v-1] == 1){
						return false;
					}
					
				}*/
					
				break;
				
			case 3:
				
				//if(m == 0){
				
					for(int i = 0; i<9; i++){
						
						if(S3[f][i] == v && i != c){
							return false;
						}
						
					}
					for(int i = 0; i<9; i++){
						
						if(S3[i][c] == v && i != f){
							return false;
						}
						
					}
					
				/*}else{
					if(marcatge[s][0][f][v-1] == 1){
						return false;
					}
					if(marcatge[s][1][c][v-1] == 1){
						return false;
					}
					
				}*/
				
				break;
				
			case 4:
				
				//if(m == 0){
				
					for(int i = 0; i<9; i++){
						
						if(S4[f][i] == v && i != c){
							return false;
						}
						
					}
					for(int i = 0; i<9; i++){
						
						if(S4[i][c] == v && i != f){
							return false;
						}
						
					}
					
				/*}else{
					if(marcatge[s][0][f][v-1] == 1){
						return false;
					}
					if(marcatge[s][1][c][v-1] == 1){
						return false;
					}
					
				}*/
				
				break;
				
		}
		return bona;
	}
	
	public void initMarcatge(){
		for(int k = 0; k<5;k++){
			for(int i = 0; i<9 ; i++){
				for(int j = 0; j<9; j++){
					if(Array[k][i][j] != -1){
						Marca(k, i, j, Array[k][i][j]);
					}
				}
			}
		}
	}

	public void DesmarcatgeIO(){
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(S1[i+6][j+6] == -1 ){
					Desmarca(1, i+6, j+6, Array[0][i][j]);
				}				
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(S2[i+6][j] == -1){
					Desmarca(2, i+6, j, Array[0][i][j+6]);
				}	
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(S3[i][j+6] == -1){
					Desmarca(3, i, j+6, Array[0][i+6][j]);
				}	
			}
		}
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(S4[i][j] == -1){
					Desmarca(4, i, j, Array[0][i+6][j+6]);
				}	
				
			}
		}
	}
	
}


