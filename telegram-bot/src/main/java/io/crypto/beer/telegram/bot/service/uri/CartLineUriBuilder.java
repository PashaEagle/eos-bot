package io.crypto.beer.telegram.bot.service.uri;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import io.crypto.beer.telegram.bot.config.properties.EndpointProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartLineUriBuilder {

	private final EndpointProperties properties;

	public URI fetchAddUri() {
		log.debug("Method fetchAddUri of CartLineUriBuilder");
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getCartLine().getPath().getByDefault()).build().encode().toUri();
	}

	public URI fetchGetByIdUri(Integer id) {
		log.debug("Method fetchGetByIdUri of CartLineUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getCartLine().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

	public URI fetchGetByCartIdUri(Integer cartId, Integer page, Integer size) {
		log.debug("Method fetchGetByIdUri of CartLineUriBuilder, cartId: {}, page: {}, size: {}", cartId, page, size);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getCartLine().getPath().getByCartId(), cartId))
				.queryParam("page", page).queryParam("size", size).build().encode().toUri();
	}

	public URI fetchUpdateUri(Integer id) {
		log.debug("Method fetchUpdateUri of CartLineUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getCartLine().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

}
