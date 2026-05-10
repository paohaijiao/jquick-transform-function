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

/**
 * RSA生成密钥对方法提供者
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRsaGenerateKeyPairFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickRsaGenerateKeyPairFunctionProvider() {
        super("rsaGenerateKeyPair", "[Crypto] RSA生成密钥对 - 用法: rsaGenerateKeyPair() 返回包含公钥和私钥的Map");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 0);
        try {
            RsaCryptoService service = new RsaCryptoService();
            Map<String, String> keyPair = new java.util.HashMap<>();
            keyPair.put("publicKey", service.getBase64PublicKey());
            keyPair.put("privateKey", service.getBase64PrivateKey());
            return keyPair;
        } catch (CryptoException e) {
            throw new RuntimeException("生成RSA密钥对失败: " + e.getMessage(), e);
        }
    }
}
