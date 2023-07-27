package br.com.votacao.vote.bem.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Duration;

public class DurationDeserializer extends StdDeserializer<Duration> {

    public DurationDeserializer() {
        super( Duration.class );
    }

    @Override
    public Duration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree( jsonParser );
        long duracaoEmMinutos = node.asLong();
        return (duracaoEmMinutos <= 0) ? Duration.ofMinutes( 1 ) : Duration.ofMinutes( duracaoEmMinutos );
    }
}
