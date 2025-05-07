### Lab Practice: Enhancing Stream API Usage

1. **takeWhile**: Use the `takeWhile` method to process transactions with amounts less than 200. Observe how the stream terminates when the predicate returns false.

2. **dropWhile**: Implement the `dropWhile` method to skip smaller transactions and process the remaining ones. This is the opposite of `takeWhile`.

3. **ofNullable**: Handle nullable transactions using the `ofNullable` method. Test with both valid and null transactions to see how the stream processes them gracefully.

4. **teeing**: Use the `teeing` collector to calculate both the average and total of transaction amounts. Create a `TransactionSummary` class to store these results.

5. **toList**: Collect the transactions into a list using the `toList` method. Try mutating the resulting list and observe its behavior.

6. **Bonus**: Experiment with error handling in the `takeWhile` method by simulating scenarios where exceptions might occur during predicate evaluation.
