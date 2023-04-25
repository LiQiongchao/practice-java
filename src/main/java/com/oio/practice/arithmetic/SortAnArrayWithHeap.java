package com.oio.practice.arithmetic;

import java.util.Arrays;
import java.util.List;

public class SortAnArrayWithHeap {

    public static void main(String[] args) {
        int[] a = new int[]{4, 6, 8, 5, 9};
        SortAnArrayWithHeap heapSort = new SortAnArrayWithHeap();
        heapSort.heapSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 堆排序
     * @param arr
     */
    private void heapSort(int[] arr) {
        // 建堆，建堆的同时，已经把最大的值全部放到父节点上。
        // 最后一个节点下标n的父节点 = (n-1)/2
        int len = arr.length;
        // (len - 1 - 1)/2 ，len - 1是最后一个值的下标。
        for (int i = (len >> 1) - 1; i >= 0; i--) {
            heapify(arr, i, len);
        }

        // 排序
        for (int i = len - 1; i >= 0; i--) {
            // 建堆后，第一个是最大值，和最后一个交换。
            swap(arr, i, 0);
            heapify(arr, 0, i);
        }
    }


    /**
     * 堆化
     * @param num 数组
     * @param index 开始的下标
     * @param len 数组的长度
     */
    private void heapify(int[] num, int index, int len){
        // 当前节点的值
        int largest = index;
        // 左节点
        int leftSon = index * 2 + 1;
        // 右节点
        int rightSon = index * 2 + 2;
        if (leftSon < len && num[largest] < num[leftSon]) {
            largest = leftSon;
        }
        if (rightSon < len && num[largest] < num[rightSon]) {
            largest = rightSon;
        }
        if (largest != index) {
            swap(num, largest, index);
            // 递归计算，以交换后的节点为父节点进行计算
            heapify(num, largest, len);
        }
    }

    /**
     * 交换值
     * @param num
     * @param i
     * @param j
     */
    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

}
