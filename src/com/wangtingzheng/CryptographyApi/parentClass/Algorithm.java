package com.wangtingzheng.CryptographyApi.parentClass;

import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:33
 * @features
 */
public abstract class Algorithm {
    String messageSpace = null;
    String cipherSpace = null;
    List keySpace = null;

    public Algorithm(String messageSpace, String cipherSpace, List keySpace) {
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
    public final String doDecoding()
    {
        if(check())
        {
            decoding();
        }
        return cipherSpace;
    }
}
