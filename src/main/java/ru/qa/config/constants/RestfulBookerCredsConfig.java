package ru.qa.config.constants;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:pass.properties"})
public interface RestfulBookerCredsConfig extends Config {

    @Key("pass.rstBookerName")
    @DefaultValue("default_admin")
    String rstBookerName();

    @Key("pass.rstBookerPass")
    @DefaultValue("default_password")
    String rstBookerPass();
}
