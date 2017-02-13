package com.xiaojun.cache.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaojun.entity.SysUserEntity;
import com.xiaojun.service.cache.CacheService;
import com.xiaojun.util.GSONUtils;

@Service(interfaceName = "com.xiaojun.service.cache.CacheService")
public class CacheServiceImpl implements CacheService {
	@Override
	public String getUserInfo(String key) {
		SysUserEntity entity = new SysUserEntity();
		entity.setUsername("xiaojun");
		entity.setMobile("18279661989");
		return GSONUtils.toJson(entity, true);
	}

}
