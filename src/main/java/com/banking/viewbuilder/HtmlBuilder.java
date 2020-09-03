package com.banking.viewbuilder;

import com.banking.model.*;
import com.banking.service.BankingService;

import java.text.NumberFormat;
import java.util.List;

public class HtmlBuilder {

    public static String registerUpperPortion(){
        String s = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <link href=\"http://localhost:6969/rocp-bank/css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"./css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <meta HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache, no-store, must-revalidate\">\n" +
                "    <meta HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">\n" +
                "    <meta HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\n" +
                "    <title>ROCP | Register</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"mainhead\">\n" +
                "    <h1><a href=\"/rocp-bank/api/login\">ROCP BANK</a></h1>" +
                "    </div>\n" +
                "    <h1 class=\"title\">Register</h1>\n" +
                "  <div class=\"logindiv\">";
        return s;
    }

    public static String registerLowerPortion(){
        String s = "<form action=\"/rocp-bank/api/register-user\" method=\"post\">\n" +
                "            <h2 class=\"labels\">Username:</h2>\n" +
                "            <input name  = \"username\" type = \"text\" class=\"inputfield\" placeholder = \"  Please enter your username...\" required>\n" +
                "            <h2 class=\"labels\">Password:</h2>\n" +
                "            <input name  = \"password\" type = \"password\" class=\"inputfield\" placeholder = \"  Please enter your password...\" required>\n" +
                "            <h2 class=\"labels\">First Name:</h2>\n" +
                "            <input name  = \"first_name\" type = \"text\" class=\"inputfield\" placeholder = \"  Please enter your firstname...\" required>\n" +
                "            <h2 class=\"labels\">Last Name:</h2>\n" +
                "            <input name  = \"last_name\" type = \"text\" class=\"inputfield\" placeholder = \"  Please enter your lastname...\" required>\n" +
                "            <h2 class=\"labels\">Email :</h2>\n" +
                "            <input name  = \"email\" type = \"email\" class=\"inputfield\" placeholder = \"  Please enter your email...\" pattern=\"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$\" required>\n" +
                "            <h2 class=\"labels\">User type :</h2>\n" +
                "            <p>\n" +
                "                <label>\n" +
                "                  <input type=\"radio\" name=\"user_type\" value=\"3\" id=\"user_type_0\" checked>\n" +
                "                  Standard</label>\n" +
                "                <br>\n" +
                "                <label>\n" +
                "                  <input type=\"radio\" name=\"user_type\" value=\"4\" id=\"user_type_1\" >\n" +
                "                  Premium</label>\n" +
                "                <br>\n" +
                "            </p>\n" +
                "        <button type=\"submit\">Register</button>\n" +
                "        </form>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
        return s;
    }

    public static String loginUpper(){
        String s = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <link href=\"http://localhost:6969/rocp-bank/css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"./css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <meta HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache, no-store, must-revalidate\">\n" +
                "    <meta HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">\n" +
                "    <meta HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\n" +
                "    <title>ROCP | Login</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"mainhead\">\n" +
                "    <h1><a href=\"/rocp-bank/api/login\">ROCP BANK</a></h1>"+
                "    </div>\n" +
                "    <h1 class=\"title\">Login</h1>\n" +
                "      <div class=\"logindiv\">";
        return s;
    }

    public static String loginLower(){
        String s ="        <form action=\"/rocp-bank/api/login\" method=\"post\">\n" +
                "        <h2 class=\"labels\">Username:</h2> <input name  = \"username\" type = \"text\" class=\"inputfield\" placeholder = \"  Please enter your username...\" required>\n" +
                "        <h2 class=\"labels\">Password:</h2> <input name  = \"password\" type = \"password\" class=\"inputfield\" placeholder = \"  Please enter your password...\" required>\n" +
                "        <br/>\n" +
                "        <br/>\n" +
                "        <button class=\"signin\" type=\"submit\">Sign in</button>\n" +
                "        </form>\n" +
                "        <form action=\"/rocp-bank/api/register\" method=\"get\">\n" +
                "        <button class=\"register\" type=\"submit\">Register</button>\n" +
                "        </form>\n" +
                "      </div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        return s;
    }

