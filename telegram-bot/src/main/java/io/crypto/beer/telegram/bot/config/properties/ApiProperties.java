package io.crypto.beer.telegram.bot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("api")
public class ApiProperties {

	private String path;
	private String host;
	private String port;
	private String uri;

}
