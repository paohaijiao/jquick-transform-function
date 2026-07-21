package com.github.paohaijiao.function.string;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.List;

/**
 * 分组连接函数 - 将多个字符串连接成一个
 * 类似 SQL 的 GROUP_CONCAT 或 STRING_AGG
 * 用法：groupConcat(",", "a", "b", "c") => "a,b,c"
 *       groupConcat(" - ", "apple", "banana", "orange") => "apple - banana - orange"
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickGroupConcatFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickGroupConcatFunctionProvider() {
        super("groupConcat", "[SQL] 分组连接 - 使用指定的分隔符合并字符串，第一个参数为分隔符");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 2, Integer.MAX_VALUE);
        String separator = asString(args.get(0));
        if (separator == null) {
            separator = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.size(); i++) {
            if (i > 1) {
                sb.append(separator);
            }
            sb.append(asString(args.get(i)));
        }
        return sb.toString();
    }
}