    public static String accountSummaryUpper(String name, String label){
        String s = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"cache-control\" content=\"no-cache, must-revalidate, post-check=0, pre-check=0\" />\n" +
                "    <meta http-equiv=\"cache-control\" content=\"max-age=0\" />\n" +
                "    <meta http-equiv=\"expires\" content=\"0\" />\n" +
                "    <meta http-equiv=\"expires\" content=\"Tue, 01 Jan 1980 1:00:00 GMT\" />\n" +
                "    <meta http-equiv=\"pragma\" content=\"no-cache\" />\n" +
                "    <link href=\"http://localhost:6969/rocp-bank/css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"./css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\n" +
                "    <title>ROCP | Account Summary</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"mainhead\">\n" +
                "    <h1><a href=\"/rocp-bank/api/login\">ROCP BANK</a></h1>" +
                "<form action=\"/rocp-bank/api/update/user\" method=\"post\"><button class=\"userbutton\">"+name+"</button></form>"+
                "        <form action=\"/rocp-bank/api/logout\" method=\"get\">\n" +
                "            <button class=\"logoutBtn\" type=\"submit\">Log out</button>\n" +
                "        </form>\n" +
                "</div>"+
                "\n";
        if(!label.equals("")){
            s += "    <div class=\"greetings\">\n" +
                    "      <h1>"+label+"</h1>\n" +
                    "    </div>";
        }
        return s;
    }
    public static String accountSummaryLower(){
        String s = "</body>\n" +
                "</html>";
        return s;
    }

    public static String accountFormBuilder(Account account){

        String disabled = "";
        String account2 = "";
        if(account.getStatus().getStatusId() != 2){
            disabled = "disabled";
            account2 = "2";
        }

        String s = "    <form action=\"/rocp-bank/api/accounts/details\" method=\"Get\" >\n" +
                "        <button class=\"account"+account2+"\" type=\"submit\" name=\"account_id\" value=\""+account.getAccountId()+"\""+disabled+">\n" +
                "            <p>"+ account.getType().getType()+"</p>\n" +
                "            <p>Account number : "+ account.getAccountId()+"</p>\n" +
                "            <p>Account is currently "+ account.getStatus().getStatus()+"</p>\n" +
                "        </button>\n" +
                "    </form>";
        return s;
    }
    public static String createAccountButton(){
        String s ="    <form action=\"/rocp-bank/api/createAccount\" method=\"post\" >\n" +
                "        <button class=\"account\" type=\"submit\">\n" +
                "            <p>Submit Account</p>\n" +
                "        </button>\n" +
                "    </form>";
        return s;
    }
    public static String createAccountForm(String response){
        String s = "   <h1 class=\"title\">Create Account</h1>\n" +
                "    <div class=\"createaccount\">\n" +
                "        \n" +
                "           <form action=\"/rocp-bank/api/create-account\" method=\"post\">\n" +
                response +
                "            <h2 class=\"labels\">Account type :</h2>\n" +
                "            <p>\n" +
                "                <label>\n" +
                "                  <input type=\"radio\" name=\"account_type\" value=\"1\" id=\"account_type_0\" checked>\n" +
                "                  Checking</label>\n" +
                "                <br>\n" +
                "                <label>\n" +
                "                  <input type=\"radio\" name=\"account_type\" value=\"2\" id=\"account_type_1\" >\n" +
                "                  Savings</label>\n" +
                "                <br>\n" +
                "            </p>\n" +
                "        <button type=\"submit\">Create</button>\n" +
                "        </form>\n" +
                "    </div>";
        return s;
    }

