package com.rlrg.webserver.admin.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.utillities.Constants;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.webserver.admin.form.SearchForm;
import com.rlrg.webserver.admin.service.MvcTaskService;

@RequestMapping("/task")
@Controller
public class MvcTaskController {

	private static final Logger LOG = LoggerFactory.getLogger(MvcTaskController.class);

	@Autowired
	private MvcTaskService taskService;

	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public String manage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			ModelMap model, @Valid SearchForm searchForm, BindingResult bResult) {
		try {
			ResultList<TaskDTO> result = null;
			if (null == searchForm || null == searchForm.getKeyword()) {
				model.addAttribute("searchForm", new SearchForm());
				result = taskService.getAllTasks(page);
			} else if (bResult.hasErrors()) {
				return "task.manage";
			} else {
				result = taskService.searchTasksByKeyword(searchForm.getKeyword(), page);
				model.addAttribute("searchForm", searchForm);
			}
			//
			if (null != result) {
				model.addAttribute("totalPage",Math.ceil((double) result.getTotal()/ Constants.PAGE_SIZE));
				model.addAttribute("tasks", result.getList());
			}
			return "task.manage";
		} catch (RestClientException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (ConvertException e) {
			LOG.error(e.getTechnicalMsg());
			return "error";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public String edit(@RequestParam("id") Long id, ModelMap model) {
		try {
			TaskDTO data = taskService.getTaskById(id);
			model.addAttribute("taskDTO", data);
			//
			return "task.edit";
		} catch (RestClientException | ConvertException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid TaskDTO data, BindingResult bResult) {
		try {
			if (bResult.hasErrors()) {
				return "task.edit";
			} else {
				taskService.updateTask(data);
				return "redirect:/task/manage.html";
			}
		} catch (RestClientException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (ConvertException e){
			LOG.error(e.getTechnicalMsg());
			return "error";
		}
	}
	
}
