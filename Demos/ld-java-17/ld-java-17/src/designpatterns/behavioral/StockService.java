package designpatterns.behavioral;

import org.w3c.dom.ls.LSOutput;

public class StockService {
    private StockPriceSubject stockPriceSubject;
    public StockService(StockPriceSubject stockPriceSubject) {
        this.stockPriceSubject = stockPriceSubject;
    }

    //Simulate the price change and notify the investors
    public void updateStockPrice(String stockSymbol, double newPrice) {
        System.out.println("Updating stock price for " + stockSymbol + " to $" + newPrice );
        stockPriceSubject.setStockPrice(stockSymbol, newPrice);
        // The change in price of the stock notifies observers through the stockPriceSubject
    }
}