    public static String accountTransactions(Account account, List<TransactionDetails> transactionList){
        String users = "";
        for (User u : account.getUserList()) {
            if(users.equals("")) {
                users += u.getFirstName() + " " + u.getLastName();
            }else{
                users += ","+u.getUsername();
            }
        }
        String s = "    <div class=\"currentaccount\">\n" +
                "        <p>"+account.getType().getType()+"</p>\n" +
                "        <p>Balance : "+formatDecimal(account.getBalance())+"</p>\n" +
                "        <p>Owner : "+users+" </p>\n" +
                "    </div>\n" +
                "    <form class=\"buttonDiv\">\n" +
                "            <button formaction=\"/rocp-bank/api/accounts/transfer\" method=\"post\" type=\"submit\">Transfer Money</button>\n" +
                "            <button formaction=\"/rocp-bank/api/accounts/deposit\" method=\"post\" type=\"submit\">Deposit Money</button>\n" +
                "            <button formaction=\"/rocp-bank/api/accounts/withdraw\" method=\"post\" type=\"submit\">Withdraw Money</button>\n" +
                "    </form>";
        s += transaction(transactionList);
        return s;
    }

    static String transaction(List<TransactionDetails> transactionList){
        String transactions = "";
        double lastBalance = 0;
        for (TransactionDetails transaction: transactionList) {
            String symbol = "";
            if(transaction.getTransactionType().getTransactionName().equals("withdraw")){
                symbol = "-";
            }else if(transaction.getTransactionType().getTransactionName().equals("transfer")){
                if(transaction.getTransactionBalance() > lastBalance){
                    symbol = "";
                }else {
                    symbol= "-";
                }
            }
            transactions += "            <tr>\n" +
                    "              <td>"+transaction.getTransactionDate()+"</td>\n" +
                    "              <td>"+transaction.getTransactionType().getTransactionName()+"</td>\n" +
                    "              <td> "+symbol+formatDecimal(transaction.getTransactionAmount())+"</td>\n" +
                    "              <td> "+formatDecimal(transaction.getTransactionBalance())+"</td>\n" +
                    "            </tr>";
            lastBalance = transaction.getTransactionBalance();
        }

        String s = "    <div class=\"transactions\">\n" +
                "        <h1>Transactions :\n" +
                "        </h1>\n" +
                "        <table class=\"accountDetails\" id=\"table\">\n" +
                "            <tr>\n" +
                "              <th>Date</th>\n" +
                "              <th>Type</th>\n" +
                "              <th>Amount</th>\n" +
                "              <th>Balance</th>\n" +
                "            </tr>\n" +
                transactions +
                "        </table>\n" +
                "    </div>";
        return s;
    }

    public static String withdrawPage(String name, String label, Account account,String results){
        String s = "    <h1 class=\"title\">Withdraw</h1>\n" +
                "    <div class=\"createaccount\">\n" +
                "        \n" +
                "           <form action=\"/rocp-bank/api/accounts/withdraw/submit\" method=\"post\">\n" +
                results +
                "            <h2 class=\"labels\">Account ID : "+account.getAccountId()+"</h2>\n" +
                "            <h2 class=\"labels\"> Balance : "+formatDecimal(account.getBalance())+" </h2>\n" +
                "           <input name=\"money\" class=\"inputfield\" type=\"number\"  min=\"0\" max=\""+account.getBalance()+"\" required placeholder = \"  Please enter an amount to withdraw...\">" +
                "        <br><br>\n" +
                "        <button type=\"submit\">Submit</button>\n" +
                "        </form>\n" +
                "    </div>";
        return  accountSummaryUpper(name,label) + s + accountSummaryLower();
    }
    public static String depositPage(String name, String label, Account account,String results){
        String s = "    <h1 class=\"title\">Deposit</h1>\n" +
                "    <div class=\"createaccount\">\n" +
                "        \n" +
                "           <form action=\"/rocp-bank/api/accounts/deposit/submit\" method=\"post\">\n" +
                results +
                "            <h2 class=\"labels\">Account ID : "+account.getAccountId()+"</h2>\n" +
                "            <h2 class=\"labels\"> Balance : "+formatDecimal(account.getBalance())+" </h2>\n" +
                "           <input name=\"money\" class=\"inputfield\" type=\"number\"required placeholder = \"  Please enter an amount to deposit...\">" +
                "        <br><br>\n" +
                "        <button type=\"submit\">Submit</button>\n" +
                "        </form>\n" +
                "    </div>";
        return  accountSummaryUpper(name,label) + s + accountSummaryLower();
    }

