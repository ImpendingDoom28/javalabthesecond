package ru.itis.mail.controllers;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.mail.aop.AfterFileUploadAspect;
import ru.itis.mail.services.FilesService;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;

@Controller
@MultipartConfig
public class FilesController {

    @Autowired
    private FilesService filesService;
    @Autowired
    private AfterFileUploadAspect afterFileUploadAspect;

    @PostMapping(value = "/files")
    public void uploadFile(@RequestParam("file")MultipartFile multipartFile) {
        ProxyFactory factory = new ProxyFactory(filesService);
        factory.addAdvice(afterFileUploadAspect);
        FilesService proxy = (FilesService)factory.getProxy();
        proxy.saveFile(multipartFile);
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public ModelAndView  getFilesUploadPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("files");
        return modelAndView;
    }

    @RequestMapping(value ="/files/{file-name:.+}" , method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("files");
        File file = filesService.getFile(fileName);
        modelAndView.addObject("file", file);
        return modelAndView;
    }
}
