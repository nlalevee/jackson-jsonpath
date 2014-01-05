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
package com.fasterxml.jackson.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.jsonpath.internal.JsonPathEvaluator;

public class JsonPathNoValue extends JsonPathValue {

    public static final JsonPathNoValue INSTANCE = new JsonPathNoValue();

    @Override
    public JsonPathValue apply(JsonPathEvaluator evaluator) {
        return INSTANCE;
    }

    @Override
    public void addTo(JsonPathMultiValue ret) {
        // nothing to do
    }

    @Override
    public JsonNode toNode() {
        return JsonNodeFactory.instance.nullNode();
    }
}
