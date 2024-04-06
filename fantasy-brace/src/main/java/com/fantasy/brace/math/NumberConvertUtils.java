package com.fantasy.brace.math;


public class NumberConvertUtils {

    /**
     * 解析出数字的二进制原码表示数组
     *
     * @param number 需要解析的目标数字
     * @return 结果数组
     */
    public static int[] toBinaryInt(Integer number) {
        int[] bitArray = new int[32];
        int cursor = 31;

        bitArray[0] = number >>> 31;
        number = (bitArray[0] > 0) ? (~number) + 1 : number;

        while (number > 0) {
            bitArray[cursor--] = (number % 2 == 0) ? 0 : 1;
            number = number / 2;
        }

        return bitArray;
    }

    /**
     * 计算指定整型数字中的区间段内容的二进制值
     * @param number 目标整型数字
     * @param high 高位
     * @param low 低位
     * @return
     */
    public static int subIntValue(Integer number, int high, int low) {
        return number << (32 - 1 - high) >>> (32 - 1 - high + low);
    }
}
