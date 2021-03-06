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

import java.util.Collections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;

public class JsonPathSingleValue extends JsonPathValue {

    public static final JsonPathSingleValue EMPTY = new JsonPathSingleValue(MissingNode.getInstance());

    private JsonNode node;

    public JsonPathSingleValue(JsonNode node) {
        this.node = node;
    }

    @Override
    public void addTo(JsonPathVectorValue ret) {
        if (!node.isMissingNode()) {
            ret.add(node, null);
        }
    }

    @Override
    public JsonNode asNode() {
        return node;
    }

    @Override
    public Iterable<JsonNode> getNodes() {
        if (node.isMissingNode()) {
            return Collections.emptyList();
        }
        return Collections.singletonList(node);
    }
}
