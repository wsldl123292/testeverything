package synchronization.synchroniz;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 12:54
 */
public class CinemaMain {

    public static void main(String[] args) throws InterruptedException {
        Cinema cinema = new Cinema();

        TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOffice1,"TicketOffice1");

        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2,"TicketOffice2");

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();


        System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());
    }
}
