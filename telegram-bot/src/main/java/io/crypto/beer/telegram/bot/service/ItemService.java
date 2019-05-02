package io.crypto.beer.telegram.bot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.service.model.ItemDTO;
import io.crypto.beer.telegram.bot.service.model.page.ItemPageDTO;
import io.crypto.beer.telegram.bot.service.transformer.ItemTransformer;
import io.crypto.beer.telegram.bot.service.uri.ItemUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

	private final RestTemplate template;
	private final ItemUriBuilder uriBuilder;
	private final ItemTransformer transformer;

	public Item add(Item item) {
		log.debug("Method add of ItemService, item: {}", item);

		HttpEntity<ItemDTO> request = new HttpEntity<>(transformer.transform(item));
		ResponseEntity<ItemDTO> response = template.exchange(uriBuilder.fetchAddUri(), HttpMethod.POST, request,
				ItemDTO.class);

		return transformer.transform(response.getBody());
	}

	public Item getById(Integer id) {
		log.debug("Method getById of ItemService, id: {}", id);

		ResponseEntity<ItemDTO> response = template.exchange(uriBuilder.fetchGetByIdUri(id), HttpMethod.GET, null,
				ItemDTO.class);

		return transformer.transform(response.getBody());
	}

	public List<Item> getBySearch(String name, Integer variationId, Integer breweryId, Integer page, Integer size) {
		log.debug("Method getBySearch of ItemService, name: {}, variationId: {}, breweryId: {}, page: {}, size: {}",
				name, variationId, breweryId, page, size);

		ResponseEntity<ItemPageDTO> response = template.exchange(
				uriBuilder.fetchGetBySearchUri(name, variationId, breweryId, page, size), HttpMethod.POST, null,
				ItemPageDTO.class);
				
		return response.getBody().getContent().stream().map(transformer::transform).collect(Collectors.toList());
	}

	public Item update(Integer id, Item item) {
		log.debug("Method update of ItemService, id: {}, item: {}", id, item);

		HttpEntity<ItemDTO> request = new HttpEntity<>(transformer.transform(item));
		ResponseEntity<ItemDTO> response = template.exchange(uriBuilder.fetchUpdateUri(id), HttpMethod.PUT, request,
				ItemDTO.class);

		return transformer.transform(response.getBody());
	}

}
