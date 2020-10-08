package ind.ck;

import ind.ck.construct.EnumTest;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CaiKun 2019-12-27 15:14:27
 * @Description:
 * @Project algorithm
 */
public class Main {
    // 1100 1011 1111 0010 1001 1100 1110 0100 1000 0100 0010 0010 0010 0011 0010 0101
    private static final long FNV_64_INIT = 0xcbf29ce484222325L;
    // 1000 0000 0000 0000 0000 0000 0000 0000 1101 1001 01111
    private static final long FNV_64_PRIME = 0x100000001b3L;

    public static long ag(String k) {
        long rv = FNV_64_INIT;
        final int len = k.length();
        for (int i = 0; i < len; i++) {
            rv ^= k.charAt(i);
            rv *= FNV_64_PRIME;
        }
        return rv;
    }

    public static String substringBefore(String str, String separator) {

        return "";
    }

    public static final List<Date> getThisWeekDay2() {
        List<Date> weekDays = new LinkedList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        cld.setTimeInMillis(System.currentTimeMillis());//当前时间
        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 1; i <= 7; i ++) {
//            cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
            weekDays.add(cld.getTime());
            cld.add(Calendar.DAY_OF_MONTH, 1);
        }
        return weekDays;
    }

    /**
     * 获取本周1~~本周7的date
     *
     * @return
     */
    public static final List<Date> getThisWeekDay() {
        List<Date> weekDays = new LinkedList<>();
        Calendar c = Calendar.getInstance();
        // 今天是一周中的第几天
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK );
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
//        if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
//            c.add(Calendar.DAY_OF_MONTH, 1);
//        }
        // 计算一道周开始的日期
//        c.add(Calendar.DAY_OF_MONTH, dayOfWeek-2);

        for (int i = 1; i <= 7; i ++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            weekDays.add(c.getTime());
        }
        return weekDays;
    }

    public static void main(String[] args) {
        EnumTest instance = EnumTest.getInstance();
//        System.out.println(getThisWeekDay2());
//        ReentrantLock reentrantLock = new ReentrantLock();
//
//        reentrantLock.lock();
//
//        reentrantLock.unlock();
//        System.out.println(Long.toHexString((FNV_64_INIT << 1) + (FNV_64_INIT << 4) + (FNV_64_INIT << 5) + (FNV_64_INIT << 7) + (FNV_64_INIT << 8) + (FNV_64_INIT << 40) ) );
//        System.out.println(Long.toBinaryString(FNV_64_INIT & FNV_64_PRIME));
//        System.out.println(ag("a"));
//        System.out.println(ag("aa"));
//        System.out.println(ag("aaa"));
//        System.out.println(ag("aaaa"));
//        String phonenum = "0123456789";
//        char[] chars = phonenum.replaceAll("-", "").replaceAll("/+", "").toCharArray();
//        if (chars == null || chars.length == 0) {
//            System.out.printf("ERR1");
//        }
//        for (char num : chars) {
//            if (num > '9' || num < '0') {
//                System.out.printf("ERR2");
//            }
//        }
//        System.out.printf("over:" + phonenum);
//
//
//        NumberUtils.toLong(StringUtils.substringBefore(baseRequest.getChannel(), ","))

        System.out.println((3 & 2) + "");
    }
}
