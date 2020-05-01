package com.wangtingzheng.CryptographyApi.parentClass;

import com.wangtingzheng.CryptographyApi.util.Matrix;
import com.wangtingzheng.CryptographyApi.util.MatrixClass;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:33
 * @features
 */
public abstract class Algorithm {
    public Matrix messageSpace = null;
    public Matrix cipherSpace = null;
    public Matrix keySpace = null;

    public Algorithm(Matrix messageSpace, Matrix cipherSpace, Matrix keySpace) {
        this.messageSpace = messageSpace;
        this.cipherSpace = cipherSpace;
        this.keySpace = keySpace;
    }

    public abstract void decoding();

    public abstract void encoding();

    public abstract boolean checkMessageSpace();

    public abstract boolean checkCipherSpace();

    public abstract boolean checkKeySpace();

    public final boolean check()
    {
        return checkMessageSpace()&&checkCipherSpace()&&checkKeySpace();
    }
    public final Matrix doDecoding()
    {
        if(check())
        {
            decoding();
        }
        return cipherSpace;
    }
}
