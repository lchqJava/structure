package org.structure.boot.oauth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Encoder;

/**
 * @author Administrator
 */
public class BPwdEncoderUtil {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 用BCryptPasswordEncoder
     *
     * @param password
     * @return
     */
    public static String bCryptPassword(String password) {
        return ENCODER.encode(password);
    }

    /**
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }

    public static String jdkBase64Encoder(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(str.getBytes());
        return encode;
    }
}
