package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 想做一个文本编码格式转换类。
 * 需求：输入参数：
 *      1.输入文件夹路径,带结束符(eg: c:/asdf/test/in/)
 *      2.目标文件类型(eg: java)
 *      3.当前格式(eg: utf-8)
 *      4.目标格式(eg: gb2312)
 *      5.输出文件夹路径,带结束符(eg: c:/asdf/test/out/)
 * 根据以上参数，将参数1下的所有目标文件转换格式后输出到参数5的路径下
 * 问题：
 *      1.暂无
 * @author weiqz
 *
 */
public class ConvertCharset {

    public static void main(String[] args) {
        ConvertCharset.convertFile("D:/下载/《设计模式之禅》源代码/",".java","gb2312","utf-8","D:/下载/《设计模式之禅》源代码/out/");
    }

    public static void convertFile(String inPath, String suffix, String source, String target, String outPath){
        // 1.获取文件列表
        List<File> resultList = getFileList(inPath, suffix);

        
        try{
            // 2.转换文件格式
            convertFileType(resultList, inPath, source, target, outPath);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static List<File> getFileList(String inPath, String suffix){
        List<File> resultList = new ArrayList<File>();
        File fileRoot = new File(inPath);
        if (!fileRoot.exists()) {
            return resultList;
        }
        File[] files = fileRoot.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                resultList.addAll(getFileList(file.getPath(),suffix));
            } else if (file.getName().endsWith(suffix)) {
                resultList.add(file);
            }
        }
        return resultList;
    }

    /*
     * 流程概述:从resultList获取文件信息file；从file中获取文件路径inFilePath以及文件夹路径inFileParentPath；
     *        依据inFileParentPath以及inPath，outPath获得outFileParentPath；
     *        判断：当outFileParentPath不存在时，建立文件夹及父文件夹
     */
    private static void convertFileType(List<File> resultList,String inPath, String source, String target, String outPath) throws IOException{

        String inFilePath = null;
        String outFilePath = null;
        File outFileParent = null;
        File outFile = null;
        String inFileParentPath = null;
        String outFileParentPath = null;
        for (File file : resultList) {
            inFileParentPath = file.getParent()+System.getProperty("file.separator");
            inFileParentPath = inFileParentPath.replace(System.getProperty("file.separator"), "/");
            outFileParentPath = inFileParentPath.replace(inPath, outPath);
            outFileParent = new File(outFileParentPath);
            // 判断文件夹是否存在，不存在建立文件夹
            if (!outFileParent.exists()) {
                outFileParent.mkdirs();
            }
            inFilePath = file.getPath();
            inFilePath = inFilePath.replace(System.getProperty("file.separator"), "/");
            outFilePath = inFilePath.replace(inPath, outPath);
            outFile = new File(outFilePath);
            copyByNIO(file, outFile, source, target);
        }
        
    }
    
    /**
     * copy文件，通过stream方式
     * 如果用此方式转换编码，要用reader和writter,暂时放弃
     */
    private static void copyByStream(File in, File out){
        InputStream is = null;
        OutputStream os = null;
        try{
            is = new BufferedInputStream(new FileInputStream(in));
            os = new BufferedOutputStream(new FileOutputStream(out));
            byte[] buf = new byte[2048];
            int i;
            while((i = is.read(buf)) != -1){
                os.write(buf, 0 , i);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
//            try {
//                is.close();
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        
    }

    /**
     * copy文件，通过channel方式
     */
    private static void copyByChannel(File in, File out){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fci = null;
        FileChannel fco = null;
        try{
            fis = new FileInputStream(in);
            fos = new FileOutputStream(out);
            fci = fis.getChannel(); // 得到对应的文件通道
            fco = fos.getChannel(); // 得到对应的文件通道 
            fci.transferTo(0, fci.size(), fco); // 连接两个通道，并且从in通道读取，然后写入
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // jdk7提供Closeable接口， 貌似可以不用close
//            fis.close();
//            fos.close();
//            fco.close();
//            fci.close();
        }
    }

    /**
     * copy文件并转码，通过NIO方式
     */
    private static void copyByNIO(File in, File out, String source, String target ) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024 * 3);// 设置缓存区的大小
        ByteBuffer temp = ByteBuffer.allocate(1024 * 3);// 设置缓存区的大小:临时  
        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);
        FileChannel fci = fis.getChannel();
        FileChannel fco = fos.getChannel();
        Charset inSet = Charset.forName(source);// 解码字符集  
        Charset outSet = Charset.forName(target);// 编码字符集  
        CharsetDecoder de = inSet.newDecoder();// 解码器  
        CharsetEncoder en = outSet.newEncoder();// 编码器  

        while (fis.available() > 0) {  
            buf.clear();// 清除标记  
            fci.read(buf); // 将文件内容读入到缓冲区内:将标记位置从0-b.capacity(),  
                        // 读取完毕标记在0-b.capacity()之间  
            buf.flip();// 调节标记,下次读取从该位置读起  
  
            temp.clear();// 清除标记  
            temp = en.encode(de.decode(buf));  
            buf.flip(); // 将标记移到缓冲区的开始,并保存其中所有的数据:将标记移到开始0  
            fco.write(temp); // 将缓冲区内的内容写入文件中:从标记处开始取出数据  
        } 

    }
}
