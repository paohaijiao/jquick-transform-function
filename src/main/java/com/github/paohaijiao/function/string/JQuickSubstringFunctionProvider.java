package com.github.paohaijiao.function.string;


import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickSubstringFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickSubstringFunctionProvider() {
        super("substring", "截取子串 - 用法: substring(str, beginIndex) 或 substring(str, beginIndex, endIndex)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 2, 3);
        String str = asString(args.get(0));
        if (str == null) return null;
        int beginIndex = asInt(args.get(1));
        if (args.size() == 2) {
            return str.substring(beginIndex);
        } else {
            int endIndex = asInt(args.get(2));
            return str.substring(beginIndex, endIndex);
        }
    }
}
