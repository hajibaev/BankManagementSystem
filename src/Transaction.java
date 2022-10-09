import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

public class Transaction implements Serializable {
    private final double amount;
    private final String timestamp;
    private final Account account;
    private final String typeOfTransaction;
    static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


    public Transaction(String typeOfTransaction, double amount, String timestamp, Account account) {
        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
        this.timestamp = timestamp;
        this.account = account;

    }


    //                              ЧЕК
    void getInfo() {
        System.out.println("----------------------------------- Пользватель -> " + getAccount().getAccountHolder().getFirstName() + " " + getAccount().getAccountHolder().getLastName() + " -----------------------------------");
        System.out.println("----------------------------------- Тип операции -> " + typeOfTransaction + " -----------------------------------");
        System.out.println("----------------------------------- Сумма транзакции -> " + getAmount() + " -----------------------------------");
        System.out.println("----------------------------------- Баланс после транзакции -> " + (getAccount().getBalance()) + " -----------------------------------");
        System.out.println("----------------------------------- Дата операции -> " + getTimestamp() + "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("----------------------------------- Тип счета на котором была совершена транзакция -> " + getAccount().getName() + " -----------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //                                   узнать информацию о своих транзациях
    static void infoTransaction() {
        boolean transaction = false;
        while (true) {
            Account.accountInfo();
            for (Transaction t : MainClass.bank.getTransactions()) {
                if (MainClass.loggedUser.getId() == t.getAccount().getAccountHolder().getId()) {
                    t.getInfo();
                    transaction = true;
                }
            }
            if (transaction) {
                break;
            }
            if (!transaction) {
                System.err.println("-*-*-* ------------------------- У вас пока что нет транзакциий --------------------- *-*-*-");
                break;
            }
        }


    }

    //                           Settings
    public static void settings() {
        while (true) {
            Account.myAccountInfo();
            System.out.println("-*-*-* ------------------------- 1 -> Изменить Логин --------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- 2 -> Изменить Пароль --------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- 3 -> Изменить Имя  --------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- 4 -> Изменить Фамилию --------------------- *-*-*-");
            System.out.println("-*-*-* ------------------------- 5 -> Назад --------------------- *-*-*-");
            try {
                int num = scanner.nextInt();
                if (num >= 1 && num <= 5) {
                    switch (num) {
                        case 1:
                            settingsLogin();
                        case 2:
                            settingsPassword();
                        case 3:
                            settingsName();
                        case 4:
                            settingsLastName();
                        case 5:
                            MainClass.mainMenuu();
                    }
                } else {
                    textWholeNum();
                }
            } catch (Exception e) {
                enteredIncorrectly();
            }
        }
    }

    private static void settingsLastName() {
        scanner = new Scanner(System.in);
        for (Account sln : MainClass.loggedUser.getAccountList()) {
            System.out.println("-*-*-* ------------------------- Введите старый фамилию --------------------- *-*-*-");
            String lastName = scanner.nextLine();
            if (lastName.equals(sln.getAccountHolder().getLastName())) {
                System.out.println("-*-*-* ------------------------- Введите новый фамилию --------------------- *-*-*-");
                String newName = scanner.nextLine();
                sln.getAccountHolder().setLastName(newName);
                System.out.println("-*-*-* ------------------------- Изменения успешно завершено --------------------- *-*-*-" + "\n");
                MainClass.mainMenuu();
            } else {
                System.err.println("-*-*-* ------------------------- Введенный Фамилия неправильно --------------------- *-*-*-" + "\n");
                MainClass.mainMenuu();
            }
        }
    }

    private static void settingsName() {
        scanner = new Scanner(System.in);
        for (Account sln : MainClass.loggedUser.getAccountList()) {
            System.out.println("-*-*-* ------------------------- Введите старый Имя --------------------- *-*-*-");
            String name = scanner.nextLine();
            if (name.equals(sln.getAccountHolder().getFirstName())) {
                System.out.println("-*-*-* ------------------------- Введите новый Имя --------------------- *-*-*-");
                String newName = scanner.nextLine();
                sln.getAccountHolder().setFirstName(newName);
                System.out.println("-*-*-* ------------------------- Изменения успешно завершено --------------------- *-*-*-" + "\n");
                MainClass.mainMenuu();
            } else {
                System.err.println("-*-*-* ------------------------- Введенный Имя неправильно --------------------- *-*-*-" + "\n");
                MainClass.mainMenuu();
            }
        }
    }

    private static void settingsPassword() {
        scanner = new Scanner(System.in);
        for (Account sp : MainClass.loggedUser.getAccountList()) {
            System.out.println("-*-*-* ------------------------- Введите старый Пароль --------------------- *-*-*-");
            String pas = scanner.nextLine();
            if (pas.equals(sp.getAccountHolder().getPassword())) {
                System.out.println("-*-*-* ------------------------- Введите новый Пароль --------------------- *-*-*-");
                String newPas = scanner.nextLine();
                if (newPas.length() >= 6) {
                    sp.getAccountHolder().setPassword(newPas);
                    System.out.println("-*-*-* ------------------------- Изменения успешно завершено --------------------- *-*-*-" + "\n");
                    MainClass.mainMenuu();
                } else {
                    System.err.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите мнимум шесть символов -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    settings();
                }

            } else {
                System.err.println("-*-*-* ------------------------- Введенный Пароль неправильно --------------------- *-*-*-" + "\n");
                MainClass.mainMenuu();
            }
        }
    }

    private static void settingsLogin() {
        scanner = new Scanner(System.in);
        for (Account sln : MainClass.loggedUser.getAccountList()) {
            System.out.println("-*-*-* ------------------------- Введите старый Логин --------------------- *-*-*-");
            String login = scanner.nextLine();
            if (login.equals(sln.getAccountHolder().getLogin())) {
                System.out.println("-*-*-* ------------------------- Введите новый Логин --------------------- *-*-*-");
                String newLogin = scanner.nextLine();
                if (MainClass.bank.checkDuplicateLogin(newLogin)) {
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                            "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Этот логин уже существует -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    ifDuplicate();
                } else {
                    sln.getAccountHolder().setLogin(newLogin);
                    System.out.println("-*-*-* ------------------------- Изменения успешно завершено --------------------- *-*-*-" + "\n");
                    MainClass.mainMenuu();
                }
            } else {
                System.err.println("-*-*-* ------------------------- Введенный Логин неправильно --------------------- *-*-*-" + "\n");
                settings();
            }
        }
    }

    private static void ifDuplicate() {
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
            textWholeNum();
            ifDuplicate();
        }
        if (userchoice >= 1 && userchoice <= 3) {
            switch (userchoice) {
                case 1:
                    MainClass.mainMenuu();
                case 2:
                    settingsLogin();
                case 3:
                    exit();
            }
        } else {
            enteredIncorrectly();
            ifDuplicate();
        }
    }


    //                    Пополнение счета
    static void replenishment() {
        while (true) {
            try {
                Account.accountInfo();
                System.out.println("-*-*-* ------------------------- 1 -> Назад\n" + "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите id своего счета -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                int id = scanner.nextInt();
                for (Account account : MainClass.loggedUser.getAccountList()) {
                    if (id == 1) {
                        MainClass.mainMenuu();
                    } else if (id == account.getId() && account.getName().equals("KGZ")) {
                        popolnenieBalansaNaKGZ(account);
                    } else if (id == account.getId() && account.getName().equals("USD")) {
                        popolnenieBalansaNaUSD(account);
                    }
                }
            } catch (Exception e) {
                enteredIncorrectly();
            }
        }
    }


    //                         Пополнить деньги на сом
    private static void popolnenieBalansaNaKGZ(Account account) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите сумму -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        double money = scanner.nextDouble();
        double d = account.getBalance() + money;
        account.setBalance(d);
        Transaction transaction = new Transaction("Пополнение счета", money, sdf.format(new Date()), account);
        MainClass.bank.getTransactions().add(transaction);
        transaction.getInfo();
        MainClass.mainMenuu();
    }

    //                                   Пополнить деньги на USD
    private static void popolnenieBalansaNaUSD(Account account) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите сумму -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        double money = scanner.nextDouble();
        double totalMoney = account.getBalance() + money;
        account.setBalance(totalMoney);
        Transaction transaction = new Transaction("Пополнение счета", money, sdf.format(new Date()), account);
        MainClass.bank.getTransactions().add(transaction);
        transaction.getInfo();
        MainClass.mainMenuu();
    }

