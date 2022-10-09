import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private final int id;
    private double balance;
    private String name;
    private User accountHolder;

    static ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(double balance, String name, User accountHolder) {
        this.id = User.genUniqueId();
        this.balance = balance;
        this.name = name;
        this.accountHolder = accountHolder;
    }


    static void getUserAccount(List<Account> listAccounts) {
        for (Account account : listAccounts) {
            account.info();
        }
    }

    static void myAccountInfo() {
        for (Account account : MainClass.loggedUser.getAccountList()) {
            System.out.println("-*-*-* ------------------------- Логин -> " + account.accountHolder.getLogin() + " ------------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- Пароль -> " + account.accountHolder.getPassword() + " ------------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- Имя -> " + account.accountHolder.getFirstName() + " ------------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- Фамилия -> " + account.accountHolder.getLastName() + " ------------------------- *-*-*-");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    void info() {
        System.out.println("-*-*-* ------------------------- Тип счета -> " + this.name + " ------------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- ID счета -> " + this.id + " ------------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- Владелец счета -> " + accountHolder.getFirstName() + " " + accountHolder.getLastName() + " ------------------------- *-*-*-" + "\n");

    }

    static void accountInfo() {
        System.out.println("==================================== Информация об учетной записи пользователя ====================================");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Account a : MainClass.loggedUser.getAccountList()) {
            System.out.println("-*-*-* ------------------------- Тип счета -> " + a.getName() + " ------------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- ID счета -> " + a.getId() + " ------------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- Баланс счета -> " + a.getBalance() + " ------------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- Владелец счета -> " + a.accountHolder.getFirstName() + " " + a.accountHolder.getLastName() + "------------------------- *-*-*-");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    static void searchAccounts(String name) {
        boolean check = false;
        for (Account account : MainClass.bank.getAccounts()) {
            if (account.getAccountHolder().getFirstName().toLowerCase().matches("(.*)" + name.toLowerCase() + "(.*)") || account.getAccountHolder().getLastName().toLowerCase().matches("(.*)" + name.toLowerCase() + "(.*)")) {
                account.info();
                check = true;
            }
        }
        if (!check) System.err.println("-*-*-* ------------------------- Не найден ------------------------- *-*-*-");
    }


    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }
}
