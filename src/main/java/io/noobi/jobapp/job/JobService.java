package io.noobi.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job findById(Long id);
    void createJob(Job job);
    boolean removeById(Long Id);
    boolean updateJob(Long id,Job updatedJob);
}
