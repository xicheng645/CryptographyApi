package com.wangtingzheng.CryptographyApi;
import com.wangtingzheng.CryptographyApi.algorithm.Affine;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:32
 * @features
 */
public class Main {


    public static void AffineTest()
    {
        String mess = "china";
        int[][] key = {{9,2,1}};
        Affine affine = new Affine(mess, key);
        if(affine.doDecoding() == null)
            System.out.println("加密/解密失败");
        else
            affine.doEncoding().printMatrix();
    }


    public static void main(String[] args)
    {
        AffineTest();
    }
}


