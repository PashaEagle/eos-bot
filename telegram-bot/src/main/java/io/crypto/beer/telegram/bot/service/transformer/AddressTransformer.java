package io.crypto.beer.telegram.bot.service.transformer;

import org.springframework.stereotype.Component;

import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.service.model.AddressDTO;

@Component
public class AddressTransformer {
	
	public Address transform(AddressDTO addressDTO) {
		return Address.builder()
				.id(addressDTO.getId())
				.country(addressDTO.getCountry())
				.city(addressDTO.getCity())
				.street(addressDTO.getStreet())
				.userId(addressDTO.getUserId())
				.build();
	}
	
	public AddressDTO transform(Address address) {
		return AddressDTO.builder()
				.id(address.getId())
				.country(address.getCountry())
				.city(address.getCity())
				.street(address.getStreet())
				.userId(address.getUserId())
				.build();
	}

}
