package learn.demo.repository;

import org.springframework.data.repository.CrudRepository;

import learn.demo.model.Team;

public interface TeamRepository extends CrudRepository<Team,Long>{
    Team findByTeamName(String teamName);
}
