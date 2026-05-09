/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.function.condition;
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickEqIFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickEqIFunctionProvider() {
        super("eq", "相等判断 - 用法: eq(a, b, ignoreCase?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 2, 3);
        boolean ignoreCase = args.size() > 2 && asBoolean(args.get(2));

        Object a = args.get(0);
        Object b = args.get(1);

        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        if (ignoreCase && a instanceof String && b instanceof String) {
            return ((String) a).equalsIgnoreCase((String) b);
        }

        return a.equals(b);
    }
}
