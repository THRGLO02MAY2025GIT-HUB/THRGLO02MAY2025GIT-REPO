package designpatterns.behavioral;

public class Investor implements StockPriceObserver{
  private String name;
    @Override
    public void update(String stockSymbol, double newPrice) {
        // When ever the stock price changes this method will be called
        System.out.println("Invester " + name + " notified. " + stockSymbol + "new price : $" + newPrice);
    }
    public Investor(String name) {
        this.name = name;
    }
}
