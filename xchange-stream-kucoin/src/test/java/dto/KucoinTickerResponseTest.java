package dto;

import com.google.common.io.CharStreams;
import dto.response.KucoinTickerResponse;
import dto.response.KucoinTradeResponse;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class KucoinTickerResponseTest {

    @Test
    public void V2_TradeResponse_ParsingTest() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("TickerResponse.json");
        Assert.assertNotNull(is);

        String body;
        try (final Reader reader = new InputStreamReader(is)) {
            body = CharStreams.toString(reader);
        }

        KucoinTickerResponse message =
                StreamingObjectMapperHelper.getObjectMapper()
                        .readValue(body, KucoinTickerResponse.class);

        // Message
        Assert.assertEquals("message", message.getType());
        Assert.assertEquals("/market/ticker:BTC-USDT", message.getTopic());
        Assert.assertEquals("trade.ticker", message.getSubject());

        // Data
        Assert.assertEquals("1545896668986", message.getData().getSequence());
        Assert.assertEquals("0.08", message.getData().getPrice());
        Assert.assertEquals("0.011", message.getData().getSize());
        Assert.assertEquals("0.08", message.getData().getBestAsk());
        Assert.assertEquals("0.18", message.getData().getBestAskSize());
        Assert.assertEquals("0.049", message.getData().getBestBid());
        Assert.assertEquals("0.036", message.getData().getBestBidSize());
    }
}
