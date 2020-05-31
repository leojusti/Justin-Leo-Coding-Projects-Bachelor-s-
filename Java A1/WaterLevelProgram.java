/**
 * Created by justinleo on 2017-01-10.
 */

import java.util.Scanner;


public class WaterLevelProgram {
    public static void main(String[] args) {
        int c = 0, p = -1;
        int sum = 0;

        Scanner keyboard = new Scanner(System.in);




        while(sum < 3){
            p = c;
            //System.out.println("Previous value = " + c);
            System.out.print("What is the water level at now (in mm): ");
            c = keyboard.nextInt();
            //System.out.println("You entered " + c);

            if(p > c){
             //   System.out.println("Current is lesser than previous ");
                sum++;
            }
        }
        System.out.println("It appears that the flood is subsiding. ");


    }
}
