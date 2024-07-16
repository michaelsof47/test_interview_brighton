package com.example.myapplication.model;

import java.util.List;

public class MovieModel{

	private List<SearchItem> search;

	public void setSearch(List<SearchItem> search){
		this.search = search;
	}

	public List<SearchItem> getSearch(){
		return search;
	}

	@Override
 	public String toString(){
		return 
			"MovieModel{" + 
			"search = '" + search + '\'' + 
			"}";
		}
}