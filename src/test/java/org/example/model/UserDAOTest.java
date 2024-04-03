package org.example.model;

import org.example.model.AccountHolder;
import org.example.model.BankEmployee;
import org.example.model.DAOs.UserDAO;
import org.example.model.User;
import org.example.utils.ORMConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class UserDAOTest {
    public static UserDAO userDAO;

    @BeforeEach
    void setUp() {
        ORMConfig.init();
        userDAO= new UserDAO(ORMConfig.getEntityManager());
    }

    @Test
    void givenNewUser_whenCreatingUser_thenUserCreateSuccessfully() {
        BankEmployee bankEmployee = new BankEmployee("r.f","1234","reza","farzin",1271111111L,1);
        Assertions.assertDoesNotThrow(() -> {
            userDAO.createUserAccount(bankEmployee);
        });
    }

    @Test
    void givenUserId_whenFindingUserById_thenUserFound() {
        User user = userDAO.findUserById(2);
        Assertions.assertEquals(user.getId() , 2);
    }

    @Test
    void givenUserId_whenDeletingUserById_thenUserDeleteSuccessfully() {
        Assertions.assertDoesNotThrow(() -> {
            userDAO.deleteUserById(1);
        });
    }

    @Test
    void whenGettingAllUsers_thenCorrectSizeReturned() {
        List<User> allUsers = userDAO.getAllUsers();
        Assertions.assertEquals(3,allUsers.size());
    }

    @Test
    void givenUserUpdates_whenUpdatingUser_thenUserUpdateSuccessfully() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("username", "reza.f");
        updates.put("firstName", "aliReza");
        Assertions.assertDoesNotThrow(() -> {
            userDAO.updateUser(updates, 4);
        });
    }

    @Test
    void whenGettingAllAccountHolders_thenCorrectSizeReturned() {
        List<AccountHolder> allAccountHolders = userDAO.getAllAccountHolders();
        Assertions.assertEquals(2,allAccountHolders.size());
    }

    @Test
    void whenGettingAllBankEmployees_thenCorrectSizeReturned() {
        List<BankEmployee> allBankEmployee = userDAO.getAllBankEmployee();
        Assertions.assertEquals(1,allBankEmployee.size());
    }

    @Test
    void givenUsernameAndPassword_whenGettingUser_thenUserFound() {
        List<User> user = userDAO.getUserByUsernameAndPassword("reza.f", "1234");
        Assertions.assertEquals(1,user.size());
    }
}