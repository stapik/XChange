package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class KucoinWebSocketSubscriptionMessage {
    @JsonProperty("id")
    long id;

    @JsonProperty("type")
    String type;

    @JsonProperty("topic")
    String topic;

    @JsonProperty("response")
    boolean response;
}

