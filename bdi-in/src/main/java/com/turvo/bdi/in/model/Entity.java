package com.turvo.bdi.in.model;

public enum Entity {

	CUSTOMER("customer"),SHIPMENT("shipment"),INVALID("invalid");

	private String value;

	private Entity(String value){
		this.value = value;
	}

	public static Entity fromValue(String value) {
		Entity[] channels = Entity.values();
		for (int i = 0; i < channels.length; ++i) {
			if (channels[i].value.equalsIgnoreCase(value)) {
				return channels[i];
			}
		}

		return Entity.INVALID;
	}

	public String get(){
		return this.value;
	}
}
