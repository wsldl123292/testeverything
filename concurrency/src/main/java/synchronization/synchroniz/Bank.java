package synchronization.synchroniz;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 11:27
 */
public class Bank implements Runnable {


    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.subtractAmount(1000);
        }
    }
}
