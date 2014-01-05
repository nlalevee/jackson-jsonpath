/*
 * Copyright 2012-2014 the original author or authors.
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
/*
BSD License

Copyright (c) 2000-2006, www.hamcrest.org
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of
conditions and the following disclaimer. Redistributions in binary form must reproduce
the above copyright notice, this list of conditions and the following disclaimer in
the documentation and/or other materials provided with the distribution.

Neither the name of Hamcrest nor the names of its contributors may be used to endorse
or promote products derived from this software without specific prior written
permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
DAMAGE.
 */
package com.fasterxml.jackson.jsonpath.jsonassert.impl.matcher;

import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Map;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class IsMapContainingValue<V> extends MapTypeSafeMatcher<Map< ? , V>> {
    private final Matcher< ? super V> valueMatcher;

    public IsMapContainingValue(Matcher< ? super V> valueMatcher) {
        this.valueMatcher = valueMatcher;
    }

    @Override
    public boolean matchesSafely(Map< ? , V> item) {
        for (V value : item.values()) {
            if (valueMatcher.matches(value)) {
                return true;
            }
        }
        return false;
    }

    public void describeTo(Description description) {
        description.appendText("map with value ").appendDescriptionOf(valueMatcher);
    }

    @Factory
    public static <V> Matcher< ? super Map< ? , V>> hasValue(V value) {
        return IsMapContainingValue.<V> hasValue(equalTo(value));
    }

    @Factory
    public static <V> Matcher< ? super Map< ? , V>> hasValue(Matcher< ? super V> valueMatcher) {
        return new IsMapContainingValue<V>(valueMatcher);
    }
}
