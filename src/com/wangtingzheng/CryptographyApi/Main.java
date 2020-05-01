package com.wangtingzheng.CryptographyApi;

import com.wangtingzheng.CryptographyApi.util.CharMatrix;
import com.wangtingzheng.CryptographyApi.util.IntMatrix;
import com.wangtingzheng.CryptographyApi.util.Matrix;
import com.wangtingzheng.CryptographyApi.util.MatrixClass;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:32
 * @features
 */
public class Main {
    public static int[][] a = {{1, 2, 3, 4}, {3, 2, 4, 1}};

    public static void main(String[] args)
    {
        Matrix matrix = new Matrix(a);
        if(matrix.type == MatrixClass.Type.IntMatirx)
        {
            IntMatrix intMatrix = matrix.getMatrix();
            intMatrix.printMatrix();
        }
    }
}
