package com.github.paohaijiao.function.math;


import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickAddFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickAddFunctionProvider() {
        super("add", "求和");
    }

    @Override
    public Object invoke(List<Object> args) {
        double sum = 0;
        for (Object arg : args) {
            sum += asDouble(arg);
        }
        return sum;
    }
}
