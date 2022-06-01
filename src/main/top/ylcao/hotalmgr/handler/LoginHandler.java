package top.ylcao.hotalmgr.handler;

import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.view.LoginView;
import top.ylcao.hotalmgr.view.StoreView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class LoginHandler extends KeyAdapter implements ActionListener {


    private final LoginView loginView;
    private StoreView storeView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 强转JButton源
        JButton sourceButton = (JButton) e.getSource();
        String buttonText = sourceButton.getText();

        if (buttonText.equals("清空")) {
            // 清除两个文本框内容
            loginView.getAccountText().setText("");
            loginView.getPasswordText().setText("");
        } else if (buttonText.equals("登陆")) {
            loginMethod();
        }

    }

    private void loginMethod() {
        String username = loginView.getAccountText().getText();
        String password = new String(loginView.getPasswordText().getPassword());

        Log.pLog("登陆:" + username + ", " + password);
        File directory = new File("");
        File adminFile = new File(directory.getAbsolutePath() + "\\data\\admin.txt");
        Log.pLog("读取" + directory.getAbsolutePath() + "\\data\\admin.txt文件");
        if ( !adminFile.exists() || !adminFile.canRead()) {
            Log.p("配置文件读写异常");
            JOptionPane.showMessageDialog(storeView, "登陆失败");
            return;
        }
        StringBuilder content = new StringBuilder();
        try {
            byte[] temp = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(adminFile);
            while(fileInputStream.read(temp) != -1){
                content.append(new String(temp));
                temp = new byte[1024];
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String u = content.toString().split(";")[0];
        String p = content.toString().split(";")[1];
        Log.pLog("读取到的用户名:" + u + ", 密码:" + p);
        boolean isSuccessLogin = username.equals(u) && password.equals(p);
        if (isSuccessLogin) {
            // 打印登录成功日志
            Log.pLog("登录成功");
            // 弹出提示框
            JOptionPane.showMessageDialog(loginView, "登陆成功!");
            // 隐藏菜单
            this.loginView.setVisible(false);
            // 进入管理面板
            this.storeView = new StoreView();
            // 显示所有学生表界面
            // this.allStudentsTable = new AllStudentsTable(allStudentInfo);
        } else {
            JOptionPane.showMessageDialog(storeView, "登陆失败...");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ENTER == e.getKeyCode()) {
            loginMethod();
        }
    }

}
