package learn.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import learn.demo.model.Match;

public interface MatchRepository extends CrudRepository<Match,Long>{

    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);
    
}
