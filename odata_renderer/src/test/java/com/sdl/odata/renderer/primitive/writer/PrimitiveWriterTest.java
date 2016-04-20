/**
 * Copyright (c) 2014 All Rights Reserved by the SDL Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sdl.odata.renderer.primitive.writer;

import com.sdl.odata.api.edm.model.PrimitiveType;
import com.sdl.odata.parser.ODataParserImpl;
import com.sdl.odata.renderer.WriterTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing {@link PrimitiveWriter}.
 */
public class PrimitiveWriterTest extends WriterTest {

    @Test
    public void testWriteLongValue() throws Exception {
        odataUri = new ODataParserImpl().parseUri("http://localhost:8080/odata.svc/Products(1)", entityDataModel);
        PrimitiveWriter writer = new PrimitiveWriter(odataUri, entityDataModel);
        String result = writer.generatePrimitiveProperty(5L, PrimitiveType.INT64);

        assertEquals("5", result);
    }
}
