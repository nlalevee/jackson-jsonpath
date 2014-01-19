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
import com.fasterxml.jackson.jsonpath.JsonPathRuntimeException;
import com.fasterxml.jackson.jsonpath.JsonPathValue;
import com.fasterxml.jackson.jsonpath.JsonPathVectorValue;

class WildcardJPE extends JsonPathExpression {

    private JsonPathExpression object;

    WildcardJPE(int position, JsonPathExpression object) {
        super(position);
        this.object = object;
    }

    @Override
    boolean isVector() {
        return true;
    }

    @Override
    public JsonPathValue eval(JsonPathContext context) {
        return evalAsDotProduct(context, object);
    }

    @Override
    JsonPathValue compute(JsonPathContext context, JsonNode[] childValues) {
        JsonNode node = childValues[0];
        JsonPathVectorValue ret = new JsonPathVectorValue();
        if (node.isArray()) {
            for (JsonNode current : node) {
                for (JsonNode value : current) {
                    ret.add(value);
                }
            }
        } else if (node.isObject()) {
            for (JsonNode subNode : node) {
                ret.add(subNode);
            }
        } else {
            throw new JsonPathRuntimeException("wilcard cannot be applied to "
                    + node.getNodeType().toString().toLowerCase(), position);
        }
        return ret;
    }

    @Override
    public String toString() {
        return object.toString() + ".*";
    }
}
