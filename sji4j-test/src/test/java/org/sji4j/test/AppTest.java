package org.sji4j.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.sji4j.Json;
import org.sji4j.JsonFactory;

public class AppTest {

	private Json json;

	@Before
	public void init() {
		json = JsonFactory.create();

	}

	@Test
	public void sineJson() {
		json.put("a", "b").put("aa", "bb").put("aaa", 1);
		System.out.println(json.toString());

		json.put("a1", "b1").put("aa1", "bb").put("aaa1", 1);
		System.out.println(json.toString());

		Map<String, String> m = new HashMap<String, String>();
		m.put("m1", "v1");
		m.put("m2", "v2");
		System.out.println(json.valueToString(m));
	}
}
