package com.oio.practice.arithmetic;

/**
 * 翻转字符串
 * @Author: LiQiongchao
 * @Date: 2019/4/14 18:55
 */
public class RotateString {

    public static void main(String[] args) {
        RotateString rotateString = new RotateString();
        rotateString.rotateString("abcdefg".toCharArray(), 3);
    }

    public void rotateString(char[] str, int offset) {
        if (str == null || str.length == 0) {
            return;
        }
        reverse(str, 0, str.length - offset - 1);
        reverse(str, str.length - offset, str.length - 1);
        reverse(str, 0, str.length - 1);

    }
    private void reverse(char[] str, int star, int end) {
        for (int i=star, j=end; i < j; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }


}
