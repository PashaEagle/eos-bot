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
public class VariationUriBuilder {

	private final EndpointProperties properties;

	public URI fetchAddUri() {
		log.debug("Method fetchAddUri of VariationUriBuilder");
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getVariation().getPath().getByDefault()).build().encode().toUri();
	}

	public URI fetchGetByIdUri(Integer id) {
		log.debug("Method fetchGetByIdUri of VariationUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getVariation().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

	public URI fetchGetByPaginationUri(Integer page, Integer size) {
		log.debug("Method fetchGetByIdUri of VariationUriBuilder, page: {}, size: {}", page, size);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		Optional.ofNullable(String.valueOf(page)).ifPresent(p -> params.add("page", p));
		Optional.ofNullable(String.valueOf(size)).ifPresent(s -> params.add("size", s));

		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getVariation().getPath().getByPagination()).queryParams(params).build().encode()
				.toUri();
	}

	public URI fetchUpdateUri(Integer id) {
		log.debug("Method fetchUpdateUri of VariationUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getVariation().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

}
