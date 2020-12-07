package org.structure.boot.common.utils;

/**
 * @author CHUCK
 * @Title: StringUtil
 * @Package com.neusoft.cheryownersclub.common.utils
 * @Description: StringUtil
 * @date: 2019/1/18 16:50
 * @Version V1.0.0
 */
public class StringUtil {

    /**
     * <ul>
     * <li>null-->true
     * <li>""-->true
     * <li>"  "-->true
     * <li>"\t"-->true
     * <li>"\n"-->true
     * <li>"\f"-->true
     * <li>"\r"-->true
     * <li>"123"-->false
     * <li>" 123 "-->false
     * <li>" 1 23 "-->false
     * </ul>
     *
     * @param string
     * @return boolean
     * @Title isBlank
     * @Description 判断字符串是否为空
     * @version v1.0.0
     **/
    public static boolean isBlank(String string) {
        if (string == null || string.length() == 0) {
            return true;
        }
        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!StringUtil.isWhitespace(string.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param ： c
     * @return boolean
     * @throws
     * @throws
     * @Title isWhitespace
     * @Description 判断是否为空白字符
     * @author chuck
     * @updateAuthor chuck
     * @Date 2020/11/3 11:22
     * @version v1.0.0
     **/

    private static boolean isWhitespace(int c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
    }

    /**
     * @param ： unicode
     * @return java.lang.String
     * @throws
     * @throws
     * @Title unicode2String
     * @Description unicode 转字符串
     * @author chuck
     * @updateAuthor chuck
     * @Date 2020/11/3 11:24
     * @version v1.0.0
     **/

    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    /**
     * @param ： string
     * @return java.lang.String
     * @throws
     * @throws
     * @Title trimAndRemoveQuot
     * @Description 去掉英文单引号以及首尾空格
     * @author chuck
     * @updateAuthor chuck
     * @Date 2020/11/3 11:24
     * @version v1.0.0
     **/

    public static String trimAndRemoveQuot(String string) {
        return string.replaceAll("['*]*", "").trim();
    }

    /**
     * @param ： string
     * @return java.lang.String
     * @throws
     * @throws
     * @Title removeAllBlankAndQuot
     * @Description 去掉英文单引号以及所有空格
     * @author chuck
     * @updateAuthor chuck
     * @Date 2020/11/3 11:24
     * @version v1.0.0
     **/

    public static String removeAllBlankAndQuot(String string) {
        return string.replaceAll("['*| *|　*|\\s*]*", "").trim();
    }

    /**
     * @param ： phone
     * @return java.lang.String
     * @throws
     * @throws
     * @Title midleReplaceStar
     * @Description 手机号中间4位替换为掩码
     * @author chuck
     * @updateAuthor chuck
     * @Date 2020/11/3 11:24
     * @version v1.0.0
     **/

    public static String midleReplaceStar(String phone) {
        String result = null;
        if (!isBlank(phone)) {
            if (phone.length() < 7) {
                result = phone;
            } else {
                String start = phone.substring(0, 3);
                String end = phone.substring(phone.length() - 4, phone.length());
                StringBuilder sb = new StringBuilder();
                sb.append(start).append("****").append(end);
                result = sb.toString();
            }
        }
        return result;
    }
}
