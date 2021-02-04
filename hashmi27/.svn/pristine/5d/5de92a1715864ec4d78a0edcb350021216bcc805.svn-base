/**
 * 
 */
package com.b07.users;

import java.sql.SQLException;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.AddressTooLongException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.RoleIdInvalidException;

/**
 * @author Muneeb
 *
 */
public class Admin extends Employee {

  /**
   * make new admin
   */
  public Admin() {
    super();
    this.setRole(Role.ADMIN);
  }

  public Admin(int id, String name, int age, String address)
      throws AddressTooLongException, DatabaseInsertException, SQLException {
    super(id, name, age, address);
    this.setRole(Role.ADMIN); // set the role to admin
  }

  public Admin(int id, String name, int age, String address, boolean authenticated)
      throws AddressTooLongException, DatabaseInsertException, SQLException,
      UserIdInvalidException {
    // call employee constructor, since its same
    super(id, name, age, address, authenticated);
    this.setRole(Role.ADMIN);
  }

  /**
   * promotes an employee
   * 
   * @param employee
   * @return if the operation is successful
   * @throws SQLException
   * @throws UserIdInvalidException
   * @throws RoleIdInvalidException
   * @throws AddressTooLongException
   * @throws DatabaseInsertException
   */
  public boolean promoteEmployee(Employee employee) throws SQLException, UserIdInvalidException,
      RoleIdInvalidException, AddressTooLongException, DatabaseInsertException {
    // change the employee's role in the database to admin
    // should terminate the employee object
    employee =
        new Admin(employee.getId(), employee.getName(), employee.getAge(), employee.getAddress()); // new
                                                                                                   // empl
    boolean result = DatabaseUpdateHelper.updateUserRole(employee.getRoleId(), employee.getId()); // get
                                                                                                  // the
                                                                                                  // result
    return result;
  }
}
