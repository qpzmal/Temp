package convertChartset;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * java字符转码:三种方法 原文地址：http://thetopofqingshan.iteye.com/blog/1502731
 * http://blog.csdn.net/zhengqiqiqinqin/article/details/12621201
 * 
 * @author weiqz
 *
 */
public class ConvertCharsetMethod3 {
    
    // 使用nio中的Charset转换字符:整个流程是文件读取-->byte-->解码(正确)-->编码--->byte-->写入文件
    private FileInputStream fis;// 文件输入流:读取文件中内容
    private FileChannel in;// 文件通道:双向,流从中而过
    private FileChannel out;// 文件通道:双向,流从中而过
    private FileOutputStream fos;// 文件输出流:向文件中写入内容
    private ByteBuffer b = ByteBuffer.allocate(1024 * 3);// 设置缓存区的大小
    private Charset inSet;// 解码字符集
    private Charset outSet;// 编码字符集
    private CharsetDecoder de;// 解码器
    private CharsetEncoder en;// 编码器
    private CharBuffer convertion;// 中间的字符数据
    private ByteBuffer temp = ByteBuffer.allocate(1024 * 3);// 设置缓存区的大小:临时
    private byte[] by = new byte[1024];
    private InputStreamReader isr;
    private char[] ch = new char[1024];

    /**
     * <pre>
     * 
     * </pre>
     * 
     * @param src
     * @param dest
     * @throws IOException
     * 
     */
    public void convertionFile_nio(String src, String dest) throws IOException {
        fis = new FileInputStream(src);
        in = fis.getChannel();
        fos = new FileOutputStream(dest);
        out = fos.getChannel();
        inSet = Charset.forName("gbk");
        outSet = Charset.forName("utf-8");
        de = inSet.newDecoder();
        en = outSet.newEncoder();
        while (fis.available() > 0) {
            b.clear();// 清除标记
            in.read(b); // 将文件内容读入到缓冲区内:将标记位置从0-b.capacity(),
                        // 读取完毕标记在0-b.capacity()之间
            b.flip();// 调节标记,下次读取从该位置读起
            convertion = de.decode(b);// 开始编码

            temp.clear();// 清除标记
            temp = en.encode(convertion);
            b.flip(); // 将标记移到缓冲区的开始,并保存其中所有的数据:将标记移到开始0
            out.write(temp); // 将缓冲区内的内容写入文件中:从标记处开始取出数据
        }
    }

    /**
     * <pre>
     * 测试转码是否成功, 指定字符集读取文件
     * </pre>
     * 
     * @param src
     *            被复制文件全路径
     * @param charset
     *            解码字符集
     * 
     * @throws IOException
     *             读取过程中的发生的异常
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

    /**
     * <pre>
     * 关闭所有流或通道
     * </pre>
     * 
     */
    public void close() {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (fis != null) {
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConvertCharsetMethod3 n = new ConvertCharsetMethod3();
        try {
            n.convertionFile_nio("C:/项目进度跟踪.txt", "C:/nio_write.txt");
            // n.read("C:/nio_write.txt", "utf-8");// 正确
            // n.read("C:/nio_write.txt", "gbk");//乱码
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            n.close();
        }
    }

}
