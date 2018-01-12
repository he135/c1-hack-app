package com.example.yl.c1_hack;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by there_gp on 1/11/2018.
 */

public class Account {

    public enum Type {
        CHECKING, SAVING;
    }

    NessieClient client = NessieClient.getInstance("bac66cb5b82693a9fbe211c2a1b30af0");

    Type type;
    String nickname;
    int rewards;
    int balance;
    String accountNumber;

    Account(Type type, String nickname, int rewards, int balance, String accountNumber) {
        this.type = type;
        this.nickname = nickname;
        this.rewards = rewards;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    protected JSONObject createCustomer(String firstName, String lastName, String streetNum, String streetName,
                                        String city, String state, String zip) { //???????????

        HttpResponse<String> response = null;
        try {
            response = Unirest.post("http://api.reimaginebanking.com/customers?key=bac66cb5b82693a9fbe211c2a1b30af0")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "e3e74937-10b6-c1bb-e50b-e1bced88ae3b")
                    .body("{\r\n  \"first_name\": \"" + firstName + "\",\r\n  \"last_name\": \"" + lastName + "\",\r\n  \"address\": " +
                            "{\r\n  \"street_number\": \"" + streetNum + "\",\r\n    \"street_name\": \"" + streetName + "\",\r\n   " +
                            " \"city\": \"" + city + "\",\r\n  \"state\": \""+ state + "\",\r\n    \"zip\": \"" + zip + "\"\r\n  }\r\n}")
                    .asString();

            return new JSONObject(response.getBody());
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } return null;

    }
              
    protected JSONObject createAccount(String... msg) { //type, nickname, rewards, balance, acctNo

        HttpResponse<String> response = null;
        try {
            response = Unirest.post("http://api.reimaginebanking.com/customers/5a581a8c6514d52c7774a415/accounts?key=" +
                    "bac66cb5b82693a9fbe211c2a1b30af0")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "0ea1e0b8-90d3-1a72-d992-ebb2b3ef3e5f")
                    .body("{\r\n  \"type\": \"" + msg[0] + "\",\r\n  \"nickname\": \"" + msg[1] + "\",\r\n  \"rewards\": " + msg[2]
                            + ",\r\n  \"balance\": " + msg[3] + ",\r\n  \"account_number\": \"" + msg[4] + "\"\r\n}")
                    .asString();

            return new JSONObject(response.getBody());
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    protected void makeTransfer(Account from, double amount, Account to) {

//        HttpResponse<String> response = null;
//        try {
//            response = Unirest.post("http://api.reimaginebanking.com/accounts/5a581a8c6514d52c7774a415/transfers?key=" +
//                    "bac66cb5b82693a9fbe211c2a1b30af0")
//                    .header("content-type", "application/json")
//                    .header("cache-control", "no-cache")
//                    .header("postman-token", "352feab8-670e-aef2-5367-0d1cdbd6bc7b")
//                    .body("{\r\n  \"medium\": \"balance\",\r\n  \"payee_id\": \"string\",\r\n  \"transaction_date\": " +
//                            "\"2018-01-12\",\r\n  \"status\": \"pending\",\r\n  \"description\": \"string\"\r\n}")
//                    .asString();
//        } catch (UnirestException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return response;

//          from.balance -= amount;
//          to.balance += amount;

    }

//    client.CUSTOMER.getCustomers(new NessieResultsListener() {
//    	@Override
//    	public void onSuccess(Object result) {
//    		List<Customer> customers = (List<Customer>) result;
//    		// do something with the list of customers here
//        }
//        @Override
//        public void onFailure(NessieError error) {
//            // handle error
//        }
//    });

}