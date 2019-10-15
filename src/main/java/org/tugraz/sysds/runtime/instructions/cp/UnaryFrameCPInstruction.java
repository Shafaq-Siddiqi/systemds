/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.tugraz.sysds.runtime.instructions.cp;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import org.tugraz.sysds.common.Types.ValueType;
import org.tugraz.sysds.runtime.controlprogram.caching.FrameObject;
import org.tugraz.sysds.runtime.controlprogram.context.ExecutionContext;
import org.tugraz.sysds.runtime.matrix.data.FrameBlock;
import org.tugraz.sysds.runtime.matrix.data.LibCommonsMath;
import org.tugraz.sysds.runtime.matrix.data.MatrixBlock;
import org.tugraz.sysds.runtime.matrix.operators.Operator;
import org.tugraz.sysds.runtime.matrix.operators.UnaryOperator;
import org.tugraz.sysds.runtime.util.UtilFunctions;
import scala.Array;

import java.util.Arrays;
import java.util.Iterator;

public class UnaryFrameCPInstruction extends UnaryCPInstruction {
    protected UnaryFrameCPInstruction(Operator op, CPOperand in, CPOperand out, String opcode, String instr) {

        super(CPType.Unary, op, in, out, opcode, instr);
    }

    @Override
    public void processInstruction(ExecutionContext ec) {
        FrameBlock inBlock = ec.getFrameInput(input1.getName());
        FrameBlock retBlock = inBlock.getSchemaTypeOf();
        ec.releaseFrameInput(input1.getName());
        ec.setFrameOutput(output.getName(), retBlock);


    }





}
