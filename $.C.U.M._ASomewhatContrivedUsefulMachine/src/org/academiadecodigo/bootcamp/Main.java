package org.academiadecodigo.bootcamp;

public class Main {

    public static void main(String[] args) {

        Machine machine = new Machine();

        MonoOperation<Integer> duplicate = (i -> (i * 2));
        Integer result1 = machine.operate(5, duplicate);
        System.out.println(result1);


        BiOperation<String> sum = ((i1, i2) -> i1 + i2);
        String result2 = machine.operate("String", "This", sum);
        System.out.println(result2);


        Boolean result = machine.confirm((i1, i2) -> i1 == i2 , "String", "This");
        System.out.println(result);

        //MonoOperation<String> operation = (string) -> string.toUpperCase();
        MonoOperation<String> operation = String::toUpperCase;
        System.out.println(machine.operate("hello", operation));

        BiOperation<Integer> add = Integer::sum;
        System.out.println(machine.operate(2, 5, add));

        BiOperationTwoTypes<Car, String> carCreator = Car::new;

        Car newCar = machine.confirm(carCreator, "Honda", "HotPink");
        String color = newCar.getColor();
        System.out.println(color);

        BiOperationThreeTypes<Integer, String , Boolean> doesThisWork = (i1, i2) -> (i1.equals(i2));
        System.out.println(machine.doesThisWorkForReal(1, "1", doesThisWork));

    }
}
