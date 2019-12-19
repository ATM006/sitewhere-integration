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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sitewhere.spi.device.DeviceContainerPolicy;
import com.sitewhere.spi.device.IDeviceType;
import com.sitewhere.spi.device.element.IDeviceElementSchema;

@Entity
@Table(name = "device_type")
public class DeviceType implements IDeviceType {

    /** Serial version UID */
    private static final long serialVersionUID = 9005645694403373144L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    /** Name */
    @Column(name = "name")
    private String name;

    /** Decription */
    @Column(name = "description")
    private String description;

    /** Device container policy */
    @Column(name = "container_policy")
    @Enumerated(EnumType.STRING)
    private DeviceContainerPolicy containerPolicy = DeviceContainerPolicy.Standalone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_element_schema_id", referencedColumnName = "id")
    private DeviceElementSchema deviceElementSchema;

    /** Date entity was last updated */
    @Column(name = "background_color")
    private String backgroundColor;

    /** Foreground color */
    @Column(name = "foreground_color")
    private String foregroundColor;

    /** Border color */
    @Column(name = "border_color")
    private String borderColor;

    /** Image URL */
    @Column(name = "image_url")
    private String imageUrl;

    /** Icon */
    @Column(name = "icon")
    private String icon;

    /** Unique token */
    @Column(name = "token")
    private String token;

    /** Date entity was created */
    @Column(name = "created_date")
    private Date createdDate;

    /** Username for creator */
    @Column(name = "created_by")
    private String createdBy;

    /** Date entity was last updated */
    @Column(name = "updated_date")
    private Date updatedDate;

    /** Username that updated entity */
    @Column(name = "updated_by")
    private String updatedBy;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @CollectionTable(name = "device_type_metadata", joinColumns = @JoinColumn(name = "device_type_id"))
    @MapKeyColumn(name = "prop_key")
    @Column(name = "prop_value")
    private Map<String, String> metadata = new HashMap<>();

    @Override
    public DeviceContainerPolicy getContainerPolicy() {
	return containerPolicy;
    }

    @Override
    public IDeviceElementSchema getDeviceElementSchema() {
	return deviceElementSchema;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public String getDescription() {
	return description;
    }

    @Override
    public String getBackgroundColor() {
	return backgroundColor;
    }

    @Override
    public String getForegroundColor() {
	return foregroundColor;
    }

    @Override
    public String getBorderColor() {
	return borderColor;
    }

    @Override
    public String getIcon() {
	return icon;
    }

    @Override
    public String getImageUrl() {
	return imageUrl;
    }

    @Override
    public UUID getId() {
	return id;
    }

    @Override
    public String getToken() {
	return token;
    }

    @Override
    public Date getCreatedDate() {
	return createdDate;
    }

    @Override
    public String getCreatedBy() {
	return createdBy;
    }

    @Override
    public Date getUpdatedDate() {
	return updatedDate;
    }

    @Override
    public String getUpdatedBy() {
	return updatedBy;
    }

    @Override
    public Map<String, String> getMetadata() {
	return metadata;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public void setContainerPolicy(DeviceContainerPolicy containerPolicy) {
	this.containerPolicy = containerPolicy;
    }

    public void setDeviceElementSchema(DeviceElementSchema deviceElementSchema) {
	this.deviceElementSchema = deviceElementSchema;
    }

    public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
    }

    public void setBackgroundColor(String backgroundColor) {
	this.backgroundColor = backgroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
	this.foregroundColor = foregroundColor;
    }

    public void setBorderColor(String borderColor) {
	this.borderColor = borderColor;
    }

    public void setIcon(String icon) {
	this.icon = icon;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
    }

    public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
    }

    public void setMetadata(Map<String, String> metadata) {
	this.metadata = metadata;
    }

}
