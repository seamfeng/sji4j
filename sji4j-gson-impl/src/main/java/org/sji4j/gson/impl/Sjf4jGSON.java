package org.sji4j.gson.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.sji4j.Json;

import com.google.gson.Gson;

public class Sjf4jGSON implements Json {
	private Gson gson;

	private Map<String, Object> map = new HashMap<String, Object>();

	public Sjf4jGSON() {
		gson = new Gson();
	}

	@Override
	public Json put(String key, boolean value) {
		map.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, double value) {
		map.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, int value) {
		map.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, Object value) {
		map.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, Collection<?> value) {
		map.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, Map<?, ?> value) {
		map.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, long value) {
		map.put(key, value);
		return this;
	}

	@Override
	public String valueToString(Object object) {
		return gson.toJson(object);
	}

	@Override
	public String toString() {
		return gson.toJson(map);
	}

}
