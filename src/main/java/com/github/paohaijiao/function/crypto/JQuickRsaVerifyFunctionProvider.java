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
 * RSA验签方法提供者
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRsaVerifyFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Map<String, RsaCryptoService> serviceCache = new ConcurrentHashMap<>();

    public JQuickRsaVerifyFunctionProvider() {
        super("rsaVerify", "[Crypto] RSA验签 - 用法: rsaVerify(data, signature, base64PublicKey)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 3);
        String data = asString(args.get(0));
        String signature = asString(args.get(1));
        String base64PublicKey = asString(args.get(2));
        if (data == null || signature == null || base64PublicKey == null) {
            throw new IllegalArgumentException("数据、签名和公钥不能为null");
        }
        try {
            RsaCryptoService service = serviceCache.computeIfAbsent(base64PublicKey, k -> {
                try {
                    return new RsaCryptoService(k, (String) null);
                } catch (CryptoException e) {
                    throw new RuntimeException("创建RSA服务失败", e);
                }
            });
            return service.verify(data, signature);
        } catch (CryptoException e) {
            throw new RuntimeException("RSA验签失败: " + e.getMessage(), e);
        }
    }
}

