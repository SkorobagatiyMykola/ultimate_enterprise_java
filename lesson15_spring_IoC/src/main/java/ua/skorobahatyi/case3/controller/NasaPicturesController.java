package ua.skorobahatyi.case3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.skorobahatyi.case3.client.NasaPicturesClient3;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping("nasa/pictures2")
@RequiredArgsConstructor
public class NasaPicturesController {
    private final NasaPicturesClient3 nasaPicturesClient3;
    private final HttpServletRequest request;

    @GetMapping
    public String getAllPictureUrls(){
        var servletContext = request.getServletContext();
        System.out.println("Printing servlet context attributes:");
        servletContext.getAttributeNames().asIterator()
                .forEachRemaining(System.out::println);
        return nasaPicturesClient3.getAllPictures()
                .stream()
                .collect(Collectors.joining("\n"));

    }
}
