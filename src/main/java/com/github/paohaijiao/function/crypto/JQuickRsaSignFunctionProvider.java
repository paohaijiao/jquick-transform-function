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

/**
 * packageName com.github.paohaijiao.function.crypto
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/11
 */

import com.github.paohaijiao.crypto.exception.CryptoException;
import com.github.paohaijiao.crypto.impl.RsaCryptoService;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RSA签名方法提供者
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRsaSignFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Map<String, RsaCryptoService> serviceCache = new ConcurrentHashMap<>();

    public JQuickRsaSignFunctionProvider() {
        super("rsaSign", "[Crypto] RSA签名 - 用法: rsaSign(data, base64PrivateKey)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String data = asString(args.get(0));
        String base64PrivateKey = asString(args.get(1));
        if (data == null || base64PrivateKey == null) {
            throw new IllegalArgumentException("数据和私钥不能为null");
        }
        try {
            RsaCryptoService service = serviceCache.computeIfAbsent(base64PrivateKey, k -> {
                try {
                    return new RsaCryptoService(null, k);
                } catch (CryptoException e) {
                    throw new RuntimeException("创建RSA服务失败", e);
                }
            });
            return service.sign(data);
        } catch (CryptoException e) {
            throw new RuntimeException("RSA签名失败: " + e.getMessage(), e);
        }
    }
}
