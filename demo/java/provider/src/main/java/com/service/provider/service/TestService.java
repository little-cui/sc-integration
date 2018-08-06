package com.service.provider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestService implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TestService.class);

    public static TestService INSTANCE = new TestService();

    private String task;

    @Override
    public void run() {
        String t = System.getenv("TYPE");
        if (t == null) {
            return;
        }

        while (true) {
            try {
                Thread.sleep(1000);

                String task = getTask();
                if (task == null) {
                    continue;
                }

                logger.info("{} received {}", t, task);

                if ((task.equals("kill-provider") && t.equals("provider")) ||
                        (task.equals("kill-consumer") && t.equals("consumer"))) {
                    System.exit(1);
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
