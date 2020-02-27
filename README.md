   ```$xslt
Iterable <Job> jobs = jobRepository.findAll();
for(Job job:jobs)
{
System.out.println(job.getTitle()+" @ "+job.getEmployer());
System.out.println(job.getDescription());
}


<div th:each="job : ${jobs}">
    <h4 th:inline="text">[[${job.title}]] @ [[${job.employer}]]</h4>
    <p th:text="${job.description}"></p>
</div>

```