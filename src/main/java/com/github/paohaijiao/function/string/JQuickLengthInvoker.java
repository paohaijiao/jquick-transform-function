package com.github.paohaijiao.function.string;

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickLengthInvoker extends JQuickBaseMethodInvoker {

    public JQuickLengthInvoker() {
        super("length", "获取字符串长度");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String str = asString(args.get(0));
        return str != null ? str.length() : 0;
    }
}
