package org.structure.boot.redisson.utils;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

/**
 *
 * @Title:  StringUtil
 * @Description: StringUtil
 * @date:   2019/9/19 16:31
 * @Version V1.0.0
 *
 */
public class StringUtil {

    public static String getValueBySpelKey(String key, String[] parameterNames, Object[] values){
        //不存在表达式返回
        if (!key.contains("#")) {
            return key;
        }
        //使用下划线拆分表达式
        String[] spelKeys = key.split("_");
        //要返回的key
        StringBuilder sb = new StringBuilder();
        //遍历拆分结果用解析器解析
        for (int i = 0 ; i <= spelKeys.length - 1 ; i++) {
            if (!spelKeys[i].startsWith("#")) {
                sb.append(spelKeys[i]);
                continue;
            }
            String tempKey = spelKeys[i];
            //spel解析器
            ExpressionParser parser = new SpelExpressionParser();
            //spel上下文
            EvaluationContext context = new StandardEvaluationContext();
            for (int j = 0; j < parameterNames.length; j++) {
                context.setVariable(parameterNames[j], values[j]);
            }
            Expression expression = parser.parseExpression(tempKey);
            Object value = expression.getValue(context);
            if (value != null) {
                sb.append(value.toString());
            }
        }
        //返回
        return sb.toString();
    }

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
     * @throws
     * @throws
     * @Title isBlank
     * @Description 判断字符串是否为空
     * @Date 2019/1/18 16:53
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
     * @param c
     * @return boolean
     * @throws
     * @throws
     * @Title isWhitespace
     * @Description 判断是否为空白字符
     * @author qjx
     * @updateAuthor qjx
     * @Date 2019/1/18 16:52
     * @version v1.0.0
     **/
    private static boolean isWhitespace(int c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
    }

    /**
     * @param unicode
     * @return java.lang.String
     * @throws
     * @throws
     * @Title unicode2String
     * @Description unicode 转字符串
     * @Date 2019/1/18 16:52
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
     * @param string
     * @return java.lang.String
     * @throws
     * @throws
     * @Title trimAndRemoveQuot
     * @Description 去掉英文单引号以及首尾空格
     * @Date 2019/1/18 16:52
     * @version v1.0.0
     **/
    public static String trimAndRemoveQuot(String string) {
        return string.replaceAll("['*]*", "").trim();
    }

    /**
     * @param string
     * @return java.lang.String
     * @throws
     * @throws
     * @Title removeAllBlankAndQuot
     * @Description 去掉英文单引号以及所有空格
     * @Date 2019/1/18 16:52
     * @version v1.0.0
     **/
    public static String removeAllBlankAndQuot(String string) {
        return string.replaceAll("['*| *|　*|\\s*]*", "").trim();
    }

    public static  String prefixAddress(String address) {
        if (!StringUtils.isEmpty(address) && !address.startsWith("redis")) {
            return "redis://" + address;
        }
        return address;
    }

}
