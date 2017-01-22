package guava.collections;

import com.google.common.cache.*;
import guava.common.TradeAccount;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/9 13:48
 */
public class LoadingCacheTest {

    private CacheLoader<String, TradeAccount> cacheLoader = mock(CacheLoader.class);

    @Test
    public void testCacheLoaderOnlyCalledOnce() throws Exception {
        TradeAccount expectedTradeAccount = new TradeAccount.Builder().balance(250000.12).build();
        when(cacheLoader.load("223")).thenReturn(expectedTradeAccount);
        LoadingCache<String, TradeAccount> tradeAccountCache = CacheBuilder.newBuilder()
                .maximumSize(5000L)
                .build(cacheLoader);

        TradeAccount tradeAccount = tradeAccountCache.get("223");
        assertThat(tradeAccount, is(expectedTradeAccount));

        for (int i = 0; i < 10; i++) {
            TradeAccount tradeAccount1 = tradeAccountCache.get("223");
            assertThat(tradeAccount1, is(expectedTradeAccount));
        }
        verify(cacheLoader, times(1)).load("223");
    }

    @Test
    public void testCacheLoaderCalledInitiallyAndAfterExpiration() throws Exception {
        TradeAccount expectedTradeAccount = new TradeAccount.Builder().balance(250000.12).build();
        when(cacheLoader.load("223")).thenReturn(expectedTradeAccount);
        LoadingCache<String, TradeAccount> tradeAccountCache = CacheBuilder.newBuilder()
                .maximumSize(5000L)
                .expireAfterAccess(500l, TimeUnit.MILLISECONDS)
                .build(cacheLoader);

        TradeAccount tradeAccount = tradeAccountCache.get("223");
        assertThat(tradeAccount, is(expectedTradeAccount));

        Thread.sleep(1000);

        TradeAccount tradeAccount1 = tradeAccountCache.get("223");
        assertThat(tradeAccount1, is(expectedTradeAccount));
        verify(cacheLoader, times(2)).load("223");
    }

    @Test
    public void testLoadCache() throws ExecutionException, InterruptedException {
        final LoadingCache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(5)
                .expireAfterAccess(2,TimeUnit.SECONDS)
                .recordStats()
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println(notification.getKey()+"df");
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key;
                    }

                });

        cache.get("1");
        cache.get("1");
        cache.invalidate("1");
        cache.get("1");
        cache.get("1");
        CacheStats stats = cache.stats();
        System.out.println(stats.hitCount());

    }

}
