package com.github.paohaijiao.function.string;
// ReplaceInvoker.java

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickReplaceInvoker extends JQuickBaseMethodInvoker {

    public JQuickReplaceInvoker() {
        super("replace", "替换字符串 - 用法: replace(str, target, replacement)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 3);
        String str = asString(args.get(0));
        String target = asString(args.get(1));
        String replacement = asString(args.get(2));
        if (str == null || target == null) return str;
        return str.replace(target, replacement == null ? "" : replacement);
    }
}
