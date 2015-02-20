package BubbleSort2D;

import java.io.*;

class BubbleSort2D
{

	// You can basically ignore the code in main.
	// Doing nothing other than populating an array
	// and collecting user input as to which subset of
	// the array to sort and in what direction.
	public static void main(String args[])throws IOException
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 
            int NumRows = 6;
            int NumCols = 4;
            person A[][]=new person[NumRows][NumCols]; //creating a 2D array
            String input;
            String [] range;
            
            A[0][0] = new person("Scott",52);
            A[0][1] = new person("Allison",51);
            A[0][2] = new person("Kyle",18);
            A[0][3] = new person("Pepper",5);
            
            A[1][0] = new person("Bonnie",55);
            A[1][1] = new person("Merle",65);
            A[1][2] = new person("Amanda",29);
            A[1][3] = new person("Andrew",27);
            
            A[2][0] = new person("Ethan",24);
            A[2][1] = new person("Tyler",21);
            A[2][2] = new person("Brandon",21);
            A[2][3] = new person("Hope",22);

            A[3][0] = new person("Drax",41);
            A[3][1] = new person("Groot",347);
            A[3][2] = new person("Starlord",25);
            A[3][3] = new person("Rocket",14);

            A[4][0] = new person("King",99);
            A[4][1] = new person("Queen",29);
            A[4][2] = new person("Rook",70);
            A[4][3] = new person("Pawn",4);

            A[5][0] = new person("Paul",57);
            A[5][1] = new person("John",39);
            A[5][2] = new person("Ringo",60);
            A[5][3] = new person("Harrison",55);

            
            // Print the original Array 
            System.out.println("The original array:");
            System.out.println("--------------------------");
            printA(A);
 
            // Get user input, Ascending or Descending, default Ascending
            System.out.print("Sort A or D (or quit): "); 
            input = (br.readLine().toUpperCase());
            if (input.length() == 0) {
            	input = "A";
            }
            
