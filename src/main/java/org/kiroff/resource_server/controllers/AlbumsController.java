package org.kiroff.resource_server.controllers;

import org.kiroff.resource_server.domain.Album;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/albums")
public class AlbumsController
{
    @GetMapping()
    List<Album> getAlbums()
    {
        return IntStream.range(1, 1000)
                .mapToObj(i -> new Album(i, "Album " + i, "http://localhost:8082/albums/" + i))
                .toList();
    }

    @GetMapping("/{id}")
    Album getAlbums(@PathVariable("id") int i)
    {
        return new Album(i, "Album " + i, "http://localhost:8082/albums/" + i);
    }
}
