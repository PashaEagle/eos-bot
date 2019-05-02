package io.crypto.beer.telegram.bot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.model.OrderLine;
import io.crypto.beer.telegram.bot.service.model.CartLineDTO;
import io.crypto.beer.telegram.bot.service.model.page.CartLinePageDTO;
import io.crypto.beer.telegram.bot.service.transformer.CartLineTransformer;
import io.crypto.beer.telegram.bot.service.uri.CartLineUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartLineService {

	private final RestTemplate template;
	private final CartLineUriBuilder uriBuilder;
	private final CartLineTransformer transformer;

	public OrderLine add(OrderLine orderLine) {
		log.debug("Method add of CartLineService, orderLine: {}", orderLine);

		HttpEntity<CartLineDTO> request = new HttpEntity<>(transformer.transform(orderLine));
		ResponseEntity<CartLineDTO> response = template.exchange(uriBuilder.fetchAddUri(), HttpMethod.POST, request,
				CartLineDTO.class);

		return transformer.transform(response.getBody());
	}

	public OrderLine getById(Integer id) {
		log.debug("Method getById of CartLineService, id: {}", id);

		ResponseEntity<CartLineDTO> response = template.exchange(uriBuilder.fetchGetByIdUri(id), HttpMethod.GET, null,
				CartLineDTO.class);

		return transformer.transform(response.getBody());
	}
	
	public List<OrderLine> getBySearch(Integer cartId, Integer page, Integer size) {
		log.debug("Method getBySearch of ItemService, cartId: {}, page: {}, size: {}", cartId, page, size);

		ResponseEntity<CartLinePageDTO> response = template.exchange(
				uriBuilder.fetchGetByCartIdUri(cartId, page, size), HttpMethod.GET, null,
				CartLinePageDTO.class);
				
		return response.getBody().getContent().stream().map(transformer::transform).collect(Collectors.toList());
	}

	public OrderLine update(Integer id, OrderLine orderLine) {
		log.debug("Method update of CartLineService, id: {}, orderLine: {}", id, orderLine);

		HttpEntity<CartLineDTO> request = new HttpEntity<>(transformer.transform(orderLine));
		ResponseEntity<CartLineDTO> response = template.exchange(uriBuilder.fetchUpdateUri(id), HttpMethod.PUT, request,
				CartLineDTO.class);

		return transformer.transform(response.getBody());
	}

}