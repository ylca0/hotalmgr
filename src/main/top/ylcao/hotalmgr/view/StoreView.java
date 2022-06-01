package top.ylcao.hotalmgr.view;


import sun.swing.table.DefaultTableCellHeaderRenderer;
import top.ylcao.hotalmgr.handler.StoreHandler;
import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.main.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class StoreView extends JFrame {

    private ArrayList<Store> allStoreInfo;
    private Vector<Vector<String>> allStoreVector;
    private TableColumnModel tableColumnModel;
    private File storeFile;
    Container contentPane;
    JTable jTable;
    DefaultTableModel tableModel;

    public StoreView(){
        super("门店管理");
        initAllStoreInfo();
        initAllStudentsTable();
        setSize(600, 600);
        // 居中
        setLocationRelativeTo(null);
        // 关闭时销毁
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // 设置不可修改改变大小
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 设置鼠标监听器
        this.addMouseListener(new StoreHandler(this));
        // 设置可见
        setVisible(true);
    }

    private void initAllStoreInfo() {
        File dataDirectory = new File(new File("").getAbsolutePath() + "\\data\\");
        this.allStoreInfo = initStoreFile(dataDirectory);
        assert allStoreInfo != null;
        Log.p("总共有" + String.valueOf(allStoreInfo.size()) + "个门店");
    }

    private ArrayList<Store> initStoreFile(File dir) {
        for (int i = 0; i < Objects.requireNonNull(dir.listFiles()).length; i++) {
            String filename = Objects.requireNonNull(dir.listFiles())[i].getName();
            // 搜索到唯一的store.txt文件
            if ( filename.equals("store.txt") ) {
                Log.p("读取数据:" + filename);
                this.storeFile = new File(dir.getAbsolutePath() + "\\" + filename);
                StringBuilder content = new StringBuilder();
                try {
                    byte[] temp = new byte[1024];
                    FileInputStream fileInputStream = new FileInputStream(storeFile);
                    while(fileInputStream.read(temp) != -1){
                        content.append(new String(temp));
                        temp = new byte[1024];
                    }
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] storeInfoStr = content.toString().split("\n");
                ArrayList<Store> storeInfo = new ArrayList<>();
                Store store = null;
                for (String s : storeInfoStr) {
                    String[] storeInfoStrSplit = s.split(";");
                    store = new Store(storeInfoStrSplit[0], storeInfoStrSplit[1], storeInfoStrSplit[2], storeInfoStrSplit[3], storeInfoStrSplit[4]);
                    storeInfo.add(store);
                }
                return storeInfo;
            }
        }
        return null;
    }


    private void initAllStudentsTable() {
        // 初始化门店表头
        Vector<String> studentTableHeader = new Vector<>();
        studentTableHeader.addElement("门店名");
        studentTableHeader.addElement("地址");
        studentTableHeader.addElement("经理姓名");
        studentTableHeader.addElement("经理电话");

        // 初始化所有门店Vector
        this.allStoreVector = new Vector<Vector<String>>();
        for (int r = 0; r < allStoreInfo.size(); r++) {
            allStoreVector.addElement(new Vector<String>());
            allStoreVector.get(r).addElement(allStoreInfo.get(r).getName());
            allStoreVector.get(r).addElement(allStoreInfo.get(r).getAddress());
            allStoreVector.get(r).addElement(allStoreInfo.get(r).getManager());
            allStoreVector.get(r).addElement(allStoreInfo.get(r).getPhone());
        }
        // 初始化表格
        // 新建默认表模板
        this.tableModel = new DefaultTableModel();
        // 将所有学生Vector和表头Vector设置到表模版
        tableModel.setDataVector(allStoreVector, studentTableHeader);
        // JTable和table关联，只需要更新model
        this.jTable = new JTable(tableModel);
        // 新建内容面板
        this.contentPane = getContentPane();
        // 创建滑动面板
        JScrollPane jScrollPane = new JScrollPane((jTable));
        // 获取表头
        JTableHeader tableHeader = jTable.getTableHeader();
        // 设置表头字体
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        // 表头居中显示
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        jTable.getTableHeader().setDefaultRenderer(hr);
        // 设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // 添加滑动面板到内容面板
        contentPane.add(jScrollPane);

        // 设置身份证列列宽
        this.tableColumnModel = jTable.getColumnModel();
        tableColumnModel.getColumn(3).setPreferredWidth(140);
        tableColumnModel.getColumn(2).setPreferredWidth(20);
        tableColumnModel.getColumn(0).setPreferredWidth(80);

    }

    public ArrayList<Store> getAllStoreInfo() {
        return allStoreInfo;
    }

    public Vector<Vector<String>> getAllStoreVector() {
        return allStoreVector;
    }

    public TableColumnModel getTableColumnModel() {
        return tableColumnModel;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setAllStoreInfo(ArrayList<Store> allStoreInfo) {
        this.allStoreInfo = allStoreInfo;
    }

    public void setAllStoreVector(Vector<Vector<String>> allStoreVector) {
        this.allStoreVector = allStoreVector;
    }

    public void setTableColumnModel(TableColumnModel tableColumnModel) {
        this.tableColumnModel = tableColumnModel;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public File getStoreFile() {
        return storeFile;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
