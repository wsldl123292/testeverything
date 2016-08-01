package synchronization.lock;

import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 17:49
 */
public class PricesInfo {

    private double price1;

    private double price2;

    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        //读取资源锁定
        lock.readLock().lock();
        System.out.println("f "+new Date().getTime());
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        System.out.println(new Date().getTime());
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }
}