    public static String transferPage(String name, String label, Account account,String results){
        String s="  <h1 class=\"title\">Transfer</h1>\n" +
                "    <div class=\"createaccount\">\n" +
                results+
                "           <form action=\"/rocp-bank/api/accounts/transfer/submit\" method=\"post\">\n" +
                "            <h2 class=\"labels\">Source Account ID : "+account.getAccountId()+"</h2>\n" +
                "          <input name=\"target_account\" class=\"inputfield\" type=\"number\" min=\"0\" placeholder = \"  Please enter your Target Account ID...\" required>\n" +
                "           <h2 class=\"labels\"> Balance : "+formatDecimal(account.getBalance())+" </h2>\n" +
                "           <input name=\"money\" class=\"inputfield\" type=\"number\"  min=\"0\" max=\""+account.getBalance()+"\" required placeholder = \"  Please enter an amount to transfer...\">\n" +
                "        <br><br>\n" +
                "        <button type=\"submit\">Submit</button>\n" +
                "        </form>\n" +
                "    </div>";
        return accountSummaryUpper(name,label) + s + accountSummaryLower();
    }

    public static String employeePage(List<User> userList , List<Account> accountList, boolean admin, User user){
        String s;
        String greetings = "    <div class=\"greetings\">\n" +
                "      <h1>Employee Page</h1>\n" +
                "    </div>";
        String update = "";
        String userTable = "";
        String accountTable = "";
        if(admin){
            greetings = "    <div class=\"greetings\">\n" +
                    "      <h1>Admin Page</h1>\n" +
                    "    </div>";
            update = "<form class=\"transactions\" action=\"update\" method=\"get\">\n" +
                    "\n" +
                    "        <select class=\"dropdown\" name=\"update\" id=\"update\">\n" +
                    "            <option value=\"user\">Update User</option>\n" +
                    "            <option value=\"account\">Update Account</option>\n" +
                    "        </select>\n" +
                    "        <input name=\"update_id\" class=\"inputfield\" type=\"number\" placeholder=\"  Input ID\" min=\"1\">\n" +
                    "    <button type=\"submit\">Update</button>\n" +
                    "    </form>";
        }
        String finder = "<br><br><form class=\"transactions\" action=\"/rocp-bank/api/accounts\">\n" +
                "\n" +
                "        <select class=\"dropdown\" name=\"find\" id=\"find\">\n" +
                "          <optgroup label=\"Users\">\n" +
                "            <option value=\"findUsers\">All Users</option>\n" +
                "            <option value=\"findUserId\">Search Users By Id</option>\n" +
                "          </optgroup>\n" +
                "          <optgroup label=\"Accounts\">\n" +
                "            <option value=\"findAccount\">All Accounts\n" +
                "             <option value=\"findAccountId\">Search Account by Account Id</option>\n" +
                "              Accounts</option>\n" +
                "            <option value=\"findAccountsByStatusId\">Search Accounts by Status Id</option>\n" +
                "            <option value=\"findAccountsUserId\">Search Accounts by User Id</option>\n" +
                "          </optgroup>\n" +
                "        </select>\n" +
                "        <input name=\"find_id\" class=\"inputfield\" type=\"number\" placeholder=\"  Input ID\" min=\"1\">\n" +
                "    <button type=\"submit\">Find</button>\n" +
                "    </form>\n" +
                "<br>";

        if(accountList.size() > 0) {
            String accountsRow = "";
            for (Account a : accountList) {
                String owners = "";
                for (User u : a.getUserList()) {
                    if (owners.equals("")) {
                        owners += u.getUserId();
                    } else {
                        owners += "," + u.getUserId();
                    }
                }
                accountsRow += "      <tr>\n" +
                        "          <td>" + a.getAccountId() + "</td>\n" +
                        "          <td>" + formatDecimal(a.getBalance()) + "</td>\n" +
                        "          <td>" + a.getStatus().getStatusId() + " : " + a.getStatus().getStatus() + "</td>\n" +
                        "          <td>" + a.getType().getTypeId() + " : " + a.getType().getType() + "</td>\n" +
                        "          <td>" + owners + "</td>\n" +
                        "      </tr>";
            }

            accountTable = "<br><div class=\"transactions\">\n" +
                    "  <h1>Accounts :\n" +
                    "  </h1>\n" +
                    "  <table class=\"accountDetails\" id=\"table3\">\n" +
                    "      <tr>\n" +
                    "          <th>ID</th>\n" +
                    "          <th>Balance</th>\n" +
                    "          <th>Account Status</th>\n" +
                    "          <th>Account Type</th>\n" +
                    "          <th>Account Owner/s</th>\n" +
                    "      </tr>\n" +
                    accountsRow +
                    "  </table>\n" +
                    "  </div>";
        }
        if(userList.size() > 0) {
            String userRow = "";
            for (User u : userList) {
                userRow += "            <tr>\n" +
                        "                <td>" + u.getUserId() + "</td>\n" +
                        "                <td>" + u.getUsername() + "</td>\n" +
                        "                <td>" + u.getPassword() + "</td>\n" +
                        "                <td>" + u.getFirstName() + "</td>\n" +
                        "                <td>" + u.getLastName() + "</td>\n" +
                        "                <td>" + u.getEmail() + "</td>\n" +
                        "                <td>" + u.getRole().getRoleId() + " : " + u.getRole().getRole() + "</td>\n" +
                        "            </tr>";
            }
            userTable = "<br><div class=\"transactions\">\n" +
                    "        <h1>Users :\n" +
                    "        </h1>\n" +
                    "        <table class=\"accountDetails\" id=\"table2\">\n" +
                    "            <tr>\n" +
                    "                <th>ID</th>\n" +
                    "                <th>Username</th>\n" +
                    "                <th>Password</th>\n" +
                    "                <th>First Name</th>\n" +
                    "                <th>Last Name</th>\n" +
                    "                <th>Email</th>\n" +
                    "                <th>Role</th>\n" +
                    "            </tr>\n" +
                    userRow +
                    "        </table>\n" +
                    "    </div>";
        }
        s = accountSummaryUpper(user.getFirstName() + " " + user.getLastName(),"") +
                greetings + update + finder + userTable + accountTable + accountSummaryLower();
        return s;
    }

