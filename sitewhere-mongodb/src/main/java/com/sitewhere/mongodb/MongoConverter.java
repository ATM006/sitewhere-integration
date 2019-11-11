/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.mongodb;

import org.bson.Document;

/**
 * Interface for classes that can convert Mongo {@link Document} to SiteWhere
 * SPI objects.
 * 
 * @param <I>
 */
public interface MongoConverter<I> {

    /**
     * Create a Mongo {@link Document} based on SPI input.
     * 
     * @param source
     * @return
     */
    public Document convert(I source);

    /**
     * Create the REST object from a Mongo DBObject.
     * 
     * @param source
     * @return
     */
    public I convert(Document source);
}