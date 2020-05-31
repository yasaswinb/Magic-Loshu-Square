package com.company;

import java.util.InputMismatchException;//this will used to identify exception
import java.util.Scanner;//this will used to use scanner

public class magicAndLoShuSquare {

    public static int[][] input(int rows, int[][] array2D) {//this is the input method

        Scanner sc = new Scanner(System.in);//this will enable to get the input from user

        int n = 1;//this will be used to tell which number that user going to enter

        for (int r = 0; r < rows; r++) {//this is the outer loop it represents rows
            for (int c = 0; c < rows; c++) {//this is the inner loop it represents columns

                try {//if the input is not string this will execute
                    System.out.print("Enter No " + n++ + ":");//this line will prompt user to enter number with remarks
                    array2D[r][c] = sc.nextInt();//this line will get the number from user

                    if(array2D[r][c] < 1){//if user input is less than 1 this will execute
                        System.out.println("\nInvalid number, Enter 1 or above 1\n");//invalid number error will be printed
                        c--;//because this is an error inner loop column number will decreased
                        n--;//because this is an error prompt remark will be decreased
                    }
                }
                catch (InputMismatchException e){//if input is a string this will continue
                    System.out.println("\nInvalid character, Enter 1 or above 1\n");//invalid character will be printed
                    sc.next();//entered string will be cleared
                    c--;//because this is an error inner loop column number will decreased
                    n--;//because this is an error prompt remark will be decreased
                }
            }
        }
        return array2D;//2D array will be returned
    }

    public static void displaySquare(int rows, int[][] array2D) {//this is the display square method it displays the square

        for (int r = 0; r < rows; r++) {//this is the outer loop it represents rows
            for (int c = 0; c < rows; c++) {//this is the inner loop it represents columns
                System.out.print(array2D[r][c] + " ");//this will print each and every element in 2D array
            }
            System.out.println();//this will print in new row
        }
    }

    public static int[] sumOfRows(int rows, int[][] array2D,int[] rowSumArray){//this is the sum of rows method it holds the sum of rows

        int rowSum = 0;//this will initialize rowSum to zero

        for (int r = 0; r < rows; r++) {//this is the outer loop it represents rows
            for (int c = 0; c < rows; c++) {//this is the inner loop it represents columns
                rowSum += array2D[r][c];//this will add each and every element in row to rowSum
            }
            rowSumArray[r] = rowSum;//this will append each row sum to rowSumArray
            rowSum = 0;//this will set the rowSum to zero to get the next row's sum
        }
        return rowSumArray;//this will return the rowSumArray
    }

    public static int[] sumOfColumns(int rows, int[][] array2D,int[] colSumArray) {//this is the sum of columns method it holds the sum of columns

        int colSum = 0;//this will initialize the colSum to zero

        for (int c = 0; c < rows; c++) {//this is the outer loop it represents columns
            for (int r = 0; r < rows; r++) {//this is the inner loop it represents rows
                colSum += array2D[r][c];//this will add each and every element in column to colSum
                colSumArray[c] = colSum;//this will append each column sum to colSumArray
            }
            colSum = 0;//this will set the colSum to zero to get the next column's sum
        }
        return colSumArray;//this will return the colSumArray
    }

    public static boolean checkingMagicSquare(int rows,int[][] array2D,int [] rowSumArray, int[] colSumArray, int leftDiagonalSum, boolean flow1){//this will check whether the magic square is true

        int m = 0;//this will initialize m to zero and this represents the index of column in diagonals
        for (int r = 0; r < rows; r++) {//in this loop r represents the index of row in diagonals
            leftDiagonalSum += array2D[r][m++];//every elements in diagonal will be added to leftDiagonalSum
        }

        outerLoop:
        for (int i = 0; i < rowSumArray.length ;i++) {//this is the outer loop i represents the index of rowSumArray
            for (int j = 0; j < colSumArray.length; j++) {//this is the outer loop j represents the index of colSumArray
                if (rowSumArray [i] != colSumArray [j] || colSumArray[j] != leftDiagonalSum || rowSumArray[i] != leftDiagonalSum) {//this will check all the sum of each row, column and left diagonal are equal
                    flow1 = false;//if any of them are not equal to each other flow1 will be equal to false
                    break outerLoop;//and the outer loop will be break
                }
            }
        }
        if (flow1 == true){//checking magic square is true to give the output
            System.out.println("Is magic square : True");//if the magic square is true this line will be executed
        }
        else{//if none of above happened this will be executed
            System.out.println("Is magic square : False");//if the magic square is false this line will be executed
        }
        return flow1;//this will return the boolean value of flow1
    }

