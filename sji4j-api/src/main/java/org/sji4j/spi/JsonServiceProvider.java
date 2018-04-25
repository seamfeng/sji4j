package org.sji4j.spi;

import org.sji4j.Json;
import org.sji4j.JsonConfig;

public interface JsonServiceProvider {

	/**
	 * 返回基于Json接口的对象实例
	 * 
	 * @return
	 */
	public Json getJson(JsonConfig config);

	/**
	 * 第三方支持JDK的最低版本
	 * 
	 * @return
	 */
	public String getJdkVersion();

	/**
	 * 初始化操作
	 */
	public void initialize();

}
