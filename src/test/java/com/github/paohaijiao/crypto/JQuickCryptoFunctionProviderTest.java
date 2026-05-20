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
package com.github.paohaijiao.crypto;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * packageName com.github.paohaijiao.crypto
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/11
 */
public class JQuickCryptoFunctionProviderTest {

    private static JQuickMethodInvocationManager manager;

    @BeforeClass
    public static void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }


    @Test
    public void testAesEncryptDecrypt() {
        String originalData = "Hello, 中国! 123 @#$";
        // 生成密钥
        String base64Key = (String) manager.invoke("aesGenerateKey", Arrays.asList());
        assertNotNull(base64Key);
        // 加密
        String encrypted = (String) manager.invoke("aesEncrypt", Arrays.asList(originalData, base64Key));
        assertNotNull(encrypted);
        assertFalse(encrypted.equals(originalData));
        System.out.println("Encrypted: " + encrypted);
        // 解密
        String decrypted = (String) manager.invoke("aesDecrypt", Arrays.asList(encrypted, base64Key));
        assertEquals(originalData, decrypted);
    }


    @Test
    public void testRsaGenerateKeyPair() {
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("rsaGenerateKeyPair", Arrays.asList());
        assertNotNull(keyPair);
        assertTrue(keyPair.containsKey("publicKey"));
        assertTrue(keyPair.containsKey("privateKey"));
        assertNotNull(keyPair.get("publicKey"));
        assertNotNull(keyPair.get("privateKey"));
        System.out.println("RSA Public Key: " + keyPair.get("publicKey").substring(0, 50) + "...");
        System.out.println("RSA Private Key: " + keyPair.get("privateKey").substring(0, 50) + "...");
    }

    @Test
    public void testRsaEncryptDecrypt() {
        String originalData = "RSA加密测试数据";
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("rsaGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");
        String encrypted = (String) manager.invoke("rsaEncrypt", Arrays.asList(originalData,publicKey));
        assertNotNull(encrypted);
        System.out.println("RSA Encrypted: " + encrypted);
        String decrypted = (String) manager.invoke("rsaDecrypt", Arrays.asList(encrypted,privateKey));
        assertEquals(originalData, decrypted);
    }

    @Test
    public void testEccEncryptDecrypt() {
        String originalData = "ECC加密测试数据";
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");
        String encrypted = (String) manager.invoke("eccEncrypt", Arrays.asList(originalData, publicKey));
        assertNotNull(encrypted);
        System.out.println("ECC Encrypted: " + encrypted);
        String decrypted = (String) manager.invoke("eccDecrypt", Arrays.asList(encrypted, privateKey));
        assertEquals(originalData, decrypted);
    }

}
