package ru.qa.base;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public abstract class WireMockExt  {
        @RegisterExtension
        public static WireMockExtension wm = WireMockExtension.newInstance()
                                                              .options(wireMockConfig().port(8089))
                                                              .build();
}