    public static void checkingLoShuSquare(int rows, int[][] array2D, int[] rowSumArray, int[] colSumArray, int leftDiagonalSum, boolean flow1, boolean flow2){//this will check whether lo shu square is true or false

        int m = 0;//this will initialize m to zero and this represents the index of column in diagonals
        for (int r = 0; r < rows; r++) {//in this loop r represents the index of row in diagonals
            leftDiagonalSum += array2D[r][m++];//every elements in diagonal will be added to leftDiagonalSum
        }

        //Checking all the numbers within 1 to 9 range
        outerLoop:
        for (int r = 0; r < rows; r++) {//this is the outer loop it represents rows
            for (int c = 0; c < rows; c++) {//this is the inner loop it represents columns
                if(!((0<array2D[r][c] && array2D[r][c]<10) && rowSumArray[c] ==15 && colSumArray[c] ==15 && leftDiagonalSum == 15)) {//according to this argument all numbers has to be within 1 in 9 range and any number can't be repeated
                    flow2 = false;//if any number is repeated or out of range 1 to 9 flow2 will be set to false
                    break outerLoop;//the loop will be break
                }
            }
        }
        if(flow2 == true && flow1 == true){//checking lo shu square is true to give the output
            System.out.println("Is Lo Shu square : True");//if the lo shu square is true this will be printed
        }
        else{//if none of above happened this will be executed
            System.out.println("Is Lo Shu square : False");//if the lo shu square is false this will be printed
        }
    }

    public static String exit() {

        Scanner sc = new Scanner(System.in);

        // Asking from user that you want to enter a new square
        String userInput;//this will initialize the data type

        System.out.println("Do you wish to enter a new square (y/n)");//prompts to user to enter y/n
        userInput = sc.next();//get the input from user to userInput

        while (sc.hasNextLine()) {//it will get a input from user
            if (userInput.toLowerCase().equals("y")) {//if user's input is "y" this will continue
                System.out.println("\nEnter rows");//this prompts to user
                break;//this will break the while loop
            }
            else if (userInput.toLowerCase().equals("n")) {//if input is "n" this will be executed
                userInput = "n";//this will set the userInput to "n"
                System.out.println("Bye");//this will prompt to user and end
                break;//this will break the while loop
            }
            else {//if none of above happened this will be executed
                System.out.println("Enter 'y' or 'n'\n");
                System.out.println("Do you wish to enter a new square (y/n)");//prompts to user to enter y/n
                userInput = sc.next();//get the input from user to userInput
            }
        }return userInput;
    }
    public static void main(String[] args) {//this is the main method

        Scanner sc = new Scanner(System.in);//this will enable to get the input from user

        int rows;//this will initialize the data type of rows variable
        System.out.println("Enter rows");//this will prompt to user
        //initializing userInput as y
        String userInput = "y";

        do {//do loop stats here

            while (!sc.hasNextInt()) {//if user input is string this loop will be executed

                System.out.println("\nInvalid character, Enter a row above 2\n");//invalid character error will be printed
                System.out.println("Enter rows");//again prompts to user
                sc.next();//entered string will be cleared
            }
            rows = sc.nextInt();//this will get value to rows from the input from user

            int[][] array2D = new int[rows][rows];//2D array will be initialized

            if (2 <= rows) {//if value of rows greater than or equals to 2 this argument wil executed

                boolean flow1 = true;//flow1 will be initialized to true
                boolean flow2 = true;//flow2 will be initialized to true
                int[] rowSumArray = new int[rows];//rowSumArray will be initialized
                int[] colSumArray = new int[rows];//colSumArray will be initialized
                int leftDiagonalSum = 0;//leftDiagonalSum will be set to zero

                //calling the methods
                input(rows, array2D);//inputs will get from the user
                displaySquare(rows, array2D);//square will be displayed

                sumOfRows(rows, array2D, rowSumArray);//sum of the rows will be returned
                sumOfColumns(rows, array2D, colSumArray);//sum of the columns will be returned

                checkingMagicSquare(rows, array2D, rowSumArray, colSumArray, leftDiagonalSum, flow1);//checking whether is it magic square or not
                checkingLoShuSquare(rows, array2D, rowSumArray, colSumArray, leftDiagonalSum, flow1, flow2);//checking whether is it lo shu square or not

                if(exit().equals("n")){//check the return value of exit() method
                    break;//if it is "n" break while loop
                }
            }
            else{//if none of above happened
                System.out.println("\nInvalid number, Enter a row above 2\n");//if rows less than 2 this will be prompted
                System.out.println("Enter Rows");//this prompts to user
            }

        }while (!(2 <= rows) || userInput.toLowerCase().equals("y"));//when variable rows less than 2 or userInput equals to y this loop will be executed
    }
}