package org.Leetcode.matrix;

public class TransposeMatrix {

    public int[][] transpose(int[][] matrix){
        int temp =0;
        for(int row=0;row<matrix.length;row++){
            for(int col=0;col<matrix[0].length;col++){
                temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        TransposeMatrix transposeMatrix = new TransposeMatrix();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        transposeMatrix.transpose(matrix);
    }
}
