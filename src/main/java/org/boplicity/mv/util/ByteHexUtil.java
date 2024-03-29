package org.boplicity.mv.util;

public class ByteHexUtil {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String byteArrayToHexString(final byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            final int hiVal = (b & 0xF0) >> 4;
            final int loVal = b & 0x0F;
            hex.append((char) ('0' + (hiVal + (hiVal / 10 * 7))));
            hex.append((char) ('0' + (loVal + (loVal / 10 * 7))));
        }
        return hex.toString();
    }
}