    public static String updatePage(User user,String result, boolean admin){
        String s;
        String rolesRadio = "";
        String submit = "submit";
        if(admin){
            List<Role> roleList = BankingService.findRoles();
            submit = "admin";
            String radioCenter = "";
            int i = 0;
            for (Role r:roleList) {
                String checked = "";
                if(r.getRoleId() == user.getRole().getRoleId()){
                    checked = "checked";
                }
                radioCenter += "<label>\n" +
                        "<input type=\"radio\" name=\"user_type\" value=\""+r.getRoleId()+"\" id=\"user_type_"+i+"\" "+checked+">\n" +
                        r.getRole()+"</label>\n" +
                        "<br>";
                i++;
            }

            rolesRadio = "            <h2 class=\"labels\">User type :</h2>\n" +
                    "            <p>\n" +
                    radioCenter +
                    "            </p>";
        }
        String updateString = "   <h1 class=\"title\">Update Account</h1>\n" +
                "  <div class=\"logindiv\">\n" +
                "\n" +
                "      <form action=\"/rocp-bank/api/update/user-"+submit+"\" method=\"post\">\n" +
                result +
                "            <h2 class=\"labels\">Username:</h2>\n" +
                "            <input name  = \"username\" type = \"text\" class=\"inputfield\" placeholder = \"  Please enter your username...\" required value=\""+user.getUsername()+"\">\n" +
                "            <h2 class=\"labels\">New Password:</h2>\n" +
                "            <input name  = \"password\" type = \"password\" class=\"inputfield\" placeholder = \"  Please enter new password...\">\n" +
                "            <h2 class=\"labels\">First Name:</h2>\n" +
                "            <input name  = \"first_name\" type = \"text\" class=\"inputfield\" placeholder = \"  Please enter your firstname...\" required value=\""+user.getFirstName()+"\">\n" +
                "            <h2 class=\"labels\">Last Name:</h2>\n" +
                "            <input name  = \"last_name\" type = \"text\" class=\"inputfield\" placeholder = \"  Please enter your lastname...\" required value=\""+user.getLastName()+"\">\n" +
                "            <h2 class=\"labels\">Email :</h2>\n" +
                "            <input name  = \"email\" type = \"email\" class=\"inputfield\" placeholder = \"  Please enter your email...\" pattern=\"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$\" required value=\""+user.getEmail()+"\">\n" +
                rolesRadio +
                "<br>"+
                "<br>"+
                "        <button type=\"submit\">Update</button>\n" +
                "    </form>";
        s = accountSummaryUpper(user.getFirstName() + " " + user.getLastName(),"") + updateString + accountSummaryLower();
        return s;
    }

