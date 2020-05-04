package com.wangtingzheng.CryptographyApi.algorithm;


import com.wangtingzheng.cryptographystuct.error.Error;
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

        k1 = keySpace.getIntValue(0,0); //从密钥空间获取第一个密钥
        k2 = keySpace.getIntValue(0,1); //从密钥空间获取第二个密钥

        Matrix messageSpace_int = messageSpace.toIntMatrix(); //把字符型的明文空间转化为整型的明文空间

        for(int ele: messageSpace_int.intData[0]) //循环读取明文空间里的明文字符，当然是转化为数字之后的
        {
            cipherList.add(mod(ele*k1+k2, 26)); //进行加密运算
        }
        cipherSpace = new Matrix(cipherList).toCharMatrix(); //把加密运算的结果（一个数组）作为初始值，创建一个矩形对象，再把矩形对象转化成字符型的，作为密文空间
    }

    @Override
    public void decoding() {
        int k1, k2;
        List<Integer> messageList = new ArrayList<>();

        k1 = keySpace.getIntValue(0,0); //从密钥空间获取第一个密钥
        k2 = keySpace.getIntValue(0,1); //从密钥空间获取第二个密钥
        k1 = returnInverse(k1,26); //获取k1的逆

        Matrix cipherSpaceInt = cipherSpace.toIntMatrix();  //把字符型的密文空间转化为整型的密文空间

        for(int ele: cipherSpaceInt.intData[0])//循环读取密文空间里的密文字符，当然是转化为数字之后的
        {
            messageList.add(mod(k1 * (ele - k2),26));//进行解密运算
        }
        messageSpace = new Matrix(messageList).toCharMatrix(); //把解密运算的结果（一个数组）作为初始值，创建一个矩阵对象，再把矩阵对象转化成字符型的，作为明文空间
    }

    @Override
    public boolean checkMessageSpace() {
        boolean type = messageSpace.getType() == Matrix.Type.CharMatrix; //检查密文空间是否都是阿拉伯字母（先要是字符，再要是字母）
        if(!type)
        {
            errorList.enableError(ErrorNumber.MessageSpaceNotChar);
            return false;
        }
        boolean letter = messageSpace.checkIsLetter();
        if(!letter)
        {
            errorList.enableError(ErrorNumber.MessageSpcaeHasNotLetter);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkCipherSpace() {
        boolean type = cipherSpace.getType() == Matrix.Type.CharMatrix; //检查明文空间是否都是阿拉伯字母（先要是字符，再要是字母）
        if(!type)
        {
            errorList.enableError(ErrorNumber.CipherSpaceNotChar);
            return false;
        }
        boolean letter = cipherSpace.checkIsLetter();
        if(!letter)
        {
            errorList.enableError(ErrorNumber.CipherSpcaeHasNotLetter);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkKeySpace() {
        boolean type = keySpace.getType() == Matrix.Type.IntMatrix;
        if(!type)
        {
            errorList.enableError(ErrorNumber.KeySpaceNotInt);
            return false;
        }
        boolean size = keySpace.getRow() == 1 && keySpace.getColumn() == 2;
        if(!size)
        {
            errorList.enableError(ErrorNumber.keyNumberNotMatch);
            return false;
        }
        int k1 = keySpace.getIntValue(0,0); //获取第一个密钥
        if(gcd(k1, 26) != 1)  //检查密钥k1于26的最大公约数是否是1，这个是仿射密码算法的要求
        {
           errorList.enableError(ErrorNumber.GcdError);
           return false;
        }
        keySpace.setValue(0,0, mod(keySpace.getIntValue(0,0),26));  //规范密钥（如果不止规定区间内的话）
        keySpace.setValue(0,1, mod(keySpace.getIntValue(0,1),26));  //规范密钥（如果不止规定区间内的话）
        return true;
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

    @Override
    public void errorInit() {
        Error MessageSpaceNotChar = new Error(ErrorNumber.MessageSpaceNotChar,this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("Message Space is not char.");
            }
        };
        Error MessageSpcaeHasNotLetter = new Error(ErrorNumber.MessageSpcaeHasNotLetter,this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("Message Space has not-letter element.");
            }
        };
        Error CipherSpaceNotChar = new Error(ErrorNumber.CipherSpaceNotChar,this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("Cipher Space is not char.");
            }
        };

        Error CipherSpcaeHasNotLetter = new Error(ErrorNumber.CipherSpcaeHasNotLetter,this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("Cipher Space has not-letter element.");
            }
        };
        Error KeySpaceNotInt = new Error(ErrorNumber.KeySpaceNotInt,this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("Key Space is not int.");
            }
        };
        Error GcdError = new Error(ErrorNumber.GcdError,this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("The gcd of k1 and 26 not 1.");
            }
        };

        Error keyNumberNotMatch = new Error(ErrorNumber.keyNumberNotMatch,this.getClass(), errorList) {
            @Override
            public void errorReport() {
                System.out.println("The number of key is not 2.");
            }
        };
        errorList.addError(keyNumberNotMatch);
    }

    public static class ErrorNumber
    {
        public static int MessageSpaceNotChar = 1;
        public static int MessageSpcaeHasNotLetter = 2;
        public static int CipherSpaceNotChar = 3;
        public static int CipherSpcaeHasNotLetter = 4;

        public static int KeySpaceNotInt = 5;
        public static int GcdError = 6;
        public static int keyNumberNotMatch =7;
    }
}
