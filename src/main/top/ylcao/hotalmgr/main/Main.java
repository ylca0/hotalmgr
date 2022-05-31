package top.ylcao.hotalmgr.main;

import top.ylcao.hotalmgr.view.LoginView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        System.out.println("System is Running");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("runoob.txt"));
            out.write("菜鸟教程6666");
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }
    }

}
