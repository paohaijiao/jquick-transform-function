package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickAbsFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickAbsFunctionProvider() {
        super("abs", "绝对值");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        return Math.abs(asDouble(args.get(0)));
    }
}
