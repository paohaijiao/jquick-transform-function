package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickCeilFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickCeilFunctionProvider() {
        super("ceil", "向上取整");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        return Math.ceil(asDouble(args.get(0)));
    }
}
