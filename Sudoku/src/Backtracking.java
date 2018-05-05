
public class Backtracking {
	
	private int solucions;
	private int marcatge[][][];
	
	public int getSolucions() {
		return solucions;
	}

	public void setSolucions(int solucions) {
		this.solucions = solucions;
	}

	public int[][][] getMarcatge() {
		return marcatge;
	}

	public void setMarcatge(int[][][] marcatge) {
		this.marcatge = marcatge;
	}

	public Backtracking(){

		marcatge = new int[3][9][9];
		
	}
	
	public void Backtracking9(int[][] x, int f, int c, Timer timer, int opcio, Fitxer fs, int m, String nomFitxS){
	
		int fi = 0;
			
		while(x[f][c]!=(-1) && fi == 0){
			
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
			x[f][c] = 0;
			//hi ha successor
			while(x[f][c] < 9){
				//seguent germa		
				x[f][c]++;
				
				
				//solucio
				if(c == 8 && f == 8){
					if(bona9(x,f,c,x[f][c],m)){
						
						solucions ++;
						
						timer.stop();
						//System.out.println(timer.toString());

						if(opcio == 1){
							System.out.println("Solucio numero: " + solucions);
							System.out.println("");
							for(int i = 0; i< 9; i++){
								for(int j = 0; j< 9; j++){
									if(j == 8){
										System.out.println(x[i][j]);
									}else{
										System.out.print(x[i][j]);
										System.out.print(" ");
									}
								}
							}
							System.out.println("");
						}
						if(opcio == 2){
							SudokuGUI solucio = new SudokuGUI("Solucio num:" + solucions, 350, 350, new boolean[9][9]);
							solucio.updateBoard(x);
						}

						if(opcio == 3){
							fs.escriuFitxer(x, 9,nomFitxS);
						}
						
						
					}
				}else{
					//!solucio
					if(bona9(x,f,c,x[f][c],m)){
						Marca(f, c, x[f][c]);
						if(c == 8){
							Backtracking9(x, f+1, 0, timer, opcio, fs, m,nomFitxS);
						}else{
							Backtracking9(x, f, c+1, timer, opcio, fs, m,nomFitxS);
						}
						Desmarca(f, c, x[f][c]);
					}
				}
			}
			x[f][c] = -1;

		}else{
			
			solucions ++;
			
			timer.stop();
			//System.out.println(timer.toString());
			
			
			if(opcio == 1){
				System.out.println("Solucio numero: " + solucions);
				System.out.println("");
				for(int i = 0; i< 9; i++){
					for(int j = 0; j< 9; j++){
						if(j == 8){
							System.out.println(x[i][j]);
						}else{
							System.out.print(x[i][j]);
							System.out.print(" ");
						}
						
					}	
				}
				System.out.println("");
			}
			if(opcio == 2){
				SudokuGUI solucio = new SudokuGUI("Solucio num:" + solucions, 350, 350, new boolean[9][9]);
				solucio.updateBoard(x);
			}

			if(opcio == 3){
				fs.escriuFitxer(x, 9,nomFitxS);
			}
			
			fi = 0;
		}
		
			
		
	}		
	
	public boolean bona9(int[][] x, int f, int c, int v, int m){
		
		boolean bona = true;
		
		if(m == 0){
			
			int aux_c=0, aux_f=0;
			
			for(int i = 0; i <= 8; i++){
				if(x[f][i] == v && c != i) bona = false;
			}
			
			for(int i = 0; i<= 8; i++){
				if(x[i][c] == v && f != i)bona = false;
			}
			
			aux_c = (c % 3);
			aux_f = (f % 3);
			
			for(int j = (f-aux_f); j <= (f-aux_f)+ 2;j++){			
				for(int i = (c-aux_c) ; i <= (c-aux_c) + 2;i++){
					if(x[j][i] == v && (f != j && c != i)) bona = false;
				}
			}
			return bona;
		}else{
			
			if(marcatge[0][f][v-1] == 1){
				return false;
			}
			if(marcatge[1][c][v-1] == 1){
				return false;
			}
			if(marcatge[2][(f/3)*3+(c/3)][v-1] == 1){
				return false;
			}
			return true;
		}
		
	}

	public void Marca(int f, int c, int v){
		marcatge[0][f][v-1] = 1;
		marcatge[1][c][v-1] = 1;
		marcatge[2][(f/3)*3+(c/3)][v-1] = 1;
	}
	
	public void Desmarca(int f, int c, int v){
		marcatge[0][f][v-1] = 0;
		marcatge[1][c][v-1] = 0;
		marcatge[2][(f/3)*3+(c/3)][v-1] = 0;
	}
	
	public void initMarcatge(int[][] x){
		for(int i = 0; i<9 ; i++){
			for(int j = 0; j<9; j++){
				if(x[i][j] != -1){
					Marca(i, j, x[i][j]);
				}
			}
		}
	}
	
	
}