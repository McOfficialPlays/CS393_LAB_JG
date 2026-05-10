package week7;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/** Submit to the TA, and he will assign you a grade based on a few selected responses.  */





public class Week10_labs
{
    public static void main(String[] args)
	{
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

		// Collect elements into a Set
        Set<String> fruitSet = fruit.stream()
                .collect(Collectors.toSet());
        System.out.println("Set of fruit: " + fruitSet);

        // Collect the fruit into groups based on their first character
        Map<Character, List<String>> byFirstChar = fruit.stream()
        	.collect(Collectors.groupingBy(s -> s.charAt(0))); //do the collecting s-> char at 0 means the first char
        System.out.println("Grouped by first character: " + byFirstChar);
		// Group fruit by the length of the name
        Map<Integer, List<String>> byLength = fruit.stream()
                .collect(Collectors.groupingBy(String::length)); //string :: lenght meansgrouping by length
        System.out.println("Grouped by length: " + byLength);
        // Collect the fruit that has erry in it
        List<String> fruitsWithErry = fruit.stream()
                .filter(s -> s.contains("erry"))//filter everything that has erry
                .collect(Collectors.toList());
        System.out.println("Fruit with 'erry': " + fruitsWithErry);

        // Create a partition of fruit based on if it contains erry
        Map<Boolean, List<String>> partitionErry = fruit.stream()
                .collect(Collectors.partitioningBy(s -> s.contains("erry")));//partition out erry
        System.out.println("Partition by contains 'erry': " + partitionErry);

        // Collect the fruit that has 5 or less symbols
        List<String> shortFruit = fruit.stream()
                .filter(s -> s.length() <= 5) // filter by 5 or less
                .collect(Collectors.toList());
        System.out.println("Fruit with length <= 5: " + shortFruit);

        // Find the total number of symbols in all the fruit stored
        int totalFruitSymbols = fruit.stream()
                .mapToInt(String::length) // finding the length of the fruits
                .sum();  //adding to the sum
        System.out.println("Total number of symbols in all fruit: " + totalFruitSymbols);


	List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

	 // Partition data based on if >= 50
        Map<Boolean, List<Integer>> partitionBy50 = data.stream()
                .collect(Collectors.partitioningBy(n -> n >= 50)); // if >=50 
        System.out.println("Partition data by >= 50: " + partitionBy50);

        // Divide data into groups based on the remainder when divided by 7
        Map<Integer, List<Integer>> byRemainder7 = data.stream()
                .collect(Collectors.groupingBy(n -> n % 7)); //get remainder of 7
        System.out.println("Grouped by remainder when divided by 7: " + byRemainder7);

        // Find the sum of the data
        int sumData = data.stream()
                .mapToInt(Integer::intValue) //extract the data
                .sum(); //sum of the data
        System.out.println("Sum of data: " + sumData);

        // Collect the unique values
        Set<Integer> uniqueData = data.stream()
                .collect(Collectors.toSet()); //collecting
        System.out.println("Unique values: " + uniqueData);

        // Compute the cube of each value
        List<Integer> cubes = data.stream()
                .map(n -> n * n * n) // computing cube of n
                .collect(Collectors.toList());
        System.out.println("Cube of each value: " + cubes);

        // Find the sum of the cubes of each value
        int sumOfCubes = data.stream()
                .mapToInt(n -> n * n * n) // computing cube of n
                .sum(); // doing sum
        System.out.println("Sum of cubes: " + sumOfCubes);

        // Increase the value of each element by 5
        List<Integer> plusFive = data.stream()
                .map(n -> n + 5) //adding 5
                .collect(Collectors.toList()); //collecting into list
        System.out.println("Each value increased by 5: " + plusFive);

        // Compute the cube of the even values
        List<Integer> cubesOfEven = data.stream()
                .filter(n -> n % 2 == 0) // get even
                .map(n -> n * n * n) // compute cube
                .collect(Collectors.toList());
        System.out.println("Cube of even values: " + cubesOfEven);
    

   }
}
