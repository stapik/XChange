package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KucoinTickerResponse extends KucoinWebSocketResponse{

    @JsonProperty("data")
    Data data;

    @Getter
    @Setter
    public class Data{
        @JsonProperty("sequence")
        String sequence;

        @JsonProperty("price")
        String price;

        @JsonProperty("size")
        String size;

        @JsonProperty("bestAsk")
        String bestAsk;

        @JsonProperty("bestAskSize")
        String bestAskSize;

        @JsonProperty("bestBid")
        String bestBid;

        @JsonProperty("bestBidSize")
        String bestBidSize;
    }
}

