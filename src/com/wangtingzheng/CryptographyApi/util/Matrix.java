package com.wangtingzheng.CryptographyApi.util;

/**
 * @author WangTingZheng
 * @date 2020/5/1 18:06
 * @features
 */
public class Matrix {
    public IntMatrix intMatrix;
    public CharMatrix charMatrix;
    public int type;

    public Matrix(int row, int column, int type) {
        this.type = type;
        if(type == MatrixClass.Type.IntMatirx)
        {
            intMatrix = new IntMatrix(row, column);
        }
        else if(type == MatrixClass.Type.CharMatrix)
        {
            charMatrix = new CharMatrix(row, column);
        }
    }

    public Matrix(int[][] intData) {
        type = MatrixClass.Type.IntMatirx;
        intMatrix = new IntMatrix(intData);
    }

    public Matrix(char[][] charData)
    {
        type = MatrixClass.Type.CharMatrix;
        charMatrix = new CharMatrix(charData);
    }

    public Matrix(String data)
    {
        type = MatrixClass.Type.CharMatrix;
        charMatrix = new CharMatrix(data);
    }

    public <V> V getMatrix()
    {
        if(type == MatrixClass.Type.IntMatirx)
        {
            return  (V) intMatrix;
        }
        else if(type == MatrixClass.Type.CharMatrix)
        {
            return (V) charMatrix;
        }
        return null;
    }
}
