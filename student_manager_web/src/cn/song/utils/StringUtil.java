package cn.song.utils;

public class StringUtil {
    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str))return true;
        return false;
    }
}
