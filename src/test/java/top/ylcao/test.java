package top.ylcao;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        File file = new File(new File("").getAbsolutePath() + "\\");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.isDirectory());
        System.out.println(file.list()[0]);
    }
}
