package com.wangtingzheng.CryptographyApi;

import com.wangtingzheng.CryptographyApi.algorithm.Test;
import com.wangtingzheng.CryptographyApi.parentClass.Algorithm;
import com.wangtingzheng.CryptographyApi.util.IntMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:32
 * @features
 */
public class Main {
    public static int[][] a = {{3,1,2},{-2,0,5}};
    public static int[][] b = {{-1,3},{0,5},{2,5}};

    public static void main(String[] args)
    {
        IntMatrix aMat = new IntMatrix(a);
        IntMatrix bMat = new IntMatrix(b);
        IntMatrix res = aMat.multiplyWith(bMat);
        if(res != null)
            res.printMatrix();
    }

}
