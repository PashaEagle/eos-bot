package io.crypto.beer.telegram.bot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.model.Variation;
import io.crypto.beer.telegram.bot.service.model.VariationDTO;
import io.crypto.beer.telegram.bot.service.model.page.VariationPageDTO;
import io.crypto.beer.telegram.bot.service.transformer.VariationTransformer;
import io.crypto.beer.telegram.bot.service.uri.VariationUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VariationService {

	private final RestTemplate template;
	private final VariationUriBuilder uriBuilder;
	private final VariationTransformer transformer;

	public Variation add(Variation variation) {
		log.debug("Method add of VariationService, variation: {}", variation);

		HttpEntity<VariationDTO> request = new HttpEntity<>(transformer.transform(variation));
		ResponseEntity<VariationDTO> response = template.exchange(uriBuilder.fetchAddUri(), HttpMethod.POST, request,
				VariationDTO.class);

		return transformer.transform(response.getBody());
	}

	public Variation getById(Integer id) {
		log.debug("Method getById of VariationService, id: {}", id);

		ResponseEntity<VariationDTO> response = template.exchange(uriBuilder.fetchGetByIdUri(id), HttpMethod.GET, null,
				VariationDTO.class);

		return transformer.transform(response.getBody());
	}

	public List<Variation> getByPagination(Integer page, Integer size) {
		log.debug("Method getByPagination of VariationService, page: {}, size: {}", page, size);
		
		ResponseEntity<VariationPageDTO> response = template.exchange(uriBuilder.fetchGetByPaginationUri(page, size), HttpMethod.GET, null,
				VariationPageDTO.class);

		return response.getBody().getContent().stream().map(transformer::transform).collect(Collectors.toList());
	}

	public Variation update(Integer id, Variation variation) {
		log.debug("Method update of VariationService, id: {}, variation: {}", id, variation);

		HttpEntity<VariationDTO> request = new HttpEntity<>(transformer.transform(variation));
		ResponseEntity<VariationDTO> response = template.exchange(uriBuilder.fetchUpdateUri(id), HttpMethod.PUT,
				request, VariationDTO.class);

		return transformer.transform(response.getBody());
	}

}
