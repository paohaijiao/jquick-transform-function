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
import com.github.paohaijiao.crypto.impl.AesCryptoService;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AES解密方法提供者
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickAesDecryptFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Map<String, AesCryptoService> serviceCache = new ConcurrentHashMap<>();

    public JQuickAesDecryptFunctionProvider() {
        super("aesDecrypt", "[Crypto] AES解密 - 用法: aesDecrypt(encryptedData, base64Key)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String encryptedData = asString(args.get(0));
        String base64Key = asString(args.get(1));
        if (encryptedData == null || base64Key == null) {
            throw new IllegalArgumentException("加密数据和密钥不能为null");
        }
        try {
            AesCryptoService service = serviceCache.computeIfAbsent(base64Key, k -> {
                try {
                    return new AesCryptoService(k);
                } catch (CryptoException e) {
                    throw new RuntimeException("创建AES服务失败", e);
                }
            });
            return service.decrypt(encryptedData);
        } catch (CryptoException e) {
            throw new RuntimeException("AES解密失败: " + e.getMessage(), e);
        }
    }
}
