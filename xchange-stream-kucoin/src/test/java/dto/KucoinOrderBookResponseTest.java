package dto;

import com.google.common.io.CharStreams;
import dto.response.KucoinOrderBookResponse;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;

public class KucoinOrderBookResponseTest {

    @Test
    public void V2_OrderBookResponse_ParsingTest() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("OrderBookResponse.json");
        Assert.assertNotNull(is);

        String body;
        try (final Reader reader = new InputStreamReader(is)) {
            body = CharStreams.toString(reader);
        }

        KucoinOrderBookResponse message =
                StreamingObjectMapperHelper.getObjectMapper()
                        .readValue(body, KucoinOrderBookResponse.class);

        // Message
        Assert.assertEquals("message", message.getType());
        Assert.assertEquals("/market/level2:BTC-USDT", message.getTopic());
        Assert.assertEquals("trade.l2update", message.getSubject());

        // Data
        Assert.assertEquals(1545896669105L, message.getData().getSequenceStart());
        Assert.assertEquals(1545896669106L, message.getData().getSequenceEnd());
        Assert.assertEquals("BTC-USDT", message.getData().getSymbol());

        // Bid
        Assert.assertEquals("4", message.getData().getChanges().getBids()[0][0]);
        Assert.assertEquals("1", message.getData().getChanges().getBids()[0][1]);
        Assert.assertEquals("1545896669106", message.getData().getChanges().getBids()[0][2]);
        // Ask
        Assert.assertEquals("6", message.getData().getChanges().getAsks()[0][0]);
        Assert.assertEquals("1", message.getData().getChanges().getAsks()[0][1]);
        Assert.assertEquals("1545896669105", message.getData().getChanges().getAsks()[0][2]);
    }
}
