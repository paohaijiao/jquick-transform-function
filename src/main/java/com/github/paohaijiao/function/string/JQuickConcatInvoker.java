package com.github.paohaijiao.function.string;

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickConcatInvoker extends JQuickBaseMethodInvoker {

    public JQuickConcatInvoker() {
        super("concat", "拼接多个字符串");
    }

    @Override
    public Object invoke(List<Object> args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (arg != null) {
                sb.append(arg);
            }
        }
        return sb.toString();
    }
}
