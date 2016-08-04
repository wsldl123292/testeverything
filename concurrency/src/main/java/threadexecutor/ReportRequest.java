package threadexecutor;

import java.util.concurrent.CompletionService;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 23:01
 */
public class ReportRequest implements Runnable {

    private String name;

    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name,"Report");
        service.submit(reportGenerator);
    }
}
