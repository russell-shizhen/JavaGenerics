/*
 * -----------------------------------------------------------------------------
 *
 * Copyright (c) 2019.   -  Zhang Shizhen
 *
 * -----------------------------------------------------------------------------
 * @Author: Zhang Shizhen
 * @Contact: shizhen.chn@gmail.com
 * -----------------------------------------------------------------------------
 */

package com.arophix.java.generics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arophix.java.generics.R;
import com.arophix.java.generics.container.Plate;
import com.arophix.java.generics.object.Apple;
import com.arophix.java.generics.object.Banana;
import com.arophix.java.generics.object.Food;
import com.arophix.java.generics.object.Fruit;
import com.arophix.java.generics.object.Car;
import com.arophix.java.generics.object.GreenApple;
import com.arophix.java.generics.object.Meat;
import com.arophix.java.generics.object.Pork;
import com.arophix.java.generics.object.RedApple;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        // Case 1
        Plate<Apple> plateOfApple = new Plate<>(new Apple());
        Plate<Banana> plateOfBanana = new Plate<>(new Banana());
    
        Plate<Fruit> plateOfOneKindOfFruit = new Plate<Fruit>(new Fruit());
        plateOfOneKindOfFruit = new Plate<Fruit>(new Apple());
    
        // Compiler error: Apple "IS-A" fruit, BUT, a plate of Apple "NOT-IS-A" plate of Fruit.
        plateOfOneKindOfFruit = new Plate<>(new Apple());
        plateOfOneKindOfFruit = new Plate<Apple>(new Apple());
    
        
        // Case 2
        Plate<? extends Fruit> plateOfAllKindsOfFruit;
    
        plateOfAllKindsOfFruit = new Plate<Apple>(new Apple());
        Fruit fruit = plateOfAllKindsOfFruit.getItem();
        
        // Error: Because, compiler only knows in side of the plate, the object should be either Fruit
        // or the sub-class of Fruit. But compiler CANNOT decide what type it exactly is.
        // For example: if during runtime,  plateOfAllKindsOfFruit is holding Apple, invoke setItem with
        // a Banana will become an undefined behavior.
        plateOfAllKindsOfFruit.setItem(new Apple()); //error!
        plateOfAllKindsOfFruit.setItem(new Fruit()); //error!
        plateOfAllKindsOfFruit.setItem(new Banana()); //error!
        
        // Error: Because, compiler DOES NOT know whether the plate is holding apples or bananas,
        // it only KNOWS that inside of the plate, it is holding A-KIND-OF Fruit, but it does not know
        // that kind of Fruit IS-A apple or IS-A banana, so assigning getter result to a specific subclass
        // is not accepted, even if you know it is Apple or Banana.
        Apple apple = plateOfAllKindsOfFruit.getItem(); //error!
    
        
        // Case 3
        Plate<? super Fruit> plateOfAllFruitOrFood;
    
        plateOfAllFruitOrFood = new Plate<Fruit>(new Fruit());
        plateOfAllFruitOrFood = new Plate<Fruit>(new Food()); // error!
        
        plateOfAllFruitOrFood = new Plate<Food>(new Fruit());
        plateOfAllFruitOrFood = new Plate<Food>(new Food());
    
    
        plateOfAllFruitOrFood.setItem(new Fruit());
        plateOfAllFruitOrFood.setItem(new Apple());
        plateOfAllFruitOrFood.setItem(new Banana());
        plateOfAllFruitOrFood.setItem(new RedApple());
        plateOfAllFruitOrFood.setItem(new GreenApple());
    
        // Error: Because, compiler only knows in side of the plate, the object should be either Fruit
        // or the super-class of Fruit. But compiler CANNOT decide what type it exactly is.
        // For example: if during runtime,  plateOfAllFruitOrFood is holding Fruit, invoke setItem with
        // a Food will become an undefined behavior.
        plateOfAllFruitOrFood.setItem(new Food());
        
        // Error: Because, Port, Meat or Car does not have a valid inheritance hierarchy, which
        // is "the class has to be a super class of Fruit".
        // E.g. For class GreenApple, it has an inheritance hierarchy of "Food <- Fruit <- Apple <- GreenApple",
        // its super classes, Fruit satisfy the constraints <? super Fruit>,
        // so, plateOfAllFruitOrFood.setItem(new GreenApple()); is fine.
        // But, class Pork, which has an inheritance hierarchy of "Food <- Meat <- Pork", is not valid.
        plateOfAllFruitOrFood.setItem(new Pork());
        plateOfAllFruitOrFood.setItem(new Meat());
        plateOfAllFruitOrFood.setItem(new Car());
    }
}
