package com.niici.study.datastructure.sparsearray;

import lombok.extern.slf4j.Slf4j;

/**
 * 稀疏数组
 *
 * @author niici
 * Created by niici on 2021/6/11.
 */
@Slf4j
public class LearnSparseArray {
    private static int[][] sparseArr;
    private static int[][] chessArr;
    private static int[][] sparseRestoreArr;


    public static void main(String[] args) {
        transforToSparseArray();
        parseArrayRestore();
    }

    /**
     * 转换为稀疏数组
     */
    private static void transforToSparseArray() {
        // 初始化一个11*11的二维数组 --- 创建一个棋盘
        chessArr = new int[11][11];
        // 0：无子，1：黑子，2：白子
        chessArr[1][2] = 1;
        chessArr[3][6] = 2;
        chessArr[1][4] = 1;
        chessArr[5][7] = 2;
        System.out.println("---------------原始二维数组----------------");
        for (int[] rows : chessArr) {
            // 打印单行数据
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            // 换行
            System.out.println();
        }
        System.out.println(" ---------------稀疏数组----------------");
        // 转换为稀疏数组
        // 计算稀疏数组的行数 --- 行数为有效值计数 + 1
        int count = 0;
        for (int[] rows : chessArr) {
            for (int data : rows) {
                if (data != 0) {
                    count++;
                }
            }
        }
        // 初始化稀疏数组
        log.info("count: {}", count);
        sparseArr = new int[count + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = count;

        // 遍历二维数组，将有效数据的row、col、value出入稀疏数组中
        int sparseRow = 1; // 稀疏数组行计数
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArr[sparseRow][0] = i;
                    sparseArr[sparseRow][1] = j;
                    sparseArr[sparseRow][2] = chessArr[i][j];
                    sparseRow++;
                }
            }
        }

        // 打印
        for (int[] rows : sparseArr) {
            // 打印单行数据
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            // 换行
            System.out.println();
        }
    }

    /**
     * 稀疏数组还原
     */
    private static void parseArrayRestore() {
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        sparseRestoreArr = new int[row][col];
        for (int i = 1; i < sparseArr.length; i++) {
            int restoreRow = sparseArr[i][0];
            int resotreCol = sparseArr[i][1];
            sparseRestoreArr[restoreRow][resotreCol] = sparseArr[i][2];
        }

        // 打印
        for (int[] rows : sparseRestoreArr) {
            // 打印单行数据
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            // 换行
            System.out.println();
        }
    }
}
