package io.noobi.jobapp.job;

import io.noobi.jobapp.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = jobService.findAll();
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job created Successfully!!!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        if (job != null) {
            return ResponseEntity.ok(job);
        }
        Job dummy = new Job(0L, "TestJob", "No jobs Found", "0 LPA", "0 LPA", "Unknown", null);
        return new ResponseEntity<>(dummy, HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeById(@PathVariable Long id) {
        boolean deleted = jobService.removeById(id);
        if (deleted) {
            return ResponseEntity.ok("Job successfully deleted with ID : " + id);
        }
        return new ResponseEntity<>("Job Not found with ID : " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job body) {
        boolean updated = jobService.updateJob(id, body);
        Job job = findById(id).getBody();
        if (updated) {
            return ResponseEntity.ok(job);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
