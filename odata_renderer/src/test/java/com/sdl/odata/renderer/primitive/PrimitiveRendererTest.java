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
package com.sdl.odata.renderer.primitive;

import com.sdl.odata.api.ODataException;
import com.sdl.odata.api.parser.ODataUri;
import com.sdl.odata.api.processor.query.QueryResult;
import com.sdl.odata.api.service.ODataRequest;
import com.sdl.odata.api.service.ODataRequestContext;
import com.sdl.odata.api.service.ODataResponse;
import com.sdl.odata.renderer.RendererTest;
import com.sdl.odata.test.model.Customer;
import com.sdl.odata.test.util.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static com.sdl.odata.test.util.TestUtils.SERVICE_ROOT;
import static com.sdl.odata.test.util.TestUtils.createODataRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test to covering {@link PrimitiveRenderer}.
 */
public class PrimitiveRendererTest extends RendererTest {

    private PrimitiveRenderer primitiveRenderer;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        primitiveRenderer = new PrimitiveRenderer();
    }

    @Test
    public void testCountPathScore() throws UnsupportedEncodingException {
        ODataUri countPathODataUri = TestUtils.createODataCountEntitiesUri(SERVICE_ROOT, "Customers");
        ODataRequestContext oDataRequestContext = TestUtils.createODataRequestContext(
                createODataRequest(ODataRequest.Method.GET), countPathODataUri, entityDataModel);

        int score = primitiveRenderer.score(oDataRequestContext, QueryResult.from(5L));
        assertTrue(score > 0);
    }

    @Test
    public void testValuePathScore() throws UnsupportedEncodingException {
        ODataUri customerPhoneUri = TestUtils.createODataValueEntitiesUri(SERVICE_ROOT, "Customers", Customer.PHONE);
        ODataRequestContext oDataRequestContext = TestUtils.createODataRequestContext(
                createODataRequest(ODataRequest.Method.GET), customerPhoneUri, entityDataModel);

        int score = primitiveRenderer.score(oDataRequestContext, QueryResult.from(5L));
        assertTrue(score > 0);
    }

    @Test
    public void testPrimitiveRender() throws UnsupportedEncodingException, ODataException {
        ODataUri countPathODataUri = TestUtils.createODataCountEntitiesUri(SERVICE_ROOT, "Customers");
        ODataRequestContext requestContext = TestUtils.createODataRequestContext(
                createODataRequest(ODataRequest.Method.GET), countPathODataUri, entityDataModel);

        ODataResponse.Builder builder = new ODataResponse.Builder();
        primitiveRenderer.render(requestContext, QueryResult.from(5L), builder);
        builder.setStatus(ODataResponse.Status.OK);

        assertEquals("5", builder.build().getBodyText(StandardCharsets.UTF_8.name()));
    }
}
