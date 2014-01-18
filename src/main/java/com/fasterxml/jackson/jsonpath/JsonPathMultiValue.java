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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class JsonPathMultiValue extends JsonPathValue {

    private List<JsonNode> nodes = new ArrayList<JsonNode>();

    @Override
    public void addTo(JsonPathMultiValue ret) {
        for (JsonNode node : nodes) {
            ret.add(node);
        }
    }

    public void add(JsonNode node) {
        nodes.add(node);
    }

    @Override
    public ArrayNode toNode() {
        ArrayNode array = JsonNodeFactory.instance.arrayNode();
        array.addAll(nodes);
        return array;
    }

    public List<JsonNode> getNodes() {
        return nodes;
    }
}
