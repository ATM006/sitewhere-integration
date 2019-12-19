/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rdb.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sitewhere.spi.device.command.ICommandParameter;
import com.sitewhere.spi.device.command.IDeviceCommand;

@Entity
@Table(name = "device_command")
public class DeviceCommand implements IDeviceCommand {

    /** Serial version UID */
    private static final long serialVersionUID = 7140127377676943042L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    /** Unique id for parent specification */
    @Column(name = "device_type_id")
    private UUID deviceTypeId;

    @Column(name = "device_type_token")
    private String deviceTypeToken;

    /** Command namespace */
    @Column(name = "namespace")
    private String namespace;

    /** Command name */
    @Column(name = "name")
    private String name;

    /** Command description */
    @Column(name = "description")
    private String description;

    /** Parameter list */
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "deviceCommand")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CommandParameter> parameterList = new ArrayList<>();

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
    @CollectionTable(name = "device_command_metadata", joinColumns = @JoinColumn(name = "device_command_id"))
    @MapKeyColumn(name = "prop_key")
    @Column(name = "prop_value")
    private Map<String, String> metadata = new HashMap<>();

    @Override
    public UUID getDeviceTypeId() {
	return deviceTypeId;
    }

    @Override
    public String getNamespace() {
	return namespace;
    }

    @Override
    public List<ICommandParameter> getParameters() {
	List<ICommandParameter> lst = new ArrayList<>();
	for (CommandParameter p : parameterList) {
	    lst.add(p);
	}
	return lst;
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
	return createdBy;
    }

    @Override
    public Map<String, String> getMetadata() {
	return metadata;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    public void setDeviceTypeId(UUID deviceTypeId) {
	this.deviceTypeId = deviceTypeId;
    }

    public void setNamespace(String namespace) {
	this.namespace = namespace;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDescription(String description) {
	this.description = description;
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

    public List<CommandParameter> getParameterList() {
	return parameterList;
    }

    public void setParameterList(List<CommandParameter> parameterList) {
	this.parameterList = parameterList;
    }

    public String getDeviceTypeToken() {
	return deviceTypeToken;
    }

    public void setDeviceTypeToken(String deviceTypeToken) {
	this.deviceTypeToken = deviceTypeToken;
    }
}
