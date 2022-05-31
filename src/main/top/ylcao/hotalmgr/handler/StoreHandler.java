package top.ylcao.hotalmgr.handler;

import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.main.Store;
import top.ylcao.hotalmgr.view.RoomView;
import top.ylcao.hotalmgr.view.StoreView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class StoreHandler extends MouseAdapter {

    private StoreView storeView;
    private JPopupMenu popupMenu;

    public StoreHandler(StoreView storeView) {
        this.storeView = storeView;
        initPopupMenu();
        initHandler();
    }

    private void initPopupMenu() {
        popupMenu = new JPopupMenu();
        // TODO: 完成剩余功能
        JMenuItem item = new JMenuItem("进入门店");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.p("进入门店:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
                try {
                    RoomView roomView = new RoomView(storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        popupMenu.add(item);
        item = new JMenuItem("查看信息");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.p("查看门店信息:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);
        item = new JMenuItem("编辑门店");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.p("编辑门店:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);
        item = new JMenuItem("删除门店");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.p("删除门店:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);

        item = new JMenuItem("统计报表");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.p("统计报表:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);

        item = new JMenuItem("添加门店");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.p("添加门店:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);

        item = new JMenuItem("搜索门店");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.p("搜索门店:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);
    }

    private void initHandler() {
        //添加鼠标响应事件，当点击右键时，弹出菜单
        storeView.getjTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3) {
                    //通过点击位置找到点击为表格中的行
                    int focusedRowIndex = storeView.getjTable().rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    //将表格所选项设为当前右键点击的行
                    storeView.getjTable().setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    //弹出菜单
                    popupMenu.show(storeView.getjTable(), e.getX(), e.getY());
                }
            }
        });
    }

    private void updateStoreFile(ArrayList<Store> allStoreInfo) {
        for (Store store : allStoreInfo) {

        }
    }


}
