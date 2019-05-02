package io.crypto.beer.telegram.bot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.model.Brewery;
import io.crypto.beer.telegram.bot.service.model.BreweryDTO;
import io.crypto.beer.telegram.bot.service.model.page.BreweryPageDTO;
import io.crypto.beer.telegram.bot.service.transformer.BreweryTransformer;
import io.crypto.beer.telegram.bot.service.uri.BreweryUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BreweryService {

	private final RestTemplate template;
	private final BreweryUriBuilder uriBuilder;
	private final BreweryTransformer transformer;

	public Brewery add(Brewery brewery) {
		log.debug("Method add of BreweryService, brewery: {}", brewery);

		HttpEntity<BreweryDTO> request = new HttpEntity<>(transformer.transform(brewery));
		ResponseEntity<BreweryDTO> response = template.exchange(uriBuilder.fetchAddUri(), HttpMethod.POST, request,
				BreweryDTO.class);

		return transformer.transform(response.getBody());
	}

	public Brewery getById(Integer id) {
		log.debug("Method getById of BreweryService, id: {}", id);

		ResponseEntity<BreweryDTO> response = template.exchange(uriBuilder.fetchGetByIdUri(id), HttpMethod.GET, null,
				BreweryDTO.class);

		return transformer.transform(response.getBody());
	}

	public List<Brewery> getByPagination(Integer page, Integer size) {
		log.debug("Method getByPagination of BreweryService, page: {}, size: {}", page, size);
		
		ResponseEntity<BreweryPageDTO> response = template.exchange(uriBuilder.fetchGetByPaginationUri(page, size), HttpMethod.GET, null,
				BreweryPageDTO.class);

		return response.getBody().getContent().stream().map(transformer::transform).collect(Collectors.toList());
	}

	public Brewery update(Integer id, Brewery brewery) {
		log.debug("Method update of BreweryService, id: {}, brewery: {}", id, brewery);

		HttpEntity<BreweryDTO> request = new HttpEntity<>(transformer.transform(brewery));
		ResponseEntity<BreweryDTO> response = template.exchange(uriBuilder.fetchUpdateUri(id), HttpMethod.PUT,
				request, BreweryDTO.class);

		return transformer.transform(response.getBody());
	}

}
