package synchronization.synchroniz;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 11:31
 */
public class SyncMain {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        System.out.printf("Account: Initial Balance: %f\n", account.getBalance());

        companyThread.start();
        bankThread.start();


        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account: Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
