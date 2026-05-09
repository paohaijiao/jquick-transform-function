package com.github.paohaijiao.function.math;


import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickMultiplyFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickMultiplyFunctionProvider() {
        super("multiply", "乘法");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return 1.0;
        double result = 1.0;
        for (Object arg : args) {
            result *= asDouble(arg);
        }
        return result;
    }
}
