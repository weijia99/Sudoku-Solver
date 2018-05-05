
public class Backtracking16 {

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

	public Backtracking16(){
		marcatge = new int[3][16][16];
	}

	public void Backtracking(int[][] x, int f, int c, Timer timer, int opcio, Fitxer fs, int m,String nomFitxS){
		
		int fi = 0;
		
		while(x[f][c]!=(-1) && fi == 0){
			if(c == 15 && f == 15){
				fi = 1;
				
			}else{
				c++;
				if(c > 15){
					c = 0;
					f++;
				}
			}
		}
		
		if(fi == 0){
			
			//prepara recorregut nivell
			x[f][c] = 0;
			//mentre hi ha successor
			while (x[f][c] < 16){
				//seguent germa
				x[f][c]++;
				//solucio
				if(f == 15 && c == 15){
					// solucio bona
					if(bona16(x,f,c,x[f][c],m)){
						
						timer.stop();
						//System.out.println(timer.toString());
						
						solucions ++;
						
						
						if(opcio == 1){
							System.out.println("Solucio numero: " + solucions);
							System.out.println("");
							for(int i = 0; i< 16; i++){
								for(int j = 0; j< 16; j++){
									if(j == 15){
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
							SudokuGUI solucio = new SudokuGUI("Solucio num:" + solucions, 350, 350, new boolean[16][16]);
							solucio.updateBoard(x);
						}
						if(opcio == 3){
							fs.escriuFitxer(x, 16, nomFitxS);
						}
						//Backtracking16(x,f,c,s);
					}
				//¬solucio
				}else{
					if(bona16(x,f,c,x[f][c],m)){
						Marca(f, c, x[f][c]);
						if(c == 15){
							Backtracking(x,f+1,0, timer, opcio, fs,m,nomFitxS);
						}else{
							Backtracking(x,f,c+1, timer, opcio, fs,m,nomFitxS);
						}
						Desmarca(f, c, x[f][c]);
					}
				}
			}
			x[f][c] = -1;
			
		}else{
			
			timer.stop();
			solucions ++;
			
			
			if(opcio == 1){
				System.out.println("Solucio numero: " + solucions);
				System.out.println("");
				for(int i = 0; i< 16; i++){
					for(int j = 0; j< 16; j++){
						if(j == 15){
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
				SudokuGUI solucio = new SudokuGUI("Solucio num:" + solucions, 350, 350, new boolean[16][16]);
				solucio.updateBoard(x);
			}
			if(opcio == 3){
				fs.escriuFitxer(x, 16,nomFitxS);
			}
			
			
		}
		
	}

	public boolean bona16(int[][] x, int f, int c, int valor, int m){
		
		if(m == 0){
			int aux_c = 0;
			int aux_f = 0;
		
			//mirem verticalment
			for(int i=0 ; i <= 15 ; i++){
				if(x[f][i] == valor && c != i) return false;	
			}
			//mirem horitzontalment
			for(int i=0 ; i <= 15 ; i++){
				if (x[i][c] == valor && f != i) return false;
			}
			//mirem el quadrat
			aux_c = (c % 4);
			aux_f = (f % 4);
			
			for(int i = (f-aux_f); i <= (f-aux_f) + 3;i++){
				for(int j = (c-aux_c) ; j <= (c-aux_c) + 3;j++){
					if(x[i][j] == valor && (f != i && c != j)) return false;
				}
			}
		}else{
			if(marcatge[0][f][valor-1] == 1){
				return false;
			}
			if(marcatge[1][c][valor-1] == 1){
				return false;
			}
			if(marcatge[2][(f/4)*4+(c/4)][valor-1] == 1){
				return false;
			}
			return true;
		}
		
		
		return true;
	}
	
	public void Marca(int f, int c, int v){
		marcatge[0][f][v-1] = 1;
		marcatge[1][c][v-1] = 1;
		marcatge[2][(f/4)*4+(c/4)][v-1] = 1;
	}
	
	public void Desmarca(int f, int c, int v){
		marcatge[0][f][v-1] = 0;
		marcatge[1][c][v-1] = 0;
		marcatge[2][(f/4)*4+(c/4)][v-1] = 0;
	}
	
	public void initMarcatge(int[][] x){
		for(int i = 0; i<16 ; i++){
			for(int j = 0; j<16; j++){
				if(x[i][j] != -1){
					Marca(i, j, x[i][j]);
				}
			}
		}
	}
	
}

