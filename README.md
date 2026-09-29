<p align="center">
  <img src="./src/main/resources/static/jquick-logo.svg" width="420" alt="jquick-transform-function" />
</p>

# jquick-transform-function

> A pure-Java universal data transform function library for the JQuick ecosystem — 200+ built-in functions, native SPI extension, zero framework intrusion.

<p align="center">
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-transform-function"><img src="https://img.shields.io/badge/version-1.4.0-blue.svg" alt="Version" /></a>
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-transform-function"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-transform-function.svg" alt="Maven Central" /></a>
  <a href="./LICENSE"><img src="https://img.shields.io/badge/license-Apache%202.0-blue.svg" alt="License" /></a>
  <a href="https://github.com/paohaijiao/jquick-transform-function/stargazers"><img src="https://img.shields.io/github/stars/paohaijiao/jquick-transform-function.svg" alt="Stars" /></a>
  <a href="https://github.com/paohaijiao/jquick-transform-function/network/members"><img src="https://img.shields.io/github/forks/paohaijiao/jquick-transform-function.svg" alt="Forks" /></a>
</p>

> 📘 官方文档：<https://www.jquick.org/jquick-transform>
> 📦 Maven Central：<https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-transform-function>
> 🐛 Issue Tracker：<https://github.com/paohaijiao/jquick-transform-function/issues>


<p align="center">
  English | <a href="./README-CN.md">简体中文</a>
</p>

---

## 📖 Introduction

**jquick-transform-function** is a general-purpose data transform function library for the **JQuick** ecosystem. It provides a unified function registry to simplify data conversion logic .

It is designed for:

- **Traditional Spring projects and legacy systems** — plain Java, no framework required, drop-in usage.
- **Xinchuang (domestic IT) environments** — verified alongside domestic databases such as **Dameng (DM)** and **KingbaseES**.
- **Function-rule engines** — every built-in function is itself an SPI provider, so the whole library can be embedded as a function layer inside SQL/rendering/rule engines.

## ✨ Core Features

| Feature | Description |
|---------|-------------|
| 200+ built-in functions | 16 categories: string, math, date/time, condition, conversion, business (ID card / phone / bank card), collection, array, bit, boolean, random, JSON, crypto (AES/RSA/ECC), geometry, extra, translator |
| **SPI extension** | Developers can plug in custom converters through the **native Java SPI** mechanism — no source code changes, plug-in style extension of transform rules |
| Override built-ins | Replace any built-in function by registering a provider with the same method name (or a higher priority) |
| Unified invocation | Thread-safe singleton registry `JQuickMethodInvocationManager`, invoke any function by name: `invoke("toUpper", "hello")` |
| Lambda registration | Register simple functions in one line with a lambda expression |
| Zero intrusion | Pure Java, works in plain Java projects, Spring / Spring Boot, and legacy systems without any adaptation |
| Lightweight | JDK 8+, no heavy third-party dependencies |

## 📦 Quick Start

### Maven

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

### Minimal Example

```java
import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;

public class Demo {
    public static void main(String[] args) {
        JQuickMethodInvocationManager manager = JQuickMethodInvocationManager.getInstance();

        Object upper  = manager.invoke("toUpper", "hello");           // HELLO
        Object sum    = manager.invoke("add", 10, 20, 30);            // 60.0
        Object sub    = manager.invoke("substring", "hello world", 0, 5); // hello
        Object masked = manager.invoke("phoneMask", "13812345678");   // masked phone number

        System.out.println(upper + " / " + sum + " / " + sub + " / " + masked);
    }
}
```

On the first call, the manager auto-loads all built-in functions through SPI — no extra initialization code is needed.

## 🧩 SPI Extension

### How the SPI Mechanism Works

- The extension point is the `com.github.paohaijiao.function.core.JQuickMethodFunctionProvider` interface (provided by `javelin-core`).
- It is based on the **native Java SPI** (`java.util.ServiceLoader`), **not** Spring SPI — implementations are discovered from the standard `META-INF/services/` directory and loaded by priority (`@Priority`).
- Therefore you can extend the library **without touching the source code** in plain Java projects, Spring projects, and Xinchuang projects alike — completely non-intrusive.
- A custom provider registered with the **same method name as a built-in function** (or a higher priority) will **override the built-in implementation**.

Extending takes three steps: **implement → register → call**.

### Step 1: Implement a Custom Converter

