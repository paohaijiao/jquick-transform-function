/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.function.crypto;

import com.github.paohaijiao.crypto.exception.CryptoException;
import com.github.paohaijiao.crypto.impl.RsaCryptoService;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RSA解密方法提供者
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRsaDecryptFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Map<String, RsaCryptoService> serviceCache = new ConcurrentHashMap<>();

    public JQuickRsaDecryptFunctionProvider() {
        super("rsaDecrypt", "[Crypto] RSA解密 - 用法: rsaDecrypt(encryptedData, base64PrivateKey)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String encryptedData = asString(args.get(0));
        String base64PrivateKey = asString(args.get(1));
        if (encryptedData == null || base64PrivateKey == null) {
            throw new IllegalArgumentException("加密数据和私钥不能为null");
        }
        try {
            RsaCryptoService service = serviceCache.computeIfAbsent(base64PrivateKey, k -> {
                try {
                    return new RsaCryptoService(null, k);
                } catch (CryptoException e) {
                    throw new RuntimeException("创建RSA服务失败", e);
                }
            });
            return service.decrypt(encryptedData);
        } catch (CryptoException e) {
            throw new RuntimeException("RSA解密失败: " + e.getMessage(), e);
        }
    }
}
