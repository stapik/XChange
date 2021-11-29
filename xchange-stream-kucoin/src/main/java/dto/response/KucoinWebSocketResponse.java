package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class KucoinWebSocketResponse {
    @JsonProperty("type")
    String type;

    @JsonProperty("topic")
    String topic;

    @JsonProperty("subject")
    String subject;
}
