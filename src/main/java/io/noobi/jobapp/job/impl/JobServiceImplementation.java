package io.noobi.jobapp.job.impl;

import io.noobi.jobapp.job.Job;
import io.noobi.jobapp.job.JobRepository;
import io.noobi.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean removeById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id,Job updatedJob) {
        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job= jobOptional.get();
            if(updatedJob.getTitle()!=null)job.setTitle(updatedJob.getTitle());
            if(updatedJob.getDescription()!=null)job.setDescription(updatedJob.getDescription());
            if(updatedJob.getMinSalary()!=null)job.setMinSalary(updatedJob.getMinSalary());
            if(updatedJob.getMaxSalary()!=null)job.setMaxSalary(updatedJob.getMaxSalary());
            if(updatedJob.getLocation()!=null)job.setLocation(updatedJob.getLocation());
            return true;
        }
        return false;
    }

}
