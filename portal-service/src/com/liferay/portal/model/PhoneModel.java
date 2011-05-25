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
 * The base model interface for the Phone service. Represents a row in the &quot;Phone&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.PhoneModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.PhoneImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Phone
 * @see com.liferay.portal.model.impl.PhoneImpl
 * @see com.liferay.portal.model.impl.PhoneModelImpl
 * @generated
 */
public interface PhoneModel extends AttachedModel, AuditedModel, BaseModel<Phone> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a phone model instance should use the {@link Phone} interface instead.
	 */

	/**
	 * Gets the primary key of this phone.
	 *
	 * @return the primary key of this phone
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this phone
	 *
	 * @param primaryKey the primary key of this phone
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Gets the phone ID of this phone.
	 *
	 * @return the phone ID of this phone
	 */
	public long getPhoneId();

	/**
	 * Sets the phone ID of this phone.
	 *
	 * @param phoneId the phone ID of this phone
	 */
	public void setPhoneId(long phoneId);

	/**
	 * Gets the company ID of this phone.
	 *
	 * @return the company ID of this phone
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this phone.
	 *
	 * @param companyId the company ID of this phone
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user ID of this phone.
	 *
	 * @return the user ID of this phone
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this phone.
	 *
	 * @param userId the user ID of this phone
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this phone.
	 *
	 * @return the user uuid of this phone
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this phone.
	 *
	 * @param userUuid the user uuid of this phone
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this phone.
	 *
	 * @return the user name of this phone
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this phone.
	 *
	 * @param userName the user name of this phone
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this phone.
	 *
	 * @return the create date of this phone
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this phone.
	 *
	 * @param createDate the create date of this phone
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this phone.
	 *
	 * @return the modified date of this phone
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this phone.
	 *
	 * @param modifiedDate the modified date of this phone
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the class name of the model instance this phone is polymorphically associated with.
	 *
	 * @return the class name of the model instance this phone is polymorphically associated with
	 */
	public String getClassName();

	/**
	 * Gets the class name ID of this phone.
	 *
	 * @return the class name ID of this phone
	 */
	public long getClassNameId();

	/**
	 * Sets the class name ID of this phone.
	 *
	 * @param classNameId the class name ID of this phone
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Gets the class p k of this phone.
	 *
	 * @return the class p k of this phone
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this phone.
	 *
	 * @param classPK the class p k of this phone
	 */
	public void setClassPK(long classPK);

	/**
	 * Gets the number of this phone.
	 *
	 * @return the number of this phone
	 */
	@AutoEscape
	public String getNumber();

	/**
	 * Sets the number of this phone.
	 *
	 * @param number the number of this phone
	 */
	public void setNumber(String number);

	/**
	 * Gets the extension of this phone.
	 *
	 * @return the extension of this phone
	 */
	@AutoEscape
	public String getExtension();

	/**
	 * Sets the extension of this phone.
	 *
	 * @param extension the extension of this phone
	 */
	public void setExtension(String extension);

	/**
	 * Gets the type ID of this phone.
	 *
	 * @return the type ID of this phone
	 */
	public int getTypeId();

	/**
	 * Sets the type ID of this phone.
	 *
	 * @param typeId the type ID of this phone
	 */
	public void setTypeId(int typeId);

	/**
	 * Gets the primary of this phone.
	 *
	 * @return the primary of this phone
	 */
	public boolean getPrimary();

	/**
	 * Determines if this phone is primary.
	 *
	 * @return <code>true</code> if this phone is primary; <code>false</code> otherwise
	 */
	public boolean isPrimary();

	/**
	 * Sets whether this phone is primary.
	 *
	 * @param primary the primary of this phone
	 */
	public void setPrimary(boolean primary);

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

	public int compareTo(Phone phone);

	public int hashCode();

	public Phone toEscapedModel();

	public String toString();

	public String toXmlString();
}