package com.b07.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.ConnectionFailedException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidAgeException;
import com.b07.exceptions.InvalidItemIdException;
import com.b07.exceptions.InvalidNameException;
import com.b07.exceptions.InvalidPriceException;
import com.b07.exceptions.NameTooLongException;
import com.b07.exceptions.NegativeInventoryQuantityException;
import com.b07.exceptions.TooLongAddressException;
import com.b07.exceptions.UserIdAlreadyExistException;
import com.b07.inventory.Inventory;
import com.b07.inventory.InventoryImpl;
import com.b07.inventory.Item;
import com.b07.inventory.ItemImpl;
import com.b07.users.Account;
import com.b07.users.Admin;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.EmployeeInterface;
import com.b07.users.User;

public class SalesApplication {

  public static void main(String[] argv) throws ConnectionFailedException, DatabaseInsertException,
      SQLException, IOException, NegativeInventoryQuantityException, NameTooLongException,
      InvalidPriceException, InvalidNameException, InvalidAgeException, TooLongAddressException,
      UserIdAlreadyExistException, InterruptedException {

    // Sets up all the variables for name, address, ages, first run employee, customer and admin
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    Connection connection = DatabaseDriverExtender.connectOrCreateDataBase();
    String names = null;
    String ages;
    String addresses = null;
    boolean firstRun = true;
    int userId = 1; // at the top
    Employee employee;
    Customer customer;
    String password = null;
    Admin admin;
    Inventory inventory = null;
    int age = 0;
    String input;
    String userInput;
    // If connection is null gives you "NOOO"
    if (connection == null) {
      System.out.print("NOOO");
    }
    try {
      // Sets the names, ages, address, firstrun, userId starting at 1, employee, admin, customer,
      // default password
      // admin at stationary values.

      // ROLE ID IS DEFAULTED AT; ADMIN: 1; EMPLOYEE: 2; COSTUMER: 3

      // Program will begin and will only stop if user inputs 0
      boolean program = true;
      while (program == true) {
        System.out.println("Hello! Welcome to the MENU for the sale application");
        System.out.println(
            "----------------------------------------------------------------------------");
        System.out.println(
            "WARNING: ONLY PRESS -1 IF Inventorymgmt.db isnt created otherwise you will crash the program");
        System.out.println("If its your FIRST RUN type -1");
        System.out.println("(1) Login as an ADMIN");
        System.out.println("(2) Login as an EMPLOYEE");
        System.out.println("(3) Login as a CUSTOMER");
        System.out.println("(0) EXIT");
        input = bufferedReader.readLine();
        // If its EQUAL to -1
        if (input.equals("-1")) {
          while (firstRun == true) {
            System.out.println("We have initiliazed the five items in the APPENDIX");
            System.out.println("We defaulted them to five quantity of each");
            System.out.println("Their prices are also arbitrary values");
            System.out
                .println("We will need you to CREATE an ADMIN account and a EMPLOYEE account");
            System.out
                .println("IMPORTANT: PLEASE REMEMBER THE USER-ID ASSOCIATED WITH THE ACCOUNT");
            System.out.println(
                "----------------------------------------------------------------------------");
            // Initializes all the items and inventory defaulted at zero
            // Sets the database connection
            DatabaseDriverExtender.initialize(connection);
            // Sets ItemOne to Five according to the ItemType's appendix also hard-codes the prices
            Item itemOne = new ItemImpl(1, "FISHING_ROD", new BigDecimal(1.5));
            Item itemTwo = new ItemImpl(2, "HOCKEY_STICK", new BigDecimal(3.5));
            Item itemThree = new ItemImpl(3, "SKATES", new BigDecimal(4.5));
            Item itemFour = new ItemImpl(4, "RUNNING_SHOES", new BigDecimal(2.5));
            Item itemFive = new ItemImpl(5, "PROTEIN_BAR", new BigDecimal(1.5));
            // Insert it to the database
            HashMap<Item, Integer> itemMap = new HashMap<>();
            itemMap.put(itemOne, 5);
            itemMap.put(itemTwo, 5);
            itemMap.put(itemThree, 5);
            itemMap.put(itemFour, 5);
            itemMap.put(itemFive, 5);
            // insert to the inventory
            inventory = new InventoryImpl(itemMap);
            // insert the three roles according to the Roles APPENDIX
            DatabaseInsertHelper.insertRole("ADMIN");
            DatabaseInsertHelper.insertRole("EMPLOYEE");
            DatabaseInsertHelper.insertRole("CUSTOMER");
            // To create an admin
            int myUserIdTwo = 0;
            String myUserId = "";
            String pw = "";
            boolean correctInput = false;
            // Ask user for name, age, address, password. Then autoincrements one for userId
            // Catches all exceptions
            do {
              try {
                System.out.println("To create an admin; please type your name:");
                names = bufferedReader.readLine();
                if (names.length() >= 64) {
                  throw new NameTooLongException();
                }
                System.out.println("To create an admin; please type your age:");
                ages = bufferedReader.readLine();
                age = Integer.parseInt(ages);
                System.out.println("To create an admin; please type your address:");
                addresses = bufferedReader.readLine();
                if (addresses.length() > 100) {
                  throw new TooLongAddressException();
                }
                System.out.println("To create an admin; please type your password:");
                pw = bufferedReader.readLine();
                admin = new Admin(myUserIdTwo, names, age, addresses);
                correctInput = true;
              } catch (NumberFormatException e) {
                // Exception if user enters not a number for age and userId
                System.out.println("Your age and UserId must be a number!!");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } catch (NameTooLongException e) {
                // Exception if the name is too long over 64
                System.out.println("Your name was too long please rewrite (over 64)");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } catch (TooLongAddressException e) {
                // Exception if the address is over 100
                System.out.println("Your address was too long (over 100)");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } catch (InvalidAgeException e) {
                // exception is age cant be less than 0 or over 120
                System.out.println("Age cant be less than 0 or over 120");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } catch (SQLException e) {
                // if the input isnt in the database
                System.out.println("Not valid input in the database");
              }
            } while (correctInput == false);
            // Creates an admin based on names, ages, addresses, and password
            DatabaseInsertHelper.insertNewUser(names, age, addresses, pw);
            DatabaseInsertHelper.insertUserRole(1, 1);
            System.out.println(names + ", your userId is " + 1 + ". Please remember this!!");
            System.out.println("");
            TimeUnit.SECONDS.sleep(1);
            userId += 1;
            // to create an Employee
            correctInput = false;
            // Prompt the user to enter name, age, address, message
            do {
              try {
                List<Integer> allRoleIds = new ArrayList<>();
                allRoleIds = DatabaseSelectHelper.getRoleIds();
                // Just checks if the userId already exist.
                int count = 0;
                while (count < allRoleIds.size()) {
                  int countTwo = 0;
                  while (countTwo < DatabaseSelectHelper.getUsersByRole(allRoleIds.get(count))
                      .size()) {
                    if (myUserIdTwo == DatabaseSelectHelper.getUsersByRole(allRoleIds.get(count))
                        .get(countTwo)) {
                      throw new UserIdAlreadyExistException();
                    }
                    countTwo += 1;
                  }
                  count += 1;
                }
                System.out.println("To create an EMPLOYEE; please type your name:");
                names = bufferedReader.readLine();
                if (names.length() >= 64) {
                  throw new NameTooLongException();
                }
                System.out.println("To create an EMPLOYEE; please type your age:");
                ages = bufferedReader.readLine();
                age = Integer.parseInt(ages);
                System.out.println("To create an EMPLOYEE; please type your address:");
                addresses = bufferedReader.readLine();
                if (addresses.length() > 100) {
                  throw new TooLongAddressException();
                }
                System.out.println("To create an employee; please type your password:");
                password = bufferedReader.readLine();
                employee = new Employee(myUserIdTwo, names, age, addresses);
                correctInput = true;

              } catch (NumberFormatException e) {
                // exceptions if age and userId isnt a number
                System.out.println("Your age and UserId must be a number!!");
                System.out.println("");
              } catch (UserIdAlreadyExistException e) {
                // exception if UserID isn't in the database
                System.out.println("User ID already exists in the database, please pick anew");
                System.out.println("");
              } catch (NameTooLongException e) {
                // Exception if name was too long
                System.out.println("Your name was too long, please rewrite (over 64)");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } catch (TooLongAddressException e) {
                // exception if address was too long
                System.out.println("Your address was too long (over 100 characters)");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } catch (InvalidAgeException e) {
                // exception if age was too long
                System.out.println("Age cant be less than 0 or over 120");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } catch (SQLException e) {
                // exception if invalid input for database
                System.out.println("Not valid input for the database");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              }

            } while (correctInput == false);
            DatabaseInsertHelper.insertNewUser(names, age, addresses, password);
            DatabaseInsertHelper.insertUserRole(2, 2);
            // Creates an employee based on names, ages, addresses, and password
            System.out.println(names + ", your userId is " + 2 + ". Please remember this!!");
            System.out.println("");
            // sets it to false
            firstRun = false;
          }
        } else if (input.equals("1")) {
          boolean correctInput = false;
          int id = 0;
          String myId;
          int databaseUserId = 0;
          boolean found = false;
          List<User> userIds = new ArrayList<>();
          String name = "";
          // Ask for userID and password
          do {
            try {
              System.out.println("Hello ADMIN; please type your UserID:");
              myId = bufferedReader.readLine();
              id = Integer.parseInt(myId);
              System.out.println("Hello ADMIN; please type your password:");
              String myPassword = bufferedReader.readLine();
              userIds = DatabaseSelectHelper.getUsersDetails();
              int idRole = DatabaseSelectHelper.getUserRoleId(id);
              name = DatabaseSelectHelper.getRoleName(idRole);
              correctInput = true;

              if (userIds.get(id - 1).authenticate(myPassword) == true) {
                found = true;
              } else {
                found = false;
              }

            } catch (NumberFormatException e) {
              // exception if userID isnt a number
              System.out.println("Your user id must be a number!!");
              System.out.println("");
              TimeUnit.SECONDS.sleep(1);
            } catch (SQLException e) {
              // exception if userID doesnt exist
              System.out.println("User-id doesn't exist.");
              System.out.println("");
              TimeUnit.SECONDS.sleep(1);
            }
          } while (correctInput == false);
          // condition if name doesnt equal admin
          if (found == true && name.equals("ADMIN")) {
            correctInput = false;
            boolean areYouDone = false;
            do {
              System.out.println("Log-in successful.");
              System.out.println(
                  "---------------------------------------------------------------------------");
              System.out.println(
                  "Welcome to the ADMIN interface. Please pick from one of the options given:");
              System.out.println("(1) Promote an EMPLOYEE");
              System.out.println("(2) View books");
              System.out.println("(3) View ACTIVE accounts");
              System.out.println("(4) View IN-ACTIVE accounts");
              System.out.println("(5) EXIT");
              String adminInput = bufferedReader.readLine();
              areYouDone = false;
              Admin currentAdmin = (Admin) DatabaseSelectHelper.getUserDetails(id);
              if (adminInput.equals("1")) {
                // asks for which employee to promote
                do {
                  try {
                    System.out.println(
                        "Please choose USERID of the EMPLOYEE you wish to promote. If you want to exit type 0");
                    String myIdTwo = bufferedReader.readLine();
                    if (myIdTwo.equals("0") == false) {
                      int myIdd = Integer.parseInt(myIdTwo);
                      int roleIdOfEmployee = DatabaseSelectHelper.getUserRoleId(myIdd);
                      if (DatabaseSelectHelper.getRoleName(roleIdOfEmployee).equals("EMPLOYEE")) {
                        List<Integer> allTheRoleIds = new ArrayList<>();
                        allTheRoleIds = DatabaseSelectHelper.getUsersByRole(roleIdOfEmployee);
                        // conditions if roleid isnt 1
                        if (allTheRoleIds.size() != 1) {
                          DatabaseUpdateHelper
                              .updateUserRole(DatabaseSelectHelper.getUserRoleId(id), myIdd);
                          System.out.println("Successfully promoted.");
                          System.out.println("");
                          TimeUnit.SECONDS.sleep(1);
                          correctInput = true;

                        } else {
                          // if you cant promote the employee
                          System.out.println("Sorry, you can't promote your only employee");
                          System.out.println("");
                          TimeUnit.SECONDS.sleep(1);
                          correctInput = true;
                        }

                      } else {
                        correctInput = true;
                        // if its not an employee
                        System.out.println("Not an EMPLOYEE");
                        System.out.println("");
                        TimeUnit.SECONDS.sleep(1);
                      }

                    } else {
                      // ends loop
                      correctInput = true;
                    }


                  } catch (SQLException e) {
                    // exception if not valid userID
                    System.out.println("Not a valid userid");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } catch (NumberFormatException e) {
                    // exception if its not a number
                    System.out.println("Must be a number");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  }
                } while (correctInput == false);

              } else if (adminInput.equals("2")) {
                List<Sale> allSales = new ArrayList<>();
                allSales = DatabaseSelectHelper.getSales().getSaleLog();
                int count = 0;
                HashMap<String, Integer> allItems = new HashMap<>();
                BigDecimal totals = new BigDecimal("0");
                while (count < allSales.size()) {
                  System.out.println("Customer: " + allSales.get(count).getUser().getName());
                  System.out.println("Purchase Number " + allSales.get(count).getId());
                  System.out
                      .println("Total Purchase Price: $" + allSales.get(count).getTotalPrice());
                  System.out.println("Itemized breakdown below");
                  HashMap<Item, Integer> allItemizedSales =
                      DatabaseSelectHelper.getItemizedSaleById(allSales.get(count).getId());
                  for (Item key : allItemizedSales.keySet()) {
                    System.out.println(key.getName() + ":" + allItemizedSales.get(key));
                    if (allItems.containsKey(key.getName()) == true) {
                      allItems.compute(key.getName(), (k, v) -> v + allItemizedSales.get(key));
                    } else {
                      allItems.put(key.getName(), allItemizedSales.get(key));
                    }
                  }
                  totals = totals.add(allSales.get(count).getTotalPrice());
                  System.out.println("-----------------------------------------------");
                  count += 1;

                }
                for (String theNames : allItems.keySet()) {
                  System.out.println("Number " + theNames + " Sold: " + allItems.get(theNames));
                }
                System.out.println("TOTAL SALES: " + totals);
                TimeUnit.SECONDS.sleep(1);
              } else if (adminInput.equals("5")) {
                System.out.println("exiting admin interface...");
                System.out.println("");
                areYouDone = true;
                TimeUnit.SECONDS.sleep(1);
              } else if (adminInput.equals("3")) {
                List<Integer> allActives = currentAdmin.getActiveAccounts();
                System.out.println("ID's of current active accounts");
                System.out.println(allActives);
                System.out.println("");
              } else if (adminInput.equals("4")) {
                List<Integer> allInactives = currentAdmin.getInActiveAccounts();
                System.out.println("ID's of current in-active accounts");
                System.out.println(allInactives);
                System.out.println("");
              }

            } while (areYouDone == false);


          } else {
            // when not admin or invalid userID
            System.out.println("Not an admin or invalid userId");
            System.out.println("");
            TimeUnit.SECONDS.sleep(1);
          }

        } else if (input.equals("0")) {
          // conditions if you want to exit menu
          program = false;
          System.out.println("exiting menu....");
          TimeUnit.SECONDS.sleep(1);

          // condition if you want to enter employee interface
        } else if (input.equals("2")) {

          // sets all variables for input
          boolean correctInput = false;
          int id = 0;
          String myId;
          boolean found = false;
          int databaseUserId = 0;
          List<User> userIds = new ArrayList<>();
          String name = "";
          // gets userID and password
          do {
            try {
              System.out.println("Hello Employee; please type your UserID:");
              myId = bufferedReader.readLine();
              id = Integer.parseInt(myId);

              System.out.println("Hello Employee; please type your password:");
              String myPassword = bufferedReader.readLine();
              userIds = DatabaseSelectHelper.getUsersDetails();
              int idRole = DatabaseSelectHelper.getUserRoleId(id);
              name = DatabaseSelectHelper.getRoleName(idRole);
              System.out.println(name); // name.equals("CUSTOMER")
              if (userIds.get(id - 1).authenticate(myPassword) == true) {
                found = true;
              } else {
                found = false;
              }
              correctInput = true;
            } catch (NumberFormatException e) {
              // exception if userID isnt a number
              System.out.println("Your user id must be a number!!");
              System.out.println("");
            } catch (SQLException e) {
              // exceptions if USER-ID is not in the database
              System.out.println("USER-ID Not located in the database");
            }
          } while (correctInput == false);
          // conditions if name equals employee and found in the database
          if (found == true && name.equals("EMPLOYEE")) {
            System.out.println("Log-in succesful.");
            // sets employee
            Employee currentEmployee =
                new Employee(userIds.get(id - 1).getId(), userIds.get(id - 1).getName(),
                    userIds.get(id - 1).getAge(), userIds.get(id - 1).getAddress());
            EmployeeInterface employeeInteface =
                new EmployeeInterface(DatabaseSelectHelper.getInventory());
            /*
             * 1. authenticate new employee 2. Make new User 3. Make new account 4. Make new
             * Employee 5. Restock Inventory 6. Exit
             */
            // starts shop
            boolean stop = false;
            while (stop == false) {
              // all options
              System.out.println("-----------------------------------------------");
              System.out.println(
                  "Welcome to the EMPLOYEE interface. Please pick one of the options given:");
              System.out.println("(1) Authenticate new employee");
              System.out.println("(2) Make new User");
              System.out.println("(3) Make new account");
              System.out.println("(4) Make new Employee");
              System.out.println("(5) Restock Inventory");
              System.out.println(
                  "(6) SPECIAL FEATURE: Buy the shoppingCart given customer account ID and two verifications");
              System.out.println("(7) Exit");
              TimeUnit.SECONDS.sleep(1);
              userInput = bufferedReader.readLine();
              // conditions if its 1 sets current employee
              if (userInput.equals("1")) {
                employeeInteface.setCurrentEmployee(currentEmployee);
                System.out.println(
                    "Successfully set " + currentEmployee.getName() + " as the current employee");
                TimeUnit.SECONDS.sleep(1);
              } else if (userInput.equals("3")) {
                // conditions for number 3 sets newId

                int newId;
                // ask for userID so you can get accountID
                try {
                  System.out.println(
                      "Please pick the customer's userId that you wish to associate the account with: ");
                  userInput = bufferedReader.readLine();
                  newId = Integer.parseInt(userInput);
                  if (DatabaseSelectHelper.getRoleName(DatabaseSelectHelper.getUserRoleId(newId))
                      .equals("CUSTOMER")) {
                    int accountId = DatabaseInsertHelper.insertAccount(newId, true);
                    System.out.println(DatabaseSelectHelper.getUserDetails(newId).getName()
                        + " account id is " + accountId);
                  } else {
                    System.out.println("Not a CUSTOMER user id");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  }
                  DatabaseInsertHelper databaseInsert = new DatabaseInsertHelper();
                } catch (NumberFormatException e) {
                  // exceptions for userID must be an integer
                  System.out.println("userId must be an integer");
                  TimeUnit.SECONDS.sleep(1);
                } catch (SQLException e) {
                  // Exception for SQLException
                  System.out.println("Not a valid userId from the USER's table");
                  TimeUnit.SECONDS.sleep(1);
                }
                // if condition is 2
              } else if (userInput.equals("2")) {
                // sets all the variables
                int myUserIdTwo = 0;
                String myUserId = "";
                addresses = "";
                String pw = "";
                correctInput = false;
                // asks for custoer name, age, address, password and inputs to database
                do {
                  try {

                    System.out
                        .println("To create a CUSTOMER; please type the name of the customer:");
                    names = bufferedReader.readLine();
                    System.out
                        .println("To create a CUSTOMER; please type the age of the customer:");
                    ages = bufferedReader.readLine();
                    age = Integer.parseInt(ages);
                    System.out
                        .println("To create a CUSTOMER; please type the address of the customer:");
                    addresses = bufferedReader.readLine();
                    System.out
                        .println("To create a CUSTOMER; please set the password for the customer:");
                    pw = bufferedReader.readLine();
                    Customer customerAccount = new Customer(myUserIdTwo, names, age, addresses);
                    myUserIdTwo = DatabaseInsertHelper.insertNewUser(names, age, addresses, pw);
                    correctInput = true;

                  } catch (NumberFormatException e) {
                    // exceptions for age and usreID not being numbers
                    System.out.println("Your age and UserId must be a number!!");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } catch (NameTooLongException e) {
                    // exceptions for name too
                    System.out.println("Your name was too long, please rewrite (over 64)");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                    // exception for too long address
                  } catch (TooLongAddressException e) {
                    System.out.println("Your address was too long (over 100 characters)");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } catch (SQLException e) {
                    // exception for ID not existant in database
                    System.out.println("ID's not in the database");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);

                  } catch (InvalidAgeException e) {
                    // exception for age being above 120
                    System.out.println("Age can not be below zero or over 120");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  }
                  // ends loop
                } while (correctInput == false);


                // Creates an admin based on names, ages, addresses, and password

                List<Integer> roleIds = new ArrayList<>();
                roleIds = DatabaseSelectHelper.getRoleIds();
                int customerId = 3;
                int count = 0;
                // itterates through role id
                while (count < roleIds.size()) {
                  // sees if its customer or not
                  String occuName = DatabaseSelectHelper.getRoleName(roleIds.get(count));
                  if (occuName.equals("CUSTOMER") == true) {
                    customerId = roleIds.get(count);
                  }
                  count += 1;
                }
                // gets the userRole
                DatabaseInsertHelper.insertUserRole(myUserIdTwo, customerId);
                System.out.println(
                    names + ", your userId is " + myUserIdTwo + ". Please remember this!!");
                System.out.println("");
                // if input is 4 sets variables
              } else if (userInput.equals("4")) {
                int myUserIdTwo = 0;
                String myUserId = "";
                addresses = "";
                String pw = "";
                correctInput = false;
                // gets name age address password for the database
                do {
                  try {
                    System.out.println("To create an EMPLOYEE; please type your name:");
                    names = bufferedReader.readLine();
                    System.out.println("To create an EMPLOYEE; please type your age:");
                    ages = bufferedReader.readLine();
                    age = Integer.parseInt(ages);
                    System.out.println("To create an EMPLOYEE; please type your address:");
                    addresses = bufferedReader.readLine();
                    System.out.println("To create an EMPLOYEE; please type your password:");
                    pw = bufferedReader.readLine();
                    Employee employeeAccount = new Employee(myUserIdTwo, names, age, addresses);
                    myUserIdTwo = DatabaseInsertHelper.insertNewUser(names, age, addresses, pw);
                    correctInput = true;

                  } catch (NumberFormatException e) {
                    // condition if age and userID isn't a number
                    System.out.println("Your age and UserId must be a number!!");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } catch (NameTooLongException e) {
                    // condition if name was too long
                    System.out.println("Your name was too long please rewrite (over 64)");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } catch (TooLongAddressException e) {
                    // condition if address was too long
                    System.out.println("Your address was too long (over 100)");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } catch (InvalidAgeException e) {
                    // condition if age is below 0 or over 120
                    System.out.println("Age is below 0 or over 120");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } catch (SQLException e) {
                    // condition if it cant be in database
                    System.out.println("Cant be in the database");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  }
                } while (correctInput == false);
                // Creates an admin based on names, ages, addresses, and password


                List<Integer> roleIds = new ArrayList<>();
                roleIds = DatabaseSelectHelper.getRoleIds();
                int employeeId = 3;
                int count = 0;
                // itterates through roleIds
                while (count < roleIds.size()) {
                  // conditions if its employee
                  String occuName = DatabaseSelectHelper.getRoleName(roleIds.get(count));
                  if (occuName.equals("EMPLOYEE") == true) {
                    employeeId = roleIds.get(count);
                  }
                  count += 1;
                }
                // inserts userRole
                DatabaseInsertHelper.insertUserRole(myUserIdTwo, employeeId);
                System.out
                    .println(names + " Your userId is " + myUserIdTwo + " Please remember this!!");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);

              }
              // condition if its 5
              else if (userInput.equals("5")) {
                System.out.println(
                    "The current Inventory is as follows with Item to the left, Quantity to the right");
                // itterates through the inventory
                inventory = DatabaseSelectHelper.getInventory();
                int itemId = 1;
                inventory = DatabaseSelectHelper.getInventory();
                itemId = 1;
                for (Item key : inventory.getItemMap().keySet()) {
                  System.out.println(
                      "ITEM-ID " + itemId + ": " + DatabaseSelectHelper.getItem(itemId).getName()
                          + "  | Quantity: " + DatabaseSelectHelper.getInventoryQuantity(itemId)
                          + " | Price: $" + DatabaseSelectHelper.getItem(itemId).getPrice());
                  itemId += 1;
                }
                System.out.println("");
                System.out.println("ITEM IDS");
                System.out.println("1 -> FISHING ROD");
                System.out.println("2 -> HOCKEY STICK");
                System.out.println("3 -> SKATES");
                System.out.println("4 -> RUNNING SHOES");
                System.out.println("5 -> PROTEIN BAR");
                System.out.println("");
                correctInput = false;
                // ask for itemID to buy
                try {
                  System.out.println("Choose the ITEMID that you want to adjust:");
                  List<String> allItemIds = new ArrayList<>();
                  allItemIds.add("1");
                  allItemIds.add("2");
                  allItemIds.add("3");
                  allItemIds.add("4");
                  allItemIds.add("5");
                  input = bufferedReader.readLine();
                  // if its found in the itemIds
                  if (allItemIds.contains(input) == true) {
                    int idTwo = Integer.parseInt(input);
                    System.out.println("Choose the QUANTITY that you want to add to this item:");
                    String quantity = bufferedReader.readLine();
                    int quant = Integer.parseInt(quantity);
                    inventory.updateMap(DatabaseSelectHelper.getItem(idTwo), quant);
                    System.out.println("Successful.");
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(1);
                  } else {
                    throw new InvalidItemIdException();
                  }


                } catch (SQLException e) {
                  // if its not a validitemID
                  System.out.println("Not a valid itemId");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);

                } catch (NumberFormatException e) {
                  // exception if the numberformat aint same
                  System.out.println("A number for itemID please");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (InvalidItemIdException e) {
                  // exception if above range
                  System.out.println("Only choose the itemId given to you please");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (NegativeInventoryQuantityException e) {
                  System.out.println("Quantity in the database cannot be negative");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                }
                // sets inventory and gets you current stuff in the database
                inventory = DatabaseSelectHelper.getInventory();
                itemId = 1;
                for (Item key : inventory.getItemMap().keySet()) {
                  System.out.println(
                      "ITEM-ID " + itemId + ": " + DatabaseSelectHelper.getItem(itemId).getName()
                          + "  | Quantity: " + DatabaseSelectHelper.getInventoryQuantity(itemId)
                          + " | Price: $" + DatabaseSelectHelper.getItem(itemId).getPrice());
                  itemId += 1;
                }
                // if input is 6
              } else if (userInput.equals("7")) {
                stop = true;
                // exits employee interface
                System.out.println("Exiting employee interface...");
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);
              } else if (userInput.equals("6")) {
                int accountId;
                int userbd;
                boolean foundOne = false;
                List<Account> accountUserIds = new ArrayList<>();
                try {
                  System.out.println("Please type a valid account ID");
                  String accId = bufferedReader.readLine();
                  accountId = Integer.parseInt(accId);
                  System.out.println("Please type the userid associated with this accountID");
                  String usId = bufferedReader.readLine();
                  userbd = Integer.parseInt(usId);
                  System.out
                      .println("Please type the name of the user associated with this accountid");
                  String theName = bufferedReader.readLine();
                  System.out.println(
                      "Please type the address of the user associated with this accountid");
                  String theAddress = bufferedReader.readLine();
                  Customer currentCustomer = (Customer) DatabaseSelectHelper.getUserDetails(userbd);
                  if (!(theName.equals(currentCustomer.getName())
                      && theAddress.equals(currentCustomer.getAddress()))) {
                    throw new InvalidNameException();
                  }
                  Inventory myInventory = DatabaseSelectHelper.getInventory();
                  accountUserIds = DatabaseSelectHelper.getUserAccounts(userbd);
                  System.out.println(accountUserIds.get(0).getId());
                  int count = 0;
                  foundOne = false;

                  while (count < accountUserIds.size() && foundOne == false) {
                    if (accountId == accountUserIds.get(count).getId()) {
                      foundOne = true;
                      ShoppingCart shoppingCart = DatabaseSelectHelper.getAccountDetails(accountId);
                      shoppingCart.setCustomer(currentCustomer);
                      boolean allStillThere = true;
                      for (Item key : shoppingCart.getCart().keySet()) {
                        Item work = null;
                        for (Item keyTwo : myInventory.getItemMap().keySet()) {
                          if (keyTwo.getName().equals(key.getName())) {
                            work = keyTwo;
                            break;
                          }
                        }
                        if (myInventory.getItemMap().get(work) < shoppingCart.getCart().get(key)) {
                          allStillThere = false;
                        }
                      }
                      if (allStillThere == true) {
                        for (Item key : shoppingCart.getCart().keySet()) {
                          myInventory.updateMap(key, -shoppingCart.getCart().get(key));
                        }
                        System.out.println("You have succesfully checked out; your total price was "
                            + shoppingCart.getTotal());
                        DatabaseInsertHelper databaseInsert = new DatabaseInsertHelper();
                        int saleId = shoppingCart.checkOut();
                        for (Item key : shoppingCart.getCart().keySet()) {
                          databaseInsert.insertItemizedSale(saleId, key.getId(),
                              shoppingCart.getCart().get(key));
                        }
                        DatabaseUpdateHelper.updateAccountStatus(accountId, false);
                        shoppingCart.clearCart();
                        System.out.println("");

                        TimeUnit.SECONDS.sleep(1);


                      } else {
                        System.out.println(
                            "The items arent available anymore so the purchase couldn't be made");
                        System.out.println("Going back to menu");
                      }

                    } else {
                      count += 1;
                    }

                  }


                } catch (NumberFormatException e) {
                  System.out.println("Account id or user id must be an integer");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (InvalidNameException e) {
                  System.out.println(
                      "The name or address don't match the user associated with the account");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (SQLException e) {
                  System.out.println("User id or account id does not exist in the database");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (NegativeInventoryQuantityException e) {
                  System.out.println(
                      "The shoppingCart is no longer available. The shop has ran out of those items");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (NullPointerException e) {
                  System.out.println("The user-id is invalid and not in the database");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (ClassCastException e) {
                  System.out.println("Not a customer");
                  System.out.println("");
                }

              }

            }



          } else {
            // if wrong password or userID
            System.out.println("Wrong password or UserId");
            System.out.println("");
            TimeUnit.SECONDS.sleep(1);
          }

          // if input is 3
        } else if (input.equals("3")) {
          /*
           * TODO create a context menu for the customer Shopping cart Prompt the customer for their
           * id and password Attempt to authenticate them If the authentication fails or they are
           * not a customer, repeat If they get authenticated and are a customer, give them this
           * menu: 1. List current items in cart 2. Add a quantity of an item to the cart 3. Check
           * total price of items in the cart 4. Remove a quantity of an item from the cart 5. check
           * out 6. Exit
           */
          // sets all the variables
          boolean correctInput = false;
          int id = 0;
          String myId;
          boolean found = false;
          int databaseUserId = 0;
          String name = "";
          int accountId = 0;
          List<User> userIds = new ArrayList<>();
          // ask for userID and password then puts it in the database
          do {
            try {
              System.out.println("Hello CUSTOMER; please type your UserID:");
              myId = bufferedReader.readLine();
              id = Integer.parseInt(myId);
              System.out.println("Hello CUSTOMER; please type your password:");
              String myPassword = bufferedReader.readLine();
              userIds = DatabaseSelectHelper.getUsersDetails();
              int idRole = DatabaseSelectHelper.getUserRoleId(id);
              name = DatabaseSelectHelper.getRoleName(idRole);
              System.out.println(name); // name.equals("CUSTOMER")

              if (userIds.get(id - 1).authenticate(myPassword) == true) {
                found = true;
              } else {
                found = false;
              }
              correctInput = true;

            } catch (NumberFormatException e) {
              // exception if userID is a number
              System.out.println("Your user id must be a number!!");
              System.out.println("");
              TimeUnit.SECONDS.sleep(1);

            } catch (SQLException e) {
              // exception if UserID is not in the database
              System.out.println("UserID is not in the database");
              System.out.println("");
              TimeUnit.SECONDS.sleep(1);
            }
          } while (correctInput == false);

          // condition if found is true and name equals customer
          if (found == true && name.equals("CUSTOMER")) {
            // conditions if you succesfully logged in
            System.out.println("You have succesfully logged in " + userIds.get(id - 1).getName());
            System.out.println("-----------------------------------------------------");
            Customer currentCustomer =
                new Customer(userIds.get(id - 1).getId(), userIds.get(id - 1).getName(),
                    userIds.get(id - 1).getAge(), userIds.get(id - 1).getAddress());
            // starts shopping programs
            ShoppingCart shoppingCart = new ShoppingCart(currentCustomer);
            Inventory myInventory = DatabaseSelectHelper.getInventory();
            boolean shop = true;
            boolean foundOne = false;
            accountId = 0;
            // condtition for the account part
            try {
              System.out.println("Welcome to the CUSTOMER interface. Please choose an option:");
              System.out.println(
                  "(1) Continue with an account if and only if the items are still in the store");
              System.out.println(
                  "If you don't have one, please click exit on the guest interface and you'll get a chance");
              System.out.println("to create an account");
              System.out.println(
                  "(2) Special feature account: Continue with an account doesn't matter if its not in the store. (You asked to be held)");
              System.out.println("(anything else) to continue as guest");
              String inputs = bufferedReader.readLine();
              // if input is 1
              if (inputs.equals("1")) {
                System.out.println(
                    "--------------------------------------------------------------------------");
                System.out.println("Please type your account id to continue:");
                String saccountId = bufferedReader.readLine();
                // sets all the variables
                accountId = Integer.parseInt(saccountId);
                List<Account> accountUserIds = new ArrayList<>();
                accountUserIds = DatabaseSelectHelper.getUserAccounts(id);
                int count = 0;
                foundOne = false;
                List<Account> inactive = new ArrayList<>();
                List<Integer> allInactives = new ArrayList<>();
                int countTwo = 0;
                inactive = DatabaseSelectHelper.getUserInactiveAccounts(id);
                while (countTwo < inactive.size()) {
                  allInactives.add(inactive.get(countTwo).getId());
                  countTwo = countTwo + 1;
                }

                while (count < accountUserIds.size() && foundOne == false) {
                  if (accountId == accountUserIds.get(count).getId()) {
                    if (allInactives.contains(accountId) == true) {
                      throw new SQLException();
                    }

                    foundOne = true;
                    shoppingCart = DatabaseSelectHelper.getAccountDetails(accountId);
                    shoppingCart.setCustomer(currentCustomer);
                    boolean allStillThere = true;
                    for (Item key : shoppingCart.getCart().keySet()) {
                      Item work = null;
                      for (Item keyTwo : myInventory.getItemMap().keySet()) {
                        if (keyTwo.getName().equals(key.getName())) {
                          work = keyTwo;
                          break;
                        }
                      }
                      if (myInventory.getItemMap().get(work) < shoppingCart.getCart().get(key)) {
                        allStillThere = false;
                      }
                    }
                    if (allStillThere == true) {
                      for (Item key : shoppingCart.getCart().keySet()) {
                        myInventory.updateMap(key, -shoppingCart.getCart().get(key));
                      }
                      System.out.println("");
                      System.out.println(
                          "Succesfully logged in and stored your previous shopping cart to this account");
                      System.out.println("Continuing to the store...");
                      System.out.println("");
                    } else {
                      shoppingCart = new ShoppingCart(currentCustomer);
                      System.out.println("Your items arent available anymore");
                      System.out.println("Continuing as guest....");
                      accountId = 0;
                    }



                  } else {
                    count += 1;
                  }

                }
                // if foundOne is false
                if (foundOne == false) {
                  shop = false;
                  System.out.println(
                      "AccountID not associated with your UserID or your account is inactive");
                  System.out.println("");
                  shoppingCart = new ShoppingCart(currentCustomer);
                  accountId = 0;
                  TimeUnit.SECONDS.sleep(1);
                }

              } else if (inputs.equals("2")) {

                System.out.println(
                    "--------------------------------------------------------------------------");
                System.out.println("Please type your account id to continue:");
                String saccountId = bufferedReader.readLine();
                // sets all the variables
                accountId = Integer.parseInt(saccountId);
                List<Account> accountUserIds = new ArrayList<>();
                accountUserIds = DatabaseSelectHelper.getUserAccounts(id);
                int count = 0;
                foundOne = false;
                List<Account> inactive = new ArrayList<>();
                List<Integer> allInactives = new ArrayList<>();
                int countTwo = 0;
                inactive = DatabaseSelectHelper.getUserInactiveAccounts(id);
                while (countTwo < inactive.size()) {
                  allInactives.add(inactive.get(countTwo).getId());
                  countTwo = countTwo + 1;
                }

                while (count < accountUserIds.size() && foundOne == false) {
                  if (accountId == accountUserIds.get(count).getId()) {
                    if (allInactives.contains(accountId) == true) {
                      throw new SQLException();
                    }
                    foundOne = true;
                    DatabaseUpdateHelper.updateAccountStatus(accountId, false);
                    shoppingCart = DatabaseSelectHelper.getAccountDetails(accountId);
                    shoppingCart.setCustomer(currentCustomer);
                    boolean allStillThere = true;
                    for (Item key : shoppingCart.getCart().keySet()) {
                      Item work = null;
                      for (Item keyTwo : myInventory.getItemMap().keySet()) {
                        if (keyTwo.getName().equals(key.getName())) {
                          work = keyTwo;
                          break;
                        }
                      }
                      if (myInventory.getItemMap().get(work) < shoppingCart.getCart().get(key)) {
                        allStillThere = false;
                      }
                    }
                    if (allStillThere == true || allStillThere == false) {
                      System.out.println("");
                      System.out.println(
                          "Succesfully logged in and stored your previous held shopping cart to this account");
                      System.out.println("Continuing to the store...");
                      System.out.println("");
                    } else {
                      shoppingCart = new ShoppingCart(currentCustomer);
                      System.out.println("Continuing as guest....");
                      accountId = 0;
                    }



                  } else {
                    count += 1;
                  }

                }
                // if foundOne is false
                if (foundOne == false) {
                  shop = false;
                  System.out.println(
                      "AccountID not associated with your UserID or your account is inactive");
                  System.out.println("");
                  shoppingCart = new ShoppingCart(currentCustomer);
                  accountId = 0;
                  TimeUnit.SECONDS.sleep(1);
                }



              } else {
                // continue as guest otherwise for all exceptions below
                System.out.println("Continuing as guest....");
                shoppingCart = new ShoppingCart(currentCustomer);
                accountId = 0;
                System.out.println(
                    "--------------------------------------------------------------------------");
                System.out.println("");

              }

            } catch (NumberFormatException e) {
              System.out.println("AccountId must be an integer");
              System.out.println("Continuing as guest....");
              shoppingCart = new ShoppingCart(currentCustomer);
              System.out.println("");
              accountId = 0;
              TimeUnit.SECONDS.sleep(1);
            } catch (SQLException e) {
              System.out
                  .println("Account ID does not exist in the database or account is inactive");
              System.out.println("Continuing as guest....");
              accountId = 0;
              shoppingCart = new ShoppingCart(currentCustomer);
              System.out.println("");
              TimeUnit.SECONDS.sleep(1);
            } catch (NegativeInventoryQuantityException e) {
              System.out.println(
                  "The items in your previous shoppingCart is no longer available. Sorry.");
              System.out.println("Continuing as guess...");
              accountId = 0;
              shoppingCart = new ShoppingCart(currentCustomer);
              System.out.println("");
              TimeUnit.SECONDS.sleep(1);
            }


            // keep on looping while shop is true
            while (shop == true) {
              System.out.println("These are all the items available in this store");
              System.out.println("");
              TimeUnit.SECONDS.sleep(1);
              int itemId = 1;
              // itterate through the inventory
              for (Item key : myInventory.getItemMap().keySet()) {
                System.out.println(
                    "ITEM-ID " + itemId + ": " + DatabaseSelectHelper.getItem(itemId).getName()
                        + "  | Quantity: " + DatabaseSelectHelper.getInventoryQuantity(itemId)
                        + " | Price: $" + DatabaseSelectHelper.getItem(itemId).getPrice());
                itemId += 1;
              }
              // all available options
              System.out.println("");
              System.out.println("Please choose an option");
              System.out.println("(1) List current items in cart");
              System.out.println("(2) Add a quantity of an item to the cart");
              System.out.println("(3) Check total price of items in the cart");
              System.out.println("(4) Remove a quantity of an item from the cart");
              System.out.println("(5) Check out ");
              System.out.println("(6) EXIT");
              System.out.println("");
              input = bufferedReader.readLine();
              // if its 6
              if (input.equals("6")) {
                // ends shop either put it in account or back to the inventory
                shop = false;
                System.out.println("Exiting customer interface...");
                System.out.println("Do you want to save your past shoppingCart? Type an option.");
                System.out.println("(1) Save it to get it faster next time");
                System.out.println(
                    "(2) save it and the items are held so it wont add back to the database");
                System.out.println("(anything else) to exit normally");
                input = bufferedReader.readLine();

                try {

                  if (input.equals("1")) {
                    int newAccountId = DatabaseInsertHelper
                        .insertAccount(shoppingCart.getCustomer().getId(), true);
                    for (Item key : shoppingCart.getCart().keySet()) {
                      DatabaseInsertHelper.insertAccountLine(newAccountId, key.getId(),
                          shoppingCart.getCart().get(key));
                      myInventory.updateMap(key, shoppingCart.getCart().get(key));
                    }
                    System.out.println("Remember this accountId " + newAccountId
                        + " To get this shoppingCart again");
                  } else if (input.equals("2")) {
                    int newAccountId = DatabaseInsertHelper
                        .insertAccount(shoppingCart.getCustomer().getId(), true);
                    for (Item key : shoppingCart.getCart().keySet()) {
                      DatabaseInsertHelper.insertAccountLine(newAccountId, key.getId(),
                          shoppingCart.getCart().get(key));
                    }
                    System.out.println("Remember this accountId " + newAccountId
                        + " To get this shoppingCart again");

                  } else {
                    for (Item key : shoppingCart.getCart().keySet()) {
                      myInventory.updateMap(key, shoppingCart.getCart().get(key));
                    }
                  }

                } catch (SQLException e) {
                  System.out.println(
                      "Sorry cant use same account ID to store previous shoppingCart twice");
                  for (Item key : shoppingCart.getCart().keySet()) {
                    myInventory.updateMap(key, shoppingCart.getCart().get(key));
                  }
                }


                System.out.println("");
                // if input is 1 gets current items in cart
              } else if (input.equals("1")) {
                System.out.println("Current items in the Cart");
                String resultTwo = "";
                for (Item key : shoppingCart.getCart().keySet()) {
                  resultTwo = resultTwo + " | Item: " + key.getName() + " Quantity: | "
                      + shoppingCart.getCart().get(key);
                }
                System.out.println(resultTwo);
                // if input is 2 gets the inventory
              } else if (input.equals("2")) {
                try {
                  System.out.println("Please say the itemId you wish to buy");
                  String optionsTwo = bufferedReader.readLine();
                  int optTwo = Integer.parseInt(optionsTwo);
                  List<Integer> validItemIds = new ArrayList<>();
                  validItemIds.add(1);
                  validItemIds.add(2);
                  validItemIds.add(3);
                  validItemIds.add(4);
                  validItemIds.add(5);
                  if (validItemIds.contains(optTwo) == false) {
                    throw new NullPointerException();
                  }
                  System.out.println("Please type the quantity of the item you wish to buy:");
                  String optionsThree = bufferedReader.readLine();
                  int optThree = Integer.parseInt(optionsThree);
                  Item youWant = DatabaseSelectHelper.getItem(optTwo);
                  myInventory.updateMap(DatabaseSelectHelper.getItem(optTwo), -optThree);
                  shoppingCart.addItem(youWant, optThree);
                  // ADD UPDATE SHOPPING LINE HERE
                  System.out.println("Successfully added to your shopping Cart.");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);

                  // all exceptions if wrong itemID
                } catch (NegativeInventoryQuantityException e) {
                  System.out.println("Apologies, but we don't have that much");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (NullPointerException e) {
                  System.out.println("ItemId does not exist.");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (NumberFormatException e) {
                  System.out.println("ItemId does not exist.");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (SQLException e) {
                  System.out.println("Not a valid ID in the database");
                }
                // if input is 3 gets total price
              } else if (input.equals("3")) {
                System.out.println("Total price of cart is");
                System.out.println(shoppingCart.getTotal());
                TimeUnit.SECONDS.sleep(1);
                // if input is 4
              } else if (input.equals("4")) {
                System.out.println("ITEM IDS");
                System.out.println("1 -> FISHING ROD");
                System.out.println("2 -> HOCKEY STICK");
                System.out.println("3 -> SKATES");
                System.out.println("4 -> RUNNING SHOES");
                System.out.println("5 -> PROTEIN BAR");
                System.out.println("");
                System.out.println("Your current shopping Cart is");
                System.out.println("Current items in the Cart");
                String resultTwo = "";
                for (Item key : shoppingCart.getCart().keySet()) {
                  resultTwo = resultTwo + " | Item: " + key.getName() + " Quantity: | "
                      + shoppingCart.getCart().get(key);
                }
                System.out.println(resultTwo);
                System.out.println("");
                // the itemID you want to remove with quantity in your shoppingCart
                try {
                  System.out.println("Please say the itemId you wish to remove:");
                  String optionsTwo = bufferedReader.readLine();
                  int optTwo = Integer.parseInt(optionsTwo);
                  List<Integer> validItemIds = new ArrayList<>();
                  validItemIds.add(1);
                  validItemIds.add(2);
                  validItemIds.add(3);
                  validItemIds.add(4);
                  validItemIds.add(5);
                  if (validItemIds.contains(optTwo) == false) {
                    throw new NullPointerException();
                  }
                  System.out.println("Please say the quantity you wish to remove:");
                  String optionsThree = bufferedReader.readLine();
                  int optThree = Integer.parseInt(optionsThree);
                  shoppingCart.removeItem(DatabaseSelectHelper.getItem(optTwo), optThree);
                  myInventory.updateMap(DatabaseSelectHelper.getItem(optTwo), optThree);
                  // all conditions for itemID
                } catch (NullPointerException e) {
                  System.out
                      .println("Sorry you don't have this itemID or your quantity doesnt exist");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);

                } catch (NumberFormatException e) {
                  System.out
                      .println("Sorry you don't have this itemID or your quantity doesnt exist");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (NegativeInventoryQuantityException e) {
                  System.out
                      .println("Uhm... you are trying to remove a negative amount of inventory");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                } catch (SQLException e) {
                  System.out.println("Not a valid id in the database");
                  System.out.println("");
                  TimeUnit.SECONDS.sleep(1);
                }


                // if input is 5 checks out
              } else if (input.equals("5")) {
                System.out.println("You have succesfully checked out; your total price was "
                    + shoppingCart.getTotal());
                DatabaseInsertHelper databaseInsert = new DatabaseInsertHelper();
                int saleId = shoppingCart.checkOut();
                for (Item key : shoppingCart.getCart().keySet()) {
                  databaseInsert.insertItemizedSale(saleId, key.getId(),
                      shoppingCart.getCart().get(key));
                }
                if (accountId != 0) {
                  DatabaseUpdateHelper.updateAccountStatus(accountId, false);
                }
                shoppingCart.clearCart();
                System.out.println("");
                TimeUnit.SECONDS.sleep(1);

              }

            }
          } else {
            System.out.println("Wrong password or userID");
            System.out.println("");
            TimeUnit.SECONDS.sleep(1);
          }


        }
      }

    } catch (Exception e) {
      System.out.println("Something went wrong :(");
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println("Looks like it was closed already :)");
      }
    }
  }
}
