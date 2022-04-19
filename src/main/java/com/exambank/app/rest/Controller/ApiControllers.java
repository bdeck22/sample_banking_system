//Web Services

package com.exambank.app.rest.Controller;

import com.exambank.app.rest.Model.User;
import com.exambank.app.rest.Repository.UserRepository;
import com.exambank.app.rest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Welcome, Page
    @GetMapping(value = "/")
    public String welcomePage() {
        return "Welcome to Banking System!";
    }

    // Adding Users to the database
    @PostMapping(value = "/users/signUpPage")
    public User signUpPage(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Balance Inquiry Web Service
     * @param userName
     * @param password
     * this controller needs the userName and password to view the total account balance
     * @return
     */
    @GetMapping(value = "/users/balanceInquiryPage/{userName}/{password}")
    public User balanceInquiryPage(@PathVariable String userName, @PathVariable String password) {
        return userService.getByUserNameAndPassword(userName, password);
    }

    /**
     * Deposit Web Service
     * @param userName
     * @param password
     * this controller needs the userName and password to deposit
     * @param depositAmount - amount will deposit
     * @return
     */
    @PutMapping(value = "/users/depositPage/{userName}/{password}/{depositAmount}")
    public User depositPage(@PathVariable String userName, @PathVariable String password, @PathVariable Double depositAmount) {
        User user = userService.getByUserNameAndPassword(userName, password);

        // Add the depositAmount to total balance
        user.setAccountTotalAmount(user.getAccountTotalAmount() + depositAmount);

        return userService.save(user);

    }

    /**
     * Withdraw Web Service
     * @param userName
     * @param password
     * this controller needs the userName and password to withdraw
     * @param withdrawAmount - amount will withdraw
     * @return
     */
    @PutMapping(value = "/users/withdrawPage/{userName}/{password}/{withdrawAmount}")
    public User withdrawPage(@PathVariable String userName, @PathVariable String password, @PathVariable Double withdrawAmount) {
        User user = userService.getByUserNameAndPassword(userName, password);

        // Subtract the withdrawAmount to total balance
        user.setAccountTotalAmount(user.getAccountTotalAmount() - withdrawAmount);

        return userService.save(user);

    }

    /**
     *
     * @param userName
     * @param password
     * @param transferAmount - the amount will transfer to another user/account
     * this controller needs the userName and password to withdraw
     * @param transferToId - the user id of recipient of transfer funds
     * @param fNameTransferTo - first name of recipient
     * @param lNameTransferTo - last name of recipient
     * @return
     */
    @PutMapping(value = "/users/transferFundPage/{userName}/{password}/{transferAmount}/{transferToId}/{fNameTransferTo}/{lNameTransferTo}")
    public String transferFundPage(@PathVariable String userName,
                                   @PathVariable String password,
                                   @PathVariable Double transferAmount,
                                   @PathVariable long transferToId,
                                   @PathVariable String fNameTransferTo,
                                   @PathVariable String lNameTransferTo) {

        // Account that will transfer funds
        User userTransferFrom = userService.getByUserNameAndPassword(userName, password);
        userTransferFrom.setAccountTotalAmount(userTransferFrom.getAccountTotalAmount() - transferAmount);
        userService.save(userTransferFrom);

        // Account that receives the transfer funds
        User userTransferTo = userService.getByIdAndFirstNameAndLastName(transferToId, fNameTransferTo, lNameTransferTo);
        userTransferTo.setAccountTotalAmount(userTransferTo.getAccountTotalAmount() + transferAmount);
        userService.save(userTransferTo);

        return "You transferred " + transferAmount + " to " + userTransferTo.getFirstName() + " " + userTransferTo.getLastName();
    }

}