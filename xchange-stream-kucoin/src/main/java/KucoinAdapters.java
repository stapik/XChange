import dto.response.KucoinOrderBookResponse;
import dto.response.KucoinTradeResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.utils.DateUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class KucoinAdapters {

    public static OrderBook adaptOrderBook(KucoinOrderBookResponse orderBookResponse, CurrencyPair currencyPair) {
        List<LimitOrder> asks = createOrders(currencyPair, Order.OrderType.ASK, orderBookResponse.getData().getChanges().getAsks());
        List<LimitOrder> bids = createOrders(currencyPair, Order.OrderType.BID, orderBookResponse.getData().getChanges().getBids());
        return new OrderBook(DateUtils.fromMillisUtc(orderBookResponse.getData().getSequenceEnd()), asks, bids);
    }

    public static List<LimitOrder> createOrders(CurrencyPair currencyPair, Order.OrderType orderType, String[][] orders) {
        List<LimitOrder> limitOrders = new ArrayList<>();
        for (String[] orderData : orders) {
            checkArgument(orderData.length == 3,
                    "Expected values (price, size, sequence) but got {0} elements.", orderData.length);
            limitOrders.add(createOrder(currencyPair, orderData, orderType));
        }
        return limitOrders;
    }

    public static LimitOrder createOrder(CurrencyPair currencyPair, String[] priceAndAmount, Order.OrderType orderType) {
        return new LimitOrder(
                orderType, new BigDecimal(priceAndAmount[1]), currencyPair, "", null, new BigDecimal(priceAndAmount[0])
        );
    }

    public static void checkArgument(boolean argument, String msgPattern, Object... msgArgs) {
        if (!argument) {
            throw new IllegalArgumentException(MessageFormat.format(msgPattern, msgArgs));
        }
    }

    public static Trade adaptTrade(KucoinTradeResponse tradeResponse, CurrencyPair currencyPair) {
        return new Trade(
                tradeResponse.getData().getSide().toUpperCase(Locale.ROOT).equals("BUY") ? Order.OrderType.BID : Order.OrderType.ASK,
                tradeResponse.getData().getSize(),
                currencyPair,
                tradeResponse.getData().getPrice(),
                DateUtils.fromMillisUtc(tradeResponse.getData().getSequence()),
                null,
                tradeResponse.getData().getMakerOrderId(),
                tradeResponse.getData().getTakerOrderId()
        );
    }
}
