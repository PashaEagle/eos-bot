package io.crypto.beer.telegram.bot.service.uri;

import java.net.URI;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import io.crypto.beer.telegram.bot.config.properties.EndpointProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemUriBuilder {

	private final EndpointProperties properties;

	public URI fetchAddUri() {
		log.debug("Method fetchAddUri of ItemUriBuilder");
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getItem().getPath().getByDefault()).build().encode().toUri();
	}

	public URI fetchGetByIdUri(Integer id) {
		log.debug("Method fetchGetByIdUri of ItemUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getItem().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

	public URI fetchGetBySearchUri(String name, Integer variationId, Integer breweryId, Integer page, Integer size) {
		log.debug(
				"Method fetchGetBySearchUri of ItemUriBuilder, name: {}, variationId: {}, breweryId: {}, page: {}, size: {}",
				name, variationId, breweryId, page, size);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		Optional.ofNullable(name).ifPresent(n -> params.add("name", n));
		Optional.ofNullable(String.valueOf(variationId)).ifPresent(v -> params.add("variationId", v));
		Optional.ofNullable(String.valueOf(breweryId)).ifPresent(b -> params.add("breweryId", b));
		Optional.ofNullable(String.valueOf(page)).ifPresent(p -> params.add("page", p));
		Optional.ofNullable(String.valueOf(size)).ifPresent(s -> params.add("size", s));

		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getItem().getPath().getBySearch()).queryParams(params).build().encode().toUri();
	}

	public URI fetchUpdateUri(Integer id) {
		log.debug("Method fetchUpdateUri of ItemUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getItem().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

}
