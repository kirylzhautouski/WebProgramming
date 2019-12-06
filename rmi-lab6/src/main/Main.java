//package main;
//
//import chef.Chef;
//import salad.Salad;
//import salad.SaladBuilder;
//import vegetable.*;
//
///**
// * Class containing main method - an entry point of an application
// */
//public class Main {
//
//    /**
//     * Main method - an entry point of an application
//     * @param args
//     *        Command-line arguments
//     */
//    public static void main(String[] args) {
//
//        VegetableFactory vegetableFactory = new VegetableFactory();
//
//        Vegetable vegetable = vegetableFactory.getRawVegetable(Tomato.class, 100);
//
//        System.out.println(vegetable);
//
//        System.out.println();
//
//        SaladBuilder saladBuilder = new SaladBuilder();
//        saladBuilder.setHasSalt();
//        saladBuilder.setVegetables(new Vegetable[] {
//            vegetableFactory.getRawVegetable(Tomato.class, 100),
//            vegetableFactory.getBoiledVegetable(Carrot.class, 100),
//            vegetableFactory.getRoastedVegetable(Cucumber.class, 100) });
//        saladBuilder.setSourCream();
//
//        Chef chef = new Chef();
//
//        Salad salad = chef.prepareSalad(saladBuilder);
//
//
//        System.out.println();
//
//        System.out.println(chef.sortedByCalories(salad));
//
//        System.out.println();
//
//        VegetableParameters vegetableParameters = new VegetableParameters(18, VegetableState.RAW);
//
//        System.out.println(chef.findVegetablesWithParameters(salad, vegetableParameters));
//    }
//
//}
