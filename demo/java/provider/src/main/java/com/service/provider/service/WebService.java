package com.service.provider.service;

import com.service.provider.utils.QueryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebService implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(WebService.class);

    public static WebService INSTANCE = new WebService();

    private boolean recoverSend(String serviceName, int recover) throws InterruptedException {
        Thread.sleep(10000);
        int times = 0;
        while (true) {
            try {
                QueryApi.INSTANCE.send(serviceName, "normal");
                break;
            } catch (Exception e) {
                logger.error(e.getMessage());
                if (times > recover) {
                    return false;
                }
            }
            times++;
            Thread.sleep(1000);
        }
        return true;
    }

    private boolean injectSend(String serviceName, String command, int inject) throws InterruptedException {
        int times = 0;
        while (true) {
            try {
                QueryApi.INSTANCE.send(serviceName, command);
                if (times > inject) {
                    return false;
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                if (times > inject) {
                    break;
                }
            }
            times++;
            Thread.sleep(1000);
        }
        return true;
    }

    private boolean execute(String executor, String command, int inject, int recover) throws InterruptedException {
        logger.info("CASE: '{}' do '{}' command", executor, command);
        if (!recoverSend(executor, recover)) {
            return false;
        }

        if (!injectSend(executor, command, inject)) {
            return false;
        }

        return recoverSend(executor, recover);
    }

    public boolean test() throws InterruptedException {
        if (!execute("consumer", "kill-consumer", 5, 2)) {
            return false;
        }
        if (!execute("consumer", "kill-provider", 5, 2)) {
            System.exit(1);
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        String t = System.getenv("TYPE");
        if (t == null || !t.equals("web")) {
            return;
        }

        try {
            Thread.sleep(5000);
            while (true) {
                if (!test()) {
                    System.exit(1);
                    return;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
