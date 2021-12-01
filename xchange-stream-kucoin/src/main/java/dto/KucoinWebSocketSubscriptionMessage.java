package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KucoinWebSocketSubscriptionMessage {
    @JsonProperty("id")
    private final long id;

    @JsonProperty("type")
    private final String type;

    @JsonProperty("topic")
    private final String topic;

    @JsonProperty("response")
    private final boolean response;
}

