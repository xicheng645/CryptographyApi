package com.wangtingzheng.CryptographyApi.parentClass;

import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/4/30 15:33
 * @features
 */
public class Algorithm {
    public String messageSpace;
    public String cipherSpace;
    public List keySpace = null;

    public Algorithm(String messageSpace, String cipherSpace, List keySpace) {
        this.messageSpace = messageSpace;
        this.cipherSpace = cipherSpace;
        this.keySpace = keySpace;
    }

    public void decoding()
    {

    }

    public void encoding()
    {

    }

    public boolean checkMessageSpace()
    {
        return false;
    }

    public boolean checkCipherSpace()
    {
        return false;
    }

    public boolean checkKeySpace()
    {
        return false;
    }

    public final boolean check()
    {
        return checkMessageSpace()&&checkCipherSpace()&&checkKeySpace();
    }
}
