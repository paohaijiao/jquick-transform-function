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
package com.github.paohaijiao.string;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * String 字符串函数提供者测试类
 * 测试范围：字符串处理、编码解码、正则匹配、哈希、字符串判断等
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionStringProviderTest {

    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }

    @Test
    public void testLeft() {
        String result = (String) manager.invoke("left", Arrays.asList("hello world", 5));
        assertEquals("hello", result);

        result = (String) manager.invoke("left", Arrays.asList("hi", 10));
        assertEquals("hi", result);

        result = (String) manager.invoke("left", Arrays.asList("test", 0));
        assertEquals("", result);

        result = (String) manager.invoke("left", Arrays.asList(null, 5));
        assertNull(result);
    }

    @Test
    public void testMid() {
        // 2个参数 - 从 start 到结尾
        String result = (String) manager.invoke("mid", Arrays.asList("hello world", 6));
        assertEquals("world", result);

        // 3个参数 - 指定长度
        result = (String) manager.invoke("mid", Arrays.asList("hello world", 6, 3));
        assertEquals("wor", result);

        // start 为负数
        result = (String) manager.invoke("mid", Arrays.asList("test", -1, 3));
        assertEquals("tes", result);

        // start 超出长度
        result = (String) manager.invoke("mid", Arrays.asList("hi", 5));
        assertEquals("", result);

        result = (String) manager.invoke("mid", Arrays.asList(null, 1));
        assertNull(result);
    }

    @Test
    public void testlength() {
        int result = (int) manager.invoke("length", Arrays.asList("hello"));
        assertEquals(5, result);

        result = (int) manager.invoke("length", Arrays.asList(""));
        assertEquals(0, result);

        result = (int) manager.invoke("length", Arrays.asList((Object) null));
        assertEquals(0, result);
    }

    @Test
    public void testSubstring() {
        String result = (String) manager.invoke("substring", Arrays.asList("hello world", 0, 5));
        assertEquals("hello", result);

        result = (String) manager.invoke("substring", Arrays.asList("hello world", 6));
        assertEquals("world", result);
    }


    @Test
    public void testLeftPad() {
        // 2个参数 - 默认空格
        String result = (String) manager.invoke("leftPad", Arrays.asList("hello", 10));
        assertEquals("     hello", result);

        // 3个参数 - 指定填充字符
        result = (String) manager.invoke("leftPad", Arrays.asList("hello", 10, "*"));
        assertEquals("*****hello", result);

        // 不需要填充
        result = (String) manager.invoke("leftPad", Arrays.asList("hello", 3));
        assertEquals("hello", result);

        result = (String) manager.invoke("leftPad", Arrays.asList(null, 5));
        assertEquals("     ", result);
    }

    @Test
    public void testRightPad() {
        String result = (String) manager.invoke("rightPad", Arrays.asList("hello", 10));
        assertEquals("hello     ", result);

        result = (String) manager.invoke("rightPad", Arrays.asList("hello", 10, "-"));
        assertEquals("hello-----", result);
    }

    @Test
    public void testCenterPad() {
        String result = (String) manager.invoke("centerPad", Arrays.asList("hello", 11));
        assertEquals("   hello   ", result);

        result = (String) manager.invoke("centerPad", Arrays.asList("hello", 10));
        assertEquals("  hello   ", result);

        result = (String) manager.invoke("centerPad", Arrays.asList("hello", 11, "*"));
        assertEquals("***hello***", result);
    }


    @Test
    public void testRemoveStart() {
        String result = (String) manager.invoke("removeStart", Arrays.asList("prefix_test", "prefix_"));
        assertEquals("test", result);

        result = (String) manager.invoke("removeStart", Arrays.asList("test", "pre"));
        assertEquals("test", result);

        // 忽略大小写
        result = (String) manager.invoke("removeStart", Arrays.asList("PREFIX_test", "prefix_", true));
        assertEquals("test", result);
    }

    @Test
    public void testRemoveEnd() {
        String result = (String) manager.invoke("removeEnd", Arrays.asList("test_suffix", "_suffix"));
        assertEquals("test", result);

        // 忽略大小写
        result = (String) manager.invoke("removeEnd", Arrays.asList("test_SUFFIX", "_suffix", true));
        assertEquals("test", result);
    }

    @Test
    public void testRemoveWhitespace() {
        String result = (String) manager.invoke("removeWhitespace", Arrays.asList("hello world"));
        assertEquals("helloworld", result);

        result = (String) manager.invoke("removeWhitespace", Arrays.asList("  a  b  c  "));
        assertEquals("abc", result);

    }

    @Test
    public void testRemoveDuplicates() {
        String result = (String) manager.invoke("removeDuplicates", Arrays.asList("hello"));
        assertEquals("helo", result);

        result = (String) manager.invoke("removeDuplicates", Arrays.asList("aabbcc"));
        assertEquals("abc", result);

        result = (String) manager.invoke("removeDuplicates", Arrays.asList("aaabbbccc"));
        assertEquals("abc", result);
    }

    @Test
    public void testAbbreviate() {
        // 2个参数
        String result = (String) manager.invoke("abbreviate", Arrays.asList("hello world", 8));
        assertEquals("hello...", result);

        // 3个参数 - 自定义省略号
        result = (String) manager.invoke("abbreviate", Arrays.asList("hello world", 8, "***"));
        assertEquals("hello***", result);

        // 字符串长度小于 maxWidth
        result = (String) manager.invoke("abbreviate", Arrays.asList("hi", 10));
        assertEquals("hi", result);
    }


    @Test
    public void testCapitalize() {
        String result = (String) manager.invoke("capitalize", Arrays.asList("hello"));
        assertEquals("Hello", result);

        result = (String) manager.invoke("capitalize", Arrays.asList("Hello"));
        assertEquals("Hello", result);

        result = (String) manager.invoke("capitalize", Arrays.asList(""));
        assertEquals("", result);

    }

    @Test
    public void testToUpper() {
        String result = (String) manager.invoke("toUpper", Arrays.asList("hello"));
        assertEquals("HELLO", result);
    }

    @Test
    public void testToLower() {
        String result = (String) manager.invoke("toLower", Arrays.asList("HELLO"));
        assertEquals("hello", result);
    }



    @Test
    public void testIsBlank() {
        assertTrue((boolean) manager.invoke("isBlank", Arrays.asList((Object) null)));
        assertTrue((boolean) manager.invoke("isBlank", Arrays.asList("")));
        assertTrue((boolean) manager.invoke("isBlank", Arrays.asList("   ")));
        assertFalse((boolean) manager.invoke("isBlank", Arrays.asList("hello")));
    }

    @Test
    public void testIsEmpty() {
        assertTrue((boolean) manager.invoke("isEmpty", Arrays.asList("")));
        assertTrue((boolean) manager.invoke("isEmpty", Arrays.asList((Object) null)));
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList("  ")));
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList("hello")));
    }

    @Test
    public void testIsAlpha() {
        assertTrue((boolean) manager.invoke("isAlpha", Arrays.asList("hello")));
        assertFalse((boolean) manager.invoke("isAlpha", Arrays.asList("hello123")));
        assertFalse((boolean) manager.invoke("isAlpha", Arrays.asList("")));
    }

    @Test
    public void testIsAlphaNumeric() {
        assertTrue((boolean) manager.invoke("isAlphaNumeric", Arrays.asList("hello123")));
        assertTrue((boolean) manager.invoke("isAlphaNumeric", Arrays.asList("abc123")));
        assertFalse((boolean) manager.invoke("isAlphaNumeric", Arrays.asList("hello world")));
        assertFalse((boolean) manager.invoke("isAlphaNumeric", Arrays.asList("")));
    }

    @Test
    public void testIsNumeric() {
        assertTrue((boolean) manager.invoke("isNumeric", Arrays.asList("12345")));
        assertFalse((boolean) manager.invoke("isNumeric", Arrays.asList("123a")));
        assertFalse((boolean) manager.invoke("isNumeric", Arrays.asList("")));
    }

    @Test
    public void testIsString() {
        assertTrue((boolean) manager.invoke("isString", Arrays.asList("hello")));
        assertFalse((boolean) manager.invoke("isString", Arrays.asList(123)));
    }



    @Test
    public void testContains() {
        boolean result = (boolean) manager.invoke("contains", Arrays.asList("hello world", "world"));
        assertTrue(result);

        result = (boolean) manager.invoke("contains", Arrays.asList("hello", "world"));
        assertFalse(result);
    }

    @Test
    public void testEqualsIgnoreCase() {
        assertTrue((boolean) manager.invoke("equalsIgnoreCase", Arrays.asList("hello", "HELLO")));
        assertFalse((boolean) manager.invoke("equalsIgnoreCase", Arrays.asList("hello", "world")));
        assertTrue((boolean) manager.invoke("equalsIgnoreCase", Arrays.asList(null, null)));
        assertFalse((boolean) manager.invoke("equalsIgnoreCase", Arrays.asList(null, "hello")));
    }

    @Test
    public void testEqualsAny() {
        boolean result = (boolean) manager.invoke("equalsAny", Arrays.asList("apple", "apple", "banana", "cherry"));
        assertTrue(result);

        result = (boolean) manager.invoke("equalsAny", Arrays.asList("apple", "banana", "cherry"));
        assertFalse(result);

        result = (boolean) manager.invoke("equalsAny", Arrays.asList(null, null));
        assertFalse(result);
    }

    @Test
    public void testCompareTo() {
        int result = (int) manager.invoke("compareTo", Arrays.asList("apple", "banana"));
        assertTrue(result < 0);

        result = (int) manager.invoke("compareTo", Arrays.asList("banana", "apple"));
        assertTrue(result > 0);

        result = (int) manager.invoke("compareTo", Arrays.asList("hello", "hello"));
        assertEquals(0, result);

        // 忽略大小写
        result = (int) manager.invoke("compareTo", Arrays.asList("APPLE", "apple", true));
        assertEquals(0, result);
    }



    @Test
    public void testIndexOf() {
        int result = (int) manager.invoke("indexOf", Arrays.asList("hello world", "world"));
        assertEquals(6, result);

        result = (int) manager.invoke("indexOf", Arrays.asList("hello hello", "hello", 1));
        assertEquals(6, result);

        result = (int) manager.invoke("indexOf", Arrays.asList("hello", "xxx"));
        assertEquals(-1, result);
    }

    @Test
    public void testLastIndexOf1() {
        int result = (int) manager.invoke("lastIndexOf", Arrays.asList("hello hello", "hello"));
        assertEquals(6, result);
    }



    @Test
    public void testCountChar() {
        int result = (int) manager.invoke("countChar", Arrays.asList("hello", "l"));
        assertEquals(2, result);

        result = (int) manager.invoke("countChar", Arrays.asList("Hello", "h", true));
        assertEquals(1, result);
    }

    @Test
    public void testCountMatches() {
        int result = (int) manager.invoke("countMatches", Arrays.asList("abcabcabc", "abc"));
        assertEquals(3, result);

        result = (int) manager.invoke("countMatches", Arrays.asList("HelloHello", "hello", true));
        assertEquals(2, result);
    }



    @Test
    public void testReplace() {
        String result = (String) manager.invoke("replace", Arrays.asList("hello world", "world", "java"));
        assertEquals("hello java", result);

        result = (String) manager.invoke("replace", Arrays.asList("abc abc", "a", "x"));
        assertEquals("xbc xbc", result);
    }

    @Test
    public void testReplaceAll() {
        String result = (String) manager.invoke("replaceAll", Arrays.asList("abc123def456", "\\d+", "#"));
        assertEquals("abc#def#", result);
    }



    @Test
    public void testSplit() {
        String[] result = (String[]) manager.invoke("split", Arrays.asList("a,b,c", ","));
        assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    @Test
    public void testConcat() {
        String result = (String) manager.invoke("concat", Arrays.asList("Hello", " ", "World", "!"));
        assertEquals("Hello World!", result);

        result = (String) manager.invoke("concat", Arrays.asList());
        assertEquals("", result);
    }

    @Test
    public void testJoin() {
        String result = (String) manager.invoke("join", Arrays.asList(Arrays.asList("a", "b", "c"), "-"));
        assertEquals("a-b-c", result);
    }



    @Test
    public void testBase64Encode() {
        String result = (String) manager.invoke("base64Encode", Arrays.asList("hello"));
        assertEquals("aGVsbG8=", result);

        result = (String) manager.invoke("base64Encode", Arrays.asList(null));
        assertNull(result);
    }

    @Test
    public void testBase64Decode() {
        String result = (String) manager.invoke("base64Decode", Arrays.asList("aGVsbG8="));
        assertEquals("hello", result);
    }

    @Test
    public void testEncodeUrl() {
        String result = (String) manager.invoke("encodeUrl", Arrays.asList("hello world"));
        assertTrue(result.contains("hello+world") || result.contains("hello%20world"));
    }

    @Test
    public void testDecodeUrl() {
        String result = (String) manager.invoke("decodeUrl", Arrays.asList("hello%20world"));
        assertEquals("hello world", result);
    }



    @Test
    public void testEscapeHtml() {
        String result = (String) manager.invoke("escapeHtml", Arrays.asList("<script>alert('xss')</script>"));
        assertTrue(result.contains("&lt;"));
        assertTrue(result.contains("&gt;"));
    }

    @Test
    public void testEscapeRegex() {
        String result = (String) manager.invoke("escapeRegex", Arrays.asList(".*[a-z]+"));
        assertEquals("\\Q.*[a-z]+\\E", result);
    }



    @Test
    public void testMd5() {
        String result = (String) manager.invoke("md5", Arrays.asList("hello"));
        assertEquals("5d41402abc4b2a76b9719d911017c592", result);

        String result2 = (String) manager.invoke("md5", Arrays.asList("hello"));
        assertEquals(result, result2);

    }

    @Test
    public void testSha2() {
        String result = (String) manager.invoke("sha1", Arrays.asList("hello"));
        assertEquals("aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d", result);
    }



    @Test
    public void testMask() {
        String result = (String) manager.invoke("mask", Arrays.asList("1234567890", 2, 6));
        assertEquals("12****7890", result);

        result = (String) manager.invoke("mask", Arrays.asList("abcdefg", 0, 3, "#"));
        assertEquals("###defg", result);
    }

    @Test
    public void testMaskEmail() {
        String result = (String) manager.invoke("maskEmail", Arrays.asList("zhangsan@example.com"));
        assertTrue(result.matches("z\\*\\*\\*n@example.com") || result.matches("z\\*\\*\\*n@example.com"));
    }



    @Test
    public void testLevenshtein() {
        int result = (int) manager.invoke("levenshtein", Arrays.asList("kitten", "sitting"));
        assertEquals(3, result);

        result = (int) manager.invoke("levenshtein", Arrays.asList("hello", "hello"));
        assertEquals(0, result);
    }




    @Test
    public void testMatches() {
        boolean result = (boolean) manager.invoke("matches", Arrays.asList("12345", "\\d+"));
        assertTrue(result);

        result = (boolean) manager.invoke("matches", Arrays.asList("abc", "\\d+"));
        assertFalse(result);
    }

    @Test
    public void testFormat() {
        String result = (String) manager.invoke("format", Arrays.asList("Hello %s", "World"));
        assertEquals("Hello World", result);

        result = (String) manager.invoke("format", Arrays.asList("%d + %d = %d", 1, 2, 3));
        assertEquals("1 + 2 = 3", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLeftWithNoArgs() {
        manager.invoke("left", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsWithNoArgs() {
        manager.invoke("contains", Arrays.asList());
    }

    @Test
    public void testEdgeCases1() {
        // 空字符串处理
        assertEquals("", manager.invoke("left", Arrays.asList("", 5)));
        assertEquals("", manager.invoke("right", Arrays.asList("", 5)));

        // 负数长度
        assertEquals("", manager.invoke("left", Arrays.asList("test", -1)));
        assertEquals("", manager.invoke("right", Arrays.asList("test", -1)));

        // 超出范围
        assertEquals("test", manager.invoke("left", Arrays.asList("test", 100)));
        assertEquals("test", manager.invoke("right", Arrays.asList("test", 100)));
    }


    @Test
    public void testStringProcessingScenario() {
        // 字符串脱敏场景
        String phone = "13812345678";
        String maskedPhone = (String) manager.invoke("mask", Arrays.asList(phone, 3, 7));
        assertEquals("138****5678", maskedPhone);

        // 邮箱脱敏
        String email = "zhangsan@example.com";
        String maskedEmail = (String) manager.invoke("maskEmail", Arrays.asList(email));
        System.out.println("脱敏邮箱: " + maskedEmail);

        // 编码解码
        String original = "Hello 世界";
        String encoded = (String) manager.invoke("base64Encode", Arrays.asList(original));
        String decoded = (String) manager.invoke("base64Decode", Arrays.asList(encoded));
        assertEquals(original, decoded);
    }

    @Test
    public void testValidationScenario() {
        // 数据验证场景
        assertTrue((boolean) manager.invoke("isEmail", Arrays.asList("test@example.com")));
        assertFalse((boolean) manager.invoke("isEmail", Arrays.asList("invalid")));

        assertTrue((boolean) manager.invoke("isPhone", Arrays.asList("13812345678")));
        assertFalse((boolean) manager.invoke("isPhone", Arrays.asList("12345")));
    }

    @Test
    public void testStringSimilarityScenario() {
        // 字符串相似度计算
        String str1 = "kitten";
        String str2 = "sitting";

        int distance = (int) manager.invoke("levenshtein", Arrays.asList(str1, str2));
        System.out.println("编辑距离: " + distance);

        // 相似度 = 1 - distance / max(length)
        double similarity = 1 - distance / (double) Math.max(str1.length(), str2.length());
        System.out.println("相似度: " + similarity);
    }
    @Test
    public void testRepeatChar() {
        String result = (String) manager.invoke("repeatChar", Arrays.asList("*", 5));
        assertEquals("*****", result);

        result = (String) manager.invoke("repeatChar", Arrays.asList("-", 3));
        assertEquals("---", result);

        result = (String) manager.invoke("repeatChar", Arrays.asList("a", 0));
        assertEquals("", result);

        result = (String) manager.invoke("repeatChar", Arrays.asList("abc", 2));
        assertEquals("aa", result);
    }

    @Test
    public void testRepeat() {
        // 2个参数
        String result = (String) manager.invoke("repeat", Arrays.asList("abc", 3));
        assertEquals("abcabcabc", result);

        // 3个参数 - 带分隔符
        result = (String) manager.invoke("repeat", Arrays.asList("abc", 3, "-"));
        assertEquals("abc-abc-abc", result);

        result = (String) manager.invoke("repeat", Arrays.asList("x", 5, ","));
        assertEquals("x,x,x,x,x", result);

        // count <= 0
        result = (String) manager.invoke("repeat", Arrays.asList("abc", 0));
        assertEquals("", result);
    }


    @Test
    public void testReverse() {
        String result = (String) manager.invoke("reverse", Arrays.asList("hello"));
        assertEquals("olleh", result);

        result = (String) manager.invoke("reverse", Arrays.asList(""));
        assertEquals("", result);

    }

    @Test
    public void testSubstringBefore() {
        String result = (String) manager.invoke("substringBefore", Arrays.asList("hello-world", "-"));
        assertEquals("hello", result);

        result = (String) manager.invoke("substringBefore", Arrays.asList("hello", "-"));
        assertEquals("hello", result);

        result = (String) manager.invoke("substringBefore", Arrays.asList(null, "-"));
        assertNull(result);
    }

    @Test
    public void testSubstringAfter() {
        String result = (String) manager.invoke("substringAfter", Arrays.asList("hello-world", "-"));
        assertEquals("world", result);

        result = (String) manager.invoke("substringAfter", Arrays.asList("hello", "-"));
        assertEquals("", result);
    }

    @Test
    public void testSubstringBetween() {
        String result = (String) manager.invoke("substringBetween", Arrays.asList("hello [world] today", "[", "]"));
        assertEquals("world", result);

        result = (String) manager.invoke("substringBetween", Arrays.asList("hello world", "[", "]"));
        assertNull(result);
    }


    @Test
    public void testSwapCase() {
        String result = (String) manager.invoke("swapCase", Arrays.asList("Hello World"));
        assertEquals("hELLO wORLD", result);

        result = (String) manager.invoke("swapCase", Arrays.asList("Java"));
        assertEquals("jAVA", result);

        result = (String) manager.invoke("swapCase", Arrays.asList("123ABC"));
        assertEquals("123abc", result);
    }

    @Test
    public void testUncapitalize() {
        String result = (String) manager.invoke("uncapitalize", Arrays.asList("Hello"));
        assertEquals("hello", result);

        result = (String) manager.invoke("uncapitalize", Arrays.asList("JAVA"));
        assertEquals("jAVA", result);

        result = (String) manager.invoke("uncapitalize", Arrays.asList(""));
        assertEquals("", result);
    }

    @Test
    public void testToCamelCase() {
        // 默认首字母小写
        String result = (String) manager.invoke("toCamelCase", Arrays.asList("hello-world"));
        assertEquals("helloWorld", result);

        result = (String) manager.invoke("toCamelCase", Arrays.asList("user_name"));
        assertEquals("userName", result);

        result = (String) manager.invoke("toCamelCase", Arrays.asList("HELLO WORLD"));
        assertEquals("helloWorld", result);

        // 首字母大写
        result = (String) manager.invoke("toCamelCase", Arrays.asList("hello-world", true));
        assertEquals("HelloWorld", result);
    }

    @Test
    public void testToSnakeCase() {
        String result = (String) manager.invoke("toSnakeCase", Arrays.asList("helloWorld"));
        assertEquals("hello_world", result);

        result = (String) manager.invoke("toSnakeCase", Arrays.asList("userName"));
        assertEquals("user_name", result);

        result = (String) manager.invoke("toSnakeCase", Arrays.asList("HelloWorld"));
        assertEquals("hello_world", result);

        result = (String) manager.invoke("toSnakeCase", Arrays.asList("hello-world"));
        assertEquals("hello_world", result);
    }



    @Test
    public void testSplitByLength() {
        @SuppressWarnings("unchecked")
        List<String> result = (List<String>) manager.invoke("splitByLength", Arrays.asList("abcdefghi", 3));
        assertEquals(Arrays.asList("abc", "def", "ghi"), result);

        result = (List<String>) manager.invoke("splitByLength", Arrays.asList("12", 5));
        assertEquals(Arrays.asList("12"), result);

        result = (List<String>) manager.invoke("splitByLength", Arrays.asList(null, 3));
        assertEquals(0, result.size());
    }

    @Test
    public void testTokenize() {
        @SuppressWarnings("unchecked")
        List<String> result = (List<String>) manager.invoke("tokenize", Arrays.asList("hello,world;test", ",;"));
        assertEquals(Arrays.asList("hello", "world", "test"), result);

        result = (List<String>) manager.invoke("tokenize", Arrays.asList("a b c d", " "));
        assertEquals(Arrays.asList("a", "b", "c", "d"), result);

        result = (List<String>) manager.invoke("tokenize", Arrays.asList(null, ","));
        assertEquals(0, result.size());
    }

    @Test
    public void testUniqueChars() {
        String result = (String) manager.invoke("uniqueChars", Arrays.asList("hello"));
        assertEquals("helo", result);

        result = (String) manager.invoke("uniqueChars", Arrays.asList("abacabad"));
        assertEquals("abcd", result);

        result = (String) manager.invoke("uniqueChars", Arrays.asList(null));
        assertNull(result);
    }



    @Test
    public void testWordCount() {
        int result = (int) manager.invoke("wordCount", Arrays.asList("hello world"));
        assertEquals(2, result);

        result = (int) manager.invoke("wordCount", Arrays.asList("  This  is  a  test  "));
        assertEquals(4, result);


        result = (int) manager.invoke("wordCount", Arrays.asList(""));
        assertEquals(0, result);
    }



    @Test
    public void testSimilarity() {
        double result = (double) manager.invoke("similarity", Arrays.asList("hello", "hello"));
        assertEquals(100.0, result, 0.1);

        result = (double) manager.invoke("similarity", Arrays.asList("kitten", "sitting"));
        System.out.println("相似度: " + result);
        assertTrue(result >= 0 && result <= 100);

        result = (double) manager.invoke("similarity", Arrays.asList("", ""));
        assertEquals(100.0, result, 0.1);

        result = (double) manager.invoke("similarity", Arrays.asList("", "hello"));
        assertEquals(0.0, result, 0.1);
    }



    @Test
    public void testToString() {
        // 普通对象
        String result = (String) manager.invoke("toString", Arrays.asList(123));
        assertEquals("123", result);

        result = (String) manager.invoke("toString", Arrays.asList(3.14));
        assertEquals("3.14", result);

        // 日期类型 - 带格式
        LocalDate date = LocalDate.of(2024, 5, 10);
        result = (String) manager.invoke("toString", Arrays.asList(date, "yyyy-MM-dd"));
        assertEquals("2024-05-10", result);

        LocalDateTime dateTime = LocalDateTime.of(2024, 5, 10, 15, 30, 0);
        result = (String) manager.invoke("toString", Arrays.asList(dateTime, "yyyy-MM-dd HH:mm:ss"));
        assertEquals("2024-05-10 15:30:00", result);

        // Date 类型
        Date utilDate = new Date(124, 4, 10); // 2024-05-10
        result = (String) manager.invoke("toString", Arrays.asList(utilDate, "yyyy-MM-dd"));
        assertNotNull(result);
    }



    @Test
    public void testTrim() {
        String result = (String) manager.invoke("trim", Arrays.asList("  hello  "));
        assertEquals("hello", result);
    }



    @Test
    public void testLastIndexOf() {
        int result = (int) manager.invoke("lastIndexOf", Arrays.asList("hello hello", "hello"));
        assertEquals(6, result);

        result = (int) manager.invoke("lastIndexOf", Arrays.asList("hello", "xxx"));
        assertEquals(-1, result);
    }



    @Test
    public void testSha1() {
        String result = (String) manager.invoke("sha1", Arrays.asList("hello"));
        assertEquals("aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d", result);

        result = (String) manager.invoke("sha1", Arrays.asList(null));
        assertNull(result);
    }

    @Test
    public void testUnescapeHtml() {
        String result = (String) manager.invoke("unescapeHtml", Arrays.asList("&lt;div&gt;hello&lt;/div&gt;"));
        assertEquals("<div>hello</div>", result);

        result = (String) manager.invoke("unescapeHtml", Arrays.asList("&amp;&lt;&gt;&quot;&#39;"));
        assertEquals("&<>\"'", result);
    }



    @Test
    public void testIsEmail() {
        assertTrue((boolean) manager.invoke("isEmail", Arrays.asList("test@example.com")));
        assertFalse((boolean) manager.invoke("isEmail", Arrays.asList("invalid")));
    }

    @Test
    public void testIsPhone() {
        assertTrue((boolean) manager.invoke("isPhone", Arrays.asList("13812345678")));
        assertFalse((boolean) manager.invoke("isPhone", Arrays.asList("12345")));
    }



    @Test(expected = IllegalArgumentException.class)
    public void testRepeatCharWithNoArgs() {
        manager.invoke("repeatChar", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitByLengthWithInvalidChunkSize() {
        manager.invoke("splitByLength", Arrays.asList("test", 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitByLengthWithNegativeChunkSize() {
        manager.invoke("splitByLength", Arrays.asList("test", -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTokenizeWithNoArgs() {
        manager.invoke("tokenize", Arrays.asList());
    }



    @Test
    public void testEdgeCases() {
        // 空字符串处理
        assertEquals("", manager.invoke("repeat", Arrays.asList("", 5)));
        assertEquals("", manager.invoke("repeatChar", Arrays.asList("*", 0)));
        assertEquals("", manager.invoke("reverse", Arrays.asList("")));

        // 超长重复
        String longResult = (String) manager.invoke("repeat", Arrays.asList("x", 1000));
        assertEquals(1000, longResult.length());
    }



    @Test
    public void testNamingConversionScenario() {
        // 命名转换场景
        String input = "user_profile_info";

        // 蛇形转驼峰
        String camelCase = (String) manager.invoke("toCamelCase", Arrays.asList(input));
        assertEquals("userProfileInfo", camelCase);

        // 驼峰转蛇形
        String snakeCase = (String) manager.invoke("toSnakeCase", Arrays.asList(camelCase));
        assertEquals("user_profile_info", snakeCase);

        // 大小写互换
        String swapped = (String) manager.invoke("swapCase", Arrays.asList(camelCase));
        assertEquals("USERpROFILEiNFO", swapped);

        System.out.println("原始: " + input);
        System.out.println("驼峰: " + camelCase);
        System.out.println("蛇形: " + snakeCase);
        System.out.println("大小写互换: " + swapped);
    }

    @Test
    public void testTextProcessingScenario() {
        // 文本处理场景
        String text = "  This  is  a  long  text  with  multiple  spaces  ";

        // 去除首尾空格
        String trimmed = (String) manager.invoke("trim", Arrays.asList(text));
        // 统计单词数
        int wordCount = (int) manager.invoke("wordCount", Arrays.asList(trimmed));
        // 反转
        String reversed = (String) manager.invoke("reverse", Arrays.asList(trimmed));

        System.out.println("原始文本: " + text);
        System.out.println("去除空格后: " + trimmed);
        System.out.println("单词数: " + wordCount);
        System.out.println("反转后: " + reversed);

        assertEquals(8, wordCount);
    }

    @Test
    public void testStringSimilarityScenario1() {
        // 字符串相似度匹配
        String searchTerm = "recognition";
        String[] candidates = {"recognition", "reconition", "recognision", "recog", "unknown"};

        System.out.println("搜索: " + searchTerm);
        System.out.println("相似度结果:");
        for (String candidate : candidates) {
            double similarity = (double) manager.invoke("similarity", Arrays.asList(searchTerm, candidate));
            boolean isSimilar = similarity > 50;
            System.out.printf("  '%s' -> %.2f%% %s%n", candidate, similarity, isSimilar ? "(相似)" : "");
        }
    }

    @Test
    public void testHtmlEscapeScenario() {
        // HTML 转义场景
        String html = "<script>alert('XSS')</script>";
        String escaped = (String) manager.invoke("escapeHtml", Arrays.asList(html));
        String unescaped = (String) manager.invoke("unescapeHtml", Arrays.asList(escaped));

        System.out.println("原始HTML: " + html);
        System.out.println("转义后: " + escaped);
        System.out.println("反转义后: " + unescaped);

        assertEquals(html, unescaped);
        assertTrue(escaped.contains("&lt;"));
        assertTrue(escaped.contains("&gt;"));
    }

    @Test
    public void testStringExtractionScenario() {
        // 字符串提取场景
        String log = "[2024-05-10 15:30:00] INFO: User 12345 logged in";

        // 提取时间
        String dateTime = (String) manager.invoke("substringBetween", Arrays.asList(log, "[", "]"));
        System.out.println("时间: " + dateTime);

        // 提取冒号后的内容
        String message = (String) manager.invoke("substringAfter", Arrays.asList(log, "] "));
        System.out.println("消息: " + message);

        // 提取用户ID
        String userId = (String) manager.invoke("substringBetween", Arrays.asList(message, "User ", " logged"));
        System.out.println("用户ID: " + userId);

        assertNotNull(dateTime);
        assertNotNull(message);
        assertEquals("12345", userId);
    }

    @Test
    public void testTextChunkingScenario() {
        // 文本分块场景
        String longText = "abcdefghijklmnopqrstuvwxyz";

        @SuppressWarnings("unchecked")
        List<String> chunks = (List<String>) manager.invoke("splitByLength", Arrays.asList(longText, 5));
        System.out.println("分块结果: " + chunks);

        assertEquals(6, chunks.size());
        assertEquals("abcde", chunks.get(0));
        assertEquals("fghij", chunks.get(1));
        assertEquals("vwxyz", chunks.get(5));
    }

    @Test
    public void testStringDeduplicationScenario() {
        // 字符串去重场景
        String input = "aaabbbcccdddeeefff";

        // 移除相邻重复
        String noAdjacentDup = (String) manager.invoke("removeDuplicates", Arrays.asList(input));
        System.out.println("移除相邻重复: " + noAdjacentDup);
        assertEquals("abcdef", noAdjacentDup);

        // 保留唯一字符（按首次出现顺序）
        String uniqueChars = (String) manager.invoke("uniqueChars", Arrays.asList("hello world"));
        System.out.println("唯一字符: " + uniqueChars);
        assertEquals("helo wrd", uniqueChars);
    }

    @Test
    public void testClamp() {
        // 数值限制范围测试
        double result = (double) manager.invoke("clamp", Arrays.asList(5.0, 1.0, 10.0));
        assertEquals(5.0, result, 0.001);

        result = (double) manager.invoke("clamp", Arrays.asList(0.0, 1.0, 10.0));
        assertEquals(1.0, result, 0.001);

        result = (double) manager.invoke("clamp", Arrays.asList(15.0, 1.0, 10.0));
        assertEquals(10.0, result, 0.001);
    }

    @Test
    public void testLerp() {
        double result = (double) manager.invoke("lerp", Arrays.asList(10.0, 20.0, 0.5));
        assertEquals(15.0, result, 0.001);
    }

}