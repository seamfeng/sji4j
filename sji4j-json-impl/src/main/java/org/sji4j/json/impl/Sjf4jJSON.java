package org.sji4j.json.impl;

import java.util.Collection;
import java.util.Map;

import org.json.JSONObject;
import org.sji4j.Json;

public class Sjf4jJSON implements Json {
	private JSONObject jsonObject;

	public Sjf4jJSON() {
		jsonObject = new JSONObject();
	}

	@Override
	public Json put(String key, boolean value) {
		jsonObject.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, double value) {
		jsonObject.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, int value) {
		jsonObject.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, Object value) {
		jsonObject.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, Collection<?> value) {
		jsonObject.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, Map<?, ?> value) {
		jsonObject.put(key, value);
		return this;
	}

	@Override
	public Json put(String key, long value) {
		jsonObject.put(key, value);
		return this;
	}

	@Override
	public String valueToString(Object object) {
		return JSONObject.valueToString(object);
	}

	@Override
	public String toString() {
		return jsonObject.toString();
	}

}
