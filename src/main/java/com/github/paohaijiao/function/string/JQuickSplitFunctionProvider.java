package com.github.paohaijiao.function.string;


import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickSplitFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickSplitFunctionProvider() {
        super("split", "字符串分割 - 用法: split(str, regex)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String str = asString(args.get(0));
        String regex = asString(args.get(1));

        if (str == null || regex == null) return new String[0];
        return str.split(regex);
    }
}
