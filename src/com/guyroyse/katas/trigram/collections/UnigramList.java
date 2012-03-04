package com.guyroyse.katas.trigram.collections;

import java.util.List;
import java.util.Random;

public interface UnigramList extends List<String> {

	void setRandom(Random random);

	String getRandomUnigram();

}
