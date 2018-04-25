package org.sji4j;

import java.util.Collection;
import java.util.Map;

public interface Json {

	public Json put(String key, boolean value);

	public Json put(String key, double value);

	public Json put(String key, int value);

	public Json put(String key, Object value);

	public Json put(String key, Collection<?> value);

	public Json put(String key, Map<?, ?> value);

	public Json put(String key, long value);

	public String valueToString(Object object);

	public String toString();
}
