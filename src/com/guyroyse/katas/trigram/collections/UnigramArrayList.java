package com.guyroyse.katas.trigram.collections;

import java.util.ArrayList;
import java.util.Random;

public class UnigramArrayList extends ArrayList<String> implements UnigramList {
	
	private Random random = new Random();

	@Override
	public void setRandom(Random random) {
		this.random = random;
	}

	@Override
	public String getRandomUnigram() {
		int index = 0;
		if (size() > 1) index = random.nextInt(size() - 1);
		return get(index);
	}

}
