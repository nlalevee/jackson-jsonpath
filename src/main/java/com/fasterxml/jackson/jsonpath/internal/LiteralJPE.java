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
import com.fasterxml.jackson.jsonpath.JsonPathSingleValue;
import com.fasterxml.jackson.jsonpath.JsonPathValue;

public class LiteralJPE extends JsonPathExpression {

    private JsonNode literal;

    LiteralJPE(int position, JsonNode literal) {
        super(position, false);
        this.literal = literal;
    }

    @Override
    public JsonPathValue eval(JsonPathContext context) {
        return new JsonPathSingleValue(literal);
    }

    @Override
    public String toString() {
        return literal.toString();
    }

    public JsonNode getLiteral() {
        return literal;
    }
}
