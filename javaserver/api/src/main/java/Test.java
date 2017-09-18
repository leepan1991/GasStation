import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lealhom on 2017/7/20 19:53
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(5, TimeUnit.SECONDS).build();
        cache.put("verifyCode", "123456");
        try {
//            String value =  cache.get("verifyCode", new Callable<String>() {
//                @Override
//                public String call() throws Exception {//缓存失效时，会调用call
//                    return null;
//                }
//            });
            for(int i=0;i<8;i++){
                System.out.println(cache.getIfPresent("verifyCode"));
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}