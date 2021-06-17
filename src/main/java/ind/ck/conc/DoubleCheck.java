package ind.ck.conc;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午4:35 21-6-10
 **/
public class DoubleCheck {

    private static DoubleCheck om = null;

    public Object carrier = null;

    public static DoubleCheck getInstance() {
        if (om == null) {
            synchronized (DoubleCheck.class) {
                if (om == null) {
                    om = new DoubleCheck();
                }
            }
        }
        return om;

    }


    private DoubleCheck () {
        this.carrier = new Object();
    }

    public void clear() {
        om = null;
    }

}
