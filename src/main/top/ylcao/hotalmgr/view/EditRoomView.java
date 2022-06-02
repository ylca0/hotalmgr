package top.ylcao.hotalmgr.view;

import top.ylcao.hotalmgr.handler.RoomHandler;
import top.ylcao.hotalmgr.main.Log;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class EditRoomView extends JFrame{
    private JLabel cateLabel;
    private JTextField cateField;
    private JLabel priceLabel;
    private JTextField priceField;
    private JLabel dateLabel;
    private JTextField dateField;
    private JLabel guestLabel;
    private JTextField guestField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JButton confirmButton;
    private RoomHandler roomHandler;


    public EditRoomView(RoomHandler rh, String title) {
        super(title);
        roomHandler = rh;
        initLayout();
        initHandler();
    }

    private void initLayout() {
        cateLabel = new JLabel("房间类型");
        cateField = new JTextField(roomHandler.findEventRoom(roomHandler.mouseEvent).getCategory());
        priceLabel = new JLabel("房间价格");
        priceField = new JTextField(roomHandler.findEventRoom(roomHandler.mouseEvent).getPrice());
        dateLabel = new JLabel("使用日期");
        dateField = new JTextField(roomHandler.findEventRoom(roomHandler.mouseEvent).getDate());
        guestLabel = new JLabel("客户姓名");
        guestField = new JTextField(roomHandler.findEventRoom(roomHandler.mouseEvent).getGuest());
        phoneLabel = new JLabel("客户电话");
        phoneField = new JTextField(roomHandler.findEventRoom(roomHandler.mouseEvent).getPhone());
        confirmButton = new JButton("确定");

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 10));
        panel.add(cateLabel);
        panel.add(cateField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(guestLabel);
        panel.add(guestField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(new JPanel());
        panel.add(confirmButton);
        this.add(panel);

        setSize(300, 250);
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
                // 保证信息输入完全
                if (cateField.getText().length() == 0 || priceField.getText().length() == 0 || dateField.getText().length() == 0 || guestField.getText().length() == 0 || phoneField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(EditRoomView.this, "请输入完整信息!");
                    return;
                }
                roomHandler.findEventRoom(roomHandler.mouseEvent).setIsUsing("是");
                roomHandler.findEventRoom(roomHandler.mouseEvent).setCategory(cateField.getText());
                roomHandler.findEventRoom(roomHandler.mouseEvent).setPrice(priceField.getText());
                roomHandler.findEventRoom(roomHandler.mouseEvent).setDate(dateField.getText());
                roomHandler.findEventRoom(roomHandler.mouseEvent).setGuest(guestField.getText());
                roomHandler.findEventRoom(roomHandler.mouseEvent).setPhone(phoneField.getText());

                // 更新store里的营收
//                roomHandler.roomView.store.getSaleHashMap().put((Integer) roomHandler.roomView.store.getSaleHashMap().keySet().toArray()[roomHandler.roomView.store.getSaleHashMap().keySet().size() - 1], roomHandler.roomView.store.getSaleHashMap().get(roomHandler.roomView.store.getSaleHashMap().keySet().toArray()[roomHandler.roomView.store.getSaleHashMap().keySet().size() - 1]) + Integer.parseInt(priceField.getText()));
                // 修改房间外观
                roomHandler.roomView.updateNoEmptyView((JLabel) roomHandler.roomView.panel.getComponent(roomHandler.findEventNum(roomHandler.mouseEvent)));
                // 更新room文件
                roomHandler.roomView.store.updateAllRoomFile();
                // 更新store文件
                try {
                    roomHandler.roomView.storeHandler.updateAllStoreFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }



                Log.p("修改房间完成:" + roomHandler.findEventRoom(roomHandler.mouseEvent).getFormatRoomInfo());
                // 修改房间外观
                EditRoomView.this.dispose();
            }
        });
    }
}
