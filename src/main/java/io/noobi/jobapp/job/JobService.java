package io.noobi.jobapp.job;

import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    Job findById(Long id);

    void createJob(Job job);

    boolean removeById(Long Id);

    boolean updateJob(Long id, Job updatedJob);
}
