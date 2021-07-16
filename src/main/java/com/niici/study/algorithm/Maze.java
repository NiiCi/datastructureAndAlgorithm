package com.niici.study.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 迷宫问题
 *
 * @author niici
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Maze {
    private int row;
    private int col;
    private int[][] maze;

    public Maze(int row, int col) {
        this.row = row;
        this.col = col;
        this.maze = initMaze(row, col);
    }

    public static void main(String[] args) {
        Maze maze = new Maze(8, 7);
        log.info("迷宫情况：");
        for (int i = 0; i < maze.row; i++) {
            for (int j = 0; j < maze.col; j++) {
                System.out.print(maze.getMaze()[i][j] + " ");
            }
            System.out.println();
        }
        boolean isFind = maze.findWay(maze.getMaze(), 1, 1);
        if (isFind) {
            log.info("小球走过, 并标识过的迷宫情况：");
            for (int i = 0; i < maze.row; i++) {
                for (int j = 0; j < maze.col; j++) {
                    System.out.print(maze.getMaze()[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            log.info("小球未找到迷宫通路!");
        }

        // 调整策略查找路径
        Maze maze2 = new Maze(8, 7);
        boolean isBestFind = maze2.findBestWay(maze2.getMaze(), 1, 1);
        if (isBestFind) {
            log.info("小球走过, 并标识过的迷宫情况：");
            for (int i = 0; i < maze2.row; i++) {
                for (int j = 0; j < maze2.col; j++) {
                    System.out.print(maze2.getMaze()[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            log.info("小球未找到迷宫通路!");
        }
    }


    /**
     * 初始化迷宫
     * @param row
     * @param col
     * @return
     */
    private int[][] initMaze(int row, int col) {
        // 使用二维数组初始化一个迷宫
        int[][] maze = new int[row][col];
        // 使用1表示迷宫的墙, 迷宫的四周都是墙, 则都为1
        // 第一行和最后一行设置为墙
        for (int i = 0; i < col; i++) {
            maze[0][i] = 1;
            maze[row - 1][i] = 1;
        }
        // 第一列和最后一列设置为墙
        for (int i = 0; i < row; i++) {
            maze[i][0] = 1;
            maze[i][col - 1] = 1;
        }
        // 设置挡板
        maze[3][1] = 1;
        maze[3][2] = 1;
        return maze;
    }

    /**
     * 是否找到路径
     * 如果能到达最右下角, 则说明通路找到。
     * 约定：0表示该点没有走过, 1表示墙, 2表示通路可以走, 3表示该点已经走过, 但不通
     * 找路策略：下->右->上->左, 走不通则回溯
     * @param maze 迷宫数组
     * @param startRow 开始查找的位置行
     * @param startCol 开始查找的位置列
     * @return
     */
    private boolean findWay(int[][] maze, int startRow, int startCol) {
        // 迷宫右下角为2, 则说明已找到通路
        if (maze[row-2][col-2] == 2) {
            return true;
        }
        // 为0表示没有走过, 则暂设该点为2, 按照找路策略进行查找
        if (maze[startRow][startCol] == 0) {
            maze[startRow][startCol] = 2;
            // 向下走
            if (findWay(maze, startRow + 1, startCol)) {
                return true;
            // 向右走
            } else if (findWay(maze, startRow, startCol + 1)) {
                return true;
            // 向上走
            } else if (findWay(maze, startRow - 1, startCol)) {
                return true;
            // 向左走
            } else if (findWay(maze, startRow, startCol - 1)) {
                return true;
            // 都不通则设为3, 返回false
            } else {
                maze[startRow][startCol] = 3;
                return false;
            }
        }
        // 1 和 3都表示不通, 直接返回false
        return false;
    }

    /**
     * 更换策略查找通路
     * 如果能到达最右下角, 则说明通路找到。
     * 约定：0表示该点没有走过, 1表示墙, 2表示通路可以走, 3表示该点已经走过, 但不通
     * 找路策略：上->右->下->左, 走不通则回溯
     * @param maze 迷宫数组
     * @param startRow 开始查找的位置行
     * @param startCol 开始查找的位置列
     * @return
     */
    private boolean findBestWay(int[][] maze, int startRow, int startCol) {
        // 迷宫右下角为2, 则说明已找到通路
        if (maze[row-2][col-2] == 2) {
            return true;
        }
        // 为0表示没有走过, 则暂设该点为2, 按照找路策略进行查找
        if (maze[startRow][startCol] == 0) {
            maze[startRow][startCol] = 2;
            // 向上走
            if (findBestWay(maze, startRow - 1, startCol)) {
                return true;
                // 向右走
            } else if (findBestWay(maze, startRow, startCol + 1)) {
                return true;
                // 向下走
            } else if (findBestWay(maze, startRow + 1, startCol)) {
                return true;
                // 向左走
            } else if (findBestWay(maze, startRow, startCol - 1)) {
                return true;
                // 都不通则设为3, 返回false
            } else {
                maze[startRow][startCol] = 3;
                return false;
            }
        }
        // 1 和 3都表示不通, 直接返回false
        return false;
    }
}
