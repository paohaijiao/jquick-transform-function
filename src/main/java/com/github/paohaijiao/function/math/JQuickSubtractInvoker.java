package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickSubtractInvoker extends JQuickBaseMethodInvoker {

    public JQuickSubtractInvoker() {
        super("subtract", "减法 - 用法: subtract(a, b, ...) 返回 a-b-...");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return 0.0;
        double result = asDouble(args.get(0));
        for (int i = 1; i < args.size(); i++) {
            result -= asDouble(args.get(i));
        }
        return result;
    }
}
