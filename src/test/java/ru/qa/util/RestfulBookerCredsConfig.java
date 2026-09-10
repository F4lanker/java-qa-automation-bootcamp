package ru.qa.util;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:pass.properties"})
public interface RestfulBookerCredsConfig extends Config {


    @DefaultValue("default_admin")
    String rstBookerName();


    @DefaultValue("default_password")
    String rstBookerPass();
}
