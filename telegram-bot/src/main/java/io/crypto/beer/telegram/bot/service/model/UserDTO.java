package io.crypto.beer.telegram.bot.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer id;
	
	private Integer telegaId;
	
	private String phone;
	
	private String telegaFullName;
	
	private String telegaUserName;
	
	private String telegaLanguageCode;
	
	private String referral;
	
	private String providedReferral;
	
}
