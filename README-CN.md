<p align="center">
  <img src="./src/main/resources/static/jquick-logo.svg" width="420" alt="jquick-transform-function" />
</p>

# jquick-transform-function

> JQuick 生态组件 —— 纯 Java 通用数据转换函数库：内置 200+ 转换函数，原生 SPI 扩展，零框架侵入。

<p align="center">
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-transform-function"><img src="https://img.shields.io/badge/version-1.4.0-blue.svg" alt="Version" /></a>
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-transform-function"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-transform-function.svg" alt="Maven Central" /></a>
  <a href="./LICENSE"><img src="https://img.shields.io/badge/license-Apache%202.0-blue.svg" alt="License" /></a>
  <a href="https://github.com/paohaijiao/jquick-transform-function/stargazers"><img src="https://img.shields.io/github/stars/paohaijiao/jquick-transform-function.svg" alt="Stars" /></a>
  <a href="https://github.com/paohaijiao/jquick-transform-function/network/members"><img src="https://img.shields.io/github/forks/paohaijiao/jquick-transform-function.svg" alt="Forks" /></a>
</p>

<p align="center">
  <a href="./README.md">English</a> | 简体中文
</p>

---

## 📖 项目简介

**jquick-transform-function** 是 **JQuick** 生态下的通用数据转换函数库。它提供统一的函数注册与调用中心，简化数据转换逻辑。

它适用于：

- **传统 Spring 项目与老旧业务系统** —— 纯 Java 实现，不依赖任何框架，引入即用。
- **信创环境** —— 已适配达梦（DM）、人大金仓（KingbaseES）等国产数据库场景。
- **函数规则引擎** —— 所有内置函数本身即为 SPI 提供者，整个函数库可作为函数层嵌入 SQL / 渲染 / 规则引擎。

## ✨ 核心特性

| 特性 | 说明 |
|---------|-------------|
| 200+ 内置函数 | 覆盖 16 大类：字符串、数学、日期时间、条件、类型转换、业务（身份证/手机号/银行卡）、集合、数组、位运算、布尔、随机、JSON、加解密（AES/RSA/ECC）、几何、扩展、翻译 |
| **SPI 扩展** | 开发者可通过**原生 Java SPI** 机制自定义转换器，无需修改源码，插件式扩展转换规则 |
| 覆盖内置函数 | 注册同名方法（或更高优先级）的提供者，即可替换任意内置函数 |
| 统一调用入口 | 线程安全的单例注册中心 `JQuickMethodInvocationManager`，按函数名调用：`invoke("toUpper", "hello")` |
| Lambda 注册 | 一行 Lambda 表达式即可注册简单函数 |
| 零侵入 | 纯 Java 实现，普通 Java 项目、Spring / Spring Boot、老旧系统均可直接使用，无需任何适配 |
| 轻量级 | JDK 8+，无重型第三方依赖 |

## 📦 快速上手

### Maven 依赖引入

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-transform-function</artifactId>
    <version>${latest.version}</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.paohaijiao:jquick-transform-function:1.4.0'
```

### 最简代码示例

```java
import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;

public class Demo {
    public static void main(String[] args) {
        JQuickMethodInvocationManager manager = JQuickMethodInvocationManager.getInstance();

        Object upper  = manager.invoke("toUpper", "hello");           // HELLO
        Object sum    = manager.invoke("add", 10, 20, 30);            // 60.0
        Object sub    = manager.invoke("substring", "hello world", 0, 5); // hello
        Object masked = manager.invoke("phoneMask", "13812345678");   // 手机号脱敏结果

        System.out.println(upper + " / " + sum + " / " + sub + " / " + masked);
    }
}
```

首次调用时，管理器会通过 SPI 自动加载全部内置函数，无需额外的初始化代码。

## 🧩 SPI 扩展集成

### SPI 机制说明

- 扩展点为 `com.github.paohaijiao.function.core.JQuickMethodFunctionProvider` 接口（由 `javelin-core` 提供）。
- 基于**原生 Java SPI**（`java.util.ServiceLoader`）实现，**并非 Spring SPI** —— 实现类从标准 `META-INF/services/` 目录发现，并按优先级（`@Priority`）加载。
- 因此在普通 Java 项目、Spring 项目、信创项目中都可以**无侵入**地扩展函数库，完全不改动源码。
- 自定义提供者若与内置函数**同名**（或优先级更高），将**覆盖内置实现**。

扩展只需三步：**实现 → 注册 → 调用**。

### 第一步：实现自定义转换器

```java
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;

import java.util.List;

/**
 * 自定义函数：maskName("张三") -> "张**"
 */
