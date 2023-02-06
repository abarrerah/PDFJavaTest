package com.testpdf.Utils;

import java.util.Objects;
import java.util.Scanner;

public class PathUtils {

    private static final String KEY = "VIAFIRMA_PATH";

    public String getFolderPath() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to enter a new path? 1 is yes and 2 is no.");
        String booleanPath = sc.nextLine();

        if (Objects.equals(booleanPath, "1")) {
            System.out.println("Please, add the new path.");
            return sc.nextLine();
        } else {
            return System.getenv(PathUtils.KEY);
        }
    }
}
