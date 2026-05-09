package com.github.paohaijiao.function.math;


import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickMinFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickMinFunctionProvider() {
        super("min", "取最小值");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return null;
        double min = asDouble(args.get(0));
        for (Object arg : args) {
            min = Math.min(min, asDouble(arg));
        }
        return min;
    }
}
