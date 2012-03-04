package com.guyroyse.katas.trigram;

import com.guyroyse.katas.trigram.collections.TrigramMap;

public class Deriver {

	private Analyzer analyzer = new Analyzer();
	private Generator generator = new Generator();

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}

	public void createDerivativeWork(String sourceFile, String destinationFile, String startingBigram) {
		String sourceDocument = TrigramFile.readFile(sourceFile);
		TrigramMap map = analyzer.analyze(sourceDocument);
		String derivedDocument = generator.generate(map, startingBigram);
		TrigramFile.writeFile(destinationFile, derivedDocument);
	}

}