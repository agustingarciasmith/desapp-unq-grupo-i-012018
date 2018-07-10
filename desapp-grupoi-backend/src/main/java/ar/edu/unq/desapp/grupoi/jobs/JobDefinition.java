package ar.edu.unq.desapp.grupoi.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.TaskScheduler;

import java.sql.Date;

public abstract class JobDefinition implements InitializingBean {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected TaskScheduler scheduler;

    public JobDefinition(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        schedule();
    }

    public void schedule() {
        logger.info("Scheduleando job {}", jobName());

        Date fechaInicio = new Date(System.currentTimeMillis());
        scheduler.schedule(task(), fechaInicio);
    }

    public abstract String jobName();

    public abstract Runnable task();

}
