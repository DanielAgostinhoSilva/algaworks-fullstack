package br.com.algaworks.algamoneyapi.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties( prefix = "algamoney")
public class AlgamoneyApiProperty {

    private String originPermitida = "http://localhost:8000";

    private final Seguranca seguranca = new Seguranca();

    @Getter
    @Setter
    public static class Seguranca {
        private boolean enableHttps;
    }

}