            // Main Program Loop
            while (!input.equals("QUIT")) {
            	ResetSortFlags(A);
            	
            	// Ask for the range within the array to sort
            	System.out.print("Range [<col>][<row>] to [<col>][<row>] (eg. 0 0 2 2)"); 
                range = (br.readLine().split(" "));

                // Validate number of parameters
               	if (range.length == 4) {

               		// Try to convert string parameters to int
               		try {
               		int StartCol = Integer.parseInt(range[0]);
               		int StartRow = Integer.parseInt(range[1]);
               		int EndCol = Integer.parseInt(range[2]);
               		int EndRow = Integer.parseInt(range[3]);
               		
               		// Call Sort
               		System.out.println("SORTING [" + StartCol + "][" + StartRow + "] to [" + EndCol + "][" + EndRow + "]");
                    BubbleSortOptimized(A, input, StartCol, StartRow, EndCol, EndRow);

               		} catch (NumberFormatException e) {
               			System.out.println("Please enter 4 numbers next time...");
               		}

                } else {
                	// Invalid number of parameters, default sort the whole array
                    System.out.println("INPUT ERROR: SORTING [0][0] to [" + (A.length-1) + "][" + (A[0].length-1) + "]");
                    BubbleSortOptimized(A, input, 0, 0, A.length-1, A[0].length-1);
                }
                
                // Print the sorted array
                System.out.println("The SORTED array:");
                System.out.println("--------------------------");
            	printA(A);

                // Prompt for which direction to sort, ascending or descending and start over
            	System.out.print("Sort A or D (or quit): "); 
            	input = (br.readLine().toUpperCase());
            }
 
        }

	// This is an optimized bubble sort using only two loops and integer math to calculate
	// the array indices. It also traverses the array only the minimum required number of times
	// breaking out of the function if it finishes before the loops end.
	public static void BubbleSortOptimized(person [][] a, String sort, int sc, int sr, int ec, int er){

		// Calculate the number of columns, rows & total cells from the coordinates given
       	int numcols = ec - sc + 1;
       	int numrows = er - sr + 1;
       	int numcells = numrows * numcols;
       	
       	// Initialize flag which will be used to boot us out of the loop early if we're done.
       	boolean swapped = false;
        	
       	// For display purposes only. Marks the cells we're sorting so they print with a ">".
       	SetSortFlag(a,sc,sr,ec,er);
        
       	// Declare my temp object for swapping
       	person temp;

       	// Consider the array now to be a single long string of objects, rows placed on end.
       	// Outer loop must expect to traverse the whole string of objects, all the cells (rows * cols)
       	// This is a worst case scenario which we can exit early if we make a full pass with no swaps
       	// because this means the array will be fully sorted.
       	for(int i = 0; i < numcells-1; i++)
        {
       		System.out.println("=== Outer Loop i = " + i);
       		swapped = false;
            	
           	// The Inner loop also traverses the entire string of objects... minus 1, starting from the bottom up.
       		// This is the bubble in bubblesort. We start with the bottom-most element and see how far up the list
       		// we can swap it in each pass, one swap at a time. So each time we go through this loop, we know that
       		// the top of the list is sorted so we can stop sooner and sooner, until the last iteration just tries
       		// to swap the last two on the bottom.
       		for(int j = numcells-1; j > i; j--)
       		{
       			// Ignore all of these print lines, they're just to help you see the process in the console window
       			System.out.print("--- TEST (i=" + i + "|j=" + j + "): A[" + (j/numrows) + "][" + (j%numrows) + "] < A[" + ((j-1)/numrows) + "][" + ((j-1)%numrows) + "] | ?("
       					+ a[(j/numrows)][(j%numrows)].toString() + " < " + a[((j-1)/numrows)][((j-1)%numrows)].toString() + ")");
       			
       			if (sort.equals("A") && (a[(j/numrows)][(j%numrows)].age < a[((j-1)/numrows)][((j-1)%numrows)].age)) {

       				// Ignore the printing
       				System.out.println("  YES.");
       				System.out.println(">>> SWAP: A[" + (j/numrows) + "][" + (j%numrows) + "] < A[" + ((j-1)/numrows) + "][" + ((j-1)%numrows) + "]:");
                    
       				// Make the swap
       				temp = a[(j/numrows)][(j%numrows)];
       				a[(j/numrows)][(j%numrows)] = a[((j-1)/numrows)][((j-1)%numrows)];
       				a[((j-1)/numrows)][((j-1)%numrows)] = temp;
                        		
       				// Set the flag indicating that we did to a swap on this pass through the inner loop
       				swapped = true;
       				
       				// Ignore the printing (just showing what it looks like after each swap)
       				printA(a);

       			} else { // ignore the else its only for printing help to the console
       				System.out.println("  NO.");
       			}
       		}
               	
       		// Before starting the next iteration on the outer loop, verify that we did a swap in the last one
       		if (swapped == false) {
       			// If we did not do a swap in the last pass, go ahead and quit because we're done
       			break;
       		}
        }
	}        
        
        public static void printA(person [][] a) {
        	
            for(int r = 0; r < a[0].length; r++) {
                for(int c = 0; c < a.length; c++) {
                	if (a[c][r].sortme == true) {
                		System.out.printf("%3s %15s", ">", a[c][r]+" |");
                	}
                	else {
                		System.out.printf("%3s %15s", " ", a[c][r]+" |");
                	}
                }
                System.out.println();
            }

        }

        public static void SetSortFlag(person [][] a, int StartCol, int StartRow, int EndCol, int EndRow){
            for(int r = StartRow; r <= EndRow; r++) {
                for(int c = StartCol; c <= EndCol; c++) {
                    a[c][r].sortme = true;
                }
            }
        }

        public static void ResetSortFlags(person [][] a) {
        	
            for(int r = 0; r < a[0].length; r++) {
                for(int c = 0; c < a.length; c++) {
                	a[c][r].sortme = false;
                }
                System.out.println();
            }

        }

        
}