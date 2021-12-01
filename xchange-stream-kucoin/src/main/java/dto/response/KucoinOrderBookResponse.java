package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KucoinOrderBookResponse {

    @JsonProperty("type")
    private final String type;

    @JsonProperty("topic")
    private final String topic;

    @JsonProperty("subject")
    private final String subject;

    @JsonProperty("data")
    private final Data data;

    @Getter
    @AllArgsConstructor
    public static class Changes {
        @JsonProperty("asks")
        private final String[][] asks;

        @JsonProperty("bids")
        private final String[][] bids;
    }

    @Getter
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("sequenceStart")
        private final long sequenceStart;

        @JsonProperty("sequenceEnd")
        private final long sequenceEnd;

        @JsonProperty("symbol")
        private final String symbol;

        @JsonProperty("changes")
        private final Changes changes;
    }
}

