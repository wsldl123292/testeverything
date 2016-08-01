package synchronization.synchroniz;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 12:43
 */
public class Cinema {

    private final Object controlCinema1, controlCinema2;
    private long vacanciesCinema1;
    private long vacanciesCinema2;

    public Cinema() {
        this.vacanciesCinema1 = 20;
        this.vacanciesCinema2 = 20;
        this.controlCinema1 = new Object();
        this.controlCinema2 = new Object();
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }

    public void setVacanciesCinema1(long vacanciesCinema1) {
        this.vacanciesCinema1 = vacanciesCinema1;
    }

    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }

    public void setVacanciesCinema2(long vacanciesCinema2) {
        this.vacanciesCinema2 = vacanciesCinema2;
    }


    public boolean sellTickets1(int number) {
        synchronized (controlCinema1) {
            if (number < vacanciesCinema1) {
                vacanciesCinema1 -= number;
                return true;
            }
            return false;
        }
    }

    public boolean sellTickets2(int number) {
        synchronized (controlCinema2) {
            if (number < vacanciesCinema2) {
                vacanciesCinema2 -= number;
                return true;
            }
            return false;
        }
    }

    public boolean returnTickets1(int number) {
        synchronized (controlCinema1) {
            vacanciesCinema1 += number;
            return true;
        }
    }

    public boolean returnTickets2(int number) {
        synchronized (controlCinema2) {
            vacanciesCinema2 += number;
            return true;
        }
    }
}
