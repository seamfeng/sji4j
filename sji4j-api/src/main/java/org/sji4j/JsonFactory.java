package org.sji4j;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.sji4j.spi.JsonServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonFactory {
	private static Logger logger = LoggerFactory.getLogger(JsonFactory.class);

	private static final String[] API_COMPATIBILITY_LIST = new String[] { "1.8", "1.7" };

	static final int UNINITIALIZED = 0;
	static final int ONGOING_INITIALIZATION = 1;
	static final int FAILED_INITIALIZATION = 2;
	static final int SUCCESSFUL_INITIALIZATION = 3;
	static final int NOP_FALLBACK_INITIALIZATION = 4;

	static volatile int INITIALIZATION_STATE = UNINITIALIZED;

	private static volatile JsonServiceProvider PROVIDER;

	public static Json create() {
		return create(null);
	}

	public static Json create(JsonConfig config) {
		return getProvider().getJson(config);
	}

	private static JsonServiceProvider getProvider() {
		// 1 判断是否进行初始化，如果没有初始化，则进入performIntialization初始化！
		if (INITIALIZATION_STATE == UNINITIALIZED) {// TODO 初始化过程中考虑并发影响
			synchronized (JsonFactory.class) {
				if (INITIALIZATION_STATE == UNINITIALIZED) {
					INITIALIZATION_STATE = ONGOING_INITIALIZATION;
					performInitialization();
				}
			}
		}
		switch (INITIALIZATION_STATE) {
		case SUCCESSFUL_INITIALIZATION:
			return PROVIDER;
		case FAILED_INITIALIZATION:
			// UNSUCCESSFUL_INIT_MSG 初始化失败
			throw new IllegalStateException("初始化失败");
		}
		throw new IllegalStateException("Unreachable code");
	}

	/**
	 * 初始化过程
	 */
	private static void performInitialization() {
		bind();
		if (INITIALIZATION_STATE == SUCCESSFUL_INITIALIZATION) {
			versionSanityCheck();
		}
	}

	/**
	 * 校验第三方实现，对jdk版本的要求
	 */
	private static void versionSanityCheck() {
		try {
			String requested = PROVIDER.getJdkVersion();

			boolean match = false;
			for (String aAPI_COMPATIBILITY_LIST : API_COMPATIBILITY_LIST) {
				if (requested.startsWith(aAPI_COMPATIBILITY_LIST)) {
					match = true;
				}
			}
			if (!match) {
				// 报告不支持的版本
			}
		} catch (java.lang.NoSuchFieldError nsfe) {

		} catch (Throwable e) {

		}
	}

	/**
	 * 绑定发现的第一个JsonServiceProvider对象实例
	 */
	private static void bind() {
		try {
			List<JsonServiceProvider> providersList = findServiceProviders();
			logger.debug(providersList.toString()); // 输出：查找到几个第三方实现

			if (providersList != null && !providersList.isEmpty()) {
				PROVIDER = providersList.get(0);
				PROVIDER.initialize();
				INITIALIZATION_STATE = SUCCESSFUL_INITIALIZATION;
			} else { // 未发现SPI服务
				throw new ClassNotFoundException("not found JsonServiceProvider Provider");
			}
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected initialization failure", e);
		}
	}

	/**
	 * 查找JsonServiceProvider接口实现类（基于SPI规则）
	 * 
	 * @return
	 */
	private static List<JsonServiceProvider> findServiceProviders() {
		ServiceLoader<JsonServiceProvider> serviceLoader = ServiceLoader.load(JsonServiceProvider.class);
		List<JsonServiceProvider> providerList = new ArrayList<JsonServiceProvider>();
		for (JsonServiceProvider provider : serviceLoader) {
			providerList.add(provider);
		}
		return providerList;
	}

}
