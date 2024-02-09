package io.noobi.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job findById(Long id);
    boolean removeById(Long Id);
    boolean removeAllJobs();
    boolean updateJob(Long id,Job updatedJob);
    boolean updateAllJobs(Job updatedJob);
}
