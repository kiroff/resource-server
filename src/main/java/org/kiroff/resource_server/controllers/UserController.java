package org.kiroff.resource_server.controllers;

import org.kiroff.resource_server.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Environment env;

    @RequestMapping("/status")
    public String status()
    {
        return "Running on port " + env.getProperty("local.server.port") + " at " + java.time.LocalDateTime.now();
    }

    @PreAuthorize("hasRole('developer') or hasAnyAuthority('ROLE_developer') or #id == #jwt.subject")
//    @Secured("ROLE_developer")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id, @AuthenticationPrincipal Jwt jwt)
    {
        return "Deleted user with id=" + id + " or with JWT subject=" + jwt.getSubject();
    }

    @PostAuthorize("returnObject != null && returnObject.userId != null and principal.subject != null and returnObject.userId.equalsIgnoreCase(principal.subject)")
    @GetMapping("/{id}")
    public User details(@PathVariable("id") String id, @AuthenticationPrincipal Jwt jwt)
    {
        if(jwt == null) throw new RuntimeException("No JWT found");
        var user = new User(id, jwt.getClaims().get("given_name").toString(), jwt.getClaims().get("family_name").toString());
        LOG.info("[{}] returnObject.userId", user.userId());
        LOG.info("[{}] jwt.subject", jwt.getSubject());
        LOG.info("[{}] == [{}] => {}", user.userId(), jwt.getSubject(), user.userId() == jwt.getSubject());
        return user;
    }
}
