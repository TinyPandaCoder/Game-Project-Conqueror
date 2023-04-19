
package com.mycompany.conquer;

import java.util.ArrayList;

public class Conquer {

    public static void main(String[] args)  {
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);

for (Integer number : numbers) {
    number = number + 1;
}

System.out.println(numbers);

    }
}

