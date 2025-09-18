/*
 *
 *  *
 *  *  * Copyright (c) 2024.
 *  *  * Vahid Alizadeh
 *  *  * Object-oriented Software Development
 *  *  * DePaul University
 *  *
 *
 */

package DesignPatterns.Composite.KitchenStaffExample;

import java.util.List;

public class KitchenStaffDriver {

    public static void main(String[] args) {

        // create sample data
        Chef execChef = new Chef("Chef 1", "Executive Chef");
        Chef headChef = new Chef("Chef 2", "Head Chef");
        Chef sousChef1 = new Chef ("Chef 3", "Sous Chef");
        Chef sousChef2 = new Chef ("Chef 4", "Sous Chef");
        Chef lineChef1 = new Chef ("Chef 5", "Line Chef");
        Chef lineChef2 = new Chef ("Chef 6", "Line Chef");
        Chef lineChef3 = new Chef ("Chef 7", "Line Chef");
        Chef lineChef4 = new Chef ("Chef 8", "Line Chef");
        Chef commisChef1 = new Chef ("Chef 9", "Commis Chef");
        Chef commisChef2 = new Chef ("Chef 10", "Commis Chef");

        // establish Executive Chef at top of tree structure
        // make Head Chef subordinate to Executive Chef
        execChef.add(headChef);

        // subordinate Sous Chefs to Head Chef
        headChef.add(sousChef1);
        headChef.add(sousChef2);

        // subordinate Line Chefs to Sous Chefs
        sousChef1.add(lineChef1);
        sousChef1.add(lineChef2);
        sousChef1.add(lineChef3);
        sousChef1.add(lineChef4);

        // subordinate Commmis Chefs under Line Chef 1
        lineChef1.add(commisChef1);
        lineChef1.add(commisChef2);

        // no subordinates to other Line Chefs
        lineChef2.add(null);
        lineChef3.add(null);
        lineChef4.add(null);

        // no subordinates to Commis Chefs
        commisChef1.add(null);
        commisChef2.add(null);

        // provide console output
        System.out.println("\n\nKitchen Staff Management System\n");
        System.out.println(execChef.getDetails());

        List<KitchenStaff> head = execChef.getStaffList();
        for (int i=0; i < head.size(); i++) {
            System.out.println("\t" + head.get(i).getDetails());
        }

        List<KitchenStaff> sous = headChef.getStaffList();
        for (int i=0; i < sous.size(); i++) {
            System.out.println("\t\t" + sous.get(i).getDetails());
        }

        List<KitchenStaff> line = sousChef1.getStaffList();
        for (int i=0; i < line.size(); i++) {
            System.out.println("\t\t\t" + line.get(i).getDetails());
        }

        List<KitchenStaff> commis = lineChef1.getStaffList();
        for (int i=0; i < commis.size(); i++) {
            System.out.println("\t\t\t\t" + commis.get(i).getDetails());
        }

        // firing a line chef
        System.out.println("\n\nKITCHEN STAFF UPDATE");
                System.out.println("\t" + lineChef1.getName() +
                ", " + lineChef1.getRole() + ", has been terminated.");
                sousChef1.fire(lineChef1);
                System.out.println("\nHere is the updated list of Line Chefs:");
                List<KitchenStaff> newLine = sousChef1.getStaffList();
        for (int i=0; i < newLine.size(); i++) {
        System.out.println("\t" + newLine.get(i).getDetails());
        }
    }
}
