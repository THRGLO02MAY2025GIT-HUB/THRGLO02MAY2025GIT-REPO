package designpatterns.behavioral;

public class Main {
    public static void main(String[] args) {
        // Create a (Stock in StockMarket) StockPriceSubject
        StockPriceSubject stockPriceSubject = new StockPriceSubject();

        // create investors (observers)
        Investor dinesh = new Investor("Dinesh");
        Investor mahalingam = new Investor("Mahalingam");
        Investor priya = new Investor("Priya");

        // Add investors as observers to the stock price subject
        stockPriceSubject.addObserver(dinesh);
        stockPriceSubject.addObserver(mahalingam);

        // Create the stock service to update price
        StockService stockService = new StockService(stockPriceSubject);

        // Simulate stock updates sequentially
        stockService.updateStockPrice("AAPL", 150.50 );
        stockService.updateStockPrice("AAPL", 151.50 );
    }
}
