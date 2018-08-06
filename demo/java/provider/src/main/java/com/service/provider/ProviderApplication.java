package com.service.provider;

import com.service.provider.service.TestService;
import com.service.provider.service.WebService;
import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProviderApplication {
    private static final int poolSize = 2;

    private static Executor executor = Executors.newFixedThreadPool(poolSize);

    public static void main(String[] args) throws Exception {
        Log4jUtils.init();
        BeanUtils.init();

        executor.execute(TestService.INSTANCE);
        executor.execute(WebService.INSTANCE);
    }
}
