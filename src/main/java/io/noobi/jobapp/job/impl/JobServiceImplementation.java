package io.noobi.jobapp.job.impl;

import io.noobi.jobapp.job.Job;
import io.noobi.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class JobServiceImplementation implements JobService {

    private List<Job> jobs=new ArrayList<>();
    private Long nextId=1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job findById(Long id) {
        for (Job job:jobs) {
            if(job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean removeById(Long id) {
        ListIterator<Job> li=jobs.listIterator();
        while(li.hasNext()) {
            if(li.next().getId().equals(id)) {
                li.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAllJobs() {
        if(jobs.isEmpty()) {
            return false;
        }
        jobs.clear();
        return true;
    }

    @Override
    public boolean updateJob(Long id,Job updatedJob) {
        for(Job job:jobs) {
            if(job.getId().equals(id)) {
                if(updatedJob.getTitle() != null) job.setTitle(updatedJob.getTitle());
                if(updatedJob.getDescription()!=null) job.setDescription(updatedJob.getDescription());
                if(updatedJob.getMinSalary()!=null) job.setMinSalary(updatedJob.getMinSalary());
                if(updatedJob.getMaxSalary()!=null) job.setMaxSalary(updatedJob.getMaxSalary());
                if(updatedJob.getLocation()!=null) job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateAllJobs(Job updatedJob) {
        if(jobs.isEmpty()) {
            return false;
        }
        for(Job job:jobs) {
            if(updatedJob.getTitle() != null) job.setTitle(updatedJob.getTitle());
            if(updatedJob.getDescription()!=null) job.setDescription(updatedJob.getDescription());
            if(updatedJob.getMinSalary()!=null) job.setMinSalary(updatedJob.getMinSalary());
            if(updatedJob.getMaxSalary()!=null) job.setMaxSalary(updatedJob.getMaxSalary());
            if(updatedJob.getLocation()!=null) job.setLocation(updatedJob.getLocation());
        }
        return true;
    }
}
