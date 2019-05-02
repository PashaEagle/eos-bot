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
public class AddressUriBuilder {

	private final EndpointProperties properties;

	public URI fetchAddUri() {
		log.debug("Method fetchAddUri of AddressUriBuilder");
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(properties.getAddress().getPath().getByDefault()).build().encode().toUri();
	}

	public URI fetchGetByIdUri(Integer id) {
		log.debug("Method fetchGetByIdUri of AddressUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getAddress().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

	public URI fetchGetByUserIdUri(Integer telegaId) {
		log.debug("Method fetchGetByTelegaIdUri of AddressUriBuilder, telegaId: {}", telegaId);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getAddress().getPath().getByUserId(), telegaId)).build()
				.encode().toUri();
	}

	public URI fetchUpdateUri(Integer id) {
		log.debug("Method fetchUpdateUri of AddressUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getAddress().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

	public URI fetchDeleteUri(Integer id) {
		log.debug("Method fetchDeleteUri of AddressUriBuilder, id: {}", id);
		return UriComponentsBuilder.fromUriString(properties.getApi().getUri())
				.path(String.format("%s/%s", properties.getAddress().getPath().getByDefault(), id)).build().encode()
				.toUri();
	}

}
