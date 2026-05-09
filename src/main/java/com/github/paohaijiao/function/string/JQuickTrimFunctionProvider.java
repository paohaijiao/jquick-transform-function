package com.github.paohaijiao.function.string;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickTrimFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickTrimFunctionProvider() {
        super("trim", "去除字符串首尾空格");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String str = asString(args.get(0));
        return str != null ? str.trim() : null;
    }
}
