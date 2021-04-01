package ind.ck.jvm;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 上午10:20 21-2-2
 **/
public class AmHere {

    public void sand() {
        try {
            System.out.println("mmmm");
        } catch (Exception e) {

        }
    }

    public void sand2() {
        System.out.println("mmmm2222");
    }

    public static void main(String[] args) {
        Integer a= 1;
        synchronized (a) {

        }
    }
}