public class MaskNameFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public MaskNameFunctionProvider() {
        super("maskName", "[Business] 姓名脱敏，仅保留首字符");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String name = asString(args.get(0));
        if (name == null || name.length() <= 1) {
            return name;
        }
        return name.charAt(0) + "**";
    }
}
```

> 提示：继承 `JQuickBaseFunctionFunctionProvider` 可免费获得 `asString / asInt / asDouble / validateArgCount` 等辅助方法；也可以直接实现 `JQuickMethodFunctionProvider` 接口获得完全控制。

### 第二步：配置 META-INF/services

创建文件 `src/main/resources/META-INF/services/com.github.paohaijiao.function.core.JQuickMethodFunctionProvider`，写入提供者的全限定类名，每行一个：

```text
com.example.function.MaskNameFunctionProvider
```

### 第三步：调用

```java
JQuickMethodInvocationManager manager = JQuickMethodInvocationManager.getInstance();
Object masked = manager.invoke("maskName", "张三"); // 张**
```

### 覆盖内置转换函数

```java
// 方式一：注册一个方法名为 "toUpper" 且优先级更高的提供者类
//         （重写 getPriority() 提高优先级）。
manager.registerOrReplaceInvoker(customToUpperProvider);

// 方式二：一行 Lambda 代码替换内置函数
manager.registerOrReplaceInvoker("toUpper",
        args -> String.valueOf(args.get(0)).toUpperCase() + "!",
        "[Override] 自定义 toUpper，追加感叹号");

// 用 Lambda 注册一个全新函数
manager.registerInvoker("double", args -> ((Number) args.get(0)).doubleValue() * 2);
```

## 📖 使用示例

### 字符串函数

```java
manager.invoke("capitalize", "hello");              // Hello
manager.invoke("split", "a,b,c", ",");              // [a, b, c]
manager.invoke("replace", "hello world", "world", "java"); // hello java
manager.invoke("base64Encode", "hello");            // aGVsbG8=
manager.invoke("md5", "hello");                     // 5d41402abc4b2a76b9719d911017c592
```

### 数学函数

```java
manager.invoke("max", 10, 20, 30, 5, 25);           // 30.0
manager.invoke("avg", 10, 20, 30);                  // 20.0
manager.invoke("ceil", 3.14);                       // 4.0
manager.invoke("pow", 2, 10);                       // 1024.0
```

### 日期时间函数

```java
manager.invoke("now");                              // 当前日期时间
manager.invoke("today");                            // 今天日期
manager.invoke("addDays", LocalDate.now(), 7);      // 日期 +7 天
manager.invoke("formatDate", LocalDate.now(), "yyyy-MM-dd");
```

### 条件函数（SQL 风格）

```java
manager.invoke("if", score >= 60, "及格", "不及格");
manager.invoke("coalesce", null, null, "默认值");    // 默认值
manager.invoke("nvl", value, 0);                    // value == null ? 0 : value
manager.invoke("caseWhen",
        age < 18, "未成年",
        age < 60, "成年",
        "老年");
