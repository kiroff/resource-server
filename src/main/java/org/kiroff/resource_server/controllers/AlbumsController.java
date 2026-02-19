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

    public static final String URL = "http://localhost:8020/albums/";

    @GetMapping()
    List<Album> getAlbums()
    {
        return IntStream.range(1, 1000)
                .mapToObj(AlbumsController::getAlbum)
                .toList();
    }

    @GetMapping("/{id}")
    Album getAlbums(@PathVariable("id") String i)
    {
        return getAlbum(Integer.parseInt(i));
    }

    private static Album getAlbum(int i)
    {
        return new Album(i, "Album " + i, URL + i);
    }
}
