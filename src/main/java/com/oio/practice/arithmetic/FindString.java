package com.oio.practice.arithmetic;

/**
 * @author Liqc
 * @date 2019/4/29 10:56
 */
public class FindString {

    public static void main(String[] args) {

    }


    /**
     * @param source:
     * @param target:
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // Write your code here
        for (int i=0; i<(source.length()-target.length() + 1); i++) {
            int j = 0;
            for(j = 0;j<target.length();j++) {
                if(source.charAt(i + j) != target.charAt(j)) {
                    break;
                }

            }
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }

}
