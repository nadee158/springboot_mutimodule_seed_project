package com.test.seedproject.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class BaseDomain implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "uuid")
  private String uuId = null;

  @Column(name = "creation_time", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date creationTime;

  @Column(name = "modification_time")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date modificationTime;

  @Column(name = "created_by_user", nullable = false, updatable = false)
  @CreatedBy
  private String createdByUser;

  @Column(name = "modified_by_user", nullable = true)
  @LastModifiedBy
  private String modifiedByUser;

  @Column(name = "is_deleted")
  @Type(type = "yes_no")
  private Boolean isDeleted;

  @Column(name = "operation")
  private String operation;

  @Column(name = "timestamp")
  private Long timestamp;


  @Version
  @Column(name = "version_id")
  private long versionId = 0;


  @PrePersist
  public void onPrePersist() {
    this.uuId = UUID.randomUUID().toString();
    if (this.createdByUser == null) {
      this.createdByUser = "ANNONYMOUS_USER";
    }
    if (this.creationTime == null) {
      this.creationTime = Calendar.getInstance().getTime();
    }
    this.isDeleted = false;
    audit("INSERT");
  }

  @PreUpdate
  public void onPreUpdate() {
    if (this.modifiedByUser == null) {
      this.modifiedByUser = "ANNONYMOUS_USER";
    }
    if (this.modificationTime == null) {
      this.modificationTime = Calendar.getInstance().getTime();
    }
    audit("UPDATE");
  }

  @PreRemove
  public void onPreRemove() {
    audit("DELETE");
  }

  private void audit(String operation) {
    setOperation(operation);
    setTimestamp((new Date()).getTime());
  }

  public String getCreatedByUser() {
    return createdByUser;
  }

  public void setCreatedByUser(String createdByUser) {
    this.createdByUser = createdByUser;
  }

  public String getModifiedByUser() {
    return modifiedByUser;
  }

  public void setModifiedByUser(String modifiedByUser) {
    this.modifiedByUser = modifiedByUser;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  public Date getModificationTime() {
    return modificationTime;
  }

  public void setModificationTime(Date modificationTime) {
    this.modificationTime = modificationTime;
  }


  public String getUuId() {
    return uuId;
  }

  public void setUuId(String uuId) {
    this.uuId = uuId;
  }

  public Boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public long getVersionId() {
    return versionId;
  }

  public void setVersionId(long versionId) {
    this.versionId = versionId;
  }



}
