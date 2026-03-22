// utils/SecurityManager.java
package utils;

import java.util.Scanner;

public class SecurityManager {

    public static boolean checkPass(Scanner sc) {
        System.out.println("Enter the Password For Shop Access");
        int pass = sc.nextInt();
        return pass == 1100;
    }
}
