/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rdb.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sitewhere.spi.batch.ElementProcessingStatus;
import com.sitewhere.spi.batch.IBatchElement;

@Entity
@Table(name = "batch_element")
public class BatchElement implements IBatchElement {

    /** Serial version UID */
    private static final long serialVersionUID = 90247662858403978L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    /** Batch operation id */
    @Column(name = "batch_operation_id")
    private UUID batchOperationId;

    /** Device id */
    @Column(name = "device_id")
    private UUID deviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "processing_status")
    private ElementProcessingStatus processingStatus;

    /** Date entity was created */
    @Column(name = "processed_date")
    private Date processedDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @CollectionTable(name = "batch_element_metadata", joinColumns = @JoinColumn(name = "batch_element_id"))
    @MapKeyColumn(name = "prop_key")
    @Column(name = "prop_value")
    private Map<String, String> metadata = new HashMap<>();

    @Override
    public UUID getId() {
	return id;
    }

    @Override
    public UUID getBatchOperationId() {
	return batchOperationId;
    }

    @Override
    public UUID getDeviceId() {
	return deviceId;
    }

    @Override
    public ElementProcessingStatus getProcessingStatus() {
	return processingStatus;
    }

    @Override
    public Date getProcessedDate() {
	return processedDate;
    }

    @Override
    public Map<String, String> getMetadata() {
	return metadata;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    public void setBatchOperationId(UUID batchOperationId) {
	this.batchOperationId = batchOperationId;
    }

    public void setDeviceId(UUID deviceId) {
	this.deviceId = deviceId;
    }

    public void setProcessingStatus(ElementProcessingStatus processingStatus) {
	this.processingStatus = processingStatus;
    }

    public void setProcessedDate(Date processedDate) {
	this.processedDate = processedDate;
    }

    public void setMetadata(Map<String, String> metadata) {
	this.metadata = metadata;
    }
}
