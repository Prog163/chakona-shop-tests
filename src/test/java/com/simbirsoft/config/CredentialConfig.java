package com.simbirsoft.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:credential.properties"
})
public interface CredentialConfig extends Config {

    String user_login();
    String user_password();
    String remote_login();
    String remote_password();

}
