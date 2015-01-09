package convertChartset;

import java.io.UnsupportedEncodingException;

/**
 * java字符转码:三种方法
 * 原文地址：http://thetopofqingshan.iteye.com/blog/1502731
 *        http://blog.csdn.net/zhengqiqiqinqin/article/details/12621201
 * 
 * @author weiqz
 *
 */
public class ConvertCharsetMethod1 {

    public static void main(String[] args) {
        ConvertCharsetMethod1 c = new ConvertCharsetMethod1();
        try {
            c.convertionString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * 
     * 方法一:java.lang.String
     * 用于解码的构造器:  
     * String(byte[] bytes, int offset, int length, String charsetName) 
     * String(byte[] bytes, String charsetName) 
     * 
     * 用于编码的方法: 
     * byte[] getBytes(String charsetName)  //使用指定字符集进行编码  
     * byte[] getBytes() //使用系统默认字符集进行编码  
     * 
     */
    public void convertionString() throws UnsupportedEncodingException {
        String s = "清山";
        byte[] b = s.getBytes("gbk");// 编码
        String sa = new String(b, "gbk");// 解码:用什么字符集编码就用什么字符集解码
        System.out.println(sa);

        b = sa.getBytes("utf-8");// 编码
        sa = new String(b, "utf-8");// 解码
        System.err.println(sa);
    }
}