```java
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;

import java.util.List;

/**
 * Custom function: maskName("Zhang San") -> "Z**"
 */
public class MaskNameFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public MaskNameFunctionProvider() {
        super("maskName", "[Business] mask a person name, keep only the first character");
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

> Tip: extend `JQuickBaseFunctionFunctionProvider` to get `asString / asInt / asDouble / validateArgCount` helpers for free, or implement `JQuickMethodFunctionProvider` directly for full control.

### Step 2: Configure META-INF/services

Create the file `src/main/resources/META-INF/services/com.github.paohaijiao.function.core.JQuickMethodFunctionProvider` and write the fully-qualified class name of your provider, one per line:

```text
com.example.function.MaskNameFunctionProvider
```

### Step 3: Use It

```java
JQuickMethodInvocationManager manager = JQuickMethodInvocationManager.getInstance();
Object masked = manager.invoke("maskName", "Zhang San"); // Z**
```

### Overriding Built-in Functions

```java
// Option A: register a provider class whose method name is "toUpper"
//           with a higher priority (override getPriority()).
manager.registerOrReplaceInvoker(customToUpperProvider);

// Option B: replace a built-in function with one line of lambda code
manager.registerOrReplaceInvoker("toUpper",
        args -> String.valueOf(args.get(0)).toUpperCase() + "!",
        "[Override] custom toUpper with exclamation mark");

// Register a brand-new function with a lambda
manager.registerInvoker("double", args -> ((Number) args.get(0)).doubleValue() * 2);
```

## 📖 Usage Examples

### String Functions

```java
manager.invoke("capitalize", "hello");              // Hello
manager.invoke("split", "a,b,c", ",");              // [a, b, c]
manager.invoke("replace", "hello world", "world", "java"); // hello java
manager.invoke("base64Encode", "hello");            // aGVsbG8=
manager.invoke("md5", "hello");                     // 5d41402abc4b2a76b9719d911017c592
```

### Math Functions

```java
manager.invoke("max", 10, 20, 30, 5, 25);           // 30.0
manager.invoke("avg", 10, 20, 30);                  // 20.0
manager.invoke("ceil", 3.14);                       // 4.0
manager.invoke("pow", 2, 10);                       // 1024.0
```

### Date & Time Functions

```java
manager.invoke("now");                              // current date-time
manager.invoke("today");                            // today's date
manager.invoke("addDays", LocalDate.now(), 7);      // date + 7 days
manager.invoke("formatDate", LocalDate.now(), "yyyy-MM-dd");
```

### Condition Functions (SQL style)

```java
manager.invoke("if", score >= 60, "PASS", "FAIL");
manager.invoke("coalesce", null, null, "default");  // default
manager.invoke("nvl", value, 0);                    // value == null ? 0 : value
manager.invoke("caseWhen",
        age < 18, "minor",
        age < 60, "adult",
        "senior");
