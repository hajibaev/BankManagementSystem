import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    static double kursUsd = 81;
    static Bank bank;
    static User loggedUser;

    static List<Account> myAccount = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Account> accounts = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();


    public static void main(String[] args) {

        List<Account> acc = new ArrayList<>();
        User shokh = new User("Shohjahon", "Hajibaev", "1", "1", acc);
        Account kgzNew = new Account(9999, "KGZ", shokh);
        Account usdNew = new Account(9999, "USD", shokh);
        acc.add(kgzNew);
        acc.add(usdNew);
        accounts.addAll(acc);
        users.add(shokh);

        List<Account> abdulloh = new ArrayList<>();
        User azimov = new User("Abdulloh", "Azimov", "2", "2", abdulloh);
        Account kgz1 = new Account(9999, "KGZ", azimov);
        Account usd1 = new Account(9999, "USD", azimov);
        abdulloh.add(kgz1);
        abdulloh.add(usd1);
        accounts.addAll(abdulloh);
        users.add(azimov);

        List<Account> adilet = new ArrayList<>();
        User Adilet = new User("Adilet", "Isakov", "3", "3", adilet);
        Account kgz2 = new Account(9999, "KGZ", Adilet);
        Account usd2 = new Account(9999, "USD", Adilet);
        adilet.add(kgz2);
        adilet.add(usd2);
        accounts.addAll(adilet);
        users.add(Adilet);


        List<Account> bahaAccount = new ArrayList<>();
        User baha = new User("Adilet", "Isakov", "3", "3", bahaAccount);
        Account kgzbaha = new Account(9999, "KGZ", baha);
        Account usdbaha = new Account(9999, "USD", baha);
        bahaAccount.add(kgzbaha);
        bahaAccount.add(usdbaha);
        accounts.addAll(bahaAccount);
        users.add(baha);

        List<Account> ruslanAccounts = new ArrayList<>();
        User ruslan = new User("Ruslan", "Ruslan", "4", "4", ruslanAccounts);
        Account kgz3 = new Account(9999, "KGZ", ruslan);
        Account usd3 = new Account(9999, "USD", ruslan);
        ruslanAccounts.add(kgz3);
        ruslanAccounts.add(usd3);
        accounts.addAll(ruslanAccounts);
        users.add(ruslan);


        bank = new Bank("Shoh", users, accounts, transactions);
        getSavedBank();
        menu();
    }

    //                          MENU
    static void mainMenuu() {
        System.out.println("-*-*-* ------------------------- 0 -> Поиск аккаунта --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 1 -> Пополнить счет --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 2 -> Обменять  деньги --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 3 -> Перевести средства на другой счет --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 4 -> Просмотреть всех пользователей банка --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 5 -> История аккаунта --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 6 -> Настройка аккаунта --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 7 -> Добавить аккаунт --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 8 -> Выйти из системы --------------------- *-*-*-");
        System.out.println("-*-*-* ------------------------- 9 -> Выйти из системы и сохранить изменения --------------------- *-*-*-");
        while (true) {
            boolean b = false;
            try {
                int num = scanner.nextInt();
                if (num >= 0 && num <= 9) {
                    switch (num) {
                        case 0:
                            Transaction.searchAcc();
                            mainMenuu();
                        case 1:
                            Transaction.replenishment();
                            b = true;
                        case 2:
                            Transaction.transferMoney();
                            b = true;
                        case 3:
                            Transaction.transferMoneyToAnotherAccount();
                            b = true;
                        case 4:
                            bank.infoBank();
                            mainMenuu();
                        case 5:
                            Transaction.infoTransaction();
                            mainMenuu();
                        case 6:
                            Transaction.settings();
                            b = true;
                        case 7:
                            menu();
                        case 8:
                            Transaction.EXIT();
                        case 9:
                            save();
                    }
                    if (b) {
                        break;
                    }
                } else
                    Transaction.textWholeNum();
                mainMenuu();
            } catch (Exception e) {
                Transaction.textWholeNum();
                mainMenuu();
            }
        }
    }

    static void menu() {
        scanner = new Scanner(System.in);
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- 1 -> Зарегистрировоться -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- 2 -> Войти -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        try {
            int userChoice = scanner.nextInt();
            if (userChoice >= 1 && userChoice <= 2) {
                switch (userChoice) {
                    case 1 -> singIn();
                    case 2 -> login();
                }
            } else {
                Transaction.enteredIncorrectly();
                menu();
            }
        } catch (InputMismatchException e) {
            Transaction.textWholeNum();
            menu();
        }

    }

    private static void singIn() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите логин -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        String login = scanner.next();
        if (bank.checkDuplicateLogin(login)) ifDuplicateLogin();
        else {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                    "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите пароль -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            String password = scanner.next();
            if (password.length() >= 6) {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите ваше имя -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                String firstName = scanner.next();
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите вашу фамилию -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                String lastName = scanner.next();
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Вы успешно зарегистрировались -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                loggedUser = new User(firstName, lastName, login, password, myAccount);
                Account k = new Account(0, "KGZ", loggedUser);
                Account u = new Account(0, "USD", loggedUser);
                myAccount.add(k);
                myAccount.add(u);
                accounts.addAll(myAccount);
                users.add(loggedUser);
                bank = new Bank("Shoh", users, accounts, transactions);

                mainMenuu();
            } else {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите мнимум шесть символов -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                singIn();
            }
        }
    }
//Вы точно хотите выйти из аккаунта ? и (не сохранить изменения)

    private static void ifDuplicateLogin() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Этот логин уже существует -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- 1 -> Главный меня  -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- 2 -> Попробуйте еще раз -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- 3 -> Завершить процесс -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        int userchoice = 0;
        try {
            userchoice = scanner.nextInt();

        } catch (InputMismatchException e) {
            Transaction.textWholeNum();
            ifDuplicateLogin();
        }
        if (userchoice >= 1 && userchoice <= 3) {
            switch (userchoice) {
                case 1:
                    menu();
                case 2:
                    singIn();
                case 3:
                    Transaction.exit();
            }
        } else {
            Transaction.enteredIncorrectly();
            ifDuplicateLogin();
        }
    }

    public static void login() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите свой логин -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        String login = scanner.next();
        if (bank.checkDuplicateLogin(login)) {
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите свой пароль -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            String password = scanner.next();
            loggedUser = bank.returnUser(login, password);
            if (loggedUser != null) {
                mainMenuu();
            } else {
                System.err.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Неверный логин, попробуйте еще раз -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                login();
            }
        } else {
            System.err.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Неверный пароль, попробуйте еще раз -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            login();
        }
        menu();
    }

    private static void save() {

        try {
            FileOutputStream fos = new FileOutputStream("Bank");
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(bank);
            ois.close();
            Transaction.exit();
            System.out.println("\n" + "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Изменения сохранены -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }

    private static void getSavedBank() {
        try {
            FileInputStream fis = new FileInputStream("Bank");
            ObjectInputStream ois = new ObjectInputStream(fis);
            bank = (Bank) ois.readObject();
            ois.close();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}