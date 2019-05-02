package io.crypto.beer.telegram.bot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.service.model.AddressDTO;
import io.crypto.beer.telegram.bot.service.transformer.AddressTransformer;
import io.crypto.beer.telegram.bot.service.uri.AddressUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

	private final RestTemplate template;
	private final AddressTransformer transformer;
	private final AddressUriBuilder uriBuilder;

	public Address add(Address address) {
		log.debug("Method add of AddressService, address: {}", address);

		HttpEntity<AddressDTO> request = new HttpEntity<>(transformer.transform(address));
		ResponseEntity<AddressDTO> response = template.exchange(uriBuilder.fetchAddUri(), HttpMethod.POST, request,
				AddressDTO.class);

		return transformer.transform(response.getBody());
	}

	public Address getById(Integer id) {
		log.debug("Method getById of AddressService, id: {}", id);

		ResponseEntity<AddressDTO> response = template.exchange(uriBuilder.fetchGetByIdUri(id), HttpMethod.GET, null,
				AddressDTO.class);

		return transformer.transform(response.getBody());
	}

	public List<Address> getByUserId(Integer userId) {
		log.debug("Method getByUserId of AddressService, userId: {}", userId);

		ResponseEntity<List<AddressDTO>> response = template.exchange(uriBuilder.fetchGetByUserIdUri(userId),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<AddressDTO>>() {
				});

		return response.getBody().stream().map(transformer::transform).collect(Collectors.toList());
	}

	public Address update(Integer id, Address address) {
		log.debug("Method update of AddressService, id: {}, address: {}", id, address);

		HttpEntity<AddressDTO> request = new HttpEntity<>(transformer.transform(address));
		ResponseEntity<AddressDTO> response = template.exchange(uriBuilder.fetchUpdateUri(id), HttpMethod.PUT, request,
				AddressDTO.class);

		return transformer.transform(response.getBody());
	}

	public void delete(Integer id) {
		log.debug("Method delete of AddressService, id: {}", id);

		template.exchange(uriBuilder.fetchDeleteUri(id), HttpMethod.DELETE, null, AddressDTO.class);
	}

}
