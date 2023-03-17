package net.icircuit.clickhousebenchmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClickhouseBenchmarkApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext =
                SpringApplication.run(ClickhouseBenchmarkApplication.class, args);
        final BenchmarkRunner runner = applicationContext.getBean(BenchmarkRunner.class);
        try {
            runner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
