package org.consultjr.mvc.controller;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.core.components.AppUtils;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.service.ActivityService;
import org.consultjr.mvc.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Activity Service
 *
 * @author kallenon
 */
@Controller
@Scope("request")
@RequestMapping("/Activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ClassesService classesService;

    public void setActivityService(final ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping("") // Index Method: => /PROJECT/Activity
    public ModelAndView index() {
        return this.allActivities();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // GET: /PROJECT/Activity/add
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        modelAndView.addObject("activity", new Activity());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("activityID", null);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // Save Method: POST /PROJECT/Activity/add
    public ModelAndView addActivity(@ModelAttribute Activity activity) {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        activityService.addActivity(activity);
        
        Classes standardClasses = new Classes();
        standardClasses.setActivity (activityService.getActivityById(1));
        standardClasses.setStandard(true);
        standardClasses.setDescription("Turma Padrão");
        standardClasses.setCreated(new Date());
        standardClasses.setTitle("Turma Padrao");
        classesService.addClasses(standardClasses);
        
        
        String message = "Activity was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        Activity activity = activityService.getActivityById(id);

        activity.setDateStart(AppUtils.FormatDate(activity.getStart()));
        activity.setDateEnd(AppUtils.FormatDate(activity.getEnd()));

        modelAndView.addObject("activity", activity);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("activityID", activity.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateActivity(@ModelAttribute Activity activity, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        activityService.updateActivity(activity, id);
        String message = "Activity was successfully edited.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteActivity(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Activity/_list");
        //TODO checar se houve deleção!!!
        activityService.deleteActivity(activityService.getActivityById(id));
        List<Activity> activities = activityService.getActivities();
        modelAndView.addObject("activities", activities);
        String message = "Activity was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allActivities() {
        ModelAndView modelAndView = new ModelAndView("Activity/_list");
        List<Activity> activities = activityService.getActivities();
        modelAndView.addObject("activities", activities);
        return modelAndView;
    }

}
