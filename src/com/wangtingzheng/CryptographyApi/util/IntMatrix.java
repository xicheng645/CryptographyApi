package com.wangtingzheng.CryptographyApi.util;

/**
 * @author WangTingZheng
 * @date 2020/4/30 22:00
 * @features
 */
public class IntMatrix extends MatrixParent{

    int[][] intData = null;
    public static int add = 1;
    public static int minus = 2;
    public static int multiply = 3;

    public IntMatrix(int[][] intData) {
        this.intData = intData;
    }

    public IntMatrix(int row, int column) {
        int[][] data = new int[row][column];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
               data[i][j] = 0;
            }
        }
        this.intData = data;
    }

    public boolean check(IntMatrix intMatrix, int action)
    {
        int thisRow = this.getRow();
        int thisColumn = this.getColumn();
        int addedRow = intMatrix.getRow();
        int addedColumn = intMatrix.getColumn();

        if(action == add || action == minus)
        {
            if(thisRow != addedRow || thisColumn != addedColumn)
            {
                System.out.println("Two matrix have different row number or column number!");
                return false;
            }
        }
        else if(action == multiply)
        {
            if(thisColumn != addedRow)
            {
                System.out.println("First matrix column not equals with second matrix row");
                return false;
            }
        }
        return true;
    }

    public IntMatrix addWith(IntMatrix intMatrix)
    {
        if(check(intMatrix, add))
        {
            IntMatrix matrixRes = new IntMatrix(getRow(), getColumn());

            for(int i=0;i<getRow();i++)
            {
                for(int j =0;j<getColumn();j++)
                {
                    matrixRes.intData[i][j] = this.intData[i][j]+intMatrix.intData[i][j];
                }
            }
            return matrixRes;
        }
        return null;

    }

    public IntMatrix minusWith(IntMatrix intMatrix)
    {
        if(check(intMatrix, add))
        {
            IntMatrix matrixRes = new IntMatrix(getRow(), getColumn());

            for(int i=0;i<getRow();i++)
            {
                for(int j =0;j<getColumn();j++)
                {
                    matrixRes.intData[i][j] = this.intData[i][j]-intMatrix.intData[i][j];
                }
            }
            return matrixRes;
        }
        return null;
    }

    public IntMatrix multiplyWith(IntMatrix intMatrix)
    {
        if(check(intMatrix, multiply))
        {
            IntMatrix res  = new IntMatrix(getRow(), intMatrix.getColumn());
            IntMatrix tras = intMatrix.transpose();
            int a = 0;
            int b = 0;
            for(int i =0; i < getRow();i++)
            {
                for(int j =0; j < intMatrix.getColumn();j++)
                {
                    int result = 0;
                    for(int z = 0; z<getColumn();z++)
                    {
                        result += intData[a][z]*tras.intData[b][z];
                    }
                    res.intData[i][j] = result;
                    b++;
                }
                a++;
                b=0;
            }
            return res;
        }
        return null;
    }

    public IntMatrix transpose()
    {
        IntMatrix res = new IntMatrix(getColumn(),getRow());
        for(int i = 0; i < getRow();i++)
        {
            for(int j = 0;j < getColumn();j++)
            {
                res.intData[j][i] = intData[i][j];;
            }
        }
        return res;
    }

    public int getRow()
    {
        return intData.length;
    }

    public int getColumn()
    {
        return intData[0].length;
    }

    public void printMatrix()
    {
        for(int[] line: intData)
        {
            for (int ele: line)
            {
                System.out.print(ele+" ");
            }
            System.out.println("");
        }
    }
}