```

### 类型转换函数

```java
manager.invoke("toBoolean", "true");                // true
manager.invoke("toDate", "2026-01-01");             // LocalDate
manager.invoke("toCurrency", 1234567.891);          // 货币格式
manager.invoke("toPercentage", 0.1234, 2);          // 12.34%
manager.invoke("cast", "123", Integer.class);       // 123
```

### 业务函数（脱敏与校验）

```java
manager.invoke("phoneMask", "13812345678");         // 手机号脱敏
manager.invoke("bankCardMask", "6222021234567890"); // 银行卡号脱敏
manager.invoke("idCardValidate", "11010119900307077X"); // 身份证号校验
manager.invoke("genderName", "M");                  // 男
```

### 随机 / JSON 函数

```java
manager.invoke("randomInt", 1, 100);                // [1, 100] 内随机整数
manager.invoke("randomUUID", true);                 // 无横线 UUID
manager.invoke("toJson", userObject);               // 序列化为 JSON 字符串
```

## 📑 API 参考

### JQuickMethodInvocationManager

函数库的统一入口，通过 `JQuickMethodInvocationManager.getInstance()` 获取单例。

| 方法 | 说明 |
|--------|-------------|
| `Object invoke(String methodName, Object... args)` | 按函数名调用已注册函数 |
| `Object invoke(String methodName, List<Object> args)` | 以参数列表调用 |
| `boolean hasMethod(String methodName)` | 判断函数是否已注册 |
| `Optional<JQuickMethodFunctionProvider> getInvoker(String methodName)` | 获取函数对应的提供者 |
| `List<String> getSupportedMethods()` | 列出所有已注册函数名 |
| `int getMethodCount()` | 已注册函数数量 |
| `void registerInvoker(JQuickMethodFunctionProvider invoker)` | 注册提供者 |
| `void registerInvoker(String name, Function<List<Object>, Object> fn, [description, [priority]])` | 以 Lambda 注册 |
| `void registerInvokers(JQuickMethodFunctionProvider... invokers)` | 批量注册 |
| `void registerOrReplaceInvoker(...)` | 覆盖式注册（同名则替换） |
| `void unregisterInvoker(String methodName)` | 注销函数 |
| `List<JQuickMethodFunctionProvider> searchMethods(String keyword)` | 模糊搜索函数 |
| `Map<String, List<JQuickMethodFunctionProvider>> getGroupedInvokers()` | 按分组获取函数 |
| `void printRegisteredMethods()` / `printStats()` | 打印注册表 / 统计信息 |

### JQuickMethodFunctionProvider（SPI 接口）

扩展点接口，由 `javelin-core` 提供（`com.github.paohaijiao.function.core` 包）。

| 方法 | 说明 |
|--------|-------------|
| `String getMethodName()` | 调用时使用的函数名 |
| `Object invoke(List<Object> args)` | 执行函数并返回结果 |
| `String getDescription()` | 描述；`[Math]` 之类前缀决定分组 |
| `int getPriority()` | 加载/覆盖优先级，高者优先 |

### JQuickBaseFunctionFunctionProvider（抽象基类）

自定义提供者的便捷基类（`com.github.paohaijiao.function.domain` 包）。

| 成员 | 说明 |
|--------|-------------|
| `JQuickBaseFunctionFunctionProvider(String methodName, String description)` | 构造方法 |
| `asString / asInt / asLong / asDouble / asBoolean(Object)` | 参数类型转换辅助方法 |
| `validateArgCount(List<Object> args, int expected)` | 严格参数个数校验 |
| `validateArgCountRange(List<Object> args, int min, int max)` | 参数个数范围校验 |

### 内置函数分类

| 分类 | 示例函数 |
|----------|-------------------|
| 字符串 | `toUpper` `toLower` `trim` `substring` `split` `concat` `capitalize` `isBlank` `md5` `base64Encode` |
| 数学 | `add` `subtract` `multiply` `divide` `max` `min` `avg` `round` `pow` `sqrt` `sum` |
| 日期时间 | `now` `today` `year` `month` `day` `addDays` `formatDate` `daysBetween` `age` |
| 条件 | `if` `ifElse` `caseWhen` `switch` `coalesce` `nvl` `defaultIfNull` `eq` `gt` `lt` |
| 类型转换 | `toBoolean` `toDate` `toDateTime` `toShort` `cast` `toCurrency` `toPercentage` `typeOf` |
| 业务 | `idCardValidate` `idCardInfo` `phoneMask` `phoneValidate` `bankCardMask` `bankCardValidate` `emailMask` `genderName` |
| 集合 / 数组 | `isEmpty` `size` `join` `isArray` |
| 位运算 / 布尔 | `bitAnd` `bitOr` `bitXor` `isBoolean` |
| 随机 | `randomInt` `randomString` `randomUUID` `randomChoice` |
| JSON | `toJson` |
| 加解密 | AES / RSA / ECC 加密、解密与密钥生成函数 |
| 几何 | `areaCircle` `distance` `clamp` `factorial` `fibonacci` `gcd` |
| 翻译 | 数据字典翻译函数 |

> 完整函数清单（200+，含每个函数的用法）可在运行时通过 `manager.printRegisteredMethods()` 打印。

## ⚙️ 兼容性说明

| 项目 | 要求 |
|------|-------------|
| JDK | 8 及以上（`maven.compiler.source/target=1.8` 编译） |
| 依赖 | `javelin-core`（传递依赖，Maven 自动引入） |
| 框架 | 无框架要求 —— 纯 Java；兼容 Spring / Spring Boot 及老旧系统 |
| 数据库 | 与数据库无关（纯 Java 转换层）；适用于达梦（DM）、人大金仓（KingbaseES）等国产数据库，以及 MySQL / Oracle / PostgreSQL。已测试版本矩阵：【TODO】 |
| 构建 | Maven 3.x；构件经签名并发布至 Maven Central |

## 🤝 参与贡献

欢迎参与贡献！

1. Fork 本仓库
2. 创建特性分支（`git checkout -b feature/your-feature`）
3. 提交变更（`git commit -m "feat: add xxx"`）
4. 推送分支（`git push origin feature/your-feature`）
5. 发起 Pull Request

提交前请确认：

- 通过 `mvn test` 跑完全部测试
- 保持代码兼容 JDK 8
- 新增内置函数请继承 `JQuickBaseFunctionFunctionProvider`，并在 `META-INF/services` 中注册
