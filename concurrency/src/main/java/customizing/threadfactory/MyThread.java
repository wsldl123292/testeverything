package customizing.threadfactory;

import java.util.Date;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-11 17:20
 */
public class MyThread extends Thread {

    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate(new Date());
    }

    public long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }

    @Override
    public void run() {
        setStartDate(new Date());
        super.run();
        setFinishDate(new Date());
    }

    @Override
    public String toString() {
        return getName() +
                ": " +
                " Creation Date: " +
                creationDate +
                " : Running time: " +
                getExecutionTime() +
                " Milliseconds.";
    }
}
