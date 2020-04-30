package com.wangtingzheng.CryptographyApi.algorithm;

import com.wangtingzheng.CryptographyApi.parentClass.Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/4/30 16:36
 * @features
 */
public class Test extends Algorithm {
    public Test(String messageSpace, String cipherSpace, List keySpace)
    {
        super(messageSpace,cipherSpace, keySpace);
    }

    @Override
    public boolean checkCipherSpace() {
        return super.checkCipherSpace();
    }

    @Override
    public boolean checkKeySpace() {
        return super.checkKeySpace();
    }

    @Override
    public boolean checkMessageSpace() {
        return super.checkMessageSpace();
    }

    @Override
    public void decoding() {
        super.decoding();
    }

    @Override
    public void encoding() {
        super.encoding();
    }

    public void test()
    {
        List<String> res = castList(this.keySpace.get(0), String.class);
        System.out.println(res.get(0));
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz)
    {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