```

### Type Conversion Functions

```java
manager.invoke("toBoolean", "true");                // true
manager.invoke("toDate", "2026-01-01");             // LocalDate
manager.invoke("toCurrency", 1234567.891);          // currency format
manager.invoke("toPercentage", 0.1234, 2);          // 12.34%
manager.invoke("cast", "123", Integer.class);       // 123
```

### Business Functions (masking & validation)

```java
manager.invoke("phoneMask", "13812345678");         // phone masking
manager.invoke("bankCardMask", "6222021234567890"); // bank card masking
manager.invoke("idCardValidate", "11010119900307077X"); // ID card validation
manager.invoke("genderName", "M");                  // Male
```

### Random / JSON Functions

```java
manager.invoke("randomInt", 1, 100);                // random int in [1, 100]
manager.invoke("randomUUID", true);                 // UUID without dashes
manager.invoke("toJson", userObject);               // serialize to JSON string
```

## 📑 API Reference

### JQuickMethodInvocationManager

Entry point of the library. Obtain the singleton via `JQuickMethodInvocationManager.getInstance()`.

| Method | Description |
|--------|-------------|
| `Object invoke(String methodName, Object... args)` | Invoke a registered function by name |
| `Object invoke(String methodName, List<Object> args)` | Invoke with an argument list |
| `boolean hasMethod(String methodName)` | Check whether a function is registered |
| `Optional<JQuickMethodFunctionProvider> getInvoker(String methodName)` | Get the provider of a function |
| `List<String> getSupportedMethods()` | List all registered function names |
| `int getMethodCount()` | Number of registered functions |
| `void registerInvoker(JQuickMethodFunctionProvider invoker)` | Register a provider |
| `void registerInvoker(String name, Function<List<Object>, Object> fn, [description, [priority]])` | Register via lambda |
| `void registerInvokers(JQuickMethodFunctionProvider... invokers)` | Batch register |
| `void registerOrReplaceInvoker(...)` | Register in override mode (replaces existing name) |
| `void unregisterInvoker(String methodName)` | Remove a function |
| `List<JQuickMethodFunctionProvider> searchMethods(String keyword)` | Fuzzy search functions |
| `Map<String, List<JQuickMethodFunctionProvider>> getGroupedInvokers()` | Functions grouped by category |
| `void printRegisteredMethods()` / `printStats()` | Print registry tables / statistics |

### JQuickMethodFunctionProvider (SPI Interface)

The extension point, provided by `javelin-core` (`com.github.paohaijiao.function.core`).

| Method | Description |
|--------|-------------|
| `String getMethodName()` | Function name used for invocation |
| `Object invoke(List<Object> args)` | Execute the function and return the result |
| `String getDescription()` | Description; prefix like `[Math]` defines the group |
| `int getPriority()` | Load/override priority; higher wins |

### JQuickBaseFunctionFunctionProvider (Abstract Base Class)

Convenience base class for custom providers (`com.github.paohaijiao.function.domain`).

| Member | Description |
|--------|-------------|
| `JQuickBaseFunctionFunctionProvider(String methodName, String description)` | Constructor |
| `asString / asInt / asLong / asDouble / asBoolean(Object)` | Argument type coercion helpers |
| `validateArgCount(List<Object> args, int expected)` | Strict argument count check |
| `validateArgCountRange(List<Object> args, int min, int max)` | Argument count range check |

### Built-in Function Categories

| Category | Example Functions |
|----------|-------------------|
| String | `toUpper` `toLower` `trim` `substring` `split` `concat` `capitalize` `isBlank` `md5` `base64Encode` |
| Math | `add` `subtract` `multiply` `divide` `max` `min` `avg` `round` `pow` `sqrt` `sum` |
| Date & Time | `now` `today` `year` `month` `day` `addDays` `formatDate` `daysBetween` `age` |
| Condition | `if` `ifElse` `caseWhen` `switch` `coalesce` `nvl` `defaultIfNull` `eq` `gt` `lt` |
| Conversion | `toBoolean` `toDate` `toDateTime` `toShort` `cast` `toCurrency` `toPercentage` `typeOf` |
| Business | `idCardValidate` `idCardInfo` `phoneMask` `phoneValidate` `bankCardMask` `bankCardValidate` `emailMask` `genderName` |
| Collection / Array | `isEmpty` `size` `join` `isArray` |
| Bit / Boolean | `bitAnd` `bitOr` `bitXor` `isBoolean` |
| Random | `randomInt` `randomString` `randomUUID` `randomChoice` |
| JSON | `toJson` |
| Crypto | AES / RSA / ECC encrypt, decrypt and key generation functions |
| Geometry | `areaCircle` `distance` `clamp` `factorial` `fibonacci` `gcd` |
| Translator | Data dictionary translation functions |

> The complete function list (200+, with usage of every function) can be printed at runtime via `manager.printRegisteredMethods()`.

## ⚙️ Compatibility

| Item | Requirement |
|------|-------------|
| JDK | 8 or higher (compiled with `maven.compiler.source/target=1.8`) |
| Dependencies | `javelin-core` (transitive, pulled in automatically by Maven) |
| Framework | None required — plain Java; works with Spring / Spring Boot and legacy systems |
| Databases | Database-agnostic (pure Java transform layer); suitable for use with domestic databases such as **Dameng (DM)** and **KingbaseES**, as well as MySQL / Oracle / PostgreSQL. Tested version matrix: 【TODO】 |
| Build | Maven 3.x; artifacts signed and published to Maven Central |

## 🤝 Contributing

Contributions are welcome!

1. Fork this repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m "feat: add xxx"`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

Before submitting:

- Run the full test suite with `mvn test`
- Keep code JDK 8 compatible
- New built-in functions should extend `JQuickBaseFunctionFunctionProvider` and be registered in `META-INF/services`

