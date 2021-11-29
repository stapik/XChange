package dto;

import com.google.common.io.CharStreams;
import dto.response.KucoinTradeResponse;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

public class KucoinTradeResponseTest {

    @Test
    public void V2_TradeResponse_ParsingTest() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("TradeResponse.json");
        Assert.assertNotNull(is);

        String body;
        try (final Reader reader = new InputStreamReader(is)) {
            body = CharStreams.toString(reader);
        }

        KucoinTradeResponse message =
                StreamingObjectMapperHelper.getObjectMapper()
                        .readValue(body, KucoinTradeResponse.class);

        // Message
        Assert.assertEquals("message", message.getType());
        Assert.assertEquals("/market/match:BTC-USDT", message.getTopic());
        Assert.assertEquals("trade.l3match", message.getSubject());

        // Data
        Assert.assertEquals("1545896669145", message.getData().getSequence());
        Assert.assertEquals("type", message.getData().getType());
        Assert.assertEquals("BTC-USDT", message.getData().getSymbol());
        Assert.assertEquals("buy", message.getData().getSide());
        Assert.assertEquals("0.08200000000000000000", message.getData().getPrice());
        Assert.assertEquals("0.01022222000000000000", message.getData().getSize());
        Assert.assertEquals("5c24c5da03aa673885cd67aa", message.getData().getTradeId());
        Assert.assertEquals("5c24c5d903aa6772d55b371e", message.getData().getTakerOrderId());
        Assert.assertEquals("5c2187d003aa677bd09d5c93", message.getData().getMakerOrderId());
        Assert.assertEquals("1545913818099033203", message.getData().getTime());
    }
}
