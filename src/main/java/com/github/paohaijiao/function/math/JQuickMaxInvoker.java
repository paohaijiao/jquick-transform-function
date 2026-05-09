package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickMaxInvoker extends JQuickBaseMethodInvoker {

    public JQuickMaxInvoker() {
        super("max", "取最大值");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return null;
        double max = asDouble(args.get(0));
        for (Object arg : args) {
            max = Math.max(max, asDouble(arg));
        }
        return max;
    }
}
