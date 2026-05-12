| 序号 | Method Name        | Description                                                                                    |
| ---- | ------------------ | ---------------------------------------------------------------------------------------------- |
| 1    | isArray            | 判断是否为数组或列表 - 用法: isArray(value)                                                      |
| 2    | bitAnd             | 按位与 - 用法: bitAnd(a, b)                                                                     |
| 3    | bitOr              | 按位或 - 用法: bitOr(a, b)                                                                      |
| 4    | bitXor             | 按位异或 - 用法: bitXor(a, b)                                                                    |
| 5    | isBoolean          | 判断是否为布尔值 - 用法: isBoolean(value)                                                        |
| 6    | bankCardMask       | 银行卡号脱敏 - 用法: bankCardMask(cardNo, keepStart?, keepEnd?)                                  |
| 7    | bankCardValidate   | 校验银行卡号是否有效(Luhn算法) - 用法: bankCardValidate(cardNo)                                    |
| 8    | emailMask          | 邮箱脱敏 - 用法: emailMask(email)                                                                |
| 9    | genderName         | 获取性别名称 - 用法: genderName(code) 支持: M/F, 1/0, 男/女, male/female                           |
| 10   | idCardAge          | 从身份证号计算年龄 - 用法: idCardAge(idCard, referenceDate?)                                      |
| 11   | idCardBirthday     | 从身份证号提取生日 - 用法: idCardBirthday(idCard, pattern?)                                       |
| 12   | idCardGender       | 从身份证号获取性别 - 用法: idCardGender(idCard, format?)                                          |
| 13   | idCardInfo         | 提取身份证信息 - 用法: idCardInfo(idCard, field?) field: birthday/age/gender/region               |
| 14   | idCardValidate     | 校验身份证号是否有效 - 用法: idCardValidate(idCard)                                                |
| 15   | phoneInfo          | 获取手机号信息 - 用法: phoneInfo(phone, field?) field: carrier/prefix/location                    |
| 16   | phoneMask          | 手机号脱敏 - 用法: phoneMask(phone, keepStart?, keepEnd?)                                         |
| 17   | phoneValidate      | 校验手机号是否有效 - 用法: phoneValidate(phone)                                                   |
| 18   | isEmpty            | 判断对象是否为空 - 支持String/Collection/Map/Array                                               |
| 19   | join               | 连接集合元素 - 用法: join(list, delimiter)                                                       |
| 20   | size               | 获取集合、数组、Map或字符串的长度                                                                   |
| 21   | between            | 范围判断 - 用法: between(value, min, max, inclusive?)                                            |
| 22   | caseWhen           | CASE WHEN 条件表达式 - 用法: caseWhen(condition1, result1, condition2, result2, ..., defaultResult) |
| 23   | coalesce           | 返回第一个非空值 - 用法: coalesce(value1, value2, ...)                                           |
| 24   | defaultIfNull      | 空值替换为默认值 - 用法: defaultIfNull(value, defaultValue)                                      |
| 25   | eq                 | 相等判断 - 用法: eq(a, b, ignoreCase?)                                                           |
| 26   | gte                | 大于等于判断 - 用法: gte(a, b)                                                                   |
| 27   | gt                 | 大于判断 - 用法: gt(a, b)                                                                      |
| 28   | ifElse             | 多条件判断 - 用法: ifElse(condition1, value1, condition2, value2, ..., defaultValue)              |
| 29   | if                 | 条件判断 - 用法: if(condition, trueValue, falseValue)                                          |
| 30   | lte                | 小于等于判断 - 用法: lte(a, b)                                                                   |
| 31   | lt                 | 小于判断 - 用法: lt(a, b)                                                                      |
| 32   | ne                 | 不等判断 - 用法: ne(a, b, ignoreCase?)                                                           |
| 33   | nvl                | 空值替换 - 用法: nvl(value, defaultValue)                                                      |
| 34   | switch             | 多分支匹配 - 用法: switch(value, case1, result1, case2, result2, ..., defaultValue)               |
| 35   | toBoolean          | 转换为布尔值 - 用法: toBoolean(value, defaultValue?)                                             |
| 36   | toDate             | 转换为日期(LocalDate) - 用法: toDate(value, pattern?)                                           |
| 37   | toDateTime         | 转换为日期时间(LocalDateTime) - 用法: toDateTime(value, pattern?)                                 |
| 38   | toShort            | 转换为短整数 - 用法: toShort(value, defaultValue?)                                               |
| 39   | addDays            | 增加天数 - 用法: addDays(date, days)                                                           |
| 40   | addHours           | 增加小时 - 用法: addHours(datetime, hours)                                                     |
| 41   | addMinutes         | 增加分钟 - 用法: addMinutes(datetime, minutes)                                                 |
| 42   | addMonths          | 增加月份 - 用法: addMonths(date, months)                                                         |
| 43   | addSeconds         | 增加秒 - 用法: addSeconds(datetime, seconds)                                                    |
| 44   | addYears           | 增加年份 - 用法: addYears(date, years)                                                           |
| 45   | age                | 计算年龄 - 用法: age(birthDate, referenceDate?)                                                |
| 46   | day                | 获取日期(1-31) - 用法: day(date?)                                                                |
| 47   | dayOfWeek          | 获取星期几 - 用法: dayOfWeek(date?, locale?) 返回1-7(周一=1)或名称                                 |
| 48   | dayOfYear          | 获取年中第几天(1-366) - 用法: dayOfYear(date?)                                                    |
| 49   | daysBetween        | 计算两个日期之间的天数差 - 用法: daysBetween(date1, date2)                                         |
| 50   | endOfDay           | 获取日期结束时间(23:59:59) - 用法: endOfDay(date?)                                               |
| 51   | endOfMonth         | 获取月份最后一天 - 用法: endOfMonth(date?)                                                       |
| 52   | endOfYear          | 获取年份最后一天 - 用法: endOfYear(date?)                                                        |
| 53   | hour               | 获取小时(0-23) - 用法: hour(datetime?)                                                         |
| 54   | hoursBetween       | 计算两个日期时间之间的小时差 - 用法: hoursBetween(datetime1, datetime2)                              |
| 55   | isAfter            | 判断日期是否在之后 - 用法: isAfter(date1, date2)                                                  |
| 56   | isBefore           | 判断日期是否在之前 - 用法: isBefore(date1, date2)                                                 |
| 57   | isDate             | 判断是否为日期 - 用法: isDate(value)                                                              |
| 58   | isLeapYear         | 判断是否为闰年 - 用法: isLeapYear(year?)                                                          |
| 59   | isSameDay          | 判断两个日期是否为同一天 - 用法: isSameDay(date1, date2)                                           |
| 60   | isWeekend          | 判断是否为周末 - 用法: isWeekend(date?)                                                           |
| 61   | minute             | 获取分钟(0-59) - 用法: minute(datetime?)                                                       |
| 62   | month              | 获取月份(1-12) - 用法: month(date?)                                                            |
| 63   | monthsBetween      | 计算两个日期之间的月份差 - 用法: monthsBetween(date1, date2)                                       |
| 64   | second             | 获取秒(0-59) - 用法: second(datetime?)                                                        |
| 65   | startOfDay         | 获取日期开始时间(00:00:00) - 用法: startOfDay(date?)                                               |
| 66   | startOfMonth       | 获取月份第一天 - 用法: startOfMonth(date?)                                                        |
| 67   | startOfYear        | 获取年份第一天 - 用法: startOfYear(date?)                                                         |
| 68   | weekOfYear         | 获取年中第几周 - 用法: weekOfYear(date?)                                                          |
| 69   | year               | 获取年份 - 用法: year(date?) 不传参数则获取当前年份                                                   |
| 70   | yearsBetween        | 计算两个日期之间的年份差 - 用法: yearsBetween(date1, date2)                                        |
| 71   | areaCircle         | 计算圆面积 - 用法: areaCircle(radius)                                                           |
| 72   | areaRectangle      | 计算矩形面积 - 用法: areaRectangle(length, width)                                                |
| 73   | areaTriangle       | 计算三角形面积 - 用法: areaTriangle(base, height) 或 areaTriangle(a, b, c)海伦公式                 |
| 74   | circumference      | 计算圆周长 - 用法: circumference(radius)                                                        |
| 75   | clamp              | 限制数值范围 - 用法: clamp(value, min, max)                                                      |
| 76   | combination        | 计算组合数 C(n,k) - 用法: combination(n, k)                                                     |
| 77   | cross              | 计算向量叉积（2D标量）- 用法: cross(x1, y1, x2, y2)                                                  |
| 78   | distance           | 计算两点间距离 - 用法: distance(x1, y1, x2, y2) 或 distance(x1, y1, z1, x2, y2, z2)                |
| 79   | dot                | 计算向量点积 - 用法: dot(vector1, vector2)                                                       |
| 80   | factorial          | 计算阶乘 - 用法: factorial(n)                                                                  |
| 81   | fibonacci          | 计算斐波那契数 - 用法: fibonacci(n)                                                               |
| 82   | gcd                | 计算最大公约数 - 用法: gcd(a, b, ...)                                                             |
| 83   | hypot              | 计算sqrt(x²+y²) - 用法: hypot(x, y)                                                              |
| 84   | isPowerOfTwo       | 判断是否为2的幂 - 用法: isPowerOfTwo(n)                                                           |
| 85   | isPrime            | 判断是否为素数 - 用法: isPrime(n)                                                                 |
| 86   | lcm                | 计算最小公倍数 - 用法: lcm(a, b, ...)                                                             |
| 87   | lerp               | 线性插值 - 用法: lerp(a, b, t) 返回 a + (b - a) * t                                              |
| 88   | map                | 数值范围映射 - 用法: map(value, fromLow, fromHigh, toLow, toHigh, clamp?)                        |
| 89   | permutation        | 计算排列数 P(n,k) - 用法: permutation(n, k)                                                     |
| 90   | cast               | 强制类型转换 - 用法: cast(value, targetClass)                                                    |
| 91   | formatNumber       | 格式化数字 - 用法: formatNumber(number, pattern)                                                |
| 92   | parseNumber        | 解析格式化数字 - 用法: parseNumber(str, pattern)                                                  |
| 93   | toArray            | 转换为数组 - 用法: toArray(value1, value2, ...)                                                 |
| 94   | toCurrency         | 转换为货币格式 - 用法: toCurrency(number, locale?)                                                |
| 95   | toList             | 转换为列表 - 用法: toList(value1, value2, ...)                                                  |
| 96   | toPercentage       | 转换为百分比格式 - 用法: toPercentage(number, decimals?)                                           |
| 97   | typeOf             | 获取对象的类型名称 - 用法: typeOf(value)                                                            |
| 98   | abs                | 绝对值                                                                                          |
| 99   | acos               | 反余弦函数 - 用法: acos(value)                                                                  |
| 100  | add                | 求和                                                                                           |
| 101  | asin               | 反正弦函数 - 用法: asin(value)                                                                  |
| 102  | atan               | 反正切函数 - 用法: atan(value)                                                                  |
| 103  | atan2              | 坐标反正切 - 用法: atan2(y, x) 返回坐标(x,y)的角度                                                 |
| 104  | avg                | 平均值                                                                                          |
| 105  | ceil               | 向上取整                                                                                         |
| 106  | ceilTo             | 向上取整到指定小数位 - 用法: ceilTo(value, places)                                                   |
| 107  | e                  | 获取自然常数e的值                                                                                    |
| 108  | pi                 | 获取圆周率π的值                                                                                     |
| 109  | cos                | 余弦函数 - 用法: cos(radians)                                                                  |
| 110  | cosh               | 双曲余弦函数 - 用法: cosh(value)                                                                 |
| 111  | divide             | 除法 - 用法: divide(a, b, ...) 返回 a/b/...                                                    |
| 112  | exp                | 指数函数 e^x - 用法: exp(value)                                                                |
| 113  | expm1              | 指数函数 e^x - 1 - 用法: expm1(value)                                                          |
| 114  | floor              | 向下取整                                                                                         |
| 115  | floorTo            | 向下取整到指定小数位 - 用法: floorTo(value, places)                                                  |
| 116  | greatest           | 返回最大值 - 用法: greatest(value1, value2, ...)                                                |
| 117  | isNumber           | 判断对象是否为数字类型或数字字符串                                                                    |
| 118  | least              | 返回最小值 - 用法: least(value1, value2, ...)                                                   |
| 119  | log                | 自然对数 - 用法: log(value)                                                                    |
| 120  | log10              | 常用对数（以10为底）- 用法: log10(value)                                                            |
| 121  | log1p              | 自然对数 log(1+x) - 用法: log1p(value)                                                         |
| 122  | max                | 取最大值                                                                                         |
| 123  | median             | 计算中位数 - 用法: median(numbers...)                                                           |
| 124  | min                | 取最小值                                                                                         |
| 125  | mode               | 计算众数（出现次数最多的数）- 用法: mode(numbers...)                                                 |
| 126  | mod                | 取模运算 - 用法: mod(a, b) 返回 a % b                                                            |
| 127  | multiply           | 乘法                                                                                           |
| 128  | parseBinary        | 二进制字符串转数字 - 用法: parseBinary(binaryStr)                                                   |
| 129  | parseHex           | 十六进制字符串转数字 - 用法: parseHex(hexStr)                                                        |
| 130  | percentile         | 计算百分位数 - 用法: percentile(numbers..., percentile)                                          |
| 131  | pow                | 幂运算 - 用法: pow(base, exponent)                                                              |
| 132  | range              | 计算极差（最大值-最小值）- 用法: range(numbers...)                                                 |
| 133  | round              | 四舍五入                                                                                         |
| 134  | roundTo            | 四舍五入到指定小数位 - 用法: roundTo(value, places)                                                |
| 135  | signum             | 符号函数 - 返回-1, 0, 1 - 用法: signum(value)                                                    |
| 136  | sin                | 正弦函数 - 用法: sin(radians)                                                                  |
| 137  | sinh               | 双曲正弦函数 - 用法: sinh(value)                                                                 |
| 138  | sqrt               | 平方根 - 用法: sqrt(value)                                                                    |
| 139  | stdDev             | 计算标准差 - 用法: stdDev(numbers...)                                                           |
| 140  | subtract           | 减法 - 用法: subtract(a, b, ...) 返回 a-b-...                                                  |
| 141  | tan                | 正切函数 - 用法: tan(radians)                                                                  |
| 142  | tanh               | 双曲正切函数 - 用法: tanh(value)                                                                 |
| 143  | toBinary           | 转二进制字符串 - 用法: toBinary(number)                                                           |
| 144  | toDegrees          | 弧度转角度 - 用法: toDegrees(radians)                                                           |
| 145  | toDouble           | 转换为双精度浮点数 - 用法: toDouble(value, defaultValue?)                                           |
| 146  | toFloat            | 转换为单精度浮点数 - 用法: toFloat(value, defaultValue?)                                            |
| 147  | toHex              | 转十六进制字符串 - 用法: toHex(number)                                                             |
| 148  | toInt              | 转换为整数 - 用法: toInt(value, defaultValue?)                                                  |
| 149  | toLong             | 转换为长整数 - 用法: toLong(value, defaultValue?)                                                |
| 150  | toNumberString      | 转换为数字字符串 - 用法: toNumberString(number, pattern?)                                          |
| 151  | toOctal            | 转八进制字符串 - 用法: toOctal(number)                                                            |
| 152  | toRadians          | 角度转弧度 - 用法: toRadians(degrees)                                                           |
| 153  | ulp                | 获取浮点数的最后一位单位精度 - 用法: ulp(value)                                                      |
| 154  | variance           | 计算方差 - 用法: variance(numbers...)                                                          |
| 155  | randomBoolean      | 生成随机布尔值 - 用法: randomBoolean() 或 randomBoolean(trueProbability?)                          |
| 156  | randomChoice       | 从列表中随机选择一个元素 - 用法: randomChoice(list) 或 randomChoice(elem1, elem2, ...)              |
| 157  | randomDouble       | 生成随机浮点数 - 用法: randomDouble() 或 randomDouble(min, max)                                    |
| 158  | random             | 从数组中随机取一个元素 - 用法: random(arr) 或 random(elem1, elem2, ...)                            |
| 159  | randomInt          | 生成随机整数 - 用法: randomInt() 或 randomInt(max) 或 randomInt(min, max)                          |
| 160  | randomIntArray     | 生成随机整数数组 - 用法: randomIntArray(size, min, max)                                            |
| 161  | randomLong         | 生成随机长整数 - 用法: randomLong() 或 randomLong(max) 或 randomLong(min, max)                      |
| 162  | randomSample       | 随机取样 - 用法: randomSample(list, count, allowRepeat?)                                       |
| 163  | shuffle            | 随机打乱列表/数组顺序 - 用法: shuffle(list)                                                          |
| 164  | randomString       | 生成随机字符串 - 用法: randomString(length)                                                       |
| 165  | randomUUID         | 生成随机UUID - 用法: randomUUID(withoutDashes?)                                                |
| 166  | abbreviate         | 缩写字符串 - 用法: abbreviate(str, maxWidth, ellipsis?)                                         |
| 167  | capitalize         | 首字母大写 - 用法: capitalize(str)                                                              |
| 168  | centerPad          | 居中对齐填充 - 用法: centerPad(str, size, padChar?)                                              |
| 169  | compareTo          | 字典序比较 - 用法: compareTo(str1, str2, ignoreCase?)                                           |
| 170  | concat             | 拼接多个字符串                                                                                      |
| 171  | contains           | 判断字符串是否包含子串                                                                              |
| 172  | tokenize           | 按多个分隔符分词 - 用法: tokenize(str, delimiters)                                                 |
| 173  | countChar          | 统计字符出现次数 - 用法: countChar(str, ch, ignoreCase?)                                           |
| 174  | countMatches       | 统计子串出现次数 - 用法: countMatches(str, sub, ignoreCase?)                                       |
| 175  | equalsAny          | 判断字符串是否等于任意一个目标 - 用法: equalsAny(str, target1, target2, ...)                          |
| 176  | equalsIgnoreCase   | 忽略大小写比较字符串 - 用法: equalsIgnoreCase(str1, str2)                                            |
| 177  | escapeHtml         | HTML转义 - 将特殊字符转换为HTML实体                                                                  |
| 178  | escapeRegex        | 转义正则表达式特殊字符                                                                              |
| 179  | format             | 格式化字符串 - 用法: format(pattern, arg1, arg2, ...)                                            |
| 180  | indexOf            | 查找子串首次出现的位置 - 用法: indexOf(str, search, fromIndex?)                                       |
| 181  | isAlpha            | 判断字符串是否只包含字母                                                                             |
| 182  | isAlphaNumeric     | 判断字符串是否只包含字母和数字                                                                          |
| 183  | isBlank            | 判断字符串是否为null、空或仅包含空白字符                                                               |
| 184  | isNumeric          | 判断字符串是否只包含数字                                                                             |
| 185  | isString           | 判断是否为字符串 - 用法: isString(value)                                                           |
| 186  | left               | 取左边N个字符 - 用法: left(str, n)                                                               |
| 187  | leftPad            | 左填充 - 用法: leftPad(str, size, padChar?)                                                     |
| 188  | length             | 获取字符串长度                                                                                      |
| 189  | levenshtein        | 计算Levenshtein编辑距离 - 用法: levenshtein(str1, str2)                                          |
| 190  | maskEmail          | 邮箱脱敏 - 用法: maskEmail(email) 例: te***@example.com                                         |
| 191  | mask               | 掩码处理 - 用法: mask(str, start, end, maskChar?)                                                |
| 192  | matches            | 正则匹配 - 用法: matches(str, regex)                                                             |
| 193  | mid                | 取中间部分 - 用法: mid(str, start, length?)                                                       |
| 194  | removeDuplicates   | 移除相邻重复字符 - 用法: removeDuplicates(str)                                                     |
| 195  | removeEnd          | 移除结尾后缀 - 用法: removeEnd(str, suffix, ignoreCase?)                                         |
| 196  | removeStart        | 移除开头前缀 - 用法: removeStart(str, prefix, ignoreCase?)                                       |
| 197  | removeWhitespace   | 移除所有空白字符                                                                                     |
| 198  | repeat             | 重复字符串 - 用法: repeat(str, count, separator?)                                               |
| 199  | repeatChar         | 重复字符 - 用法: repeatChar(ch, count)                                                         |
| 200  | replace            | 替换字符串 - 用法: replace(str, target, replacement)                                            |
| 201  | replaceAll         | 正则替换全部 - 用法: replaceAll(str, regex, replacement)                                         |
| 202  | reverse            | 反转字符串                                                                                        |
| 203  | right              | 取右边N个字符 - 用法: right(str, n)                                                              |
| 204  | rightPad           | 右填充 - 用法: rightPad(str, size, padChar?)                                                    |
| 205  | similarity         | 计算字符串相似度（百分比）- 用法: similarity(str1, str2)                                                |
| 206  | split              | 字符串分割 - 用法: split(str, regex)                                                            |
| 207  | splitByLength      | 按指定长度分割字符串 - 用法: splitByLength(str, chunkSize)                                           |
| 208  | substring          | 截取子串 - 用法: substring(str, beginIndex) 或 substring(str, beginIndex, endIndex)             |
| 209  | substringAfter     | 取指定子串之后的内容 - 用法: substringAfter(str, separator)                                          |
| 210  | substringBefore    | 取指定子串之前的内容 - 用法: substringBefore(str, separator)                                         |
| 211  | substringBetween   | 取两个子串之间的内容 - 用法: substringBetween(str, open, close)                                      |
| 212  | swapCase           | 大小写互换 - 大写转小写，小写转大写                                                                      |
| 213  | toCamelCase        | 转驼峰命名 - 用法: toCamelCase(str, firstUpper?)                                                |
| 214  | toLower            | 将字符串转换为小写                                                                                    |
| 215  | toSnakeCase        | 转蛇形命名 - 用法: toSnakeCase(str)                                                             |
| 216  | toString           | 转换为字符串 - 用法: toString(value, pattern?)                                                   |
| 217  | toUpper            | 将字符串转换为大写                                                                                    |
| 218  | trim               | 去除字符串首尾空格                                                                                    |
| 219  | uncapitalize       | 首字母小写 - 用法: uncapitalize(str)                                                            |
| 220  | unescapeHtml       | HTML反转义 - 将HTML实体还原为字符                                                                   |
| 221  | uniqueChars        | 保留唯一字符（按首次出现顺序）                                                                          |
| 222  | wordCount          | 统计单词数量 - 用法: wordCount(str)                                                              |
| 223  | translate          | 码值翻译 - 用法: translate(context, code, dictType, defaultValue?)                             |
| 224  | aesEncrypt         | [Crypto] AES加密 - 用法: aesEncrypt(data) 或 aesEncrypt(data, base64Key)                          |
| 225  | aesDecrypt         | [Crypto] AES解密 - 用法: aesDecrypt(encryptedData, base64Key)                                  |
| 226  | aesGenerateKey     | [Crypto] AES生成密钥 - 用法: aesGenerateKey() 返回Base64编码的密钥                                    |
| 227  | rsaEncrypt         | [Crypto] RSA加密 - 用法: rsaEncrypt(data, base64PublicKey)                                       |
| 228  | rsaDecrypt         | [Crypto] RSA解密 - 用法: rsaDecrypt(encryptedData, base64PrivateKey)                           |
| 229  | rsaGenerateKeyPair | [Crypto] RSA生成密钥对 - 用法: rsaGenerateKeyPair() 返回包含公钥和私钥的Map                               |
| 230  | rsaSign            | [Crypto] RSA签名 - 用法: rsaSign(data, base64PrivateKey)                                       |
| 231  | rsaVerify          | [Crypto] RSA验签 - 用法: rsaVerify(data, signature, base64PublicKey)                           |
| 232  | eccEncrypt         | [Crypto] ECC加密 - 用法: eccEncrypt(data, base64PublicKey)                                       |
| 233  | eccDecrypt         | [Crypto] ECC解密 - 用法: eccDecrypt(encryptedData, base64PrivateKey)                           |
| 234  | eccGenerateKeyPair | [Crypto] ECC生成密钥对 - 用法: eccGenerateKeyPair() 返回包含公钥和私钥的Map                               |
| 235  | eccSign            | [Crypto] ECC签名 - 用法: eccSign(data, base64PrivateKey)                                       |
| 236  | eccVerify          | [Crypto] ECC验签 - 用法: eccVerify(data, signature, base64PublicKey)                           |
| 237  | formatDate         | 格式化日期 - 用法: formatDate(date, pattern)                                                        |
| 238  | now                | 获取当前日期时间                                                                                     |
| 239  | parseDate          | 解析日期字符串 - 用法: parseDate(dateStr, pattern)                                                    |
| 240  | timestamp          | 获取当前时间戳                                                                                      |
| 241  | today              | 获取当前日期                                                                                       |
| 242  | toIsoString        | 转ISO格式字符串 - 用法: toIsoString(date)                                                            |
| 243  | complexAdd         | 复数加法 - 用法: complexAdd(r1, i1, r2, i2) 返回 [实部, 虚部]                                        |
| 244  | complexMultiply    | 复数乘法 - 用法: complexMultiply(r1, i1, r2, i2) 返回 [实部, 虚部]                                   |
| 245  | matrixAdd         | 矩阵加法 - 用法: matrixAdd(matrix1, matrix2)                                                       |
| 246  | toJson             | 将对象转换为JSON字符串                                                                                |
| 247  | randomColor        | 生成随机颜色 - 用法: randomColor(type?) type: 'hex', 'rgb', 'preset'                                 |
| 248  | randomDate         | 生成随机日期 - 用法: randomDate(startDate, endDate, pattern?)                                        |
| 249  | base64Decode       | Base64解码 - 用法: base64Decode(encodedStr)                                                      |
| 250  | base64Encode       | Base64编码 - 用法: base64Encode(str)                                                             |
| 251  | decodeUrl          | URL解码 - 用法: decodeUrl(str)                                                                   |
| 252  | encodeUrl          | URL编码 - 用法: encodeUrl(str)                                                                   |
| 253  | md5                | MD5加密 - 用法: md5(str)                                                                         |
| 254  | isEmail            | 判断是否为有效的邮箱地址                                                                                 |
