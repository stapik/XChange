package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KucoinTradeResponse extends KucoinWebSocketResponse {
    @JsonProperty("type")
    String type;

    @JsonProperty("topic")
    String topic;

    @JsonProperty("subject")
    String subject;

    @JsonProperty("data")
    Data data;

    @Getter
    @Setter
    public class Data {
        @JsonProperty("sequence")
        String sequence;

        @JsonProperty("type")
        String type;

        @JsonProperty("symbol")
        String symbol;

        @JsonProperty("side")
        String side;

        @JsonProperty("price")
        String price;

        @JsonProperty("size")
        String size;

        @JsonProperty("tradeId")
        String tradeId;

        @JsonProperty("takerOrderId")
        String takerOrderId;

        @JsonProperty("makerOrderId")
        String makerOrderId;

        @JsonProperty("time")
        String time;
    }
}

