package top.ylcao.hotalmgr.main;


import top.ylcao.hotalmgr.handler.StoreHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Store {
    private String name;
    private String address;
    private String phone;
    private String manager;
    private File roomFile;
    private HashMap<Integer, Integer> saleHashMap;
    private ArrayList<Room> roomList;

    public Store(String name, String address, String phone, String manager, String saleString) throws IOException {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.manager = manager;
        this.roomList = initRoomList();
        updateAllRoomFile();
        updateSaleHashMap(saleString);
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
        return getFormatStoreInfo() + "\n以下是房间列表:\n" + roomList;
    }

    private void updateSaleHashMap(String saleString) {
        HashMap<Integer, Integer> result = new HashMap<>();
        String[] saleInfoSplit = saleString.split(",");
        for (String eachSaleInfoSplit : saleInfoSplit) {
            String MM = eachSaleInfoSplit.split(":")[0];
            String money = eachSaleInfoSplit.split(":")[1];
            result.put(Integer.parseInt(MM), Integer.parseInt(money));
        }
        this.saleHashMap = result;
    }

    public String getFormatStoreInfo() {
        StringBuilder saleString = new StringBuilder();
        for (Integer month : saleHashMap.keySet()) {
            saleString.append(month);
            saleString.append(":");
            saleString.append(saleHashMap.get(month));
            saleString.append(",");
        }
        saleString.deleteCharAt(saleString.lastIndexOf(","));
        return name + ";" + address + ";" + phone + ";" + manager + ";" + saleString.toString() + ";";
    }

    private ArrayList<Room> initRoomList() throws IOException {
        File dir = new File(new File("").getAbsolutePath() + "\\data\\");
        for (int i = 0; i < Objects.requireNonNull(dir.listFiles()).length; i++) {
            // 寻找roomfile
            String filename = "room_" + name + ".txt";
            if ( filename.equals(Objects.requireNonNull(dir.listFiles())[i].getName()) ) {
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
//                    System.out.println(s);
                    room = new Room(roomInfoStrSplit[0], roomInfoStrSplit[1], roomInfoStrSplit[2], roomInfoStrSplit[3], roomInfoStrSplit[4], roomInfoStrSplit[5]);
                    storeInfo.add(room);
                }
                return storeInfo;
            }
        }
        File newRoomFile = new File(new File("").getAbsolutePath() + "\\data\\room_" + name + ".txt");
        if(newRoomFile.createNewFile()) {
            Log.p("创建文件:" + newRoomFile.getAbsolutePath());
            roomFile = newRoomFile;
            ArrayList<Room> storeInfo = new ArrayList<>();
            for (int i = 0; i < 36; i++) {
                storeInfo.add(new Room("钟点房", "100", "否", "2022:05:31:12:00:00-2022:05:31:16:00:00", "未知", "未知"));
            }
            return storeInfo;
        }
        Log.p("roomList初始化失败!");
        return null;
    }



    public void updateAllRoomFile() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(roomFile.getAbsolutePath()));
            for (Room room : roomList) {
                out.write(room.getFormatRoomInfo());
                if (!roomList.get(roomList.size()-1).equals(room)) {
                    out.write("\n");
                }
            }
            out.close();
        } catch (Exception ex) {
            Log.p(ex.toString());
        }

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

    public HashMap<Integer, Integer> getSaleHashMap() {
        return saleHashMap;
    }

    public String getFormatSaleHashMap() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : saleHashMap.keySet()) {
            stringBuilder.append(integer).append("月营收:").append(saleHashMap.get(integer)).append("元\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n"));
        return stringBuilder.toString();
    }
}
