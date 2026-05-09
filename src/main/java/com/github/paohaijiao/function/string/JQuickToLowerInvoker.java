package com.github.paohaijiao.function.string;


import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToLowerInvoker extends JQuickBaseMethodInvoker {

    public JQuickToLowerInvoker() {
        super("toLower", "将字符串转换为小写");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String str = asString(args.get(0));
        return str != null ? str.toLowerCase() : null;
    }
}
