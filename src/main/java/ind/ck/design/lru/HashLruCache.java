package ind.ck.design.lru;

import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午5:25 21-3-26
 **/
public class HashLruCache<K, V>  extends LruCache<K, V> {

    public HashLruCache(int capacity, Storage lowSpeedStorage) {
        super(capacity, lowSpeedStorage);
    }

    private ConcurrentHashMap<K, V> concurrentHashMap = new ConcurrentHashMap<>();

    private LinkedList<K> linkedList = new LinkedList<>();

    @Override
    public V get(K key) {
        V v = concurrentHashMap.get(key);
        if (v == null) {
            // load from storage
            Object ov = lowSpeedStorage.get(key);
        }
        return v;
    }

    // trash
    private boolean cache(K k, V v) {
        if (linkedList.size() >= 10) {
            K k1 = linkedList.removeLast();
            concurrentHashMap.remove(k);
        }
        if (linkedList.contains(k)) {
            linkedList.remove(k);
            linkedList.addFirst(k);
        }
        concurrentHashMap.put(k, v);
        return true;
    }


}
