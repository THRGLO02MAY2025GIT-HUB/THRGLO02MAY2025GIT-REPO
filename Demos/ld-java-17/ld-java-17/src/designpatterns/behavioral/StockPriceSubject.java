package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

//Subject : StockPriceSubject notifies observers about stock price changes
public class StockPriceSubject {
    private List<StockPriceObserver> observers = new ArrayList<>();
    private String stockSymbol;
    private double price;

    public StockPriceSubject(){

    }
    public StockPriceSubject(String symbol){
        this.stockSymbol = symbol;
    }
    // Add an observer
    public void addObserver(StockPriceObserver observer){
        observers.add(observer);
    }

    // Remove an observer
    public void removeObserver(StockPriceObserver observer){
        observers.remove(observer);
    }

    // Notify all the observers
    public void notifyObservers(){
        for(StockPriceObserver observer : observers){
            observer.update(stockSymbol, price);
        }
    }

    public void setStockPrice(String stockSymbol, double price){
        this.stockSymbol = stockSymbol;
        this.price = price;
        notifyObservers();
    }
}
