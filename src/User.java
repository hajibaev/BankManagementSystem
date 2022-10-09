import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private List<Account> accountList;
    private String login;
    private String password;
    static List<Integer> ids = new ArrayList<>();

    public User(String firstName, String lastName, String login, String password, List<Account> accounts) {
        this.id = genUniqueId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.accountList = accounts;
    }

    //
    static void getUserAccount() {
        for (User user : MainClass.bank.getCustomers()) {
            user.userInfo();
        }
    }
//    }

    public void userInfo() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Пользватель -> " + this.firstName + " " + this.lastName + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("-*-*-* -------------------------    Информация о пользователе     ------------------------- *-*-*-");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (Account a : MainClass.bank.getAccounts()) {
            if (a.getAccountHolder().getId() == this.id) {
                a.info();
            }
        }
    }

    public static int genUniqueId() {
        int id;
        while (true) {
            id = new Random().nextInt(100000, 999999);
            if (!isHas(id)) {
                ids.add(id);
                break;
            }
        }
        return id;
    }

    private static boolean isHas(int id) {
        for (int num : ids) {
            if (num == id) {
                return true;
            }
        }
        return false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<Integer> getIds() {
        return ids;
    }

    public static void setIds(List<Integer> ids) {
        User.ids = ids;
    }
}