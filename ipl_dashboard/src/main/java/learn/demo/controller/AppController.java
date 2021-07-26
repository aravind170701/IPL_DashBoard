package learn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.demo.model.Team;
import learn.demo.service.TeamService;

@RestController
public class AppController {

    @Autowired
    private TeamService service;

    @RequestMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName)
    {
        return service.getByTeamName(teamName);
    }
    
}
