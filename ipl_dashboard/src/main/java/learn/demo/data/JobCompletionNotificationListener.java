package learn.demo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import learn.demo.model.Team;

@Component
public class JobCompletionNotificationListener
  extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(
    JobCompletionNotificationListener.class
  );

  private final EntityManager manager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager manager) {
    this.manager = manager;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Map<String, Team> map = new HashMap<>();

      manager
        .createQuery(
          "select m.team1, count(*) from Match m group by m.team1",
          Object[].class
        )
        .getResultList()
        .stream()
        .map(e -> new Team((String)e[0], (long) e[1]))
        .forEach(team -> map.put(team.getTeamName(), team));

      manager
        .createQuery(
          "select m.team2, count(*) from Match m group by m.team2",
          Object[].class
        )
        .getResultList()
        .stream()
        .forEach(e -> {
          Team t = map.get((String)e[0]);
          t.setTotalMatches(t.getTotalMatches() + (long) e[1]);
        });
        
        manager
        .createQuery(
          "select m.matchWinner, count(*) from Match m group by m.matchWinner",
          Object[].class
        )
        .getResultList()
        .stream()
        .forEach(e -> {
          Team t = map.get((String)e[0]);
          if(t!=null)
            t.setMatchesWon((long) e[1]);
        });

        map.values().forEach(team -> manager.persist(team));

        map.values().forEach(team -> System.out.println(team));

    }
  }
}
