package designpatterns.behavioral;

public interface StockPriceObserver {
    void update(String stockSymbol, double newPrice);
}
