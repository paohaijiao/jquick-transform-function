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
import com.github.paohaijiao.crypto.impl.EccCryptoService;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.paohaijiao.crypto.exception.CryptoException;
import com.github.paohaijiao.crypto.impl.EccCryptoService;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickEccDecryptFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Map<String, EccCryptoService> serviceCache = new ConcurrentHashMap<>();

    public JQuickEccDecryptFunctionProvider() {
        super("eccDecrypt", "[Crypto] ECC解密 - 用法: eccDecrypt(encryptedData, base64PrivateKey)");
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
            EccCryptoService service = serviceCache.computeIfAbsent(base64PrivateKey, k -> {
                try {
                    PrivateKey privateKey = loadPrivateKey(k);
                    return new EccCryptoService(privateKey);
                } catch (Exception e) {
                    throw new RuntimeException("创建ECC服务失败", e);
                }
            });
            return service.decrypt(encryptedData);
        } catch (CryptoException e) {
            throw new RuntimeException("ECC解密失败: " + e.getMessage(), e);
        }
    }

    private PrivateKey loadPrivateKey(String base64PrivateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return keyFactory.generatePrivate(spec);
    }
}