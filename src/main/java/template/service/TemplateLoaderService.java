package template.service;


import org.springframework.stereotype.Service;
import template.repository.TemplateRecipientsRepository;
import template.repository.TemplateRepository;

@Service
public class TemplateLoaderService {

    private TemplateRecipientsRepository templateRecipientsRepository;
    private TemplateRepository templateRepository;

}
