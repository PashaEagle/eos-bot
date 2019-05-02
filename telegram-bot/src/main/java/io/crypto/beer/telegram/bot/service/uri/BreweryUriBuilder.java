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
public class BreweryUriBuilder {

	private final EndpointProperties properties;

	public URI fetchAddUri() {
		log.debug("Method fetchAddUri of BreweryUriBuilder");
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getBrewery().getPath().getByDefault()).build().encode().toUri();
	}

	public URI fetchGetByIdUri(Integer id) {
		log.debug("Method fetchGetByIdUri of BreweryUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getBrewery().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

	public URI fetchGetByPaginationUri(Integer page, Integer size) {
		log.debug("Method fetchGetByPaginationUri of BreweryUriBuilder, page: {}, size: {}", page, size);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		Optional.ofNullable(String.valueOf(page)).ifPresent(p -> params.add("page", p));
		Optional.ofNullable(String.valueOf(size)).ifPresent(s -> params.add("size", s));

		log.info(UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getBrewery().getPath().getByPagination()).queryParams(params).build().encode().toUri()
				.toString());

		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getBrewery().getPath().getByPagination()).queryParams(params).build().encode().toUri();
	}

	public URI fetchUpdateUri(Integer id) {
		log.debug("Method fetchUpdateUri of BreweryUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getBrewery().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

}
