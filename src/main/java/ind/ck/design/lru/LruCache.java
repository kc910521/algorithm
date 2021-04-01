package ind.ck.design.lru;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午5:15 21-3-26
 **/
public abstract class LruCache<K, V> implements Storage<K, V> {

    // 缓存容量
    protected final int capacity;
    // 低速存储，所有的数据都可以从这里读到
    protected final Storage lowSpeedStorage;

    public LruCache(int capacity, Storage lowSpeedStorage) {
        this.capacity = capacity;
        this.lowSpeedStorage = lowSpeedStorage;
    }

    @Override
    public V get(K key) {

        return null;
    }




}
