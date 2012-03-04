package com.guyroyse.katas.trigram;

import static com.guyroyse.katas.trigram.asserts.TrigramAssert.assertFileContains;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.guyroyse.katas.trigram.collections.TrigramHashMap;
import com.guyroyse.katas.trigram.collections.TrigramMap;

public class DeriverTest {
	
	private Deriver deriver;

	@Before
	public void setup() {
		deriver = new Deriver();
	}
	
	@Test
	public void itAnalyzesAnInputFile() {
		deriver.setAnalyzer(new Analyzer() {
			public TrigramMap analyze(String text) {
				assertFileContains(text, "kipling.txt");
				return new TrigramHashMap();
			}
		});
		deriver.createDerivativeWork("kipling.txt", "output.txt", "As I");
	}
	
	@Test
	public void itPassesAnalysisToGenerator() {
		final TrigramMap analysis = new TrigramHashMap();
		deriver.setAnalyzer(new Analyzer() {
			public TrigramMap analyze(String text) {
				return analysis;
			}
		});
		deriver.setGenerator(new Generator() {
			public String generate(TrigramMap map, String startingBigram) {
				assertEquals(analysis, map);
				return "";
			}
		});
		deriver.createDerivativeWork("kipling.txt", "output.txt", "As I");
	}
	
	@Test
	public void itGeneratesText() {
		deriver.setGenerator(new Generator() {
			public String generate(TrigramMap map, String startingBigram) {
				return "foo";
			}
		});
		deriver.createDerivativeWork("kipling.txt", "output.txt", "As I");
		assertFileContains("foo", "output.txt");
	}
	
	@Test
	public void itGeneratesTextFromSampleFile() {
		deriver.createDerivativeWork("kipling.txt", "output.txt", "and slaughter");
		assertFileContains("and slaughter return!", "output.txt");		
	}

}
