package top.ylcao.hotalmgr.handler;

import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.main.Store;
import top.ylcao.hotalmgr.view.AddStoreView;
import top.ylcao.hotalmgr.view.EditStoreView;
import top.ylcao.hotalmgr.view.RoomView;
import top.ylcao.hotalmgr.view.StoreView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
                Store store = null;
                // 搜索右键的门店
                for (Store s : storeView.getAllStoreInfo()) {
                    if (s.getName().equals(storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName())) {
                        store = s;
                        break;
                    }
                }
                assert store != null;
                String storeStringInfo = "门店名:" + store.getName() + "\n" +
                        "门店地址:" + store.getAddress() + "\n" +
                        "经理:" + store.getManager() + "\n" +
                        "经理电话:" + store.getPhone() + "\n";
                JOptionPane.showMessageDialog(storeView, storeStringInfo);
                Log.p("查看门店信息:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);
        item = new JMenuItem("编辑门店");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Store store = null;
                // 搜索右键的门店
                for (Store s : storeView.getAllStoreInfo()) {
                    if (s.getName().equals(storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName())) {
                        store = s;
                        break;
                    }
                }
                EditStoreView editStoreView = new EditStoreView(store, StoreHandler.this);
                Log.p("编辑门店:" + storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName());
            }
        });
        popupMenu.add(item);


        item = new JMenuItem("删除门店");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String storeName = storeView.getAllStoreInfo().get(storeView.getjTable().getSelectedRow()).getName();
                for (int r = 0; r < StoreHandler.this.getStoreView().getAllStoreInfo().size(); r++) {
                    if (StoreHandler.this.getStoreView().getAllStoreInfo().get(r).getName().equals(storeName)) {
                        // 删除room文件
                        StoreHandler.this.getStoreView().getAllStoreInfo().get(r).getRoomFile().delete();
                        // 删除store
                        StoreHandler.this.getStoreView().getAllStoreInfo().remove(r);
                        // 更新table
                        storeView.getAllStoreVector().remove(r);
                        storeView.getTableModel().fireTableRowsUpdated(r, r);
                        // 更新store文件
                        try {
                            updateAllStoreFile();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                }
                Log.p("删除门店:" + storeName);
            }
        });
        popupMenu.add(item);

        // TODO: 完成剩余功能
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
                new AddStoreView(StoreHandler.this);
                Log.p("添加门店:" + storeView.getAllStoreInfo().get(storeView.getAllStoreInfo().size()-1).getName());
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

    public void updateAllStoreFile() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(storeView.getStoreFile()));
        for (Store store : storeView.getAllStoreInfo()) {
            out.write(store.getFormatStoreInfo());
            if (!storeView.getAllStoreInfo().get(storeView.getAllStoreInfo().size()-1).equals(store)) {
                out.write("\n");
            }
        }
        out.close();
    }

    public StoreView getStoreView() {
        return storeView;
    }
}
