package io.crypto.beer.telegram.bot.model;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Address {
	
	private Integer id;
	
	private String country;
	
	private String city;
	
	private String street;
	
	private Integer userId;
	
	@Override
	public String toString() {
		String country = Optional.ofNullable(this.country).orElse("null");
		String city = Optional.ofNullable(this.city).orElse("null");
		String street = Optional.ofNullable(this.street).orElse("null");
		return String.format("%s, %s, %s", country, city, street);
	}
	
}
