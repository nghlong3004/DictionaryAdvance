package controller;

import service.DictionaryServiceInterface;

public class DictionaryController {
	private final DictionaryServiceInterface dictinary;
	public DictionaryController(DictionaryServiceInterface dictinary) {
		this.dictinary = dictinary;
	}
	public DictionaryServiceInterface getDictinary() {
		return dictinary;
	}
}
