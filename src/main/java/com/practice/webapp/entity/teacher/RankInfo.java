package com.practice.webapp.entity.teacher;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class RankInfo {

	@JsonProperty("rank")
	private List<String> rank = new ArrayList<String>();

	public List<String> getRank() {
		return rank;
	}

	public void setRank(List<String> rank) {
		this.rank = rank;
	}

}
