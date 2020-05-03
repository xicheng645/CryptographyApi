package com.wangtingzheng.CryptographyApi;
import com.wangtingzheng.CryptographyApi.algorithm.Affine;
import java.util.Objects;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:32
 * @features
 */
public class Main {


    public static void AffineTest()
    {
        String mess = "china";
        int[][] key = {{9,2}};
        Affine affine = new Affine(mess, key);
        Objects.requireNonNull(affine.doEncoding()).printMatrix();
    }

    public static void main(String[] args)
    {
        AffineTest();
    }
}


