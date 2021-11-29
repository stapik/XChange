import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;

@Slf4j
public class KucoinManualExample {

    public static void main(String[] args) throws Exception {
        ProductSubscription productSubscription = ProductSubscription.create()
                .addOrderbook(CurrencyPair.ETH_USDT)
                .addOrderbook(CurrencyPair.BTC_USDT)
                .addTrades(CurrencyPair.ETH_USDT)
                .addTicker(CurrencyPair.BTC_USDT)
                .build();

        ExchangeSpecification spec =
                StreamingExchangeFactory.INSTANCE
                        .createExchangeWithoutSpecification(KucoinStreamingExchange.class)
                        .getDefaultExchangeSpecification();

        KucoinStreamingExchange exchange = (KucoinStreamingExchange) StreamingExchangeFactory.INSTANCE.createExchange(spec);

        exchange.connect(productSubscription).blockingAwait();

        Disposable sub1 = exchange
                .getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.ETH_USDT)
                .subscribe(
                        orderBook -> {
                            log.info("First ask: {}", orderBook.getAsks().get(0));
                            log.info("First bid: {}", orderBook.getBids().get(0));
                        },
                        throwable -> log.error("ERROR in getting order book: ", throwable));
        Disposable sub2 = exchange
                .getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USDT)
                .subscribe(
                        orderBook -> {
                            log.info("First ask: {}", orderBook.getAsks().get(0));
                            log.info("First bid: {}", orderBook.getBids().get(0));
                        },
                        throwable -> log.error("ERROR in getting order book: ", throwable));
        Disposable sub3 = exchange
                .getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USDT)
                .subscribe(
                        trade -> {
                            log.info("Trade Price: {}", trade.getPrice());
                            log.info("Trade Amount: {}", trade.getOriginalAmount());
                        },
                        throwable -> log.error("ERROR in getting trade: ", throwable));

        Disposable sub4 = exchange
                .getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USDT)
                .subscribe(
                        ticker -> {
                            log.info("Ticker Ask Price: {}", ticker.getAsk());
                            log.info("Ticker Bid Price: {}", ticker.getBid());
                            log.info("Ticker Bid Size: {}", ticker.getAskSize());
                            log.info("Ticker Bid Size: {}", ticker.getBidSize());
                        },
                        throwable -> log.error("ERROR in getting ticker: ", throwable));

        Thread.sleep(1000);
        sub1.dispose();
        sub2.dispose();
        sub3.dispose();
        sub4.dispose();
    }
}
