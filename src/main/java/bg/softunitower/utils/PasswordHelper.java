package bg.softunitower.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vilimir on 28.11.16.
 */
public class PasswordHelper {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static final int PASSWORD_MIN_LENGTH = 6;

    public static String md5get(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(pass.getBytes("UTF-8"));
        return bytesToHex(digest);
    }

    private static String bytesToHex(byte[] bytes){
        char[] hexChars = new char[bytes.length * 2];
        for ( int i = 0; i < bytes.length; i++ ) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