    //                   Перевести деньги
    static void transferMoney() {
        while (true) {
            try {
                Account.accountInfo();
                System.out.println("-*-*-* ------------------------- 1 -> Назад\n" + "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите id своего счета -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                int id = scanner.nextInt();
                for (Account account : MainClass.loggedUser.getAccountList()) {
                    if (id == 1) {
                        MainClass.mainMenuu();
                    } else if (id == account.getId() && account.getName().equals("KGZ")) {
                        vivestiDengiNaUsdSoSchetaKgz(account);
                    } else if (id == account.getId() && account.getName().equals("USD")) {
                        vivestiDengiNaKgzSoSchetaUsd(account);
                    }
                }
            } catch (Exception e) {
                enteredIncorrectly();
            }
        }
    }

    //                           вывести деньги на USD  со счета KGZ
    private static void vivestiDengiNaUsdSoSchetaKgz(Account account) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите id USD счета -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        try {
            int id = scanner.nextInt();
            for (Account a : MainClass.loggedUser.getAccountList()) {
                if (id == a.getId() && a.getName().equals("USD")) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите сумму -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    double money = scanner.nextDouble();
                    if (money < 0) {
                        enteredIncorrectly();
                        MainClass.mainMenuu();
                    } else if (money > account.getBalance()) {
                        notEnoughBalance();
                        MainClass.mainMenuu();
                    } else {
                        System.out.println("----------------------------------- Вы снимаете деньги с KGZ счета на USD валюту -----------------------------------");
                        System.out.println("-----------------------------------      Сумма будет уменьшена по курсу USD      -----------------------------------");
                        double newBalance = account.getBalance() - money;
                        account.setBalance(newBalance);
                        double totalTransaction = money / MainClass.kursUsd;
                        totalTransaction = Math.round(totalTransaction);
                        double b = a.getBalance() + totalTransaction;
                        a.setBalance(b);
                        Transaction transaction = new Transaction("Снять деньги со счета", totalTransaction, sdf.format(new Date()), account);
                        MainClass.bank.getTransactions().add(transaction);
                        transaction.getInfo();
                        MainClass.mainMenuu();
                    }
                } else if (id != a.getId() && a.getName().equals("USD")) {
                    enteredIncorrectly();
                    MainClass.mainMenuu();
                }
            }
        } catch (Exception e) {
            enteredIncorrectly();
            MainClass.mainMenuu();
        }
    }
