package com.example.looping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    JobRepository jobRepository;

   @RequestMapping("/")
   public String listJobs(Model model){
       model.addAttribute("jobs", jobRepository.findAll());
       return "list";
       // Model class, model object...
       // in model, name jobs... jobRepo.all...
       // return list? ... list has template for 'jobs'
       //
       /*
       <div th:each="job : ${jobs}">
    <h4 th:inline="text">[[${job.title}]] @ [[${job.employer}]]</h4>
    <p th:text="${job.description}"></p>
    </div>

    for loop, i || job ... through ${jobs} -> model.add... jobs: repo
    going through the repo on line 21 above.
        */

   }

   @GetMapping("/add")
   public String jobForm(Model model){
       model.addAttribute("job", new Job());
       return "jobform";
   }

// creates an empty JavaBean
    // provides forms to fill out -> line 49 uses this data
    // line 49 only returns jobform if invalid data
    // if successful, redirects to root, "/", the list.


    @PostMapping("/process")
    public String processForm(@Valid Job job, BindingResult result){
        if (result.hasErrors()){
            return "jobform";
        }
        jobRepository.save(job);
        return "redirect:/";
    }
}
