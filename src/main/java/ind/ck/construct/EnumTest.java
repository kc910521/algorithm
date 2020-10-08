package ind.ck.construct;

import java.util.ArrayList;
import java.util.List;

public enum EnumTest {
    INSTANCE;
    //不实例化
    public List list = null;

    // list属性
    private EnumTest() {
        //构造函数
        list = new ArrayList();
        System.out.println("init");
    }

    public static EnumTest getInstance() {
        return INSTANCE;//返回已存在的对象 }
    }
}
