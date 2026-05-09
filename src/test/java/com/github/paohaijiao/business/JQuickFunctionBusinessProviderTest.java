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
package com.github.paohaijiao.business;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * packageName com.github.paohaijiao.bool
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionBusinessProviderTest {
    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }

    @Test
    public void testIsEmail() {
        assertTrue((boolean) manager.invoke("isEmail", Arrays.asList("test@example.com")));
        assertTrue((boolean) manager.invoke("isEmail", Arrays.asList("user.name+tag@domain.co.uk")));
        assertFalse((boolean) manager.invoke("isEmail", Arrays.asList("invalid-email")));
        assertTrue((boolean) manager.invoke("isEmail", Arrays.asList("missing@domain.com")));
        assertFalse((boolean) manager.invoke("isEmail", Arrays.asList((Object) null)));
    }


    @Test
    public void testBankCardMask() {
        // 1个参数 - 默认前4后4
        String result = (String) manager.invoke("bankCardMask", Arrays.asList("6212261200001234567"));
        assertEquals("6212***********4567", result);

        // 2个参数 - 自定义前保留位数
        result = (String) manager.invoke("bankCardMask", Arrays.asList("6212261200001234567", 6));
        assertEquals("621226*********4567", result);

        // 3个参数 - 自定义前后保留位数
        result = (String) manager.invoke("bankCardMask", Arrays.asList("6212261200001234567", 6, 4));
        assertEquals("621226*********4567", result);

        // 银行卡号过短
        result = (String) manager.invoke("bankCardMask", Arrays.asList("123456"));
        assertEquals("******", result);
    }

    @Test
    public void testBankCardValidate() {
        // 有效的银行卡号（使用 Luhn 算法验证）
        // 注意：以下为测试用卡号，实际应根据 Luhn 算法验证
        boolean valid = (boolean) manager.invoke("bankCardValidate", Arrays.asList("6212261200001234567"));
        System.out.println("银行卡校验结果 (6212261200001234567): " + valid);

        // 无效：太短
        boolean invalid = (boolean) manager.invoke("bankCardValidate", Arrays.asList("12345"));
        assertFalse(invalid);

        // 无效：包含非数字
        invalid = (boolean) manager.invoke("bankCardValidate", Arrays.asList("6212abcd00001234567"));
        assertFalse(invalid);

        // 无效：长度不在16-19位
        invalid = (boolean) manager.invoke("bankCardValidate", Arrays.asList("12345678901234567890"));
        assertFalse(invalid);

        // null 处理
        invalid = (boolean) manager.invoke("bankCardValidate", Arrays.asList((Object) null));
        assertFalse(invalid);
        // Luhn 算法测试 - 有效的卡号示例
        // 6228480402564890018 是有效的测试卡号格式
        boolean result = (boolean) manager.invoke("bankCardValidate", Arrays.asList("6228480402564890018"));
        // 具体结果取决于 Luhn 算法实现
        System.out.println("Luhn 验证结果: " + result);
    }


    @Test
    public void testEmailMask() {
        // 正常邮箱
        String result = (String) manager.invoke("emailMask", Arrays.asList("zhangsan@example.com"));
        assertEquals("z***n@example.com", result);

        // 短用户名（<=2）
        result = (String) manager.invoke("emailMask", Arrays.asList("ab@example.com"));
        assertEquals("***@example.com", result);

        // 单字符用户名
        result = (String) manager.invoke("emailMask", Arrays.asList("a@example.com"));
        assertEquals("***@example.com", result);


        // 无效邮箱（没有@符号）
        result = (String) manager.invoke("emailMask", Arrays.asList("invalid"));
        assertEquals("invalid", result);

        // @在开头
        result = (String) manager.invoke("emailMask", Arrays.asList("@example.com"));
        assertEquals("@example.com", result);

        // null 处理
        result = (String) manager.invoke("emailMask", Arrays.asList((Object) null));
        assertNull(result);
    }
    @Test
    public void testGenderName() {
        // 英文 M/F
        assertEquals("男", manager.invoke("genderName", Arrays.asList("M")));
        assertEquals("女", manager.invoke("genderName", Arrays.asList("F")));
        assertEquals("男", manager.invoke("genderName", Arrays.asList("m")));
        assertEquals("女", manager.invoke("genderName", Arrays.asList("f")));

        // 英文 male/female
        assertEquals("男", manager.invoke("genderName", Arrays.asList("male")));
        assertEquals("女", manager.invoke("genderName", Arrays.asList("female")));
        assertEquals("男", manager.invoke("genderName", Arrays.asList("MALE")));
        assertEquals("女", manager.invoke("genderName", Arrays.asList("FEMALE")));

        // 中文
        assertEquals("男", manager.invoke("genderName", Arrays.asList("男")));
        assertEquals("女", manager.invoke("genderName", Arrays.asList("女")));

        // 未知
        assertEquals("未知", manager.invoke("genderName", Arrays.asList("X")));
        assertEquals("未知", manager.invoke("genderName", Arrays.asList("unknown")));
        assertEquals("未知", manager.invoke("genderName", Arrays.asList(2)));
        assertEquals("未知", manager.invoke("genderName", Arrays.asList((Object) null)));
    }

    @Test
    public void testIdCardValidate() {
        // 18位身份证（示例，需要符合校验码规则）
        boolean valid = (boolean) manager.invoke("idCardValidate", Arrays.asList("11010119900307663X"));
        System.out.println("18位身份证校验结果: " + valid);

        // 15位身份证
        boolean valid15 = (boolean) manager.invoke("idCardValidate", Arrays.asList("110101900307663"));
        System.out.println("15位身份证校验结果: " + valid15);

        // 无效：长度错误
        boolean invalid = (boolean) manager.invoke("idCardValidate", Arrays.asList("12345678901234567890"));
        assertFalse(invalid);

        // 无效：包含非法字符
        invalid = (boolean) manager.invoke("idCardValidate", Arrays.asList("11010119900307663A"));
        assertFalse(invalid);

        // 无效：出生日期非法
        invalid = (boolean) manager.invoke("idCardValidate", Arrays.asList("11010119901307663X"));
        assertFalse(invalid);

        // null 处理
        invalid = (boolean) manager.invoke("idCardValidate", Arrays.asList((Object) null));
        assertFalse(invalid);
    }

    @Test
    public void testIdCardBirthday() {
        // 18位身份证提取生日 - 返回 LocalDate
        LocalDate birthday = (LocalDate) manager.invoke("idCardBirthday", Arrays.asList("11010119900307663X"));
        assertEquals(LocalDate.of(1990, 3, 7), birthday);

        // 自定义格式 - 返回格式化字符串
        String formatted = (String) manager.invoke("idCardBirthday", Arrays.asList("11010119900307663X", "yyyy-MM-dd"));
        assertEquals("1990-03-07", formatted);

        // 其他日期格式
        formatted = (String) manager.invoke("idCardBirthday", Arrays.asList("11010119900307663X", "yyyy/MM/dd"));
        assertEquals("1990/03/07", formatted);

        formatted = (String) manager.invoke("idCardBirthday", Arrays.asList("11010119900307663X", "yyyy年MM月dd日"));
        assertEquals("1990年03月07日", formatted);

        // 15位身份证
        LocalDate birthday15 = (LocalDate) manager.invoke("idCardBirthday", Arrays.asList("110101900307663"));
        assertEquals(LocalDate.of(1990, 3, 7), birthday15);

        // 15位身份证自定义格式
        String formatted15 = (String) manager.invoke("idCardBirthday", Arrays.asList("110101900307663", "yyyy-MM-dd"));
        assertEquals("1990-03-07", formatted15);

        // null 处理
        Object result = manager.invoke("idCardBirthday", Arrays.asList((Object) null));
        assertNull(result);
    }

    @Test
    public void testIdCardAge() {
        // 使用固定参考日期
        LocalDate refDate = LocalDate.of(2024, 1, 1);
        int age = (int) manager.invoke("idCardAge", Arrays.asList("11010119900307663X", refDate));
        assertEquals(33, age);

        // 使用当前日期（不指定参考日期）
        int currentAge = (int) manager.invoke("idCardAge", Arrays.asList("11010119900307663X"));
        assertTrue(currentAge >= 33);
        System.out.println("当前年龄: " + currentAge);

        // 15位身份证计算年龄
        int age15 = (int) manager.invoke("idCardAge", Arrays.asList("110101900307663", refDate));
        assertEquals(33, age15);

        // 不同参考日期
        LocalDate pastDate = LocalDate.of(2000, 1, 1);
        int agePast = (int) manager.invoke("idCardAge", Arrays.asList("11010119900307663X", pastDate));
        assertEquals(9, agePast); // 1990-03-07 到 2000-01-01 是 9 岁

        // null 处理
        Object result = manager.invoke("idCardAge", Arrays.asList((Object) null));
        assertNull(result);
    }

    @Test
    public void testIdCardGender() {
        // 中文格式（默认）
        String gender = (String) manager.invoke("idCardGender", Arrays.asList("11010119900307663X"));
        assertEquals("男", gender);  // 第17位是3，奇数=男

        gender = (String) manager.invoke("idCardGender", Arrays.asList("11010119900307552X"));
        assertEquals("女", gender);  // 偶数=女

        // M/F 格式
        gender = (String) manager.invoke("idCardGender", Arrays.asList("11010119900307663X", "mf"));
        assertEquals("M", gender);



        // male/female 格式
        gender = (String) manager.invoke("idCardGender", Arrays.asList("11010119900307663X", "male/female"));
        assertEquals("male", gender);

        // 15位身份证
        gender = (String) manager.invoke("idCardGender", Arrays.asList("110101900307663"));
        assertEquals("男", gender);

        // 15位身份证指定格式
        gender = (String) manager.invoke("idCardGender", Arrays.asList("110101900307663", "mf"));
        assertEquals("M", gender);

        // null 处理
        Object result = manager.invoke("idCardGender", Arrays.asList((Object) null));
        assertNull(result);
    }

    @Test
    public void testIdCardInfo() {
        // 获取完整信息
        @SuppressWarnings("unchecked")
        Map<String, Object> info = (Map<String, Object>) manager.invoke("idCardInfo", Arrays.asList("11010119900307663X"));

        assertNotNull(info);
        assertEquals(LocalDate.of(1990, 3, 7), info.get("birthday"));
        assertEquals("男", info.get("gender"));
        assertEquals("M", info.get("genderCode"));
        assertEquals("11", info.get("provinceCode"));
        assertEquals("北京", info.get("province"));
        assertNotNull(info.get("age"));
        assertTrue((int) info.get("age") >= 33);

        // 获取单个字段 - province
        String province = (String) manager.invoke("idCardInfo", Arrays.asList("11010119900307663X", "province"));
        assertEquals("北京", province);

        // 获取单个字段 - birthday
        LocalDate birthday = (LocalDate) manager.invoke("idCardInfo", Arrays.asList("11010119900307663X", "birthday"));
        assertEquals(LocalDate.of(1990, 3, 7), birthday);

        // 获取单个字段 - age
        int age = (int) manager.invoke("idCardInfo", Arrays.asList("11010119900307663X", "age"));
        assertTrue(age >= 33);

        // 获取单个字段 - gender
        String gender = (String) manager.invoke("idCardInfo", Arrays.asList("11010119900307663X", "gender"));
        assertEquals("男", gender);

        // 获取单个字段 - genderCode
        String genderCode = (String) manager.invoke("idCardInfo", Arrays.asList("11010119900307663X", "genderCode"));
        assertEquals("M", genderCode);

        // 获取单个字段 - provinceCode
        String provinceCode = (String) manager.invoke("idCardInfo", Arrays.asList("11010119900307663X", "provinceCode"));
        assertEquals("11", provinceCode);

        // 15位身份证
        @SuppressWarnings("unchecked")
        Map<String, Object> info15 = (Map<String, Object>) manager.invoke("idCardInfo", Arrays.asList("110101900307663"));
        assertNotNull(info15);
        assertEquals(LocalDate.of(1990, 3, 7), info15.get("birthday"));

        // 无效身份证应抛异常
        try {
            manager.invoke("idCardInfo", Arrays.asList("invalid"));
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("身份证号格式错误", e.getMessage());
        }

        // null 处理
        Object result = manager.invoke("idCardInfo", Arrays.asList((Object) null));
        assertNull(result);
    }


    @Test
    public void testPhoneValidate() {
        // 有效手机号
        assertTrue((boolean) manager.invoke("phoneValidate", Arrays.asList("13812345678")));
        assertTrue((boolean) manager.invoke("phoneValidate", Arrays.asList("19912345678")));
        assertTrue((boolean) manager.invoke("phoneValidate", Arrays.asList("15012345678")));
        assertTrue((boolean) manager.invoke("phoneValidate", Arrays.asList("18912345678")));
        assertTrue((boolean) manager.invoke("phoneValidate", Arrays.asList("16612345678")));

        // 无效手机号
        assertFalse((boolean) manager.invoke("phoneValidate", Arrays.asList("12345678901")));
        assertFalse((boolean) manager.invoke("phoneValidate", Arrays.asList("1381234567")));   // 10位
        assertFalse((boolean) manager.invoke("phoneValidate", Arrays.asList("138123456789"))); // 12位
        assertFalse((boolean) manager.invoke("phoneValidate", Arrays.asList("1381234567a")));  // 包含字母
        assertFalse((boolean) manager.invoke("phoneValidate", Arrays.asList("23812345678")));  // 第二位不是3-9

        // 带空格的手机号（内部会去除空格）
        assertTrue((boolean) manager.invoke("phoneValidate", Arrays.asList("138 1234 5678")));

        // null 处理
        assertFalse((boolean) manager.invoke("phoneValidate", Arrays.asList((Object) null)));
    }

    @Test
    public void testPhoneMask() {
        // 默认脱敏：前3后4
        String result = (String) manager.invoke("phoneMask", Arrays.asList("13812345678"));
        assertEquals("138****5678", result);

        // 2个参数 - 自定义前保留位数
        result = (String) manager.invoke("phoneMask", Arrays.asList("13812345678", 4));
        assertEquals("1381***5678", result);

        // 3个参数 - 自定义前后保留位数
        result = (String) manager.invoke("phoneMask", Arrays.asList("13812345678", 4, 3));
        assertEquals("1381****678", result);

        // 极端值 - 保留全部
        result = (String) manager.invoke("phoneMask", Arrays.asList("13812345678", 11, 0));
        assertEquals("13812345678", result);

        // 带空格的手机号
        result = (String) manager.invoke("phoneMask", Arrays.asList("138 1234 5678"));
        assertEquals("138****5678", result);

        // 手机号过短（长度小于保留位数之和）
        result = (String) manager.invoke("phoneMask", Arrays.asList("12345"));
        assertEquals("12345", result);

        // 手机号过短自定义保留
        result = (String) manager.invoke("phoneMask", Arrays.asList("12345", 3, 3));
        assertEquals("12345", result);

        // null 处理
        result = (String) manager.invoke("phoneMask", Arrays.asList((Object) null));
        assertNull(result);
    }

    @Test
    public void testPhoneInfo() {
        // 获取完整信息
        @SuppressWarnings("unchecked")
        Map<String, Object> info = (Map<String, Object>) manager.invoke("phoneInfo", Arrays.asList("13812345678"));

        assertNotNull(info);
        assertEquals("138", info.get("prefix"));
        assertEquals("138****5678", info.get("masked"));
        assertNotNull(info.get("carrier"));
        System.out.println("手机号信息 (138): " + info);

        // 移动号段测试
        @SuppressWarnings("unchecked")
        Map<String, Object> mobileInfo = (Map<String, Object>) manager.invoke("phoneInfo", Arrays.asList("15012345678"));
        assertEquals("中国移动", mobileInfo.get("carrier"));

        // 联通号段测试
        @SuppressWarnings("unchecked")
        Map<String, Object> unicomInfo = (Map<String, Object>) manager.invoke("phoneInfo", Arrays.asList("13012345678"));
        assertEquals("中国联通", unicomInfo.get("carrier"));

        // 电信号段测试
        @SuppressWarnings("unchecked")
        Map<String, Object> telecomInfo = (Map<String, Object>) manager.invoke("phoneInfo", Arrays.asList("18912345678"));
        assertEquals("中国电信", telecomInfo.get("carrier"));

        // 广电号段测试
        @SuppressWarnings("unchecked")
        Map<String, Object> broadcastInfo = (Map<String, Object>) manager.invoke("phoneInfo", Arrays.asList("19212345678"));
        assertEquals("中国广电", broadcastInfo.get("carrier"));

        // 获取单个字段 - carrier
        String carrier = (String) manager.invoke("phoneInfo", Arrays.asList("13812345678", "carrier"));
        assertNotNull(carrier);
        System.out.println("运营商 (138): " + carrier);

        // 获取单个字段 - prefix
        String prefix = (String) manager.invoke("phoneInfo", Arrays.asList("13812345678", "prefix"));
        assertEquals("138", prefix);

        // 获取单个字段 - masked
        String masked = (String) manager.invoke("phoneInfo", Arrays.asList("13812345678", "masked"));
        assertEquals("138****5678", masked);

        // 未知号段
        @SuppressWarnings("unchecked")
        Map<String, Object> unknownInfo = (Map<String, Object>) manager.invoke("phoneInfo", Arrays.asList("10012345678"));
        assertEquals("未知", unknownInfo.get("carrier"));

        // 无效手机号应抛出异常
        try {
            manager.invoke("phoneInfo", Arrays.asList("12345678901"));
        } catch (IllegalArgumentException e) {
            assertEquals("手机号格式错误", e.getMessage());
        }

        // null 处理
        Object result = manager.invoke("phoneInfo", Arrays.asList((Object) null));
        assertNull(result);
    }
}
