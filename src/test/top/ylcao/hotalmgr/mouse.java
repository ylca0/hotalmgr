package top.ylcao;

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class mouse extends Frame{
    private JPopupMenu menu;

    public mouse() {
        this.setBounds(new Rectangle(500,400));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        menu.setVisible(true);
        this.RightMouse();
        this.add(menu);

    }
    public void RightMouse() {
        JMenuItem mAll, mCopy, mCut, mPaste, mDel;
        menu = new JPopupMenu();
        mAll = new JMenuItem("全选(A)");
        menu.add(mAll);
        mCopy = new JMenuItem("复制(C)");
        menu.add(mCopy);
        mCut = new JMenuItem("剪切(T)");
        menu.add(mCut);
        mPaste = new JMenuItem("粘贴(P)");
        menu.add(mPaste);
        mDel = new JMenuItem("删除(D)");
        menu.add(mDel);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    menu.show(mouse.this, e.getX(), e.getY());
                }
            }
        });
        mAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了全选菜单");
            }
        });

    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        new mouse();
    }

}