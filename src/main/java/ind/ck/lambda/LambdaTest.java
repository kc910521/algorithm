package ind.ck.lambda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午1:00 21-6-3
 **/
public class LambdaTest {

    /**
     * 测试用户类
     */
    static class UserObj {

        private String name;

        private int age;

        public UserObj(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("nm", this.name);
            map.put("ag", this.age);
            return map;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Hello User:" + name + "," + age;
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }
    }

    public void shelf() {
        // 做判断用
        Predicate<UserObj> predicate = Predicate.isEqual(1==1);
        // 生产者
        Supplier<UserObj> supplier = null;
        // 消费者
        Consumer<UserObj> consumer = null;
        // 映射用，下面用userObj映射为map
        Function<UserObj, Map<String, Object>> function = null;

        UnaryOperator<UserObj> userObjUnaryOperator = null;

        BinaryOperator<UserObj> binaryOperator = null;
    }



//    各种散装操作

    private static UserObj user1 = new UserObj("kangxi", 14);
    private static UserObj user2 = new UserObj("duoergun", 18);

    public static void predicateTest() {
        Predicate<UserObj> predicate = userObj -> userObj.age <= 18 && Objects.equals(userObj.name, "kangxi");
        System.out.println(predicate.test(user1));
        System.out.println(predicate.test(user2));
    }

    public static void SupplierTest() {
        Supplier<UserObj> supplier = () -> new UserObj("qianlong", 60);
        Consumer<UserObj> consumer = System.out::println;

    }

    public static void FunctionTest() {
        Function<UserObj, Map<String, Object>> function = UserObj::toMap;
    }

    public static void consumerTest() {
        Consumer<UserObj> consumer = (userObj) -> {
            System.out.println(userObj.age);
            System.out.println(userObj.name);
        };
        Consumer<UserObj> consumer2 = System.out::println;
        consumer2.accept(user1);

    }

    public static void BinaryOperatorTest() {
        BinaryOperator<UserObj> binaryOperator = (x, y) -> {
            return new UserObj(x.name + y.name, x.age + y.age);
        };
    }

//    连贯的list在这



    public static void streamTest() {
        List<UserObj> uList = Stream.of(
                new UserObj("a", 13),
                new UserObj("b", 25),
                new UserObj("c", 17),
                new UserObj("d", 13)).collect(Collectors.toList());
        List<UserObj> heis1 = Stream.of(
                new UserObj("a", 13),
                new UserObj("m1", 30)).collect(Collectors.toList());
        List<UserObj> heis2 = Stream.of(
                new UserObj("a", 93),
                new UserObj("b", 55)).collect(Collectors.toList());
//
        Stream<Map<String, Object>> mapStream = uList.stream().filter(userObj -> userObj.age < 24).map(UserObj::toMap);

//      flatmap这样就给两个流合并了
        Stream<UserObj> userObjStream1 = Stream.of(uList, heis1, heis2).flatMap(Collection::stream);
        // 求个最大值
//        UserObj userObj = userObjStream1.max(Comparator.comparing(st -> st.age)).get();
//        System.out.println("count:" + userObj);
        // reduce累加了
//        Optional<UserObj> reduce = userObjStream1.reduce((x, y) -> {
//            x.age += y.age;
//            return x;
//        });
//        System.out.println(reduce.get());
//        System.out.println(reduce.get());
//        求平均年龄
//        userObjStream1.collect()

        // 按条件分组
//        Map<Boolean, List<UserObj>> collect = userObjStream1.collect(Collectors.partitioningBy(userObj -> {
//            return userObj.age >= 18;
//        }));
//        System.out.println(collect);
        // grouping 分组
//        Map<String, List<UserObj>> collect = userObjStream1.collect(Collectors.groupingBy(UserObj::getName));
//        System.out.println(collect);

        // grouping 分组聚合1
//        Map<String, Integer> collect = userObjStream1.collect(Collectors.groupingBy(UserObj::getName, Collectors.summingInt(UserObj::getAge)));
//        System.out.println(collect);
        // grouping 分组聚合2
        Integer collect = userObjStream1.collect(Collectors.collectingAndThen(Collectors.groupingBy(UserObj::getName), Map::size));
        System.out.println(collect);
//        拼接
//        Collectors.collectingAndThen(Collectors.toList(), Optional::ofNullable)
//        String collect = userObjStream1.map(UserObj::getName).collect(Collectors.joining(",", " ", " "));
        System.out.println(collect);
    }

    public static void streamTestAdv() {
        List<UserObj> uList = Stream.of(
                new UserObj("a", 13),
                new UserObj("b", 25),
                new UserObj("c", 17),
                new UserObj("d", 13),
                new UserObj("a", 14),
                new UserObj("m1", 30)
        ).collect(Collectors.toList());
//        uList.stream().map((u) -> {
//            u.setAge(u.getAge() * 2);
//            return u;
//        });
        // 轻松转map
        Map<String, UserObj> collect = uList.stream()
//                .collect(Collectors.groupingBy(UserObj::getName, Collectors.summingInt(UserObj::getAge)))
                .collect(Collectors.toMap(UserObj::getName, Function.identity()));
        System.out.println(collect);


//        Map<Boolean, List<UserObj>> collect = uList.stream().collect(
//                Collectors.partitioningBy(
//                        u -> u.getAge() > 18)
//        );
        System.out.println(collect);
    }

    public static void streamTestHM() {
        Stream<String> st = Stream.of("a1", "a2", "end", "b1", "b2", "b3", "end", "c1", "end");
        List<String> res = new ArrayList<>(3);
        String s = st.collect(Collectors.reducing((x, y) -> {
            return "";
        })).get();
        System.out.println(s);

    }
    static class AsOs {
        long fr = 0;

        public long getFr() {
            return fr;
        }

        public void setFr(long fr) {
            this.fr = fr;
        }
    }

    static class KsOs {
        long ts = 0;
        long fr = 0;

        public long getFr() {
            return fr;
        }

        public void setFr(long fr) {
            this.fr = fr;
        }
    }

    public static void streamTestPar2() {
        String[] intFrs = new String[] {"1","2","3","4","5","6","7","8","9","10"};
        // new
        Stream.of(intFrs)
//                .parallel()
                .mapToLong(Long::parseLong)
                .mapToObj(fr -> {
                    AsOs key = new AsOs();
                    key.setFr(fr);
                    return key;
                }).filter(key -> {
            System.out.println(Thread.currentThread().getName() + " 111 + " + key);
            return key.fr % 2 == 0;
        }).map(key -> {
            KsOs flow = new KsOs();
            flow.setFr(key.getFr());
            flow.ts = System.currentTimeMillis();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( Thread.currentThread().getName() + " 222 " + key);
            return flow;
        }).collect(Collectors.collectingAndThen(Collectors.toList(), Optional::ofNullable))
                .ifPresent(x -> {
                    System.out.println("final:" + x);
                });

    }

    /**
     * fork()：开启一个新线程（或是重用线程池内的空闲线程），将任务交给该线程处理。
     * join()：等待该任务的处理线程处理完毕，获得返回值。
     */
    public static void acc() {
//        LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    }


    public static void main(String[] args) {
//        predicateTest();
//        consumerTest();
        streamTest();
//        streamTestAdv();
//        streamTestHM();
//        streamTestPar2();
    }

}
