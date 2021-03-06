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
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.jsonpath.JsonPathRuntimeException;
import com.fasterxml.jackson.jsonpath.JsonPathValue;

class FieldSelectorJPE extends JsonPathExpression {

    private JsonPathExpression object;

    private JsonPathExpression index;

    FieldSelectorJPE(int position, JsonPathExpression object, String id) {
        this(position, object, new LiteralJPE(position, JsonNodeFactory.instance.textNode(id)));
    }

    FieldSelectorJPE(int position, JsonPathExpression object, JsonPathExpression index) {
        super(position, object.isVector());
        this.object = object;
        this.index = index;
    }

    @Override
    public JsonPathValue eval(JsonPathContext context) {
        return evalAsDotProduct(context, object);
    }

    @Override
    JsonNode computeNode(JsonPathContext context, JsonNode[] childValues) {
        JsonNode o = childValues[0];
        JsonNode i = index.eval(context).asNode();
        if (i.isTextual()) {
            if (!o.isObject()) {
                throw new JsonPathRuntimeException("field selector must apply on an object, not a "
                        + o.getNodeType().toString().toLowerCase(), position);
            }
            return o.path(asString(i, "index of selector"));
        }
        if (i.isNumber()) {
            if (!o.isArray()) {
                throw new JsonPathRuntimeException("field selector must apply on an array, not a "
                        + o.getNodeType().toString().toLowerCase(), position);
            }
            int n = i.asInt();
            if (n >= o.size()) {
                throw new JsonPathRuntimeException("index out of bound " + n + " > " + (o.size() - 1), position);
            }
            if (n < -o.size()) {
                throw new JsonPathRuntimeException("index out of bound " + n + " < " + (-o.size()), position);
            }
            if (n < 0) {
                return o.get(o.size() + n);
            }
            return o.get(n);
        }
        throw new TypeMismatchException(position, JsonNodeType.NUMBER, o, "the index of the array");
    }

    @Override
    public String toString() {
        return object.toString() + "[(" + index.toString() + ")]";
    }
}
