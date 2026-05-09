package com.github.paohaijiao.function.string;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickContainsFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickContainsFunctionProvider() {
        super("contains", "判断字符串是否包含子串");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String str = asString(args.get(0));
        String substr = asString(args.get(1));
        return str != null && substr != null && str.contains(substr);
    }
}
