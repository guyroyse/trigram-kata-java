package com.guyroyse.katas.trigram;

import static com.guyroyse.katas.trigram.asserts.TrigramAssert.assertContains;
import static com.guyroyse.katas.trigram.asserts.TrigramAssert.assertEmpty;
import static com.guyroyse.katas.trigram.asserts.TrigramAssert.assertSize;

import org.junit.Before;
import org.junit.Test;

import com.guyroyse.katas.trigram.collections.TrigramMap;

public class AnalyzerTest {

	private Analyzer analyzer;

	@Before
	public void setup() {
		analyzer = new Analyzer();
	}

	@Test
	public void itReturnsAnEmptyMapForAnEmptyDocument() {
		assertEmpty(analyzer.analyze(""));
	}

	@Test
	public void itReturnsAnEmptyMapForADocumentOfLessThan3Words() {
		assertEmpty(analyzer.analyze("foo bar"));
	}

	@Test
	public void itAddsTrigramEntryToMapIfDocumentHasAtLeast3Words() {
		assertContains("baz", analyzer.analyze("foo bar baz").get("foo bar"));
	}

	@Test
	public void itAddsTrigramEntryToMapForAnAdditionalTrigram() {
		assertContains("qux", analyzer.analyze("foo bar baz qux").get("bar baz"));
	}
	
	@Test
	public void itAddsManyTrigramEntriesToTheMap() {
		assertSize(5, analyzer.analyze("foo bar baz qux quux quuux quuuux"));
	}
	
	@Test
	public void itAddsMultipleEntriesForBigramsThatRepeat() {
		TrigramMap map = analyzer.analyze("foo bar baz foo bar qux");
		assertContains(new String[] {"baz", "qux"}, map.get("foo bar"));
	}

	@Test
	public void itAddsDuplicateEntriesForTrigramsThatRepeat() {
		TrigramMap map = analyzer.analyze("foo bar baz foo bar baz");
		assertContains(new String[] {"baz", "baz"}, map.get("foo bar"));
	}
	
	@Test
	public void itIgnoresAdditionalWhitespace() {
		assertContains("baz", analyzer.analyze("  \tfoo   bar\n\r\tbaz  ").get("foo bar"));
	}

}