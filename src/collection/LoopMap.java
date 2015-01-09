package collection;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LoopMap {

    public static void main(String[] args) {
        /*     有用的！！！！！不能删掉！！！！！
        */
        Map<String, File> resultMap = new HashMap<String, File>();
        // 遍历map，方法一
        Collection<File> c = resultMap.values();
        Iterator<File> methodOne = c.iterator();
        while(methodOne.hasNext()){
            System.out.println(methodOne.next().getPath());
        }

        // 遍历map，方法二
        Set<String> key = resultMap.keySet();
        Iterator<String> methodTwo = key.iterator();
        while(methodTwo.hasNext()){
            System.out.println(methodTwo.next());
        }

        // 遍历map，方法三
        Set<Map.Entry<String, File>> set = resultMap.entrySet();
        Iterator<Map.Entry<String, File>> methodThree = set.iterator();
        while(methodThree.hasNext()){
            Map.Entry<String, File> entry = (Map.Entry<String, File>) methodThree.next();
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
        

    }

}
