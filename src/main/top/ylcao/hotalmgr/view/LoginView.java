package top.ylcao.hotalmgr.view;




import top.ylcao.hotalmgr.handler.LoginHandler;
import top.ylcao.hotalmgr.main.Log;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class LoginView extends JFrame {

    SpringLayout springLayout = new SpringLayout();
    JPanel loginPanel = new JPanel(springLayout);

    JLabel title = new JLabel("连锁酒店房间管理系统", JLabel.CENTER);
    JLabel subTitle = new JLabel("by 20计科曹应龙");
    JLabel accountLabel = new JLabel("账号");
    JTextField accountText = new JTextField();
    JLabel passwordLabel = new JLabel("密码");
    JPasswordField passwordText = new JPasswordField();
    JButton resetButton = new JButton("清空");
    JButton loginButton = new JButton("登陆");

    LoginHandler loginHandler;


    public LoginView(){
        super("连锁酒店房间管理系统");
        loginHandler = new LoginHandler(this);
        Container contentPane = getContentPane();
        // 布局
        loginLayout();
        // 添加组件
        loginPanel.add(subTitle);
        loginPanel.add(accountLabel);
        loginPanel.add(accountText);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(resetButton);


        // 设置login按钮为默认按钮
        getRootPane().setDefaultButton(loginButton);
        loginPanel.add(loginButton);
        // 绑定事件监听器
        resetButton.addActionListener(loginHandler);
        // 监听
        loginButton.addKeyListener(loginHandler);
        loginButton.addActionListener(loginHandler);

        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(loginPanel, BorderLayout.CENTER);
        //自动化页面大小--全屏
        setSize(300, 400);
        int fraWidth = this.getWidth();//frame的宽
        int fraHeight = this.getHeight();//frame的高
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setSize(screenWidth / 4, screenHeight / 4);
        this.setLocation(0, 0);
        float proportionW = screenWidth / fraWidth;
        float proportionH = screenHeight / fraHeight;

        modifyComponentSize(this, proportionW, proportionH);
        this.toFront();
        accountText.setPreferredSize(new Dimension(this.getWidth()-200, 40));
        passwordText.setPreferredSize(new Dimension(this.getWidth()-200, 40));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * frame中的控件自适应frame大小：改变大小位置和字体

     */
    public static void modifyComponentSize(JFrame frame,float proportionW,float proportionH){

        try
        {
            Component[] components = frame.getRootPane().getContentPane().getComponents();
            for(Component co:components)
            {
                float locX = co.getX() * proportionW;
                float locY = co.getY() * proportionH;
                float width = co.getWidth() * proportionW;
                float height = co.getHeight() * proportionH;
                co.setLocation((int)locX, (int)locY);
                co.setSize((int)width, (int)height);
                int size = (int)(co.getFont().getSize() * proportionH);
                Font font = new Font(co.getFont().getFontName(), co.getFont().getStyle(), size);
                co.setFont(font);
            }
        }
        catch (Exception e)
        {
            Log.p("LoginView Error:" + e);
        }

    }

    private void loginLayout() {

        title.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        subTitle.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        Font MSYAHEI = new Font("微软雅黑", Font.PLAIN, 30);
        accountLabel.setFont(MSYAHEI);
        accountText.setFont(MSYAHEI);
        passwordLabel.setFont(MSYAHEI);
        passwordText.setFont(MSYAHEI);
        resetButton.setFont(MSYAHEI);
        loginButton.setFont(MSYAHEI);


        // 设置小标题在大标题下面，也就是loginPanel最上面，并水平居中
        springLayout.putConstraint(SpringLayout.NORTH, subTitle, 0, SpringLayout.NORTH, loginPanel);
        springLayout.putConstraint(SpringLayout.WEST, subTitle, -(Spring.width(subTitle).getValue() / 2), SpringLayout.HORIZONTAL_CENTER, loginPanel);

        // 设置账号标签到指定位置
        springLayout.putConstraint(SpringLayout.NORTH, accountLabel, 40, SpringLayout.NORTH, loginPanel);
        springLayout.putConstraint(SpringLayout.WEST, accountLabel, 50, SpringLayout.WEST, loginPanel);
        // 设置账号文本框
        springLayout.putConstraint(SpringLayout.WEST, accountText, 20, SpringLayout.EAST, accountLabel);
        springLayout.putConstraint(SpringLayout.BASELINE, accountText, 0, SpringLayout.BASELINE, accountLabel);


        // 设置密码标签框
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 25, SpringLayout.SOUTH, accountLabel);
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, accountLabel);
        // 设置密码文本框
        springLayout.putConstraint(SpringLayout.BASELINE, passwordText, 0, SpringLayout.BASELINE, passwordLabel);
        springLayout.putConstraint(SpringLayout.WEST, passwordText, 0, SpringLayout.WEST, accountText);


        // 设置按钮
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 20, SpringLayout.SOUTH, passwordLabel);
        springLayout.putConstraint(SpringLayout.BASELINE, loginButton, 0, SpringLayout.BASELINE, resetButton);
        springLayout.putConstraint(SpringLayout.WEST, loginButton, 40, SpringLayout.EAST, resetButton);
        springLayout.putConstraint(SpringLayout.WEST, resetButton, -(Spring.sum(Spring.sum(Spring.width(resetButton), Spring.width(loginButton)), Spring.constant(40)).getValue() / 2), SpringLayout.HORIZONTAL_CENTER, loginPanel);



        // 如果是MacOS则设置Dock栏图标
//        if (System.getProperty("os.name").startsWith("Mac OS")) {
//            Application.getApplication().setDockIconImage(new ImageIcon(Objects.requireNonNull(LoginInter.class.getClassLoader().getResource("dockIcon.png"))).getImage());
//        }
        // 自定义图标
//        setIconImage(new ImageIcon(Objects.requireNonNull(LoginView.class.getClassLoader().getResource("icon.png"))).getImage());
        // 设置尺寸
//        setSize(380, 250);


    }



    public JTextField getAccountText() {
        return accountText;
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }
}