//                         вывести деньги на KGZ со счета USD

    static void vivestiDengiNaKgzSoSchetaUsd(Account account) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите id KGZ счета -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        try {
            int id = scanner.nextInt();
            for (Account a : MainClass.loggedUser.getAccountList()) {
                if (id == a.getId() && a.getName().equals("KGZ")) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите сумму -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                    double money = scanner.nextDouble();
                    if (money <= 0) {
                        enteredIncorrectly();
                        MainClass.mainMenuu();
                    } else if (money > account.getBalance()) {
                        notEnoughBalance();
                        MainClass.mainMenuu();
                    } else {
                        System.out.println("----------------------------------- Вы снимаете деньги с USD счета на KGZ счету -----------------------------------");
                        System.out.println("-----------------------------------      Сумма умножается на курс USD           -----------------------------------");
                        double newBalance = account.getBalance() - money;
                        account.setBalance(newBalance);
                        double totalTransaction = money * MainClass.kursUsd;
                        double b = a.getBalance() + totalTransaction;
                        a.setBalance(b);
                        Transaction transaction = new Transaction("Снять деньги со счета", totalTransaction, sdf.format(new Date()), account);
                        MainClass.bank.getTransactions().add(transaction);
                        transaction.getInfo();
                        MainClass.mainMenuu();
                    }
                } else if (id != a.getId() && a.getName().equals("KGZ")) {
                    enteredIncorrectly();
                    MainClass.mainMenuu();
                }
            }
        } catch (Exception e) {
            enteredIncorrectly();
            MainClass.mainMenuu();
        }
    }

    static void transferMoneyToAnotherAccount() {
        while (true) {
            Account.accountInfo();
            System.out.println("-*-*-* ------------------------- 1 -> Назад\n" + "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите id своего счета -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            try {
                int aydi = scanner.nextInt();
                for (Account acc : MainClass.loggedUser.getAccountList()) {
                    if (aydi == 1) {
                        MainClass.mainMenuu();
                    } else if (aydi == acc.getId()) {
                        Account.getUserAccount((List<Account>) MainClass.bank.getAccounts());
                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите ID счета на который вы хотите перевести деньги -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                        int id = scanner.nextInt();
                        for (Account account : MainClass.bank.getAccounts()) {
                            if (id == account.getId() && id != acc.getId()) {
                                account.info();
                                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите сумму -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                                double money = scanner.nextDouble();
                                if (money > acc.getBalance()) {
                                    notEnoughBalance();
                                    MainClass.mainMenuu();
                                } else {
                                    if (acc.getName().equals("KGZ") && account.getName().equals("USD")) {
                                        System.out.println("----------------------------------- Вы переводите деньги с KGZ баланса на USD баланс -----------------------------------");
                                        double totalMoney = money / MainClass.kursUsd;
                                        totalMoney = Math.round(totalMoney);
                                        if (money > acc.getBalance()) {
                                            notEnoughBalance();
                                            MainClass.mainMenuu();
                                        }
                                        Transaction transaction1 = new Transaction("Перевод средств: Отправление", totalMoney, sdf.format(new Date()), acc);
                                        Transaction transaction2 = new Transaction("Перевод средств: Получение", totalMoney, sdf.format(new Date()), account);
                                        double minusBalance = acc.getBalance() - money;
                                        double pilusBalance = account.getBalance() + totalMoney;
                                        acc.setBalance(minusBalance);
                                        account.setBalance(pilusBalance);
                                        MainClass.bank.getTransactions().add(transaction1);
                                        MainClass.bank.getTransactions().add(transaction2);
                                        transaction1.getInfo();
                                        transaction2.getInfo();
                                        MainClass.mainMenuu();
                                    } else if (acc.getName().equals("USD") && account.getName().equals("KGZ")) {
                                        System.out.println("----------------------------------- Вы переводите деньги с USD баланса на KGZ баланс -----------------------------------");
                                        double totalMoney = money * MainClass.kursUsd;
                                        totalMoney = Math.round(totalMoney);
                                        double minusBalance = acc.getBalance() - money;
                                        double pilusBalance = account.getBalance() + totalMoney;
                                        acc.setBalance(minusBalance);
                                        account.setBalance(pilusBalance);
                                        Transaction transaction1 = new Transaction("Перевод средств: Отправление", totalMoney, sdf.format(new Date()), acc);
                                        Transaction transaction2 = new Transaction("Перевод средств: Получение", totalMoney, sdf.format(new Date()), account);
                                        transaction1.getInfo();
                                        transaction2.getInfo();
                                        MainClass.bank.getTransactions().add(transaction1);
                                        MainClass.bank.getTransactions().add(transaction2);
                                        MainClass.mainMenuu();
                                    } else {
                                        double minusBalance = acc.getBalance() - money;
                                        double pilusBalance = account.getBalance() + money;
                                        acc.setBalance(minusBalance);
                                        account.setBalance(pilusBalance);
                                        Transaction transaction1 = new Transaction("Перевод средств: Отправление", money, sdf.format(new Date()), acc);
                                        Transaction transaction2 = new Transaction("Перевод средств: Получение", money, sdf.format(new Date()), account);
                                        transaction1.getInfo();
                                        transaction2.getInfo();
                                        MainClass.bank.getTransactions().add(transaction1);
                                        MainClass.bank.getTransactions().add(transaction2);
                                        MainClass.mainMenuu();
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                enteredIncorrectly();
            }
        }
    }

    static void searchAcc() {
        scanner = new Scanner(System.in);
        System.out.println("--*-*-* ------------------------- Введите владелец аккаунта --------------------- *-*-*-");
        String poisk = scanner.nextLine();
        Account.searchAccounts(poisk);
    }

    private static void notEnoughBalance() {
        System.err.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- У вас недостаточно средств на балансе для вывода этой суммы -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
    }

    static void textWholeNum() {
        System.err.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введите целые числа -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        scanner = new Scanner(System.in);
    }

    static void enteredIncorrectly() {
        System.err.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Введены числа не корректно -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        scanner = new Scanner(System.in);
    }

    static void exit() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Уважаемый  пользователь -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n" +
                "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- Мы ждем вас снова -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
    }

    static void EXIT() {
        System.out.println("-*-*-*-*-*-*-*-*-*-  Вы точно хотите выйти из аккаунта ? и (не сохранить изменения) *-*-*-*-*-*-*-*-*-" + "\n");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- 1 -> Назад " + "\n" + "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- 2 -> Да ");
        try {
            int i = scanner.nextInt();
            if (i >= 1 && i <= 2) {
                switch (i) {
                    case 1:
                        MainClass.mainMenuu();
                    case 2:
                        exit();

                        System.exit(0);
                }
            } else {
                enteredIncorrectly();
                EXIT();
            }
        } catch (InputMismatchException e) {
            enteredIncorrectly();
            EXIT();
        }
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
