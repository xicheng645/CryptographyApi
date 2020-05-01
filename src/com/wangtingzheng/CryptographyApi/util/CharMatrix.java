package com.wangtingzheng.CryptographyApi.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/5/1 16:05
 * @features
 */
public class CharMatrix extends MatrixClass {
    public char charData[][] = null;


    public CharMatrix(char[][] charData) {
        type = Type.CharMatrix;
        this.charData = charData;
    }

    public CharMatrix(int row, int column)
    {
        type = Type.CharMatrix;
        char[][] data = new char[row][column];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                data[i][j] = 'a';
            }
        }
        charData = data;
    }

    public CharMatrix(String data) {
        type = Type.CharMatrix;
        char[][] tempCharData = new char[1][data.length()];
        for(int i=0;i<data.length();i++)
        {
            tempCharData[0][i] = data.charAt(i);
        }
        charData = tempCharData;
    }


    public IntMatrix toIntMatrix()
    {
        IntMatrix intMatrix = new IntMatrix(getRow(), getColumn());
        intMatrix.initWithChar(charData);
        return intMatrix;
    }

    public void initWithInt(int[][] data)
    {
        for(int i =0;i<getRow();i++)
        {
            for(int j=0;j<getColumn();j++)
            {
                charData[i][j] = (char)(data[i][j]+97);
            }
        }
    }

    public int getRow()
    {
        return charData.length;
    }

    public int getColumn()
    {
        return charData[0].length;
    }

    public void printMatrix()
    {
        for(char[] line: charData)
        {
            for (char ele: line)
            {
                System.out.print(ele+" ");
            }
            System.out.println("");
        }
    }

    public char getChar(int row, int column)
    {
        return charData[row][column];
    }

    public void setChar(int row, int column, char value)
    {
        charData[row][column] = value;
    }
}
