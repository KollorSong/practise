package cn.song.traditional;

import java.io.File;

public class MyFileTest {

    private static String cp = "/cn/song/traditional/syscfg.properties";
    private static String cp2 = "/kkk.txt";


    public static void main(String[] args) throws Exception{
//        File file = new File("sd");
//        file.exists();
//
//        //当前类的绝对路径
//        System.out.println(MyFileTest.class.getResource("").getFile());
//        //当前项目的路径
//        System.out.println(MyFileTest.class.getResource("/").getFile());
//        //指定CLASSPATH文件的绝对路径
//        System.out.println(MyFileTest.class.getResource(cp).getFile());
//        //指定CLASSPATH文件的绝对路径
//        File f = new File(MyFileTest.class.getResource(cp).getFile());
//        System.out.println(f.getPath());
//
//        System.out.println("================");
//        try {
//            System.out.println(fromHexString("1245464854acdf5"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 其中，\'\"\\ 实际字符串代表：'"\
        String str = "张三&$李四（(、，//\'\"\\‘’“”+-*/！!~.。=——？?;；";
        String hexStr = toHexString(str);
        System.out.println(hexStr);
//        System.out.println(fromHexString(hexStr));
    }



    /**
     * 16进制直接转换成为字符串
     * @explain
     * @param hexString 16进制字符串
     * @return String （字符集：UTF-8）
     */
    public static String fromHexString(String hexString) throws Exception {
        // 用于接收转换结果
        String result = "";
        // 转大写
        hexString = hexString.toUpperCase();
        // 16进制字符
        String hexDigital = "0123456789ABCDEF";
        // 将16进制字符串转换成char数组
        char[] hexs = hexString.toCharArray();
        // 能被16整除，肯定可以被2整除
        byte[] bytes = new byte[hexString.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = hexDigital.indexOf(hexs[2 * i]) * 16 + hexDigital.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        // byte[]-->String
        result = new String(bytes, "UTF-8");
        return result;
    }
    /**
     * 字符串转换成为16进制字符串（大写）
     * @explain 因为java转义字符串在java中有着特殊的意义，
     *     所以当字符串中包含转义字符串，并将其转换成16进制后，16进制再转成String时，会出问题：
     *  java会将其当做转义字符串所代表的含义解析出来
     * @param str 字符串（去除java转义字符）
     * @return 16进制字符串
     * @throws Exception
     */
    public static String toHexString(String str) throws Exception {
        // 用于接收转换结果
        String hexString = "";
        // 1.校验是否包含特殊字符内容
        // java特殊转义符
        // String[] escapeArray = {"\b","\t","\n","\f","\r","\'","\"","\\"};
        String[] escapeArray = {"\b","\t","\n","\f","\r"};
        // 用于校验参数是否包含特殊转义符
        boolean flag = false;
        // 迭代
        for (String esacapeStr : escapeArray) {
            // 一真则真
            if (str.contains(esacapeStr)) {
                flag = true;
                break;// 终止循环
            }
        }
        // 包含特殊字符
        if (flag) throw new Exception("参数字符串不能包含转义字符！");

        // 16进制字符
        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder sb = new StringBuilder();
        // String-->byte[]
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            System.out.println(bs[i]);
            System.out.println(bs[i] >> 4 );
            System.out.println("==========");
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(hexArray[bit]);
            bit = bs[i] & 0x0f;
            sb.append(hexArray[bit]);
            if (i<bs.length-1)sb.append(",");
        }
        hexString = sb.toString();
        return hexString;
    }
}



