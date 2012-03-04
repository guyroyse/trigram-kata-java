package com.guyroyse.katas.trigram.asserts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.guyroyse.katas.trigram.TrigramFile;
import com.guyroyse.katas.trigram.collections.TrigramMap;
import com.guyroyse.katas.trigram.collections.UnigramList;

public class TrigramAssert {

	public static void assertEqualsOneOf(String[] expectedValues, String actual) {
		boolean match = false;
		for (String expected : expectedValues) {
			if (expected.equals(actual)) {
				match = true;
				break;
			}
		}
		assertTrue(match);
	}

	public static void assertEmpty(String string) {
		assertEquals("", string);
	}

	public static void assertEmpty(TrigramMap map) {
		assertSize(0, map);
	}

	public static void assertSize(int size, TrigramMap map) {
		assertEquals(size, map.entrySet().size());
	}

	public static void assertContains(String value, UnigramList list) {
		assertContains(new String[] { value }, list);
	}

	public static void assertContains(String[] values, UnigramList list) {
		for (int i = 0; i < values.length; i++)
			assertEquals(values[i], list.get(i));
	}

	public static void assertFileContains(String expected, String filename) {
		String actual = TrigramFile.readFile(filename);
		assertEquals(expected, actual);
	}

}
