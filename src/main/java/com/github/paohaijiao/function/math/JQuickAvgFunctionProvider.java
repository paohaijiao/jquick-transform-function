package com.github.paohaijiao.function.math;



import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickAvgFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickAvgFunctionProvider() {
        super("avg", "平均值");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return 0.0;
        double sum = 0;
        for (Object arg : args) {
            sum += asDouble(arg);
        }
        return sum / args.size();
    }
}
