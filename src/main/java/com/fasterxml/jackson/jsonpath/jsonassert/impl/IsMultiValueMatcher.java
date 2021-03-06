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
package com.fasterxml.jackson.jsonpath.jsonassert.impl;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import com.fasterxml.jackson.jsonpath.JsonPathVectorValue;
import com.fasterxml.jackson.jsonpath.JsonPathValue;

public class IsMultiValueMatcher extends BaseMatcher<JsonPathValue> {

    @Override
    public boolean matches(Object item) {
        return item instanceof JsonPathVectorValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an multi jsonpath value");
    }

}
