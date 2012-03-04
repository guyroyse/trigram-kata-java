package com.guyroyse.katas.trigram;

import static com.guyroyse.katas.trigram.asserts.TrigramAssert.assertEmpty;
import static com.guyroyse.katas.trigram.factory.TrigramFactory.makeUnigramList;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.guyroyse.katas.trigram.collections.TrigramHashMap;
import com.guyroyse.katas.trigram.collections.TrigramMap;
import com.guyroyse.katas.trigram.collections.UnigramList;

public class GeneratorTest {

	private TrigramMap map;
	private Generator generator;

	@Before
	public void setup() {
		map = new TrigramHashMap();
		generator = new Generator();
	}

	@Test
	public void itGeneratesNoTextWhenTrigramMapIsEmpty() {
		assertEmpty(generator.generate(map, ""));
	}

	@Test
	public void itReturnsTheBigramWhenTheBigramIsNotFound() {
		assertEquals("foo bar", generator.generate(map, "foo bar"));
	}

	@Test
	public void itReturnsATrigramWhenTheBigramIsFound() {
		map.put("foo bar", makeUnigramList("baz"));
		assertEquals("foo bar baz", generator.generate(map, "foo bar"));
	}

	@Test
	public void itReturnsATrigramEndingWithARandomUnigramWhenThereAreMultipleUnigrams() {
		UnigramList multipleUnigrams = makeUnigramList(new String[] { "baz", "qux" });
		multipleUnigrams.setRandom(new Random() {
			public int nextInt(int n) {
				return n;
			}
		});
		map.put("foo bar", multipleUnigrams);
		assertEquals("foo bar qux", generator.generate(map, "foo bar"));
	}

	@Test
	public void itChainsTrigramsToMakeLongerText() {
		map.put("foo bar", makeUnigramList("baz"));
		map.put("bar baz", makeUnigramList("qux"));
		map.put("baz qux", makeUnigramList("foo"));
		assertEquals("foo bar baz qux foo", generator.generate(map, "foo bar"));
	}

}