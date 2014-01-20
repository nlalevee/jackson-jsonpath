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
import com.fasterxml.jackson.jsonpath.JsonPathVectorValue;

class DescendingJPE extends JsonPathExpression {

    private JsonPathExpression object;

    private String field;

    DescendingJPE(int position, JsonPathExpression object, String field) {
        super(position, true);
        this.object = object;
        this.field = field;
    }

    @Override
    public JsonPathValue eval(JsonPathContext context) {
        return evalAsDotProduct(context, object);
    }

    @Override
    JsonPathValue compute(JsonPathContext context, JsonNode[] childValues) {
        JsonPathVectorValue ret = new JsonPathVectorValue();
        descend(childValues[0], ret);
        return ret;
    }

    private void descend(JsonNode node, JsonPathVectorValue result) {
        if (node.isContainerNode()) {
            if (field == null) {
                result.add(node);
            } else if (node.has(field)) {
                result.add(node.get(field));
            }
            for (JsonNode value : node) {
                descend(value, result);
            }
        }
    }

    @Override
    public String toString() {
        return object.toString() + "..";
    }
}
