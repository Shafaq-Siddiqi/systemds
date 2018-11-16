/*
 * Modifications Copyright 2018 Graz University of Technology
 * 
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


package org.tugraz.sysds.runtime.data;

import org.apache.commons.lang.NotImplementedException;
import org.tugraz.sysds.runtime.util.UtilFunctions;

public abstract class DenseBlockFactory
{
	public static DenseBlock createDenseBlock(int[] dims) {
		DenseBlock.Type type = 
			(UtilFunctions.prod(dims) < Integer.MAX_VALUE) ?
			DenseBlock.Type.DRB : DenseBlock.Type.LDRB;
		return createDenseBlock(type, dims);
	}
	
	public static DenseBlock createDenseBlock(int rlen, int clen) {
		return createDenseBlock(new int[]{rlen, clen});
	}

	public static DenseBlock createDenseBlock(double[] data, int[] dims) {
		return new DenseBlockFP64(dims, data);
	}
	
	public static DenseBlock createDenseBlock(double[] data, int rlen, int clen) {
		return createDenseBlock(data, new int[]{rlen, clen});
	}
	
	public static DenseBlock createDenseBlock(DenseBlock.Type type, int[] dims) {
		switch( type ) {
			case DRB: return new DenseBlockFP64(dims);
			case LDRB: throw new NotImplementedException();
			default:
				throw new RuntimeException("Unexpected dense block type: "+type.name());
		}
	}

	public static boolean isDenseBlockType(DenseBlock sblock, DenseBlock.Type type) {
		return (getDenseBlockType(sblock) == type);
	}

	public static DenseBlock.Type getDenseBlockType(DenseBlock dblock) {
		return (dblock instanceof DenseBlockDRB) ? DenseBlock.Type.DRB :
			(dblock instanceof DenseBlockDRB) ? DenseBlock.Type.LDRB : null; //TODO
	}
}