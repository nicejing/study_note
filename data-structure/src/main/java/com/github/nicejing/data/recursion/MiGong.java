package com.github.nicejing.data.recursion;

/**
 * @author Jing Zhi Bao
 */
public class MiGong {

    public static void main(String[] args) {

        // 初始化地图
        int[][] map = initMap();
        // 输出地图
        printMap(map);

        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        // setWay2(map, 1, 1);


        System.out.println("小球走过，并标识过的地图的情况");
        printMap(map);

    }

    private static int[][] initMap() {
        // 先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][8];
        // 使用1 表示墙
        // 上下全部置为1
        for (int i = 0; i < map.length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 左右全部置为1
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][7] = 1;
        }

        //设置挡板, 1 表示
        map[3][1] = 1;
        map[3][2] = 1;

        return map;

    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=====================");
    }

    /**
     * 使用递归回溯来给小球找路
     * 说明
     * 1. map 表示地图
     * 2. i,j 表示从地图的哪个位置开始出发(1,1)
     * 3. 如果小球能到map[7][7] 位置，则说明通路找到
     * 4. 约定： 当map[i][j] 为0 表示该点没有走过，当为1 表示墙； 2 表示通路可以走； 3 表示该点已经
     * 走过，但是走不通
     * 5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左, 如果该点走不通，再回溯
     */
    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true, 否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 通路已经找到ok
        if (map[map.length - 2][map.length - 2] == 2) {
            return true;
        } else {
            // 如果当前这个点还没有走过
            if (map[i][j] == 0) {
                //按照策略下->右->上->左走
                // 假定该点是可以走通.
                map[i][j] = 2;
                printMap(map);
                //向下走
                if (setWay(map, i + 1, j)) {
                    return true;
                    //向右走
                } else if (setWay(map, i, j + 1)) {
                    return true;
                    //向上
                } else if (setWay(map, i - 1, j)) {
                    return true;
                    // 向左走
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] != 0 , 可能是1， 2， 3
                return false;
            }
        }
    }
}
