/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.warp10;

import java.util.List;

import com.sitewhere.microservice.lifecycle.TenantEngineLifecycleComponent;
import com.sitewhere.microservice.lifecycle.parameters.StringComponentParameter;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.microservice.lifecycle.IDiscoverableTenantLifecycleComponent;
import com.sitewhere.spi.microservice.lifecycle.ILifecycleComponentParameter;
import com.sitewhere.spi.microservice.lifecycle.ILifecycleProgressMonitor;
import com.sitewhere.warp10.rest.GTSInput;
import com.sitewhere.warp10.rest.GTSOutput;
import com.sitewhere.warp10.rest.QueryParams;
import com.sitewhere.warp10.rest.Warp10RestClient;

/**
 * Client used for connecting to and interacting with an Warp 10 server.
 */
public class Warp10DbClient extends TenantEngineLifecycleComponent implements IDiscoverableTenantLifecycleComponent {

    /** Warp10 configuration parameters */
    private Warp10Configuration configuration;

    /** Hostname parameter */
    private ILifecycleComponentParameter<String> hostname;

    /** Port parameter */
    private ILifecycleComponentParameter<String> tokenSecret;

    private Warp10RestClient warp10RestClient;

    public Warp10DbClient(Warp10Configuration configuration) {
	this.configuration = configuration;
    }

    /*
     * @see
     * com.sitewhere.microservice.lifecycle.LifecycleComponent#initializeParameters(
     * )
     */
    @Override
    public void initializeParameters() throws SiteWhereException {
	// Add hostname.
	this.hostname = StringComponentParameter.newBuilder(this, "Hostname").value(getConfiguration().getHostname())
		.makeRequired().build();
	getParameters().add(hostname);

	// Add token secret.
	this.tokenSecret = StringComponentParameter.newBuilder(this, "Token secret")
		.value(getConfiguration().getTokenSecret()).makeRequired().build();
	getParameters().add(tokenSecret);
    }

    /*
     * @see com.sitewhere.microservice.lifecycle.LifecycleComponent#initialize(com.
     * sitewhere.spi.microservice.lifecycle.ILifecycleProgressMonitor)
     */
    @Override
    public void initialize(ILifecycleProgressMonitor monitor) throws SiteWhereException {
	super.start(monitor);
	this.warp10RestClient = Warp10RestClient.newBuilder().withConnectionTo(configuration.getHostname(),
		configuration.getTokenSecret(), getTenantEngine().getName()).build();
    }

    public int insertGTS(GTSInput gtsInput) {
	return warp10RestClient.ingress(gtsInput);
    }

    public List<GTSOutput> findGTS(QueryParams queryParams) {
	return warp10RestClient.fetch(queryParams);
    }

    @Override
    public boolean isRequired() {
	return false;
    }

    public Warp10Configuration getConfiguration() {
	return configuration;
    }

    public void setConfiguration(Warp10Configuration configuration) {
	this.configuration = configuration;
    }
}
