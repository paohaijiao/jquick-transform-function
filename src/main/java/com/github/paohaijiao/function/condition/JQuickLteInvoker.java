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

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickLteInvoker extends JQuickBaseMethodInvoker {

    public JQuickLteInvoker() {
        super("lte", "小于等于判断 - 用法: lte(a, b)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        double a = asDouble(args.get(0));
        double b = asDouble(args.get(1));
        return a <= b;
    }
}
