package sockets.utils;

import java.util.List;

public class Utils {

    /**
     * Joins arrays from list into one array
     * @param byteArrays
     *        Arrays
     * @return byte[]
     */
     public static byte[] joinArrays(List<byte[]> byteArrays) {
        byte[] result = new byte[countBytes(byteArrays)];

        int offset = 0;
        for (byte[] byteArray : byteArrays) {
            System.arraycopy(byteArray, 0, result, offset, byteArray.length);
            offset += byteArray.length;
        }

        return result;

    }

    /**
     * Count number of bytes in list
     * @param byteArrays
     *        Arrays
     * @return Number of bytes
     */
    public static int countBytes(List<byte[]> byteArrays) {
        int result = 0;

        for (byte[] byteArray : byteArrays) {
            result += byteArray.length;
        }

         return result;
    }

}
