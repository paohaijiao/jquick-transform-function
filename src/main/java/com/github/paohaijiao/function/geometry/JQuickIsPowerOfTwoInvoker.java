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
package com.github.paohaijiao.function.geometry;

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickIsPowerOfTwoInvoker extends JQuickBaseMethodInvoker {

    public JQuickIsPowerOfTwoInvoker() {
        super("isPowerOfTwo", "判断是否为2的幂 - 用法: isPowerOfTwo(n)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        long n = asLong(args.get(0));
        return n > 0 && (n & (n - 1)) == 0;
    }
}
