package alice.code.creator.common.util;


import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class Util {

    /**
     * 将byte[]转换成string
     *
     * @param butBuffer
     */
    public static String byteToString(byte[] butBuffer) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < butBuffer.length; i++) {
            stringBuffer.append((char) butBuffer[i]);
        }
        return stringBuffer.toString();
    }

    public static int byteToInt(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));

        }
        return intValue;
    }

    /**
     * 按位取得int转byte后的byte
     * @param i 数据
     * @param position 取第几个byte，取值范围0-3
     * @return byte数据
     */
    public static byte getByteInteger(Integer i, Integer position){
        byte[] result = Util.intToByte(i);
        return result[position];
    }

    public static byte[] intToByte(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    public static byte[] shortToByte(short i) {
        byte[] result = new byte[2];
        result[0] = (byte) ((i >> 8) & 0xFF);
        result[1] = (byte) (i & 0xFF);
        return result;
    }

    public static short byteToShort(byte[] b) {
        short s = 0;
        for (int i = 0; i < b.length; i++) {
            s += (b[i] & 0xFF) << (8 * (1 - i));

        }
        return s;
    }

    public static boolean isPowerOfTwo(int number) {

        if ((number & -number) == number) {
            return true;
        }
        return false;
    }

    /**
     * 对数组进行排序，并取得排序前各个值得索引位置数组
     *
     * @param dArray
     * @return
     */
    public static int[] getSortedBeforeSixIndex(double[] dArray) {
        int[] index = new int[dArray.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        for (int i = 0; i < dArray.length - 1; i++) {
            for (int j = i + 1; j < dArray.length; j++) {
                if (Double.compare(dArray[i], dArray[j]) < 0) {
                    double temp = dArray[i];
                    int p = index[i];
                    dArray[i] = dArray[j];
                    index[i] = index[j];
                    dArray[j] = temp;
                    index[j] = p;
                }
            }
        }
        return index;
    }

    public static double[] doubleListToArray(List<Double> list) {
        double[] dArray = new double[list.size()];
        int i = 0;
        for (double d : list) {
            dArray[i] = d;
            i++;
        }
        return dArray;
    }

    public static byte[] doubleArrayToByteArray(double[] dArray) {
        byte[] bArray = new byte[dArray.length * 8];
        int m = 0;
        for (double d : dArray) {
            System.arraycopy(Util.toByteArray(d), 0, bArray, m, 8);
            m = m + 8;
        }
        return bArray;
    }

    public static byte[] doubleListToByteArray(List<Double> list) {
        double[] dArray = Util.doubleListToArray(list);
        byte[] bArray = new byte[dArray.length * 8];
        int m = 0;
        for (double d : dArray) {
            System.arraycopy(Util.toByteArray(d), 0, bArray, m, 8);
            m = m + 8;
        }
        return bArray;
    }

    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    /**
     * 去除数组中的重复项
     * @param array 原数组
     * @return 新数组
     */
    public static String[] arrayRemoveDuplication(String[] array){
        Set<String> set = new HashSet<>();
        for(String str : array){
            set.add(str);
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取一个UUID字符串
     * @return UUID字符串
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    /**
     * 字符串空转为空串
     * @param value 原字符串
     * @return 新字符串
     */
    public static String nullToString(String value){
        if(null == value || "null".equals(value)){
            value = "";
        }
        return value;
    }

}
