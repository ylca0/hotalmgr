package top.ylcao.hotalmgr.main;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static int OPERATION = 0;
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static SimpleDateFormat formatSDF = new SimpleDateFormat();

    static {
        sdf.applyPattern("yyyy年MM月dd日-HH时mm分ss秒");
        formatSDF.applyPattern("yyyy:MM:dd:HH:mm:ss");
    }

    public static String getFTime(String t) throws ParseException {
        return sdf.format(formatSDF.parse(t));
    }

    private static String getFormatTime() {
        return sdf.format(new Date());
    }

    public static void pLog(String content) {
        System.out.println("[" + getFormatTime() + "]系统操作:" + content);
    }

    public static void p(String content) {
        System.out.println("[" + getFormatTime() + "]打印信息:" + content);
    }
}
