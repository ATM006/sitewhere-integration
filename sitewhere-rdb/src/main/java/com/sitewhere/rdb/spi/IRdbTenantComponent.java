/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rdb.spi;

import javax.persistence.EntityManager;

public interface IRdbTenantComponent<T extends IRdbClient> {

    /**
     * Get RDB client associated with component.
     * 
     * @return
     */
    T getClient();

    /**
     * Get provider that exposes {@link EntityManager} used for database operations.
     * 
     * @return
     */
    IRdbEntityManagerProvider getEntityManagerProvider();
}
