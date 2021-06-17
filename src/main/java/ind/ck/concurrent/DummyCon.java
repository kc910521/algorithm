package ind.ck.concurrent;

/**
 * @Author caikun
 * @Description
 * 哑对象，测试用
 * @Date 下午2:27 21-6-9
 **/
public class DummyCon {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DummyCon(String name) {
        this.name = name;
    }

    public DummyCon() {
    }

    @Override
    public String toString() {
        return "DUMMY: " + name;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
