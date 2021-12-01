package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class KucoinTradeResponse {
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
    public static class Data {
        @JsonProperty("sequence")
        private final long sequence;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("symbol")
        private final String symbol;

        @JsonProperty("side")
        private final String side;

        @JsonProperty("price")
        private final BigDecimal price;

        @JsonProperty("size")
        private final BigDecimal size;

        @JsonProperty("tradeId")
        private final String tradeId;

        @JsonProperty("takerOrderId")
        private final String takerOrderId;

        @JsonProperty("makerOrderId")
        private final String makerOrderId;

        @JsonProperty("time")
        private final String time;
    }
}

