package io.crypto.beer.telegram.bot.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.service.model.UserDTO;
import io.crypto.beer.telegram.bot.service.transformer.UserTransformer;
import io.crypto.beer.telegram.bot.service.uri.UserUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final RestTemplate template;
	private final UserUriBuilder uriBuilder;
	private final UserTransformer transformer;
	
	public Profile add(Profile profile) {
		log.debug("Method add of UserService, profile: {}", profile);

		HttpEntity<UserDTO> request = new HttpEntity<>(transformer.transform(profile));
		ResponseEntity<UserDTO> response = template.exchange(uriBuilder.fetchAddUri(), HttpMethod.POST, request,
				UserDTO.class);
		
		return transformer.transform(response.getBody());
	}

	public Profile getById(Integer id) {
		log.debug("Method getById of UserService, id: {}", id);

		ResponseEntity<UserDTO> response = template.exchange(uriBuilder.fetchGetByIdUri(id), HttpMethod.GET, null,
				UserDTO.class);

		return transformer.transform(response.getBody());
	}

	public Profile getByTelegaId(Integer telegaId) {
		log.debug("Method getByTelegaId of UserService, telegaId: {}", telegaId);
		
		ResponseEntity<UserDTO> response = template.exchange(uriBuilder.fetchGetByTelegaIdUri(telegaId), HttpMethod.GET,
				null, UserDTO.class);

		if (response.getBody() == null) {
			return null;
		} else {
			return transformer.transform(response.getBody());
		}
	}

	public Profile update(Integer id, Profile profile) {
		log.debug("Method update of UserService, id: {}, profile: {}", id, profile);

		HttpEntity<UserDTO> request = new HttpEntity<>(transformer.transform(profile));
		ResponseEntity<UserDTO> response = template.exchange(uriBuilder.fetchUpdateUri(id), HttpMethod.PUT, request,
				UserDTO.class);

		return transformer.transform(response.getBody());
	}

}
