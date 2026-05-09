package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickFloorFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickFloorFunctionProvider() {
        super("floor", "向下取整");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        return Math.floor(asDouble(args.get(0)));
    }
}
