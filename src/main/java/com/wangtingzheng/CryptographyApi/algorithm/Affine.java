package com.wangtingzheng.CryptographyApi.algorithm;

import com.wangtingzheng.cryptographystuct.matrix.CharMatrix;
import com.wangtingzheng.cryptographystuct.matrix.IntMatrix;
import com.wangtingzheng.cryptographystuct.matrix.Matrix;
import com.wangtingzheng.cryptographystuct.matrix.MatrixClass;
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
        IntMatrix keyIntMatrix = keySpace.getMatrix();
        k1 = keyIntMatrix.getValue(0,0);
        k2 = keyIntMatrix.getValue(0,1);
        if(messageSpace.type == MatrixClass.Type.CharMatrix)
        {
            CharMatrix charMatrix = messageSpace.getMatrix();
            IntMatrix MessageIntMatrix = charMatrix.toIntMatrix();
            IntMatrix cipherIntMatrx;
            List<Integer> cipherList = new ArrayList<>();
            for(int ele: MessageIntMatrix.intData[0])
            {
                cipherList.add(mod(ele*k1+k2, 26));
            }
            cipherIntMatrx = new IntMatrix(cipherList);
            cipherSpace.setCharMatrix(cipherIntMatrx.toCharMatrix());
        }
    }

    @Override
    public void decoding() {
        int k1, k2;
        IntMatrix keyIntMatrix = keySpace.getMatrix();
        k1 = keyIntMatrix.getValue(0,0);
        k2 = keyIntMatrix.getValue(0,1);
        k1 = returnInverse(k1,26);
        if(cipherSpace.type == MatrixClass.Type.CharMatrix)
        {

            CharMatrix charMatrix = cipherSpace.getMatrix();
            IntMatrix cipherCharMatrix = charMatrix.toIntMatrix();
            IntMatrix messageMatrix;
            List<Integer> messageList = new ArrayList<>();
            for(int ele: cipherCharMatrix.intData[0])
            {
                messageList.add(mod(k1 * (ele - k2),26));
            }
            messageMatrix = new IntMatrix(messageList);
            messageSpace.setCharMatrix(messageMatrix.toCharMatrix());
        }
    }

    @Override
    public boolean checkMessageSpace() {
        if(messageSpace.type == MatrixClass.Type.CharMatrix)
        {
            CharMatrix charMatrix = messageSpace.getMatrix();
            return charMatrix.checkIsLetter();
        }
        return false;
    }

    @Override
    public boolean checkCipherSpace() {
        if(cipherSpace.type == MatrixClass.Type.CharMatrix)
        {
            CharMatrix charMatrix = cipherSpace.getMatrix();
            return charMatrix.checkIsLetter();
        }
        return false;
    }

    @Override
    public boolean checkKeySpace() {
        if(keySpace.type == MatrixClass.Type.IntMatirx)
        {
            IntMatrix intMatrix = keySpace.getMatrix();
            if(intMatrix.getRow() == 1 && intMatrix.getColumn() == 2)
            {
                int k1 = intMatrix.getValue(0,0);
                if(gcd(k1, 26) == 1)
                {
                    intMatrix.setValue(0,0, mod(intMatrix.getValue(0,0),26));
                    intMatrix.setValue(0,1, mod(intMatrix.getValue(0,1),26));
                    keySpace.setIntMatrix(intMatrix);
                    return true;
                }
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
