/*
 * Copyright 2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fasterxml.jackson.jsonpath.internal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.jsonpath.JsonPathValue;

class BooleanJPE extends JsonPathExpression {

    enum BooleanOp {
        OR("||"), AND("&&");

        private String sign;

        private BooleanOp(String sign) {
            this.sign = sign;
        }
    }

    private BooleanOp op;

    private JsonPathExpression left;
    
    private JsonPathExpression right;

    BooleanJPE(int position, BooleanOp op, JsonPathExpression left, JsonPathExpression right) {
        super(position);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    boolean isVector() {
        return isVectorFromDotProduct(left, right);
    }

    @Override
    public JsonPathValue eval(JsonPathContext context) {
        return evalAsDotProduct(context, left, right);
    }

    @Override
    Object computeObject(JsonPathContext context, JsonNode[] childValues) {
        boolean b1 = asBoolean(childValues[0], "boolean op '", op.sign, "'");
        switch (op) {
        case AND: {
            if (!b1) {
                return false;
            }
            boolean b2 = asBoolean(childValues[1], "boolean op '", op.sign, "'");
            return b2;
        }
        case OR: {
            if (b1) {
                return true;
            }
            boolean b2 = asBoolean(childValues[1], "boolean op '", op.sign, "'");
            return b2;
        }
        default:
            throw new IllegalStateException("unsupported op " + op);
        }
    }

    @Override
    public String toString() {
        return left.toString() + ' ' + op.sign + ' ' + right.toString();
    }
}
