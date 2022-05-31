package top.ylcao.hotalmgr.main;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Store {
    private String name;
    private String address;
    private String phone;
    private String manager;
    private File roomFile;
    private HashMap<Integer, Integer> sale;
    private ArrayList<Room> roomList;

    public Store(String name, String address, String phone, String manager, String saleInfo) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.manager = manager;
        this.roomList = initRoomList();
        this.sale = initSale(saleInfo);
        Log.p("成功构造门店:" + this.toString());
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public File getRoomFile() {
        return roomFile;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", manager='" + manager + '\'' +
                ", roomFile=" + roomFile +
                ", sale=" + sale +
                ", roomList=" + roomList +
                '}';
    }

    private HashMap<Integer, Integer> initSale(String saleInfo) {
        String[] saleInfoSplit = saleInfo.split(".");
        for (String eachSaleInfoSplit : saleInfoSplit) {

        }
        return null;
    }

    private ArrayList<Room> initRoomList() {
        File dir = new File(new File("").getAbsolutePath() + "\\data\\");
        for (int i = 0; i < Objects.requireNonNull(dir.listFiles()).length; i++) {
            String filename = Objects.requireNonNull(dir.listFiles())[i].getName();
            if ( filename.equals("room_" + this.name + ".txt") ) {
                Log.p("读取数据:" + filename);
                this.roomFile = new File(dir.getAbsolutePath() + "\\" + filename);
                StringBuilder content = new StringBuilder();
                try {
                    byte[] temp = new byte[1024];
                    FileInputStream fileInputStream = new FileInputStream(roomFile);
                    while(fileInputStream.read(temp) != -1){
                        content.append(new String(temp));
                        temp = new byte[1024];
                    }
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] roomInfoStr = content.toString().split("\n");
                ArrayList<Room> storeInfo = new ArrayList<>();
                Room room = null;
                for (String s : roomInfoStr) {
                    String[] roomInfoStrSplit = s.split(";");
                    room = new Room(roomInfoStrSplit[0], roomInfoStrSplit[1], roomInfoStrSplit[2], roomInfoStrSplit[3], roomInfoStrSplit[4], roomInfoStrSplit[5]);
                    storeInfo.add(room);
                }
                return storeInfo;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

}
