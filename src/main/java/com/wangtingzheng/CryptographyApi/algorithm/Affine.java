package com.wangtingzheng.CryptographyApi.algorithm;


import com.wangtingzheng.cryptographystuct.matrix.Matrix;

import com.wangtingzheng.cryptographystuct.algorithm.Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTingZheng
 * @date 2020/5/2 9:59
 * @features
 */
public class Affine extends Algorithm {
    public Affine(Matrix messageSpace, Matrix cipherSpace, Matrix keySpace) {
        super(messageSpace, cipherSpace, keySpace);
    }

    public Affine(String message, String cipher, int[][] key) {
        super(message, cipher, key);
    }

    public Affine(String message, int[][] key) {
        super(message, key);
    }

    @Override
    public void encoding() {
        int k1, k2;
        List<Integer> cipherList = new ArrayList<>();

        k1 = keySpace.getIntValue(0,0);
        k2 = keySpace.getIntValue(0,1);

        Matrix messageSpace_int = messageSpace.toIntMatrix();

        for(int ele: messageSpace_int.intData[0])
        {
            cipherList.add(mod(ele*k1+k2, 26));
        }
        cipherSpace = new Matrix(cipherList).toCharMatrix();
    }

    @Override
    public void decoding() {
        int k1, k2;
        List<Integer> messageList = new ArrayList<>();

        k1 = keySpace.getIntValue(0,0);
        k2 = keySpace.getIntValue(0,1);
        k1 = returnInverse(k1,26);

        Matrix cipherSpaceInt = cipherSpace.toIntMatrix();

        for(int ele: cipherSpaceInt.intData[0])
        {
            messageList.add(mod(k1 * (ele - k2),26));
        }
        messageSpace = new Matrix(messageList).toCharMatrix();
    }

    @Override
    public boolean checkMessageSpace() {
        return messageSpace.checkIsLetter();
    }

    @Override
    public boolean checkCipherSpace() {
        return cipherSpace.checkIsLetter();
    }

    @Override
    public boolean checkKeySpace() {
        if(keySpace.getRow() == 1 && keySpace.getColumn() == 2)
        {
            int k1 = keySpace.getIntValue(0,0);
            if(gcd(k1, 26) == 1)
            {
                keySpace.setValue(0,0, mod(keySpace.getIntValue(0,0),26));
                keySpace.setValue(0,1, mod(keySpace.getIntValue(0,1),26));
                return true;
            }
        }
        return false;
    }

    public int gcd(int a, int b)
    {
        if(a%b == 0)
        {
            return b;
        }
        return gcd(b, a%b);
    }

    public List<Integer> returnDivisor(int a, int b, List<Integer> temp_list)
    {
        int c, d;
        c = a / b;
        d = a % b;
        if (d == 0)
            return temp_list;
        else
            temp_list.add(c);
        a = b;
        b = d;
        return returnDivisor(a, b, temp_list);
    }

    public List<Integer> returnList(int a, int b)
    {
        List<Integer> empty = new ArrayList<>();
        if(a>b)
            return returnDivisor(a, b, empty);
        return returnDivisor(b, a, empty);
    }

    public int returnInverseLoop(int a, int b, int b_1, int b_2, int n, List<Integer> list)
    {
        int b_t_2, b_t_1;
        if (n != list.size())
            b_t_2 = list.get(list.size()-n-1) * b_2 +b_1;
        else
        {
            if(n%2!=0)
                return a>b?a:b - b_2;
            return b_2;
        }
        b_t_1 = b_2;
        n ++;
        return returnInverseLoop(a, b, b_t_1, b_t_2, n, list);
    }

    public int returnInverse(int a, int b)
    {
        if(a !=1)
        {
            List<Integer> list_a = returnList(a, b);
            int b_s_1 = 1;
            int b_s_2 = list_a.get(list_a.size()-1);
            return returnInverseLoop(a, b, b_s_1, b_s_2, 1, list_a);
        }
        return 1;
    }

    public int mod(int number, int mod)
    {
        if(number < -1)
            return 26-(number*-1)%mod;
        return number%mod;
    }
}
