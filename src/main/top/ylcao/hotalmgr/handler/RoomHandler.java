package top.ylcao.hotalmgr.handler;


import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.main.Room;
import top.ylcao.hotalmgr.view.BookView;
import top.ylcao.hotalmgr.view.EditRoomView;
import top.ylcao.hotalmgr.view.RoomView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RoomHandler extends MouseAdapter{
    public RoomView roomView;
    private JPopupMenu popupMenu;
    public MouseEvent mouseEvent;

    public RoomHandler(RoomView roomView) {
        this.roomView = roomView;
        initMenu();
    }

    private JMenuItem createMenuItem(String text, ActionListener action) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(action);
        return item;
    }

    private void initMenu() {
        popupMenu = new JPopupMenu();
        popupMenu.add(createMenuItem("入住登记", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 入住登记
                new BookView(RoomHandler.this, "入住登记");
            }
        }));
        popupMenu.add(createMenuItem("退房", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 更新信息
                findEventRoom(mouseEvent).setIsUsing("否");
                // 更新外观
                roomView.updateEmptyView((JLabel) roomView.panel.getComponent(findEventNum(mouseEvent)));
                // 更新room文件
                roomView.store.updateAllRoomFile();
            }
        }));
        popupMenu.add(createMenuItem("预定", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookView(RoomHandler.this, "预定房间");
            }
        }));
//        popupMenu.add(createMenuItem("续订", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }));
//        popupMenu.add(createMenuItem("查询", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            }
//        }));
        popupMenu.add(createMenuItem("修改", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditRoomView(RoomHandler.this, "修改房间");
            }
        }));
    }

    public Room findEventRoom(MouseEvent e) {
        JLabel label = (JLabel) roomView.panel.getComponentAt(e.getPoint());
        int num = Integer.parseInt(label.getText().substring(6).split("<")[0]);
        return roomView.store.getRoomList().get(num);
    }

    public int findEventNum(MouseEvent e) {
        JLabel label = (JLabel) roomView.panel.getComponentAt(e.getPoint());
        return Integer.parseInt(label.getText().substring(6).split("<")[0]);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getButton() == MouseEvent.BUTTON3) {
            // 右键
            try {
                JLabel label = (JLabel) roomView.panel.getComponentAt(e.getPoint());
                Log.p("右键客房:" + findEventNum(e));
                mouseEvent = e;
                popupMenu.show(roomView, e.getX()+15, e.getY()+label.getHeight()+5);
            } catch (Exception ex) {
                Log.p("未右键到客房");
            }
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            // 左键
            try {
                JLabel label = (JLabel) roomView.panel.getComponentAt(e.getPoint());
                Log.p("查看客房信息:" + findEventNum(e));
                JOptionPane.showMessageDialog(roomView, findEventRoom(e).toString());
            } catch (Exception ex) {
                Log.p("未左键到客房");
            }

        }
    }



}
