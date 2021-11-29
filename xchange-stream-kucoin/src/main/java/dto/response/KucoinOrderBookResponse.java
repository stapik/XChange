package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KucoinOrderBookResponse extends KucoinWebSocketResponse{

    @JsonProperty("data")
    Data data;

    @Getter
    @Setter
    public class Changes {
        @JsonProperty("asks")
        String[][] asks;

        @JsonProperty("bids")
        String[][] bids;
    }

    @Getter
    @Setter
    public class Data {
        @JsonProperty("sequenceStart")
        long sequenceStart;

        @JsonProperty("sequenceEnd")
        long sequenceEnd;

        @JsonProperty("symbol")
        String symbol;

        @JsonProperty("changes")
        Changes changes;
    }
}

