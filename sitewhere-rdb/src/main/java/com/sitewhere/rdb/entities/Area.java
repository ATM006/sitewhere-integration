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
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sitewhere.spi.area.IArea;

@Entity
@Table(name = "area")
public class Area implements IArea {

    /** Serial version UID */
    private static final long serialVersionUID = -2015031008103232060L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    /** Area type id */
    @Column(name = "area_type_id")
    private UUID areaTypeId;

    /** Parent area id */
    @Column(name = "parent_id")
    private UUID parentId;

    /** Area name */
    @Column(name = "name")
    private String name;

    /** Area description */
    @Column(name = "description")
    private String description;

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
    @CollectionTable(name = "area_metadata")
    @MapKeyColumn(name = "prop_key")
    @Column(name = "prop_value")
    private Map<String, String> metadata = new HashMap<>();

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "area")
    private List<Location> bounds = new ArrayList<>();

    @Override
    public UUID getAreaTypeId() {
	return areaTypeId;
    }

    @Override
    public List<Location> getBounds() {
	return bounds;
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

    @Override
    public UUID getParentId() {
	return parentId;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    public void setAreaTypeId(UUID areaTypeId) {
	this.areaTypeId = areaTypeId;
    }

    public void setParentId(UUID parentId) {
	this.parentId = parentId;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDescription(String description) {
	this.description = description;
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

    public void setBounds(List<Location> bounds) {
	this.bounds = bounds;
    }

}
