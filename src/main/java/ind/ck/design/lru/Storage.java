package ind.ck.design.lru;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午5:03 21-3-26
 **/
public interface Storage<K, V> {
    /** * 根据提供的key来访问数据 * @param key 数据Key * @return 数据值 */
    V get(K key);
}

