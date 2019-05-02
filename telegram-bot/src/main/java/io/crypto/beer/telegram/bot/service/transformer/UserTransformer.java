package io.crypto.beer.telegram.bot.service.transformer;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.service.model.UserDTO;

@Component
public class UserTransformer {
	
	public UserDTO transform(Profile profile) {
		return UserDTO.builder()
				.id(profile.getId())
				.telegaId(profile.getTelegaId())
				.telegaFullName(profile.getFullName())
				.telegaUserName(profile.getUserName())
				.telegaLanguageCode(profile.getLanguageCode())
				.phone(profile.getPhone())
				.referral(profile.getReferral())
				.providedReferral(profile.getProvidedReferral())
				.build();
	}
	
	public Profile transform(UserDTO userDTO) {
		return Profile.builder()
				.id(userDTO.getId())
				.telegaId(userDTO.getTelegaId())
				.fullName(userDTO.getTelegaFullName())
				.userName(userDTO.getTelegaUserName())
				.languageCode(userDTO.getTelegaLanguageCode())
				.phone(userDTO.getPhone())
				.referral(userDTO.getReferral())
				.providedReferral(userDTO.getProvidedReferral())
				.build();
	}
	
	public Profile transform(Profile profile, User user) {
		profile.setTelegaId(user.getId());
		profile.setUserName(user.getUserName());
		profile.setLanguageCode(user.getLanguageCode());
		return profile;
	}
	
}
