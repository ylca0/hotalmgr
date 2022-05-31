package top.ylcao.hotalmgr.handler;


import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.main.Room;
import top.ylcao.hotalmgr.view.RoomView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RoomHandler extends MouseAdapter{
    private RoomView roomView;
    private JPopupMenu popupMenu;

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
        // TODO: 完善其余功能
        popupMenu.add(createMenuItem("入住登记", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }));
        popupMenu.add(createMenuItem("退房", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }));
        popupMenu.add(createMenuItem("预定", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }));
        popupMenu.add(createMenuItem("续订", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }));
        popupMenu.add(createMenuItem("查询", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }));
        popupMenu.add(createMenuItem("修改", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getButton() == MouseEvent.BUTTON3) {
            try {
                JLabel label = (JLabel) roomView.panel.getComponentAt(e.getPoint());
                Log.p("右键" + Integer.parseInt(label.getText().substring(6).split("<")[0]));
                popupMenu.show(roomView, e.getX()+15, e.getY()+label.getHeight()+5);
            } catch (Exception ex) {
                Log.p("未点击到客房");
            }
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            JLabel label = (JLabel) roomView.panel.getComponentAt(e.getPoint());
            Room room = roomView.store.getRoomList().get(Integer.parseInt(label.getText().substring(6).split("<")[0]));
            Log.p("左键" + Integer.parseInt(label.getText().substring(6).split("<")[0]));
            JOptionPane.showMessageDialog(roomView, room.toString());
        }
    }



}
