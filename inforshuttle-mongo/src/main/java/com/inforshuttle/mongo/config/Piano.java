package com.inforshuttle.mongo.config;

public class Piano extends Instrument{
	private String name = "Piano";
	private String sound;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

}