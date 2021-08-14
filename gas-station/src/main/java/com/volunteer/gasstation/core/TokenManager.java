package com.volunteer.gasstation.core;

import com.volunteer.gasstation.api.dto.ApiLoginInfoDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地Token管理器。
 *
 * @author
 */
@Service
public final class TokenManager {

    /**
     * 先把这存到本地了。 有时间再移植到数据库中存储。
     */
    private Map<String, TemporaryLoginInfo> memoryTokens = new HashMap<>();

    /**
     * 存储Token信息。
     *
     * @param tokenInfo
     */
    public void put(ApiLoginInfoDTO tokenInfo) {
        memoryTokens.put(tokenInfo.getToken(), new TemporaryLoginInfo(tokenInfo));
    }

    /**
     * 获取Token信息。
     *
     * @param token
     * @return
     */
    public ApiLoginInfoDTO get(String token) {
        String key = token;
        TemporaryLoginInfo temporaryLoginInfo = memoryTokens.get(key);
        if (null == temporaryLoginInfo) {
            return null;
        }
        // 超时
        if (temporaryLoginInfo.isTimeout()) {
            this.remove(token);
            return null;
        }
        return temporaryLoginInfo.getTokenInfo();
    }

    /**
     * 移除Token
     *
     * @param token
     */
    public void remove(String token) {
        memoryTokens.remove(token);
    }

    public static class TemporaryLoginInfo {
        private ApiLoginInfoDTO tokenInfo;
        private long time;

        public TemporaryLoginInfo(ApiLoginInfoDTO tokenInfo) {
            this.tokenInfo = tokenInfo;
            this.time = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000;
        }

        public boolean isTimeout() {
            return this.time < System.currentTimeMillis();
        }

        public ApiLoginInfoDTO getTokenInfo() {
            return tokenInfo;
        }
    }
}
