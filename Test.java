package hw4;

import java.util.Scanner;
import hw4.CFPlayer;
import hw4.RandomAI;
import hw4.ThomasAI;
import hw4.ConsoleCF;
import hw4.GUICF;


public class Test {
  public static void main(String[] args) {
	 
	 /*
	  CFPlayer ai1 = new ThomasAI();
	  CFPlayer ai2 = new RandomAI();
	  int n = 10000;
	  int winCount = 0;
	  for (int i=0; i<n; i++) {
	  ConsoleCF game = new ConsoleCF(ai1, ai2);
	  game.playOut();
	  if (game.getWinner()==ai1.getName())
	  winCount++;
	  }
	  System.out.print(ai1.getName() + " wins ");
	  System.out.print(((double) winCount)/((double) n)*100 + "%");
	  System.out.print(" of the time.");
*/
	 /*
	  
	for(int i = 0; i < 30; ++i) {
		  CFPlayer good = new ThomasAI();
		  GUICF test = new GUICF(good);
		  
	}
	*/
	 
	  
	  Scanner reader = new Scanner (System.in);
	    int gameMode = reader.nextInt();
	    
	    if (gameMode==1) {
	      new GUICF(new ThomasAI());
	    } else if (gameMode==2) {
	      CFPlayer ai1 = new ThomasAI();
	      CFPlayer ai2 = new RandomAI();
	      int n = 10000;
	      int winCount = 0;
	      for (int i =0; i < n ; i++) {
	        ConsoleCF game = new ConsoleCF(ai1, ai2);
	        game.playOut();
	        if(game.getWinner() == ai1.getName())
	          winCount++;
	      }
	      System.out.println(((double) winCount)/n);
	    } else {
	      ConsoleCF game = new ConsoleCF(new ThomasAI());
	      game.playOut();
	      System.out.println(game.getWinner() + " has won.");
	    }
	    
	    
  }
  
}