import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingMarketDataService;
import io.reactivex.Completable;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.kucoin.KucoinExchange;

@Slf4j
public class KucoinStreamingExchange extends KucoinExchange implements StreamingExchange {

  private final String V2_URL = "wss://push1-v2.kucoin.com/endpoint";

  private KucoinStreamingService streamingService;
  private StreamingMarketDataService streamingMarketDataService;

  public KucoinStreamingExchange() {}

  @Override
  public Completable connect(ProductSubscription... args) {
    if (args == null || args.length == 0)
      throw new UnsupportedOperationException("The ProductSubscription must be defined!");

    this.streamingService = new KucoinStreamingService(V2_URL, exchangeSpecification);
    this.streamingMarketDataService = new KucoinStreamingMarketDataService(streamingService);

    streamingService.subscribeMultipleCurrencyPairs(args);
    return streamingService.connect();
  }

  @Override
  public Completable disconnect() {
    KucoinStreamingService service = streamingService;
    streamingService = null;
    streamingMarketDataService = null;
    return service.disconnect();
  }

  @Override
  public StreamingMarketDataService getStreamingMarketDataService() {
    return streamingMarketDataService;
  }

  @Override
  public boolean isAlive() {
    return streamingService != null && streamingService.isSocketOpen();
  }

  @Override
  public void useCompressedMessages(boolean compressedMessages) {
    streamingService.useCompressedMessages(compressedMessages);
  }
}
