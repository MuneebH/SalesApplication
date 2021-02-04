package com.b07.store;

import java.sql.Connection;
import java.util.List;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.inventory.ItemTypes;
import com.b07.security.PasswordHelpers;
import com.b07.users.Admin;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.EmployeeInterface;
import com.b07.users.Role;
import com.b07.users.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalesApplication {
  /**
   * This is the main method to run your entire program! Follow the "Pulling it together"
   * instructions to finish this off.
   * 
   * @param argv unused.
   */
  public static void main(String[] argv) {

    Connection connection = DatabaseDriverExtender.connectOrCreateDataBase();
    if (connection == null) {
      System.out.print("NOOO");
    }
    try {
      // This line will create a buffered reader that reads from the system input using an input
      // stream reader
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      // TODO Check what is in argv


      // If it is -1
      /*
       * TODO This is for the first run only! Add this code:
       * DatabaseDriverExtender.initialize(connection); Then add code to create your first account,
       * an administrator with a password Once this is done, create an employee account as well.
       * 
       */

      if (argv.length == 2 && argv[0] == "-" && argv[1] == "1") {
        DatabaseDriverExtender.initialize(connection);
        Admin admin = new Admin(0, "Muneeb", 9, "Military Trail"); // make a default admin
        System.out.println("What's your password? ");
        String password = bufferedReader.readLine();
        String hPassword = PasswordHelpers.passwordHash(password);
        Employee employee = new Employee(1, "Hashmi", 19, "Swag Trail");
      } // If it is 1
      /*
       * TODO In admin mode, the user must first login with a valid admin account This will allow
       * the user to promote employees to admins. Currently, this is all an admin can do.
       */
      else if (argv.length == 1 && argv[0] == "1") {
        boolean valid = true;
        System.out.println("What's your name?");
        String name = bufferedReader.readLine();
        System.out.println("What's your password?");
        String password = bufferedReader.readLine();
        List<User> users = DatabaseSelectHelper.getUsersDetails();
        for (User user : users) {
          if (name == user.getName()) {
            valid = user.authenticate(password); // authenticate them
          }
        }
        if (valid) {
          Admin admin = new Admin();
          System.out.println("Do you want to promote an employee? Y/N ");
          String input = bufferedReader.readLine();
          if (input == "Y") {
            System.out.println("Enter the name of the employee you want to promote: ");
            String inputTwo = bufferedReader.readLine();
            for (User user : users) {
              if (inputTwo == user.getName()) {
                admin.promoteEmployee((Employee) user);
              }
            }
          }
        }
      } else {
        int input;
        System.out.println("1 - Employee Login");
        System.out.println("2 - Customer Login");
        System.out.println("0 - Exit ");
        System.out.println("Enter Selection");
        input = Integer.parseInt(bufferedReader.readLine());
        if (input == 1) {
          Employee employee = new Employee();
          System.out.println("Enter your id: ");
          int id = Integer.parseInt(bufferedReader.readLine());
          System.out.println("Enter your password: ");
          String password = bufferedReader.readLine();
          int selection;
          Inventory inventory = DatabaseSelectHelper.getInventory();
          EmployeeInterface employeeInterface = new EmployeeInterface(employee, inventory);
          System.out.println("1 - Authenticate new employee");
          System.out.println("2 - Make new user");
          System.out.println("3 - Make new account");
          System.out.println("4 - Make new Employee");
          System.out.println("5 - Restock Inventory");
          System.out.println("6 - Exit");
          System.out.println("Enter Selection");
          selection = Integer.parseInt(bufferedReader.readLine());
          if (selection == 1) {
            // idk
          } else if (selection == 2) {
            System.out.println("Enter name: ");
            String name = bufferedReader.readLine();
            System.out.println("Enter age: ");
            int age = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter address: ");
            String address = bufferedReader.readLine();
            System.out.println("Set password: ");
            String passwordTwo = bufferedReader.readLine();
            DatabaseInsertHelper.insertNewUser(name, age, address, passwordTwo);
          } else if (selection == 3) {
            System.out.println("Enter name: ");
            String name = bufferedReader.readLine();
            System.out.println("Enter age: ");
            int age = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter address: ");
            String address = bufferedReader.readLine();
            System.out.println("Set password: ");
            String passwordTwo = bufferedReader.readLine();
            int userId = employeeInterface.createCustomer(name, age, address, passwordTwo);
            Customer customer = new Customer(userId, name, age, address); // make new customer
          } else if (selection == 4) {
            System.out.println("Enter name: ");
            String name = bufferedReader.readLine();
            System.out.println("Enter age: ");
            int age = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter address: ");
            String address = bufferedReader.readLine();
            System.out.println("Set password: ");
            String passwordThree = bufferedReader.readLine();
            int userid = employeeInterface.createEmployee(name, age, address, passwordThree);
            Employee newEmployee = new Employee(userid, name, age, address);
          } else if (selection == 5) {
            System.out.println("Enter the item: ");
            String type = bufferedReader.readLine();
            List<Item> items = DatabaseSelectHelper.getAllItems();
            for (ItemTypes itemType : ItemTypes.values()) {
              for (Item item : items) {
                if (itemType.name().equals(type)) {
                  System.out.println("How much of it is there? ");
                  int quantity = Integer.parseInt(bufferedReader.readLine());
                  employeeInterface.restockInventory(item, quantity);
                }
              }
            }
          }
        } else if (input == 2) {
          System.out.println("Enter your id: ");
          int id = Integer.parseInt(bufferedReader.readLine());
          System.out.print("Enter your password: ");
          String password = bufferedReader.readLine();
          Customer cust = new Customer();
          boolean valid = cust.authenticate(password);
          List<User> users = DatabaseSelectHelper.getUsersDetails();
          for (User user : users) {
            if (user.getId() != id) {
              System.out.println("Enter a proper id next time.");
            }
          }
          if (!valid) {
            System.out.println("Incorrect password");
          } else {
            int selection;
            ShoppingCart cart = new ShoppingCart(cust);
            System.out.println("1 - List customer items in cart");
            System.out.println("2 - Add a quantity of an item to the cart");
            System.out.println("3 - Check total price of items in the cart");
            System.out.println("4 - Remove a quantity of an item from the cart");
            System.out.println("5 - Check out");
            System.out.println("6 - Exit");
            System.out.println("Enter Selection");
            selection = Integer.parseInt(bufferedReader.readLine());
            if (selection == 1) {
              System.out.println(cart.getItems());
            } else if (selection == 2) {
              System.out.println("Enter name of the item: ");
              String name = bufferedReader.readLine();
              System.out.println("Enter quantity: ");
              int quantity = Integer.parseInt(bufferedReader.readLine());
              List<Item> items = DatabaseSelectHelper.getAllItems();
              for (Item item : items) {
                if (item.getName() == name) {
                  cart.addItem(item, quantity);
                }
              }
            } else if (selection == 3) {
              System.out.println(cart.getTotal());
            } else if (selection == 4) {
              System.out.println("Enter name of the item: ");
              String name = bufferedReader.readLine();
              System.out.println("Enter quantity: ");
              int quantity = Integer.parseInt(bufferedReader.readLine());
              List<Item> items = DatabaseSelectHelper.getAllItems();
              for (Item item : items) {
                if (item.getName() == name) {
                  cart.removeItem(item, quantity);
                }
              }
            } else if (selection == 5) {
              cart.checkOut(cart);
              cart.clearCart();
            }

          }
        } else if (input == 0) {
          System.out.println("Thanks for shopping with us!!!");
        }

        // If anything else - including nothing
        /*
         * TODO Create a context menu, where the user is prompted with: 1 - Employee Login 2 -
         * Customer Login 0 - Exit Enter Selection:
         */
        // If the user entered 1
        /*
         * TODO Create a context menu for the Employee interface Prompt the employee for their id
         * and password Attempt to authenticate them. If the Id is not that of an employee or
         * password is incorrect, end the session If the Id is an employee, and the password is
         * correct, create an EmployeeInterface object then give them the following options: 1.
         * authenticate new employee 2. Make new User 3. Make new account 4. Make new Employee 5.
         * Restock Inventory 6. Exit
         * 
         * Continue to loop through as appropriate, ending once you get an exit code (9)
         */
        // If the user entered 2
        /*
         * TODO create a context menu for the customer Shopping cart Prompt the customer for their
         * id and password Attempt to authenticate them If the authentication fails or they are not
         * a customer, repeat If they get authenticated and are a customer, give them this menu: 1.
         * List current items in cart 2. Add a quantity of an item to the cart 3. Check total price
         * of items in the cart 4. Remove a quantity of an item from the cart 5. check out 6. Exit
         * 
         * When checking out, be sure to display the customers total, and ask them if they wish to
         * continue shopping for a new order
         * 
         * For each of these, loop through and continue prompting for the information needed
         * Continue showing the context menu, until the user gives a 6 as input.
         */
        // If the user entered 0
        /*
         * TODO Exit condition
         */
        // If the user entered anything else:
        /*
         * TODO Re-prompt the user
         */


      }
    } catch (Exception e) {
      // TODO Improve this!
      System.out.println("Something went wrong :(");
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println("Looks like it was closed already :)");
      }
    }

  }
}
