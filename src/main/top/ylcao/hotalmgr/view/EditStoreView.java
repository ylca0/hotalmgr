package top.ylcao.hotalmgr.view;

import top.ylcao.hotalmgr.handler.StoreHandler;
import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.main.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class EditStoreView extends JFrame {
    private JLabel storeNameLabel;
    private JTextField storeNameField;
    private JLabel storeAddressLabel;
    private JTextField storeAddressField;
    private JLabel storeManagerLabel;
    private JTextField storeManagerField;
    private JLabel storePhoneLabel;
    private JTextField storePhoneField;
    private JButton confirmButton;
    private Store store;
    private StoreHandler storeHandler;


    public EditStoreView(Store s, StoreHandler sh) {
        super("编辑门店信息");
        store = s;
        storeHandler = sh;
        initLayout();
        initHandler();
    }

    private void initLayout() {
        storeNameLabel = new JLabel("酒店名");
        storeNameField = new JTextField(store.getName());
        storeAddressLabel = new JLabel("酒店地址");
        storeAddressField = new JTextField(store.getAddress());
        storeManagerLabel = new JLabel("酒店经理");
        storeManagerField = new JTextField(store.getManager());
        storePhoneLabel = new JLabel("经理电话");
        storePhoneField = new JTextField(store.getPhone());
        confirmButton = new JButton("确定");

        JPanel panel = new JPanel(new GridLayout(5, 2, 5,  10));
        panel.add(storeNameLabel);
        panel.add(storeNameField);
        panel.add(storeAddressLabel);
        panel.add(storeAddressField);
        panel.add(storeManagerLabel);
        panel.add(storeManagerField);
        panel.add(storePhoneLabel);
        panel.add(storePhoneField);
        panel.add(new JPanel());
        panel.add(confirmButton);
        this.add(panel);

        setSize(300, 200);
        // 居中
        setLocationRelativeTo(null);
        // 设置不可修改改变大小
        setResizable(false);
        // 设置可见
        setVisible(true);
    }

    private void initHandler() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EditStoreView.this.storeNameField.getText().length() == 0 || EditStoreView.this.storeAddressField.getText().length() == 0 || EditStoreView.this.storeManagerField.getText().length() == 0 || EditStoreView.this.storePhoneField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(EditStoreView.this, "请输入完整信息!");
                    return;
                }
                String preName = store.getName();
                store.setName(EditStoreView.this.storeNameField.getText());
                store.setAddress(EditStoreView.this.storeAddressField.getText());
                store.setManager(EditStoreView.this.storeManagerField.getText());
                store.setPhone(EditStoreView.this.storePhoneField.getText());
                try {
                    storeHandler.updateAllStoreFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // 对应的门店room文件改名
                String newFileAbsolutePath = store.getRoomFile().getAbsolutePath().substring(0, store.getRoomFile().getAbsolutePath().lastIndexOf("\\")+1) + "room_" + EditStoreView.this.storeNameField.getText() + ".txt";
                System.out.println(newFileAbsolutePath);
                store.getRoomFile().renameTo(new File(newFileAbsolutePath));

                // 修改门店页面table
                // 查找到相应行
                for (int r = 0; r < storeHandler.getStoreView().getAllStoreVector().size(); r++) {
                    Vector<String> stringVector = storeHandler.getStoreView().getAllStoreVector().get(r);
                    if (stringVector.get(0).equals(preName)) {
                        stringVector.set(0, EditStoreView.this.storeNameField.getText());
                        // 更新对应行列的数据（酒店名）
                        storeHandler.getStoreView().getTableModel().fireTableDataChanged();
                        break;
                    }
                }
                Log.p("更新信息完成:" + store.getFormatStoreInfo());
                EditStoreView.this.dispose();
            }
        });
    }
}
