package io.crypto.beer.telegram.bot.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.model.Order;
import io.crypto.beer.telegram.bot.service.model.CartDTO;
import io.crypto.beer.telegram.bot.service.transformer.CartTransformer;
import io.crypto.beer.telegram.bot.service.uri.CartUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

	private final RestTemplate template;
	private final CartUriBuilder uriBuilder;
	private final CartTransformer transformer;

	public Order add(Order order) {
		log.debug("Method add of CartService, order: {}", order);

		HttpEntity<CartDTO> request = new HttpEntity<>(transformer.transform(order));
		ResponseEntity<CartDTO> response = template.exchange(uriBuilder.fetchAddUri(), HttpMethod.POST, request,
				CartDTO.class);

		return transformer.transform(response.getBody());
	}

	public Order getById(Integer id) {
		log.debug("Method getById of CartService, id: {}", id);

		ResponseEntity<CartDTO> response = template.exchange(uriBuilder.fetchGetByIdUri(id), HttpMethod.GET, null,
				CartDTO.class);

		return transformer.transform(response.getBody());
	}

	public Order update(Integer id, Order order) {
		log.debug("Method update of CartService, id: {}, order: {}", id, order);

		HttpEntity<CartDTO> request = new HttpEntity<>(transformer.transform(order));
		ResponseEntity<CartDTO> response = template.exchange(uriBuilder.fetchUpdateUri(id), HttpMethod.PUT, request,
				CartDTO.class);

		return transformer.transform(response.getBody());
	}

}
