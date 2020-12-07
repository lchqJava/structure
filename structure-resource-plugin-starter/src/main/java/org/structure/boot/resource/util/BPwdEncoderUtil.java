package org.structure.boot.resource.util;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.structure.boot.common.constant.SymbolConstant;
import org.structure.boot.common.enums.NumberEnum;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class BPwdEncoderUtil {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_SECRET = "clientSecret";

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

    public static JSONObject extractAndDecodeHeader(String header) throws IOException {
        byte[] base64Token = header.substring(NumberEnum.SIX.getValue()).getBytes(Charsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decoded, Charsets.UTF_8);
        int delim = token.indexOf(SymbolConstant.COLON);
        if (delim == -NumberEnum.ONE.getValue()) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        String[] tokens = new String[]{token.substring(NumberEnum.ZERO.getValue(), delim), token.substring(delim + NumberEnum.ONE.getValue())};
        String clientId = tokens[NumberEnum.ZERO.getValue()];
        String clientSecret = tokens[NumberEnum.ONE.getValue()];
        JSONObject params = new JSONObject();
        params.put(CLIENT_ID, clientId);
        params.put(CLIENT_SECRET, clientSecret);
        return params;
    }


    public static JSONObject extractAndDecode(String header) throws IOException {
        byte[] base64Token = header.getBytes(Charsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decoded, Charsets.UTF_8);
        int delim = token.indexOf(SymbolConstant.COLON);
        if (delim == -NumberEnum.ONE.getValue()) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        String[] tokens = new String[]{token.substring(NumberEnum.ZERO.getValue(), delim), token.substring(delim + NumberEnum.ONE.getValue())};
        String clientId = tokens[NumberEnum.ZERO.getValue()];
        String clientSecret = tokens[NumberEnum.ONE.getValue()];
        JSONObject params = new JSONObject();
        params.put(CLIENT_ID, clientId);
        params.put(CLIENT_SECRET, clientSecret);
        return params;
    }
}
