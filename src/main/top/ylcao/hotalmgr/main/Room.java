package top.ylcao.hotalmgr.main;


import java.text.ParseException;

public class Room {
    private String category;
    private String price;
    private String isUsing;
    private String date;
    private String guest;
    private String phone;

    public Room(String category, String price, String isUsing, String date, String guest, String phone) {
        this.category = category;
        this.price = price;
        this.isUsing = isUsing;
        this.date = date;
        this.guest = guest;
        this.phone = phone;
    }

    @Override
    public String toString() {
        if (this.isUsing.equals("是")) {
            try {
                return "房间信息:\n" +
                        "类型:" + category + '\n' +
                        "价格:" + price + '\n' +
                        "是否占用:" + isUsing + '\n' +
                        "预定日期:" + Log.getFTime(date.split("-")[0]) + "至" + Log.getFTime(date.split("-")[1]) + '\n' +
                        "客人:" + guest + '\n' +
                        "客人电话:" + phone;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            return "房间信息:\n" +
                    "类型:" + category + '\n' +
                    "价格:" + price + '\n' +
                    "是否占用:" + isUsing;
        }

    }


    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIsUsing(String isUsing) {
        this.isUsing = isUsing;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getIsUsing() {
        return isUsing;
    }

    public String getDate() {
        return date;
    }

    public String getGuest() {
        return guest;
    }

    public String getPhone() {
        return phone;
    }
}


