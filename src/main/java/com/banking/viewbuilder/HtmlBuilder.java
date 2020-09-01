package com.banking.viewbuilder;

import com.banking.model.Account;
import com.banking.model.TransactionDetails;
import com.banking.model.User;

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
                "      <h1>ROCP BANK</h1>\n" +
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
                "      <h1>ROCP BANK</h1>\n" +
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
                "    <h1>ROCP BANK</h1><h2>"+name+"</h2>\n" +
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
        String s = "    <form action=\"/rocp-bank/api/accounts/details\" method=\"Get\" >\n" +
                "        <button class=\"account\" type=\"submit\" name=\"account_id\" value=\""+account.getAccountId()+"\">\n" +
                "            <p>"+account.getType().getType()+"</p>\n" +
                "            <p>Account number : "+account.getAccountId()+"</p>\n" +
                "            <p>Balance : $ "+account.getBalance()+"</p>\n" +
                "        </button>\n" +
                "    </form>";
        return s;
    }
    public static String createAccountButton(){
        String s ="    <form action=\"/rocp-bank/api/createAccount\" method=\"post\" >\n" +
                "        <button class=\"account\" type=\"submit\">\n" +
                "            <p>Create Account</p>\n" +
                "        </button>\n" +
                "    </form>";
        return s;
    }
    public static String createAccountForm(){
        String s = "   <h1 class=\"title\">Create Account</h1>\n" +
                "    <div class=\"createaccount\">\n" +
                "        \n" +
                "           <form action=\"/rocp-bank/api/create-account\" method=\"post\">\n" +
                "\n" +
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
                users += u.getUsername();
            }else{
                users += ","+u.getUsername();
            }
        }
        String s = "    <div class=\"currentaccount\">\n" +
                "        <p>"+account.getType().getType()+"</p>\n" +
                "        <p>Balance : $ "+account.getBalance()+"</p>\n" +
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
        for (TransactionDetails transaction: transactionList) {
            transactions += "            <tr>\n" +
                    "              <td>"+transaction.getTransactionDate()+"</td>\n" +
                    "              <td>"+transaction.getTransactionType()+"</td>\n" +
                    "              <td>"+transaction.getTransactionAmount()+"</td>\n" +
                    "            </tr>";
        }

        String s = "    <div class=\"transactions\">\n" +
                "        <h1>Transactions :\n" +
                "        </h1>\n" +
                "        <table class=\"accountDetails\" id=\"table\">\n" +
                "            <tr>\n" +
                "              <th>Date</th>\n" +
                "              <th>Type</th>\n" +
                "              <th>Amount</th>\n" +
                "            </tr>\n" +
                transactions +
                "        </table>\n" +
                "    </div>";
        return s;
    }
}
