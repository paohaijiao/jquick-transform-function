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
    public void testAesGenerateKey() {
        String key = (String) manager.invoke("aesGenerateKey", Arrays.asList());
        assertNotNull(key);
        assertFalse(key.isEmpty());
        System.out.println("Generated AES Key: " + key);
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
    public void testAesEncryptWithoutKey() {
        String originalData = "Test data without key";
        // 不传密钥，自动生成
        String encrypted = (String) manager.invoke("aesEncrypt", Arrays.asList(originalData));
        assertNotNull(encrypted);
        System.out.println("Auto-key encrypted: " + encrypted);
    }

    @Test(expected = RuntimeException.class)
    public void testAesDecryptWithoutKey() {
        manager.invoke("aesDecrypt", Arrays.asList("encryptedData"));
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
        // 生成密钥对
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("rsaGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");

        // 加密
        String encrypted = (String) manager.invoke("rsaEncrypt", Arrays.asList(originalData, publicKey));
        assertNotNull(encrypted);
        System.out.println("RSA Encrypted: " + encrypted);

        // 解密
        String decrypted = (String) manager.invoke("rsaDecrypt", Arrays.asList(encrypted, privateKey));
        assertEquals(originalData, decrypted);
    }

    @Test
    public void testRsaSignAndVerify() {
        String data = "需要签名的数据";

        // 生成密钥对
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("rsaGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");

        // 签名
        String signature = (String) manager.invoke("rsaSign", Arrays.asList(data, privateKey));
        assertNotNull(signature);
        System.out.println("RSA Signature: " + signature);

        // 验签
        boolean verified = (boolean) manager.invoke("rsaVerify", Arrays.asList(data, signature, publicKey));
        assertTrue(verified);
    }

    @Test
    public void testRsaVerifyWithWrongData() {
        String data = "原始数据";
        String wrongData = "篡改的数据";

        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("rsaGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");

        String signature = (String) manager.invoke("rsaSign", Arrays.asList(data, privateKey));

        // 使用错误数据验签应该失败
        boolean verified = (boolean) manager.invoke("rsaVerify", Arrays.asList(wrongData, signature, publicKey));
        assertFalse(verified);
    }

    @Test(expected = RuntimeException.class)
    public void testRsaEncryptWithoutPublicKey() {
        manager.invoke("rsaEncrypt", Arrays.asList("data"));
    }


    @Test
    public void testEccGenerateKeyPair() {
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        assertNotNull(keyPair);
        assertTrue(keyPair.containsKey("publicKey"));
        assertTrue(keyPair.containsKey("privateKey"));
        assertNotNull(keyPair.get("publicKey"));
        assertNotNull(keyPair.get("privateKey"));
        System.out.println("ECC Public Key: " + keyPair.get("publicKey").substring(0, 50) + "...");
        System.out.println("ECC Private Key: " + keyPair.get("privateKey").substring(0, 50) + "...");
    }

    @Test
    public void testEccEncryptDecrypt() {
        String originalData = "ECC加密测试数据 🔐";

        // 生成密钥对
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");

        // 加密
        String encrypted = (String) manager.invoke("eccEncrypt", Arrays.asList(originalData, publicKey));
        assertNotNull(encrypted);
        System.out.println("ECC Encrypted: " + encrypted);

        // 解密
        String decrypted = (String) manager.invoke("eccDecrypt", Arrays.asList(encrypted, privateKey));
        assertEquals(originalData, decrypted);
    }

    @Test
    public void testEccSignAndVerify() {
        String data = "ECC签名测试数据";

        // 生成密钥对
        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");

        // 签名
        String signature = (String) manager.invoke("eccSign", Arrays.asList(data, privateKey));
        assertNotNull(signature);
        System.out.println("ECC Signature: " + signature);

        // 验签
        boolean verified = (boolean) manager.invoke("eccVerify", Arrays.asList(data, signature, publicKey));
        assertTrue(verified);
    }

    @Test
    public void testEccVerifyWithWrongSignature() {
        String data = "原始数据";

        @SuppressWarnings("unchecked")
        Map<String, String> keyPair = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        String publicKey = keyPair.get("publicKey");

        // 使用错误的签名验签应该失败
        boolean verified = (boolean) manager.invoke("eccVerify", Arrays.asList(data, "wrongSignature", publicKey));
        assertFalse(verified);
    }


    @Test
    public void testAllAlgorithmsEncryptDecrypt() {
        String testData = "跨算法测试数据 Cross-algorithm test data 1234567890 !@#$%^&*()";

        // AES
        String aesKey = (String) manager.invoke("aesGenerateKey", Arrays.asList());
        String aesEncrypted = (String) manager.invoke("aesEncrypt", Arrays.asList(testData, aesKey));
        String aesDecrypted = (String) manager.invoke("aesDecrypt", Arrays.asList(aesEncrypted, aesKey));
        assertEquals(testData, aesDecrypted);

        // RSA
        @SuppressWarnings("unchecked")
        Map<String, String> rsaKeys = (Map<String, String>) manager.invoke("rsaGenerateKeyPair", Arrays.asList());
        String rsaEncrypted = (String) manager.invoke("rsaEncrypt", Arrays.asList(testData, rsaKeys.get("publicKey")));
        String rsaDecrypted = (String) manager.invoke("rsaDecrypt", Arrays.asList(rsaEncrypted, rsaKeys.get("privateKey")));
        assertEquals(testData, rsaDecrypted);

        // ECC
        @SuppressWarnings("unchecked")
        Map<String, String> eccKeys = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        String eccEncrypted = (String) manager.invoke("eccEncrypt", Arrays.asList(testData, eccKeys.get("publicKey")));
        String eccDecrypted = (String) manager.invoke("eccDecrypt", Arrays.asList(eccEncrypted, eccKeys.get("privateKey")));
        assertEquals(testData, eccDecrypted);

        System.out.println(" All algorithms passed encryption/decryption test!");
    }

    @Test
    public void testAllAlgorithmsSignVerify() {
        String testData = "签名验证测试数据 Signature verification test";

        // RSA签名验签
        @SuppressWarnings("unchecked")
        Map<String, String> rsaKeys = (Map<String, String>) manager.invoke("rsaGenerateKeyPair", Arrays.asList());
        String rsaSignature = (String) manager.invoke("rsaSign", Arrays.asList(testData, rsaKeys.get("privateKey")));
        boolean rsaVerified = (boolean) manager.invoke("rsaVerify", Arrays.asList(testData, rsaSignature, rsaKeys.get("publicKey")));
        assertTrue(rsaVerified);

        // ECC签名验签
        @SuppressWarnings("unchecked")
        Map<String, String> eccKeys = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        String eccSignature = (String) manager.invoke("eccSign", Arrays.asList(testData, eccKeys.get("privateKey")));
        boolean eccVerified = (boolean) manager.invoke("eccVerify", Arrays.asList(testData, eccSignature, eccKeys.get("publicKey")));
        assertTrue(eccVerified);

        System.out.println(" All algorithms passed sign/verify test!");
    }

    @Test
    public void testChineseCharacters() {
        String chineseData = "你好，世界！这是一个包含中文的加密测试。";

        // AES测试
        String aesKey = (String) manager.invoke("aesGenerateKey", Arrays.asList());
        String aesEncrypted = (String) manager.invoke("aesEncrypt", Arrays.asList(chineseData, aesKey));
        String aesDecrypted = (String) manager.invoke("aesDecrypt", Arrays.asList(aesEncrypted, aesKey));
        assertEquals(chineseData, aesDecrypted);

        // ECC测试（支持中文）
        @SuppressWarnings("unchecked")
        Map<String, String> eccKeys = (Map<String, String>) manager.invoke("eccGenerateKeyPair", Arrays.asList());
        String eccEncrypted = (String) manager.invoke("eccEncrypt", Arrays.asList(chineseData, eccKeys.get("publicKey")));
        String eccDecrypted = (String) manager.invoke("eccDecrypt", Arrays.asList(eccEncrypted, eccKeys.get("privateKey")));
        assertEquals(chineseData, eccDecrypted);

        System.out.println(" Chinese characters test passed!");
    }

    @Test
    public void testEmptyAndNullData() {
        String emptyData = "";

        // AES空数据测试
        String aesKey = (String) manager.invoke("aesGenerateKey", Arrays.asList());
        String aesEncrypted = (String) manager.invoke("aesEncrypt", Arrays.asList(emptyData, aesKey));
        String aesDecrypted = (String) manager.invoke("aesDecrypt", Arrays.asList(aesEncrypted, aesKey));
        assertEquals(emptyData, aesDecrypted);

        // 测试null数据应该抛出异常
        try {
            manager.invoke("aesEncrypt", Arrays.asList((Object) null));
            fail("Should throw exception for null data");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("不能为null"));
        }
    }

    @Test
    public void testLongData() {
        // 生成长文本数据
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("这是测试文本数据第").append(i).append("行。");
        }
        String longData = sb.toString();

        // AES测试长文本
        String aesKey = (String) manager.invoke("aesGenerateKey", Arrays.asList());
        String aesEncrypted = (String) manager.invoke("aesEncrypt", Arrays.asList(longData, aesKey));
        String aesDecrypted = (String) manager.invoke("aesDecrypt", Arrays.asList(aesEncrypted, aesKey));
        assertEquals(longData, aesDecrypted);

        System.out.println(" Long data test passed! Data length: " + longData.length());
    }
}
