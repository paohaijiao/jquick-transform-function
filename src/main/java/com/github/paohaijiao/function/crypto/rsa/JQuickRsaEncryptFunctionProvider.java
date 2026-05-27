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
package com.github.paohaijiao.function.crypto.rsa;

/**
 * packageName com.github.paohaijiao.function.crypto
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/11
 */

import com.github.paohaijiao.crypto.impl.RsaCryptoService;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RSA加密方法提供者 - 修复版
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRsaEncryptFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Map<String, RsaCryptoService> serviceCache = new ConcurrentHashMap<>();

    public JQuickRsaEncryptFunctionProvider() {
        super("rsaEncrypt", "[Crypto] RSA加密 - 用法: rsaEncrypt(data, base64PublicKey)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String data = asString(args.get(0));
        String publicKey = asString(args.get(1));
        if (publicKey == null || data == null) {
            throw new IllegalArgumentException("加密数据和公钥不能为null");
        }
        try {
            RsaCryptoService service = new RsaCryptoService(loadPublicKey(publicKey));
            return service.encrypt(data);
        } catch (Exception e) {
            throw new RuntimeException("RSA加密失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从Base64字符串加载公钥
     */
    private PublicKey loadPublicKey(String base64PublicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }
}