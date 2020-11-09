package com.codecool.kitchenmanagement;

import java.time.LocalDate;
import java.util.List;

import com.codecool.kitchenmanagement.Kitchen;
import com.codecool.kitchenmanagement.employees.Employee;
import com.codecool.kitchenmanagement.employees.Chef;
import com.codecool.kitchenmanagement.employees.Cook;
import com.codecool.kitchenmanagement.employees.KitchenHelper;

public class Main {
    public static void main(String[] args) {
        var kitchen = new Kitchen();

        LocalDate oneDate = LocalDate.of(1990,1,1);
        var chef = new Chef("Chef McCheferson", oneDate, 1000);
        var cook1 = new Cook("Cook McCookface", oneDate.plusYears(3), 800);
        var cook2 = new Cook("Pan Fryingkind",  oneDate.plusYears(-2), 800);
        var helper1 = new KitchenHelper("Helper Helpowitz", oneDate.plusYears(1), 750);
        var helper2 = new KitchenHelper("Handy Smith",      oneDate.plusYears(3), 750);
        var helper3 = new KitchenHelper("Slim Quicktohelp", oneDate.plusMonths(4), 750);
        chef.receiveKnife();
        cook1.receiveKnife();
        cook2.receiveKnife();

        List<Employee> employees = List.of(chef, cook1, cook2,
                helper1, helper2, helper3);
        employees.forEach(kitchen::hireEmployee);

        kitchen.conductAShift();
    }
}