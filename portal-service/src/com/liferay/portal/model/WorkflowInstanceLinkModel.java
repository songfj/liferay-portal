/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the WorkflowInstanceLink service. Represents a row in the &quot;WorkflowInstanceLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.WorkflowInstanceLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowInstanceLink
 * @see com.liferay.portal.model.impl.WorkflowInstanceLinkImpl
 * @see com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl
 * @generated
 */
public interface WorkflowInstanceLinkModel extends AttachedModel,
	BaseModel<WorkflowInstanceLink>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a workflow instance link model instance should use the {@link WorkflowInstanceLink} interface instead.
	 */

	/**
	 * Gets the primary key of this workflow instance link.
	 *
	 * @return the primary key of this workflow instance link
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this workflow instance link
	 *
	 * @param primaryKey the primary key of this workflow instance link
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Gets the workflow instance link ID of this workflow instance link.
	 *
	 * @return the workflow instance link ID of this workflow instance link
	 */
	public long getWorkflowInstanceLinkId();

	/**
	 * Sets the workflow instance link ID of this workflow instance link.
	 *
	 * @param workflowInstanceLinkId the workflow instance link ID of this workflow instance link
	 */
	public void setWorkflowInstanceLinkId(long workflowInstanceLinkId);

	/**
	 * Gets the group ID of this workflow instance link.
	 *
	 * @return the group ID of this workflow instance link
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this workflow instance link.
	 *
	 * @param groupId the group ID of this workflow instance link
	 */
	public void setGroupId(long groupId);

	/**
	 * Gets the company ID of this workflow instance link.
	 *
	 * @return the company ID of this workflow instance link
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this workflow instance link.
	 *
	 * @param companyId the company ID of this workflow instance link
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user ID of this workflow instance link.
	 *
	 * @return the user ID of this workflow instance link
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this workflow instance link.
	 *
	 * @param userId the user ID of this workflow instance link
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this workflow instance link.
	 *
	 * @return the user uuid of this workflow instance link
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this workflow instance link.
	 *
	 * @param userUuid the user uuid of this workflow instance link
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this workflow instance link.
	 *
	 * @return the user name of this workflow instance link
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this workflow instance link.
	 *
	 * @param userName the user name of this workflow instance link
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this workflow instance link.
	 *
	 * @return the create date of this workflow instance link
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this workflow instance link.
	 *
	 * @param createDate the create date of this workflow instance link
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this workflow instance link.
	 *
	 * @return the modified date of this workflow instance link
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this workflow instance link.
	 *
	 * @param modifiedDate the modified date of this workflow instance link
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the class name of the model instance this workflow instance link is polymorphically associated with.
	 *
	 * @return the class name of the model instance this workflow instance link is polymorphically associated with
	 */
	public String getClassName();

	/**
	 * Gets the class name ID of this workflow instance link.
	 *
	 * @return the class name ID of this workflow instance link
	 */
	public long getClassNameId();

	/**
	 * Sets the class name ID of this workflow instance link.
	 *
	 * @param classNameId the class name ID of this workflow instance link
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Gets the class p k of this workflow instance link.
	 *
	 * @return the class p k of this workflow instance link
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this workflow instance link.
	 *
	 * @param classPK the class p k of this workflow instance link
	 */
	public void setClassPK(long classPK);

	/**
	 * Gets the workflow instance ID of this workflow instance link.
	 *
	 * @return the workflow instance ID of this workflow instance link
	 */
	public long getWorkflowInstanceId();

	/**
	 * Sets the workflow instance ID of this workflow instance link.
	 *
	 * @param workflowInstanceId the workflow instance ID of this workflow instance link
	 */
	public void setWorkflowInstanceId(long workflowInstanceId);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(WorkflowInstanceLink workflowInstanceLink);

	public int hashCode();

	public WorkflowInstanceLink toEscapedModel();

	public String toString();

	public String toXmlString();
}