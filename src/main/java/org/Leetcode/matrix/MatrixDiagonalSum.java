package org.Leetcode.matrix;

public class MatrixDiagonalSum {
    public int diagonalSum(int[][] mat){
        int sum = 0;
        for(int row=0;row<mat.length;row++){
            for(int col=0;col<mat[0].length;col++){
                if(row==col || row+col == mat[0].length -1 ){
                    sum += mat[row][col];
                }
            }
        }
        System.out.println(sum);
        return sum;
    }

    public static void main(String[] args) {
        MatrixDiagonalSum matObj = new MatrixDiagonalSum();
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        matObj.diagonalSum(mat);
    }

}
