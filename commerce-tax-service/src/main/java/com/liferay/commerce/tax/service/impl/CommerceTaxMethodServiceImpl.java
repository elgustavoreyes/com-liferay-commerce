/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.commerce.tax.service.impl;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.commerce.tax.service.base.CommerceTaxMethodServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CommerceTaxMethodServiceImpl
	extends CommerceTaxMethodServiceBaseImpl {

	@Override
	public CommerceTaxMethod addCommerceTaxMethod(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String engineKey, boolean percentage, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);

		return commerceTaxMethodLocalService.addCommerceTaxMethod(
			nameMap, descriptionMap, engineKey, percentage, active,
			serviceContext);
	}

	@Override
	public CommerceTaxMethod createCommerceTaxMethod(
			long groupId, long commerceTaxMethodId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);

		return commerceTaxMethodLocalService.createCommerceTaxMethod(
			commerceTaxMethodId);
	}

	@Override
	public void deleteCommerceTaxMethod(long commerceTaxMethodId)
		throws PortalException {

		CommerceTaxMethod commerceTaxMethod =
			commerceTaxMethodLocalService.getCommerceTaxMethod(
				commerceTaxMethodId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceTaxMethod.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);

		commerceTaxMethodLocalService.deleteCommerceTaxMethod(
			commerceTaxMethod);
	}

	@Override
	public CommerceTaxMethod getCommerceTaxMethod(long commerceTaxMethodId)
		throws PortalException {

		CommerceTaxMethod commerceTaxMethod =
			commerceTaxMethodLocalService.getCommerceTaxMethod(
				commerceTaxMethodId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceTaxMethod.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);

		return commerceTaxMethodLocalService.getCommerceTaxMethod(
			commerceTaxMethodId);
	}

	@Override
	public List<CommerceTaxMethod> getCommerceTaxMethods(long groupId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);

		return commerceTaxMethodLocalService.getCommerceTaxMethods(groupId);
	}

	@Override
	public List<CommerceTaxMethod> getCommerceTaxMethods(
			long groupId, boolean active)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);

		return commerceTaxMethodLocalService.getCommerceTaxMethods(
			groupId, active);
	}

	@Override
	public CommerceTaxMethod setActive(long commerceTaxMethodId, boolean active)
		throws PortalException {

		CommerceTaxMethod commerceTaxMethod =
			commerceTaxMethodLocalService.fetchCommerceTaxMethod(
				commerceTaxMethodId);

		if (commerceTaxMethod != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commerceTaxMethod.getGroupId(),
				CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);
		}

		return commerceTaxMethodLocalService.setActive(
			commerceTaxMethodId, active);
	}

	@Override
	public CommerceTaxMethod updateCommerceTaxMethod(
			long commerceTaxMethodId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean percentage,
			boolean active)
		throws PortalException {

		CommerceTaxMethod commerceTaxMethod =
			commerceTaxMethodLocalService.getCommerceTaxMethod(
				commerceTaxMethodId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceTaxMethod.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_TAX_METHODS);

		return commerceTaxMethodLocalService.updateCommerceTaxMethod(
			commerceTaxMethod.getCommerceTaxMethodId(), nameMap, descriptionMap,
			percentage, active);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommerceTaxMethodServiceImpl.class,
				"_portletResourcePermission", CommerceConstants.RESOURCE_NAME);

}