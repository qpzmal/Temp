package patterns;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestProxy implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // TODO Auto-generated method stub
//        try(File b = new File("");
//                ){File a = b;}
//        catch(Exception e) {}
        return null;
    }
}
