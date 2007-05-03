/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.DuplicatePasswordPolicyException;
import com.liferay.portal.NoSuchPasswordPolicyException;
import com.liferay.portal.NoSuchPasswordPolicyRelException;
import com.liferay.portal.PasswordPolicyNameException;
import com.liferay.portal.PortalException;
import com.liferay.portal.RequiredPasswordPolicyException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.base.PasswordPolicyLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.PasswordPolicyFinder;
import com.liferay.portal.service.persistence.PasswordPolicyUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * <a href="PasswordPolicyLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Scott Lee
 *
 */
public class PasswordPolicyLocalServiceImpl
	extends PasswordPolicyLocalServiceBaseImpl {

	public PasswordPolicy addPasswordPolicy(
			long userId, boolean defaultPolicy, String name, String description,
			String storageScheme, boolean changeable, boolean changeRequired,
			long minAge, boolean checkSyntax, boolean allowDictionaryWords,
			int minLength, boolean history, int historyCount,
			boolean expireable, long maxAge, long warningTime, int graceLimit,
			boolean lockout, int maxFailure, long lockoutDuration,
			long resetFailureCount)
		throws PortalException, SystemException {

		// Password policy

		User user = UserUtil.findByPrimaryKey(userId);
		Date now = new Date();

		validate(0, user.getCompanyId(), name);

		long passwordPolicyId = CounterLocalServiceUtil.increment(
			PasswordPolicy.class.getName());

		PasswordPolicy passwordPolicy = PasswordPolicyUtil.create(
			passwordPolicyId);

		passwordPolicy.setUserId(userId);
		passwordPolicy.setCompanyId(user.getCompanyId());
		passwordPolicy.setUserName(user.getFullName());
		passwordPolicy.setCreateDate(now);
		passwordPolicy.setModifiedDate(now);
		passwordPolicy.setDefaultPolicy(defaultPolicy);
		passwordPolicy.setName(name);
		passwordPolicy.setDescription(description);
		passwordPolicy.setStorageScheme(storageScheme);
		passwordPolicy.setChangeable(changeable);
		passwordPolicy.setChangeRequired(changeRequired);
		passwordPolicy.setMinAge(minAge);
		passwordPolicy.setCheckSyntax(checkSyntax);
		passwordPolicy.setAllowDictionaryWords(allowDictionaryWords);
		passwordPolicy.setMinLength(minLength);
		passwordPolicy.setHistory(history);
		passwordPolicy.setHistoryCount(historyCount);
		passwordPolicy.setExpireable(expireable);
		passwordPolicy.setMaxAge(maxAge);
		passwordPolicy.setWarningTime(warningTime);
		passwordPolicy.setGraceLimit(graceLimit);
		passwordPolicy.setLockout(lockout);
		passwordPolicy.setMaxFailure(maxFailure);
		passwordPolicy.setLockoutDuration(lockoutDuration);
		passwordPolicy.setRequireUnlock(lockoutDuration == 0);
		passwordPolicy.setResetFailureCount(resetFailureCount);

		PasswordPolicyUtil.update(passwordPolicy);

		// Resources

		if (!user.isDefaultUser()) {
			ResourceLocalServiceUtil.addResources(
				user.getCompanyId(), 0, userId, PasswordPolicy.class.getName(),
				passwordPolicy.getPasswordPolicyId(), false, false, false);
		}

		return passwordPolicy;
	}

	public void checkDefaultPasswordPolicy(long companyId)
		throws PortalException, SystemException {

		String defaultPasswordPolicyName = GetterUtil.getString(
			PropsUtil.get(PropsUtil.PASSWORDS_DEFAULT_POLICY_NAME));

		try {
			PasswordPolicyUtil.findByC_N(companyId, defaultPasswordPolicyName);
		}
		catch (NoSuchPasswordPolicyException nsppe) {
			addPasswordPolicy(
				UserLocalServiceUtil.getDefaultUserId(companyId), true,
				defaultPasswordPolicyName, defaultPasswordPolicyName, "md5",
				true, false, 0, false, true, 6, false, 0, false, 0, 0, 0, false,
				0, 0, 0);
		}
	}

	public void deletePasswordPolicy(long passwordPolicyId)
		throws PortalException, SystemException {

		PasswordPolicy passwordPolicy =
			PasswordPolicyUtil.findByPrimaryKey(passwordPolicyId);

		if (passwordPolicy.isDefaultPolicy()) {
			throw new RequiredPasswordPolicyException();
		}

		// Resources

		ResourceLocalServiceUtil.deleteResource(
			passwordPolicy.getCompanyId(), PasswordPolicy.class.getName(),
			ResourceImpl.SCOPE_INDIVIDUAL,
			passwordPolicy.getPasswordPolicyId());

		// Password policy

		PasswordPolicyUtil.remove(passwordPolicyId);
	}

	public PasswordPolicy getDefaultPasswordPolicy(long companyId)
		throws PortalException, SystemException {

		return PasswordPolicyUtil.findByC_DP(companyId, true);
	}

	public PasswordPolicy getPasswordPolicy(long passwordPolicyId)
		throws PortalException, SystemException {

		return PasswordPolicyUtil.findByPrimaryKey(passwordPolicyId);
	}

	public PasswordPolicy getPasswordPolicyByUserId(long userId)
		throws PortalException, SystemException {

		PasswordPolicyRel passwordPolicyRel = null;

		// Check for password policy specifically assigned to this user

		try {
			passwordPolicyRel =
				PasswordPolicyRelLocalServiceUtil.getPasswordPolicyRel(
					User.class.getName(), userId);

			return getPasswordPolicy(passwordPolicyRel.getPasswordPolicyId());
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}

		// Check for password policy specifically assigned to this location

		User user = UserUtil.findByPrimaryKey(userId);
		long locationId = user.getLocation().getOrganizationId();

		try {
			passwordPolicyRel =
				PasswordPolicyRelLocalServiceUtil.getPasswordPolicyRel(
					Organization.class.getName(), locationId);

			return getPasswordPolicy(passwordPolicyRel.getPasswordPolicyId());
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}

		// Check for password policy specifically assigned to this organization

		long organizationId = user.getOrganization().getOrganizationId();

		try {
			passwordPolicyRel =
				PasswordPolicyRelLocalServiceUtil.getPasswordPolicyRel(
					Organization.class.getName(), organizationId);

			return getPasswordPolicy(passwordPolicyRel.getPasswordPolicyId());
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}

		// Get default password policy

		return getDefaultPasswordPolicy(user.getCompanyId());
	}

	public List search(long companyId, String name, int begin, int end)
		throws SystemException {

		return PasswordPolicyFinder.findByC_N(companyId, name, begin, end);
	}

	public int searchCount(long companyId, String name)
		throws SystemException {

		return PasswordPolicyFinder.countByC_N(companyId, name);
	}

	public PasswordPolicy updatePasswordPolicy(
			long passwordPolicyId, String name, String description,
			String storageScheme, boolean changeable, boolean changeRequired,
			long minAge, boolean checkSyntax, boolean allowDictionaryWords,
			int minLength, boolean history, int historyCount,
			boolean expireable, long maxAge, long warningTime, int graceLimit,
			boolean lockout, int maxFailure, long lockoutDuration,
			long resetFailureCount)
		throws PortalException, SystemException {

		Date now = new Date();

		PasswordPolicy passwordPolicy = PasswordPolicyUtil.findByPrimaryKey(
			passwordPolicyId);

		if (!passwordPolicy.getDefaultPolicy()) {
			validate(passwordPolicyId, passwordPolicy.getCompanyId(), name);

			passwordPolicy.setName(name);
		}

		passwordPolicy.setModifiedDate(now);
		passwordPolicy.setDescription(description);
		passwordPolicy.setStorageScheme(storageScheme);
		passwordPolicy.setChangeable(changeable);
		passwordPolicy.setChangeRequired(changeRequired);
		passwordPolicy.setMinAge(minAge);
		passwordPolicy.setCheckSyntax(checkSyntax);
		passwordPolicy.setAllowDictionaryWords(allowDictionaryWords);
		passwordPolicy.setMinLength(minLength);
		passwordPolicy.setHistory(history);
		passwordPolicy.setHistoryCount(historyCount);
		passwordPolicy.setExpireable(expireable);
		passwordPolicy.setMaxAge(maxAge);
		passwordPolicy.setWarningTime(warningTime);
		passwordPolicy.setGraceLimit(graceLimit);
		passwordPolicy.setLockout(lockout);
		passwordPolicy.setMaxFailure(maxFailure);
		passwordPolicy.setLockoutDuration(lockoutDuration);
		passwordPolicy.setRequireUnlock(lockoutDuration == 0);
		passwordPolicy.setResetFailureCount(resetFailureCount);

		PasswordPolicyUtil.update(passwordPolicy);

		return passwordPolicy;
	}

	protected void validate(long passwordPolicyId, long companyId, String name)
		throws PortalException, SystemException {

		if ((Validator.isNull(name)) || (Validator.isNumber(name)) ||
			(name.indexOf(StringPool.COMMA) != -1) ||
			(name.indexOf(StringPool.STAR) != -1)) {

			throw new PasswordPolicyNameException();
		}

		try {
			PasswordPolicy passwordPolicy =
				PasswordPolicyUtil.findByC_N(companyId, name);

			if ((passwordPolicyId <= 0) ||
				(passwordPolicy.getPasswordPolicyId() != passwordPolicyId)) {

				throw new DuplicatePasswordPolicyException();
			}
		}
		catch (NoSuchPasswordPolicyException nsge) {
		}
	}

}