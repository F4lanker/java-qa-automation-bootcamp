package ru.qa.config.constants;

import org.aeonbits.owner.Config;
@Config.Sources("classpath:apikey.properties")
public interface ApiKeyConfig extends Config
 {
    @Config.Key("reqres.api.key")
    String apiKey();
}
