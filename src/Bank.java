import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {
    private String bankName;
    private final List<User> customers;
    private final List<Account> accounts;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank(String bankName, List<User> customers, List<Account> accounts, ArrayList<Transaction> transactions) {
        this.bankName = bankName;
        this.customers = customers;
        this.accounts = accounts;
        this.transactions = transactions;

    }

    public User returnUser(String login, String password) {
        for (User user : this.customers) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

//                                      BANK INFO

    public void infoBank() {
        System.out.println("-*-*-* ------------------------- Информация про банка имени " + this.bankName + " --------------------- *-*-*-");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("-*-*-* --------------------- Все пользователи банка и их аккаунты --------------------- *-*-*-");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        User.getUserAccount();
    }

    //                                 DUPLICATE
    public boolean checkDuplicateLogin(String login) {
        for (User user : this.customers) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void getUsers() {
        for (User user : this.customers) {
            user.userInfo();
        }
    }

    //                                                Информация другин пользователей


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<User> getCustomers() {
        return customers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

//
//    public List<Process> getProcesses() {return processes;}
//
//    public void setProcesses(List<Process> processes) {this.processes = processes;}
}
