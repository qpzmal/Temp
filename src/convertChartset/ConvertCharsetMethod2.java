package convertChartset;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * java字符转码:三种方法 原文地址：http://thetopofqingshan.iteye.com/blog/1502731
 * http://blog.csdn.net/zhengqiqiqinqin/article/details/12621201
 * 
 * @author weiqz
 *
 */
public class ConvertCharsetMethod2 {

    // 使用java.io桥转换:对文件进行转码 
    private FileInputStream fis;// 文件输入流:读取文件中内容
    private InputStream is;
    private InputStreamReader isr;
    private OutputStream os;
    private OutputStreamWriter osw;// 写入
    private char[] ch = new char[1024];

    public void convertionFile() throws IOException {
        is = new FileInputStream("C:/项目进度跟踪.txt");// 文件读取
        isr = new InputStreamReader(is, "gbk");// 解码
        os = new FileOutputStream("C:/项目进度跟踪_utf-8.txt");// 文件输出
        osw = new OutputStreamWriter(os, "utf-8");// 开始编码
        char[] c = new char[1024];// 缓冲
        int length = 0;
        while (true) {
            length = isr.read(c);
            if (length == -1) {
                break;
            }
            System.out.println(new String(c, 0, length));
            osw.write(c, 0, length);
            osw.flush();
        }

    }

    public void convertionString() throws UnsupportedEncodingException {
        String s = "清山集团";
        byte[] b = s.getBytes("gbk");// 编码
        String sa = new String(b, "gbk");// 解码:用什么字符集编码就用什么字符集解码
        System.out.println(sa);

        b = sa.getBytes("utf-8");// 编码
        sa = new String(b, "utf-8");// 解码
        System.err.println(sa);
    }

    /**
     * <pre>
     * 关闭所有流
     * </pre>
     * 
     */
    public void close() {
        if (isr != null) {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (osw != null) {
            try {
                osw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * <pre>
     * 用io读取文件内容
     * </pre>
     * 
     * @throws IOException
     *             读取过程中发生错误
     * 
     */

    /**
     * <pre>
     * 
     * </pre>
     * 
     * @param path
     * @param charset
     * @throws IOException
     * 
     */
    public void read(String path, String charset) throws IOException {
        fis = new FileInputStream(path);
        isr = new InputStreamReader(fis, charset);
        while (fis.available() > 0) {
            int length = isr.read(ch);
            System.out.println(new String(ch));
        }
    }

    public static void main(String[] args) {
        try {
            ConvertCharsetMethod2 cc = new ConvertCharsetMethod2();
            cc.convertionFile();
            cc.convertionString();
            cc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