    public static String accountUpdatePage(User user,Account account,String response){
        String s;
        String statusRadio = "";
        String typeRadio ="";
        List<AccountStatus> statusList = BankingService.findAccountStatus();
        List<AccountType> typeList = BankingService.findAccountTypes();
        int i = 0;
        for(AccountStatus status : statusList){
            String checked = "";
            if(status.getStatusId() == account.getStatus().getStatusId()){
                checked = "checked";
            }
            statusRadio += "<label>\n" +
                    "<input type=\"radio\" name=\"account_status\" value=\""
                    +status.getStatusId()+"\" id=\"account_status_"+i+"\""+checked+">\n" +
                    status.getStatus()+"</label>\n" +
                    "<br>";
            i++;
        }
        int j = 0;
        for(AccountType type : typeList){
            String checked = "";
            if(type.getTypeId() == account.getType().getTypeId()){
                checked = "checked";
            }
            typeRadio += "<label>\n" +
                    "<input type=\"radio\" name=\"account_type\" value=\""
                    +type.getTypeId()+"\" id=\"account_type_"+j+"\""+checked+">\n" +
                     type.getType()+"</label>\n" +
                    "<br>";
            j++;
        }
        String accountUpdate = "<h1 class=\"title\">Update Account</h1>\n" +
                "  <div class=\"logindiv\">\n" +
                "      <form action=\"/rocp-bank/api/update/update-account\" method=\"post\">\n" +
                response+
                "              <h2 class=\"labels\">Account Id:</h2>\n" +
                "            <input name  = \"account_id\" type = \"number\" class=\"inputfield\" required value=\""+account.getAccountId()+"\">\n" +
                "            <h2 class=\"labels\">Balance:</h2>\n" +
                "            <input name  = \"balance\" type=\"number\" class=\"inputfield\" required value=\""+account.getBalance()+"\">\n" +
                "            <h2 class=\"labels\">Account Status :</h2>\n" +
                "            <p>"+
                statusRadio+
                "</p>"+
                "<h2 class=\"labels\">Account Type :</h2>\n" +
                "            <p>"+
                typeRadio+
                "</p>"+
                "        <button type=\"submit\">Update</button>\n" +
                "    </form>\n" +
                "  </div>";
        s = accountSummaryUpper(user.getFirstName() + " " + user.getLastName(),"") + accountUpdate  + accountSummaryLower();
        return s;
    }

    private static String formatDecimal(double number) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return fmt.format(number);
    }
}
