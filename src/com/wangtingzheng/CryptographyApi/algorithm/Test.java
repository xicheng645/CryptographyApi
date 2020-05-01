package com.wangtingzheng.CryptographyApi.algorithm;

import com.wangtingzheng.CryptographyApi.parentClass.Algorithm;
import com.wangtingzheng.CryptographyApi.util.CharMatrix;
import com.wangtingzheng.CryptographyApi.util.Matrix;
import com.wangtingzheng.CryptographyApi.util.MatrixClass;

/**
 * @author WangTingZheng
 * @date 2020/4/30 16:36
 * @features
 */
public class Test extends Algorithm  {

    public Test(Matrix messageSpace, Matrix cipherSpace, Matrix keySpace) {
        super(messageSpace, cipherSpace, keySpace);
    }

    @Override
    public void decoding() {

    }

    @Override
    public void encoding() {

    }

    @Override
    public boolean checkMessageSpace() {
        if(messageSpace.type  == MatrixClass.Type.CharMatrix)
        {
            CharMatrix charMatrix = messageSpace.getMatrix();
            char[] res = charMatrix.charData[0];
        }

        return false;
    }

    @Override
    public boolean checkCipherSpace() {
        return false;
    }

    @Override
    public boolean checkKeySpace() {
        return false;
    }
}
