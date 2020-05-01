package com.wangtingzheng.CryptographyApi.algorithm;

import com.wangtingzheng.CryptographyApi.parentClass.Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/4/30 16:36
 * @features
 */
public class Test extends Algorithm  {

    public Test(String messageSpace, String cipherSpace, List keySpace) {
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
