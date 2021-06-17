package ind.ck.lambda;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午1:00 21-6-3
 **/
public class LambdaTest {

    /**
     * 位置
     */
    static class Step {

        int x = 0;
        int y = 0;

    }

    /**
     * 公主
     */
    static class Princess {
        // 0 北，1 西，2南;3 东
        private int headTo = 0;

        Step step = new Step();

        public void goForward() {
            switch (headTo) {
                case 0:
                    step.y ++;
                    break;
                case 1:
                    step.x --;
                    break;
                case 2:
                    step.y --;
                    break;
                case 3:
                    step.x ++;
                    break;
                default:
                    throw new RuntimeException("params error");
            }
        }

        public void turnLeft() {
            if (headTo == 3) {
                headTo = 0;
            } else {
                headTo ++;
            }
        }

        public void turnRight() {
            if (headTo == 0) {
                headTo = 3;
            } else {
                headTo --;
            }
        }

        /**
         * 回到原点
         * @return
         */
        private boolean circle() {
            return headTo == 0 && step.x == 0 && step.y == 0;
        }
    }

    /**
     * 控制器
     */
    static class Command {

        private Princess princess = null;

        public Command(Princess princess) {
            this.princess = princess;
        }

        private void exec(String c) {
            switch (c) {
                case "S":
                    princess.goForward();
                    break;
                case "R":
                    princess.turnRight();
                    break;
                case "L":
                    princess.turnLeft();
                    break;
                default:
                    throw new RuntimeException("?c");
            }
        }

    }

    public static void main(String []args) throws Exception {
        Princess princess = new Princess();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringBuilder stringBuilder = new StringBuilder();
        while (--n >= 0) {
            stringBuilder.append(br.readLine().trim());
        }
        Command command = new Command(princess);
        String[] commands = stringBuilder.toString().split("");
        int i = 0;
        for (; i < 6; i ++) {
            for (String cm : commands) {
                command.exec(cm);
            }
            if (princess.circle()) {
                System.out.println("yes");
                break;
            }
        }
        if (i == 6) {
            System.out.println("no");
        }

    }

}
