import dto.response.KucoinOrderBookResponse;
import info.bitrich.xchangestream.core.StreamingMarketDataService;
import io.reactivex.Observable;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;

@Slf4j
public class KucoinStreamingMarketDataService implements StreamingMarketDataService {
    private final KucoinStreamingService service;

    public KucoinStreamingMarketDataService(KucoinStreamingService service) {
        this.service = service;
    }

    private boolean containsPair(List<CurrencyPair> pairs, CurrencyPair pair) {
        return pairs.stream().anyMatch(p -> p.equals(pair));
    }

    @Override
    public Observable<OrderBook> getOrderBook(CurrencyPair currencyPair, Object... args) {
        if (!containsPair(service.getProduct().getOrderBook(), currencyPair))
            throw new UnsupportedOperationException(
                    String.format("The currency pair %s is not subscribed for order book", currencyPair));

        return service
                .getRawWebSocketTransactions(currencyPair, KucoinStreamingService.SPOT_ORDERBOOK_CHANNEL)
                .map(msg -> ((KucoinOrderBookResponse) msg).toOrderBook(currencyPair));
    }

    @Override
    public Observable<Ticker> getTicker(CurrencyPair currencyPair, Object... args) {
        // TODO: make ticker
        throw new NotYetImplementedForExchangeException("Not yet implemented!");
    }

    @Override
    public Observable<Trade> getTrades(CurrencyPair currencyPair, Object... args) {
        return service
                .getRawWebSocketTransactions(currencyPair, KucoinStreamingService.SPOT_TRADES_CHANNEL)
                .map(msg -> ((KucoinOrderBookResponse) msg).toTrade());
    }
}
